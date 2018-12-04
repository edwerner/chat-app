var onInactiveLogout = function () {
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
};

onInactiveLogout();