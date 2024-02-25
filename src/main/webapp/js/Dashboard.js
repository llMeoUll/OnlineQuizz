window.addEventListener('DOMContentLoaded', event => {

    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
            document.body.classList.toggle('sb-sidenav-toggled');
        }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});

// let socket = new WebSocket("ws://localhost:8888/Quizzicle/dashboard_endpoint");
//
// socket.onopen = function () {
//     console.log("WebSocket connection opened");
// };
//
// socket.onclose = function () {
//     console.log("WebSocket connection closed");
// };
//
// socket.onmessage = function (event) {
//     console.log("Notification: " + event.data);
// };
