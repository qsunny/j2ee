<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试</title>
</head>
<body>
	<div style="margin:10 auto;">mybatis测试</div>
	<c:forEach items="${list}" var="item" >
	<p><span style="margin-right:10px;">id=${item.id}</span>你好,${item.username}!你的年龄是${item.age}</p>
	</c:forEach>
	
	<div style="margin:10 auto;">redis测试</div>
	<div>
		<p>redis string:  ${uString}</p>
		<p>redis object:  ${user.id}-${user.username}-${user.age}</p>
	</div>
</body>
</html>