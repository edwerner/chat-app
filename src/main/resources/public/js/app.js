var onInactiveLogout = function() {
    var timeout;
    window.onload = resetTimer;
    document.onmousemove = resetTimer;
    document.onkeypress = resetTimer;

    function logout() {
        alert("You've been signed out due to inactivity");
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/", true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send();
        location.href = '/';
    }

    function resetTimer() {
        clearTimeout(timeout);
        timeout = setTimeout(logout, 5000);
    }
}

var removeMessage = function(id) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", yourUrl, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({ id: id }));
}

var bindAdminClick = function() {
    var messages = document.getElementsByClassName("remove-message");
    var messagesCount = messages.length;
    console.log(messagesCount);
    for (var i = 0; i < messagesCount; i += 1) {
        messages[i].onclick = function(e) {
            alert(this.id);
        }
    }
}

document.addEventListener("DOMContentLoaded", function(event) {
    bindAdminClick();
    onInactiveLogout();
});
