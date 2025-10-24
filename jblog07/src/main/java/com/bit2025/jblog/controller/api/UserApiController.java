package com.bit2025.jblog.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bit2025.jblog.dto.JsonResult;
import com.bit2025.jblog.service.UserService;

/**
 * Ajax 요청 처리 컨트롤러 
 * ===
 * @RestController 
 * - @Controller + @ResponseBody 
 * - 메서드 반환 값을 JSON으로 변환하여 HTTP 응답
 */
@RestController
@RequestMapping("/api/users")
public class UserApiController {

	private final UserService userService;

	public UserApiController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * GET /api/users/exists?blogId=...
	 * - 아이디(blogId) 중복 체크
	 * 
	 * @param blogId : 확인할 사용자 아이디
	 * @return JsonResult<Boolean> : 존재 여부(T/F)
	 */
	@GetMapping("/exists")
	public JsonResult<Boolean> checkIdExists(@RequestParam("blogId") String blogId) {
		boolean exist = userService.getUser(blogId) != null;
		
		return JsonResult.success(exist);
	}

}
