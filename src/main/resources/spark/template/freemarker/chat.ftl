<!DOCTYPE html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	<title>${title}</title>
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/css/style.css"/>
	<link rel="stylesheet" type="text/css" href="/css/flex.css"/>
</head>
<body>
	<div class="chat flex flex-column">
		<div class="header flex flex-row">
			<div class="chat-logo-content">
				<img src="../img/chat.png" />
				<span class="headline">Chat Room</span>
			</div>
		</div>
		<div class="chat-content flex flex-column flex-start">
			<div class="messages">
				<#if admin??>
					<#if messages??>
						<#list messages as message>
							<div class="flex flex-start message admin-message">${message.getMessage()}</div>
						</#list>
					</#if>
				<#else>
					<#if messages??>
						<#list messages as message>
							<div class="flex flex-start message">${message.getMessage()}</div>
						</#list>
					</#if>
				</#if>
			</div>
		</div>
		<div class="chatbar flex flex-end">
			<#include "messageform.ftl">
		</div>
	</div>
</body>
</html>