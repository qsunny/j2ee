<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<spring:url value="/assets/css/index.css" var="indexCss" />
<link href="${indexCss}" rel="stylesheet" />
<title>Gradle + Spring MVC</title>
</head>
<body>
	<div class="container">
	<h1>app name:${title}</h1>
		<h1>jdbc:${jdbcName}</h1>
	<p>
		<c:if test="${not empty msg}">
			Hello ${msg}
		</c:if>

		<c:if test="${empty msg}">
			Welcome Welcome!
		</c:if>
        </p>
        <p>
		<a class="btn btn-primary btn-lg" target="_blank" href="https://github.com/qsunny/j2ee" role="button">Learn more</a>
	</p>
	</div>
<spring:url value="/assets/js/libs/jquery-1.8.3.min.js" var="jqueryJs" />
<script src="${jqueryJs}"></script>	
</body>
</html>