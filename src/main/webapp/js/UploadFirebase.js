// import { initializeApp } from "https://www.gstatic.com/firebasejs/10.9.0/firebase-app.js";
// import { getStorage } from 'https://www.gstatic.com/firebasejs/10.9.0/firebase-storage.js'
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyCs_KjBfBJiEmU5E55HuKQD-zjEB8wrKqE",
    authDomain: "online-quizz-system.firebaseapp.com",
    projectId: "online-quizz-system",
    storageBucket: "online-quizz-system.appspot.com",
    messagingSenderId: "909571688274",
    appId: "1:909571688274:web:3524b5afe4e53ceab61c8b",
    measurementId: "G-2Z31CGGWXM"
};
firebase.initializeApp(firebaseConfig);

const storage = firebase.storage();
const storageRef = storage.ref()
const imagesRef = storageRef.child("avatars")

const fileInput = document.getElementById("fileInput")

fileInput.addEventListener("change", (e) => {
    const file = e.target.files[0]
    const uploadTask = imagesRef.child(file.name).put(file)
    uploadTask.on(
        "state_changed",
        (snapshot) => {
        },
        (error) => {
        },
        () => {
            uploadTask.snapshot.ref.getDownloadURL().then((downloadURL) => {
                    document.getElementById("avatarUrl").value = downloadURL
                    document.getElementById("avatarPreview").src = downloadURL
                    document.getElementById("avatarPreviewContainer").style.display = "block"
                    document.getElementById("avatarPreview").src = downloadURL
                    document.getElementById("buttonOpenFile").style.display = 'none'
                    document.getElementById("changeAvatarForm").submit()
                //delete old avatar

                })
        }
    )
})

