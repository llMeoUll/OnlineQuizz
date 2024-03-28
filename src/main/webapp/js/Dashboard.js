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

const socket = new WebSocket("ws://localhost:8888/Quizzicle/admin/notification");

    // Xử lý sự kiện khi mở kết nối
    socket.addEventListener('open', (event) => {
        console.log('WebSocket connection opened:', event);
    });

    // Xử lý sự kiện khi nhận thông báo từ máy chủ
document.addEventListener('DOMContentLoaded', function () {
    let myToast = new bootstrap.Toast(document.querySelector('.toast'));
    let userId;
    let content;
    socket.addEventListener('message', function (event) {
        // Hiển thị toast khi có sự kiện message từ WebSocket
        let parseEvent = JSON.parse(event.data);
        userId = parseEvent.info;
        content = parseEvent.content;
        document.querySelector('.toast-body').innerHTML = content;
        myToast.show();
        setTimeout(function () {
            myToast.hide();
        }, 20000);
    });
    document.querySelector('.toast').addEventListener('click', function () {
        console.log('Toast clicked!');
        window.location.href = '/Quizzicle/admin/user/profile?uid=' + userId;
    });
    document.querySelector('.toast').addEventListener('mouseout', function () {
       setTimeout(function () {
            myToast.hide();
       }, 5000);
    });
});

    // Xử lý sự kiện khi có lỗi
    socket.addEventListener('error', (event) => {
        console.error('WebSocket error:', event);
    });

    // Xử lý sự kiện khi đóng kết nối
    socket.addEventListener('close', (event) => {
        console.log('WebSocket connection closed:', event);
    });
