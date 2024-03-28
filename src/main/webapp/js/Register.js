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

const form = document.getElementById("register-form");
form.addEventListener("submit", function (event) {
    event.preventDefault();
    const errorName = document.getElementById("error-name").value?.trim();
    const errorUsername = document.getElementById("error-username").value?.trim();
    const errorPassword = document.getElementById("error-password").value?.trim();
    if (errorName === "" || errorUsername === "" || errorPassword === "" || errorName === undefined || errorUsername === undefined || errorPassword === undefined) {
        form.submit();
    }
});


function removeAscent(str) {
    if (str === null || str === undefined) return str;
    str = str.toLowerCase();
    str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
    str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
    str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
    str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
    str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
    str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
    str = str.replace(/đ/g, "d");
    return str;
}

function validateName() {
    let givenName = removeAscent(document.getElementById("given-name").value.trim());
    let familyName = removeAscent(document.getElementById("family-name").value.trim());
    let errorNameBox = document.getElementById("error-name");


    // Regular expression to check if the input contains only letters, spaces, and Vietnamese characters
    let lettersOnly = /^[A-Za-z\s]+$/;

    // Check if given name contains only letters, spaces, and Vietnamese characters
    if (!givenName.match(lettersOnly) || !familyName.match(lettersOnly)) {
        errorNameBox.innerText = "Given name and Family name should not contain special characters or numbers.";
    } else {
        errorNameBox.innerText = "";
    }
}


function validateUsername() {
    let username = document.getElementById("username").value;
    let errorUsernameBox = document.getElementById("error-username");
    // Regular expression to check if the input contains only letters, numbers, dash, and underscores

    let lettersNumbersUnderscores = /^[A-Za-z0-9-@_]+$/;
    // Check if username contains only letters, numbers, and underscores
    if (!username.match(lettersNumbersUnderscores)) {
        errorUsernameBox.innerText = "Username should not contain special characters. Only letters, numbers, dash, underscores, and @ are allowed.";
    } else {
        errorUsernameBox.innerText = "";
    }
}

function validatePasswordMatch() {
    let password = document.getElementById("password-input").value;
    let confirmPassword = document.getElementById("verify-password").value;

    let errorUsernameBox = document.getElementById("error-password");

    // Check if username contains only letters, numbers, and underscores
    if (password !== confirmPassword) {
        errorUsernameBox.innerText = "Verify password does not match.";
    } else {
        errorUsernameBox.innerText = "";
    }
}



