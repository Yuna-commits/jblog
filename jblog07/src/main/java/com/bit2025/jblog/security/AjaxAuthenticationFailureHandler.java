package com.bit2025.jblog.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.bit2025.jblog.dto.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		response.setContentType("application/json;charset=UTF-8");

		JsonResult<String> result = JsonResult.fail("아이디 또는 비밀번호를 잘못 입력하였습니다.");
		
		String json = new ObjectMapper().writeValueAsString(result);
		response.getWriter().write(json);
	}

}
