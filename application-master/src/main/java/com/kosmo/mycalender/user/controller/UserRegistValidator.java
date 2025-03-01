package com.kosmo.mycalender.user.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kosmo.mycalender.user.dto.UserRegistRequestDTO;


public class UserRegistValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		// 파라미터 clazz 객체가 RegisterRequest 로 변환 가능한지 확인
		// 스프링 MVC 에서 확인 하므로 코드 작성에 유의 해야함
		return UserRegistRequestDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// target 대상객체, errors 는 검사결과 에러 코드 설정
		UserRegistRequestDTO regReq = (UserRegistRequestDTO) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "required");
		// 검사 대상 객체의 name 프로퍼티가 null 이거나 공백 문자열인 경우 에러코드 추가
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		// 검사 대상 객체의 비밀번호/확인 프로퍼티가 null 인 경우 에러코드 추가
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		if(!regReq.getPassword().isEmpty()) {
			if(!regReq.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("confirmPassword", "notmatch");
			}
		}
	}

}
