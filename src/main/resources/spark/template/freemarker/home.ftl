<!DOCTYPE html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	<title>${title}</title>
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" type="text/css" href="/css/flex.css">
</head>
<body>
	<div class="home flex flex-row flex-center">
		<div class="content flex flex-column flex-center">
			<div class="logo-content">
				<img src="../img/chat.png" />
			</div>
			<div class="login-form flex flex-column flex-center">
				<h1 class="home-title">${title}</h1>
				<#if signinPage>
				<h2>Signin</h2>
				
				<div class="panel panel-default padding-10">
					or <a href="/signup">Signup</a>
				</div>
				<#else>
				<h2>Signup</h2>
				
				<div class="panel panel-default padding-10">
					or <a href="/">Signin</a>
				</div>
				</#if>
				<#if loginFail>
				<p class="alert alert-danger error home-message" role="alert">${message}</p>
				</#if>
				<#if signupFail>
				<p class="alert alert-danger error home-message" role="alert">${message}</p>
				</#if>
				<#if newUserSignup>
				<p class="alert alert-success success home-message" role="alert">${SignUpMessage}</p>
				<#else>
				</#if>
				
				<#if signinPage>
				<#include "signinform.ftl">
				<#else>
				<#include "signupform.ftl">
				</#if>
				
			</div>
		</div>
	</div>
</body>
</html>