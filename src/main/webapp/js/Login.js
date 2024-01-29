$(document).ready(function () {
    $('#show-hide-password').click(function () {
        var passwordInput = $('#password');
        var icon = $(this).find('i');

        // Toggle password visibility
        if (passwordInput.attr('type') === 'password') {
            passwordInput.attr('type', 'text');
            icon.removeClass('fa-eye-slash').addClass('fa-eye');
        } else {
            passwordInput.attr('type', 'password');
            icon.removeClass('fa-eye').addClass('fa-eye-slash');
        }
    });
});

