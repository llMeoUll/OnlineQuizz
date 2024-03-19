const socket = new WebSocket("ws://localhost:8888/Quizzicle/user/room/get/notification");

// Xử lý sự kiện khi mở kết nối
socket.addEventListener('open', (event) => {
    console.log('WebSocket connection opened:', event);
});

// Xử lý sự kiện khi nhận thông báo từ máy chủ
document.addEventListener('DOMContentLoaded', function () {
    let myToast = new bootstrap.Toast(document.querySelector('.toast'));
    let url;
    let content;
    socket.addEventListener('message', function (event) {
        // Hiển thị toast khi có sự kiện message từ WebSocket
        let parseEvent = JSON.parse(event.data);
        url = parseEvent.info;
        content = parseEvent.content;
        document.querySelector('.toast-body').innerHTML = content;
        myToast.show();
        setTimeout(function () {
            myToast.hide();
        }, 5000);
    });
    document.querySelector('.toast').addEventListener('click', function () {
        console.log('Toast clicked!');
        window.location.href = url;
    });
    document.querySelector('.toast').addEventListener('mouseout', function () {
        setTimeout(function () {
            myToast.hide();
        }, 5000);
    });
    console.log(url + content);
});

// Xử lý sự kiện khi có lỗi
socket.addEventListener('error', (event) => {
    console.error('WebSocket error:', event);
});

// Xử lý sự kiện khi đóng kết nối
socket.addEventListener('close', (event) => {
    console.log('WebSocket connection closed:', event);
});