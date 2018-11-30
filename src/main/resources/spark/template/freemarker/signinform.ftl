 <form class="form home-form" action="/list" method="POST">
    <label for="inputUsername" class="sr-only">Username</label>
    <input type="text" name = "inputUsername" id="inputUsername" class="form-control" placeholder="Username" required>
    <input type="password" name = "inputPassword" id="inputPassword" class="form-control" placeholder="Password" required>
  	<#include "button.ftl">
</form>