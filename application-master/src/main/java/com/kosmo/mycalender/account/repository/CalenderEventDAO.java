package com.kosmo.mycalender.account.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kosmo.mycalender.account.dto.CalenderEventDTO;

@Repository
public class CalenderEventDAO {
	private JdbcTemplate jdbcTemplate;
	private RowMapper<CalenderEventDTO> rowMapper = 
			new RowMapper<CalenderEventDTO>() {
				
				@Override
				public CalenderEventDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CalenderEventDTO accBook = new CalenderEventDTO();
					accBook.setDate(rs.getDate("date"));
					accBook.setTitle(rs.getString("title"));
					accBook.setColor(rs.getString("color"));
					return accBook;
				}
			};

	public CalenderEventDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	public List<CalenderEventDTO> getEvents(String userId){
		List<CalenderEventDTO> events;
		StringBuilder sql = new StringBuilder();
		sql.append("select DATE, concat(count(IDX), ' 건')  as TITLE, ");
		sql.append("	case when type = 'INCOME' then 'blue' when type = 'EXPENSE' then 'red' end as COLOR ");
		sql.append("from ACCOUNT_BOOK ");
		sql.append("where USERID = ? ");
		sql.append("group by DATE, TYPE ");
		sql.append("order by DATE, TYPE");
		
		events = jdbcTemplate.query(sql.toString(), rowMapper, userId);
		
		return events;
	}
}
