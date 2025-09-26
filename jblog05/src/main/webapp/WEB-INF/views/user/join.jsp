<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jblog.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	// 아이디 중복 체크
	$(function() {
		// id="btn-check"을 누른 click event
		$("#btn-check").click(function() {
			var id = $("#id").val();
			if(!id) {
				return;
			}
		})
	})
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<!-- 폼 입력값 userVo로 자동 바인딩 -->
		<form:form class="join-form" id="join-form" method="post" modelAttribute="userVo" action="${pageContext.request.contextPath }/user/join">
			<!-- 입력 오류 발생 시, 이전 입력 값 그대로 표시 -->
			<label class="block-label" for="name">이름</label>
			<form:input path="name" />
			<p style="padding: 0; text-align: left; color: #f00">
				<form:errors path="name" />
			</p>
			
			<label class="block-label" for="blog-id">아이디</label>
			<form:input path="id" /> 
			<input id="btn-check" type="button" value="id 중복체크">
			<img id="img-check" src="${pageContext.request.contextPath}/assets/images/check.png" 
				 style="vertical-align:bottom; width:24px; display: none;">
			<p style="padding: 0; text-align: left; color: #f00">
				<form:errors path="id" />
			</p>

			<label class="block-label" for="password">패스워드</label>
			<form:password path="password" />
			<p style="padding: 0; text-align: left; color: #f00">
				<form:errors path="password" />
			</p>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">
		</form:form>
	</div>
</body>
</html>