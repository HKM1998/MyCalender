package com.kosmo.mycalender.user.dto;

public class UserAuthInfoDTO {
	private Long userIdx;
	private String userId;
	private String name;
	
	public UserAuthInfoDTO(Long userIdx, String userId, String name) {
		this.userIdx = userIdx;
		this.userId = userId;
		this.name = name;
	}

	public Long getUserIdx() {
		return userIdx;
	}

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

}
