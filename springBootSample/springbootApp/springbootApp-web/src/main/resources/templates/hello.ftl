<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>测试</title>
</head>
<body>
<div style="margin:10 auto;">mybatis测试</div>
<#list userList as item >
    <p><span style="margin-right:10px;">id=${item.id}</span>你好,${item.username}!你的年龄是${item.age}</p>
</#list >

<div style="margin:10 auto;">redis测试</div>
<#if uString?length gt 0>
<div>
    <p>redis string:  ${uString}</p>
    <p>redis object:  ${user.id}-${user.username}-${user.age}</p>
</div>
</#if>
</body>
</html>