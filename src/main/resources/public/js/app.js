var inactivityTime = function () {
    var t;
    window.onload = resetTimer;
    // DOM Events
    document.onmousemove = resetTimer;
    document.onkeypress = resetTimer;

    function logout() {
        alert("You are now logged out.");
        location.href = '/'
    }

    function resetTimer() {
        clearTimeout(t);
        t = setTimeout(logout, 10000);
    }
};