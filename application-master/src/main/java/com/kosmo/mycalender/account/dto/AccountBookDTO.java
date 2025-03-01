package com.kosmo.mycalender.account.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AccountBookDTO {
	private Long idx;
	private Date date;
	private String category;
	private String description;
	private BigDecimal amount;
	private String type;
	private String userid;
	private String regdate;
	
	public AccountBookDTO(Date date, String category, String description, BigDecimal amount, String type, String userid, String regdate) {
		this.date = date;
		this.category = category;
		this.description = description;
		this.amount = amount;
		this.type = type;
		this.userid = userid;
		this.regdate = regdate;
	}
	
	public Long getIdx() {
		return idx;
	}
	public void setIdx(Long idx) {
		this.idx = idx;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
}
