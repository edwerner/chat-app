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
		<div class="chat-content flex flex-column flex-center">
			<div class="messages flex flex-column">
				<#if messages??>
					<#list messages as message>
						<p>${message.getMessage()}</p>
					</#list>
				</#if>
			</div>
		</div>
		<div class="chatbar flex flex-end">
			<#include "messageform.ftl">
		</div>
	</div>
</body>
</html>