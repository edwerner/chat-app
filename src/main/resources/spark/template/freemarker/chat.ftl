<!DOCTYPE html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	<title>${title}</title>
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/css/style.css"/>
	<link rel="stylesheet" type="text/css" href="/css/flex.css"/>
	<script src="/js/jquery-3.1.1.js"></script>
	<script src="/js/app.js"></script>
</head>
<body>
	<div class="chat flex flex-column">
		<div class="header flex flex-row">
			<div class="chat-logo-content">
				<img src="../img/chat.png" />
				<span class="headline">Chat Room</span>
			</div>
			<div class="logout-button">
				<#include "signoutform.ftl">
			</div>
		</div>
		<div class="chat-content flex flex-column flex-start">
			<div class="messages">
				<#if admin == true>
				<#if messages??>
				<#list messages as message>
				<#if message.removed == false>
				<div class="flex flex-row" id="edit_${message.getId()}">
					<div class="alert alert-danger flex flex-start message admin-message" role="alert">
						<div class="badge badge-primary username-badge message-username flex flex-start">
							${message.getUsername()}
						</div>
						<div class="message-content flex flex-start">
							<span class="">${message.getMessage()}</span>
						</div>
						<div class="flex flex-end">
							<div class="remove-message-text">Remove post</div>
							<button type="button" id="${message.getId()}" class="flex flex-end close remove-message" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true"> &times;</span>
							</button>
						</div>
					</div>
				</div>
				</#if>
				</#list>
				</#if>
				<#elseif admin == false>
				<#if messages??>
				<#list messages as message>
				<#if message.removed == true>
					<div class="alert alert-danger flex flex-start message admin-message" role="alert">
						<div class="badge badge-primary username-badge message-username flex flex-start">
							${message.getUsername()}
						</div>
						<div class="message-content flex flex-start">
							<span>${message.getMessage()}</span>
						</div>
					</div>
				<#elseif message.getRemoved() == false>
				<div class="message flex flex-row">
					<div class="message-username">
						<div class="badge badge-primary username-badge message-username">${message.getUsername()}</div>
					</div>
					<div class="message-content flex">
						<span>${message.getMessage()}</span>
					</div>
				</div>
				</#if>
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