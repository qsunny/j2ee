<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Gradle + Spring MVC</title>
</head>
<body>
	<div class="container">
	<h1>${title}</h1>
	<p>
		<#if (msg)?? && msg!="">
			Hello ${msg}
		<#else>
			Welcome Welcome!
		</#if>

        </p>
        <p>
		<a class="btn btn-primary btn-lg" 
                    href="#" role="button">Learn more</a>
	</p>
	</div>
<script src="/assets/js/libs/jquery-1.8.3.min.js" var="jqueryJs"></script>	
</body>
</html>