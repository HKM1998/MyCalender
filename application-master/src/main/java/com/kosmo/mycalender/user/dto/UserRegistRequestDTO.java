package com.kosmo.mycalender.user.dto;

import jakarta.validation.constraints.NotNull;

public class UserRegistRequestDTO {
	@NotNull
	private String userId;
	@NotNull
	private String password;
	@NotNull
	private String confirmPassword;
	@NotNull
	private String name;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isPasswordEqualToConfirmPassword() {
		return password.equals(confirmPassword);
	}
}
