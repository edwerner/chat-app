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

var bindAdminClick = function() {
    var messages = document.getElementsByClassName("remove-message");
    var messagesCount = messages.length;
    console.log(messagesCount);
    for (var i = 0; i < messagesCount; i++) {
        messages[i].onclick = function(e) {
            removeMessage(this.id);
        }
    }
}

var removeMessage = function(id) {
    $.post( "/remove", { id: id }).done(function(data) {
        var dataId = data.replace(/['"]+/g, '')
        $("#edit_" + dataId).remove();
    });
}

document.addEventListener("DOMContentLoaded", function(event) {
    bindAdminClick();
    // onInactiveLogout();
});
