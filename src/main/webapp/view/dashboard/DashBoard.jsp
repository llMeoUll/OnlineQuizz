<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                padding-top: 56px; /* To compensate for fixed navbar */
            }
            .left-sidebar, .right-sidebar, .middle-content {
                padding: 20px;
            }
            .admin-avatar img {
                max-width: 100%;
                height: auto;
                border-radius: 50%;
            }
            .left-sidebar {
                top: 0;
                left: 0;
                background-color: lightblue;
            }
            .middle-content {
                background-color: lightcoral;
            }
            .right-sidebar {
                background-color: lightseagreen;
            }
            .account-manage {
                background-color: aqua;
                width: 100%;
                height: 300px;
                margin-bottom: 10px;
            }
            .room-manage {
                background-color: blanchedalmond;
                width: 100%;
                height: 300px;
                margin-bottom: 10px;
            }
            .set-manage {
                background-color: aqua;
                width: 100%;
                height: 300px;
                margin-bottom: 10px;
            }
        </style>
        <title>DashBoard</title>
    </head>
    <body>
    <header class="navbar navbar-expand-lg navbar-light bg-light fixed-top header">
        <div class="container-fluid">
            <!-- Left Side: Tên web -->
            <div class="navbar-brand">Online Quizz</div>

            <!-- Middle: Navbar -->
            <ul class="navbar-nav mx-auto">
                <li class="nav-item"><a class="nav-link" href="#">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Admin Profile</a></li>
            </ul>

            <!-- Right Side: Admin Avatar -->
            <div class="admin-avatar">
                <img src="" alt="Admin Avatar">
            </div>
        </div>
    </header>

    <!-- Body -->
    <div class="container-fluid">
        <div class="row">

            <!-- Left Sidebar -->
            <div class="col-md-2 left-sidebar">
                <button onclick="" class="btn btn-secondary mb-3">Toggle Sidebar</button>
                <ul class="nav flex-column">
                    <li class="nav-item"><a class="nav-link" href="#">Overview</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Manage User</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Manage Set</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Manage Room</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Question Bank</a></li>
                </ul>
            </div>

            <div class="col-md-8 middle-content">
                <div class="d-flex flex-column">
                    <div class="account-manage">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-8">
                                    Chart
                                </div>
                                <div class="col-md-4">
                                    New Account
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="room-manage">

                    </div>
                    <div class="set-manage">

                    </div>
                </div>
            </div>

            <!-- Right Sidebar -->
            <div class="col-md-2 right-sidebar">
                <h5>Notifications</h5>
                <!-- Các thông báo nằm ở đây -->
            </div>

            <!-- Middle Content -->


        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
