const errorPasswordModalContainer = document.getElementById("createRoomModal");
if(errorPasswordModalContainer) {
    const errorPasswordModal = new bootstrap.Modal(errorPasswordModalContainer, {keyboard:false, backdrop:'static'});
    errorPasswordModal.show();
}
