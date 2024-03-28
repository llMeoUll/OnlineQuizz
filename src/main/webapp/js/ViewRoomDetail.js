function togglePasswordVisibility() {
    var passwordField = document.getElementById("passwordField");
    var toggleButton = document.getElementById("togglePassword");

    if (passwordField.type === "password") {
        passwordField.type = "text";
        toggleButton.textContent = "👁️";
    } else {
        passwordField.type = "password";
        toggleButton.textContent = "👁️";
    }
}

function showEditModal() {
    $('#editRoomModal').modal('show');
}

function showDeleteConfirmation() {
    $('#deleteConfirmationModal').modal('show');
}

function deleteRoom() {
    $('#deleteConfirmationModal').modal('hide');
}