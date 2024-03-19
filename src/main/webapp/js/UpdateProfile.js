$(document).ready(function () {
    $('#show-hide-password').click(function () {
        let passwordInput = $('.password');
        let icon = $(this).find('i');

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

addEventListener("DOMContentLoaded", (event) => {
    const password = document.getElementById("password-input");
    const passwordAlert = document.getElementById("password-alert");
    const requirements = document.querySelectorAll(".requirements");
    const leng = document.querySelector(".leng");
    const bigLetter = document.querySelector(".big-letter");
    const num = document.querySelector(".num");
    const specialChar = document.querySelector(".special-char");

    requirements.forEach((element) => element.classList.add("wrong"));

    password.addEventListener("focus", () => {
        passwordAlert.classList.remove("d-none");
        if (!password.classList.contains("is-valid")) {
            password.classList.add("is-invalid");
        }
    });

    password.addEventListener("input", () => {
        const value = password.value;
        const isLengthValid = value.length >= 8;
        const hasUpperCase = /[A-Z]/.test(value);
        const hasNumber = /\d/.test(value);
        const hasSpecialChar = /[!@#$%^&*()\[\]{}\\|;:'",<.>/?`~]/.test(value);

        leng.classList.toggle("good", isLengthValid);
        leng.classList.toggle("wrong", !isLengthValid);
        bigLetter.classList.toggle("good", hasUpperCase);
        bigLetter.classList.toggle("wrong", !hasUpperCase);
        num.classList.toggle("good", hasNumber);
        num.classList.toggle("wrong", !hasNumber);
        specialChar.classList.toggle("good", hasSpecialChar);
        specialChar.classList.toggle("wrong", !hasSpecialChar);

        const isPasswordValid = isLengthValid && hasUpperCase && hasNumber && hasSpecialChar;

        if (isPasswordValid) {
            password.classList.remove("is-invalid");
            password.classList.add("is-valid");

            requirements.forEach((element) => {
                element.classList.remove("wrong");
                element.classList.add("good");
            });

            passwordAlert.classList.remove("alert-warning");
            passwordAlert.classList.add("alert-success");
        } else {
            password.classList.remove("is-valid");
            password.classList.add("is-invalid");

            passwordAlert.classList.add("alert-warning");
            passwordAlert.classList.remove("alert-success");
        }
    });

    password.addEventListener("blur", () => {
        passwordAlert.classList.add("d-none");
    });

    const form = document.querySelector("form");
    form.addEventListener("submit", (event) => {
        if (password.classList.contains("is-invalid")) {
            // Prevent form submission if the password is invalid
            password.focus();
            event.preventDefault();
        }
    });

});

function changeAvatarButton() {
    document.getElementById("fileInput").click()
}

const inputFile = document.getElementById('fileInput');
inputFile.addEventListener('change', () => {
    let selectAvatar = document.getElementById('buttonOpenFile');
    let changeButton = document.getElementById("buttonSubmitForm");

    if (inputFile.files.length > 0) {
        selectAvatar.classList.add('d-none');
        changeButton.classList.remove('d-none');
        changeButton.classList.add('d-block');
    }

});
