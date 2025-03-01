package com.kosmo.mycalender.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosmo.mycalender.common.exception.WrongIdPasswordException;
import com.kosmo.mycalender.user.dto.UserAuthInfoDTO;
import com.kosmo.mycalender.user.dto.UserDTO;
import com.kosmo.mycalender.user.repository.UserDAO;

@Service
public class UserAuthService {
	@Autowired
	private UserDAO UserDAO;
	
	public UserAuthInfoDTO authenticate(String userId, String password) {
		UserDTO userDto = UserDAO.selectByUserId(userId);
		if(userDto == null) {
			throw new WrongIdPasswordException();
		}
		if(!userDto.getPassword().equals(password)) {
			throw new WrongIdPasswordException();
		}
		return new UserAuthInfoDTO(userDto.getUserIdx(), userDto.getUserId(), userDto.getName());
	}
}
