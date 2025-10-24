package com.bit2025.jblog.dto;

/**
 * JsonResult
 * - Ajax/REST API 응답 시 JSON 데이터를 표준 형태로 반환하기 위한 DTO
 * 
 * 1. @RestController 가 이 객체를 반환
 * 2. MappingJackson2HttpMessageConverter 호출
 * 3. Jackson(ObjectMapper)이 JSON 문자열로 자동 변환
 * 4. JSON 텍스트가 브라우저로 전송
 * {
 *    "result": "success",
 *    "data": true,
 *    "message": null
 * }
 * 
 * @param <T> : 반환 데이터 타입
 */
public class JsonResult<T> {

	private String result; // 서버 응답 : "success" or "fail"
	private T data; // 실제 반환 데이터
	private String message; // 오류 메시지 (fail 시 사용)

	public JsonResult(String result, T data, String message) {
		this.result = result;
		this.data = data;
		this.message = message;
	}

	// 성공 결과 생성
	public static <T> JsonResult<T> success(T data) {
		return new JsonResult<>("success", data, null);
	}

	// 실패 결과 생성
	public static <T> JsonResult<T> fail(String message) {
		return new JsonResult<>("fail", null, message);
	}

	/**
	 * - Jackson은 자바 Bean 규약을 따르기 때문에 getter를 통해 데이터를 읽음
	 * - getter가 없으면 필드값을 읽지 못함
	 * - 빈 JSON {}이 반환되거나 HttpMediaTypeNotAcceptableException이 발생하므로 주의
	 */
	public String getResult() {
		return result;
	}

	public T getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

}
