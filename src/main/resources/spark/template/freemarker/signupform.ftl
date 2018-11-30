<form class="form home-form" action="/signup" method="POST">
    <label for="inputUsername" class="sr-only">Username</label>
    <input type="text" name = "inputUsername" id="inputUsername" class="form-control" placeholder="Pick a username" required>
    <input type="password" name = "inputPassword" id="inputPassword" class="form-control" placeholder="Choose a password" required>
	<#include "button.ftl">
</form>