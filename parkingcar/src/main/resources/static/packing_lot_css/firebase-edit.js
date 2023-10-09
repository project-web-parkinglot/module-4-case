
import { initializeApp }
    from "https://www.gstatic.com/firebasejs/10.3.1/firebase-app.js";
import { getStorage, ref, uploadBytes, getDownloadURL  }
    from "https://www.gstatic.com/firebasejs/10.3.1/firebase-storage.js";

const firebaseConfig = {
    apiKey: "AIzaSyCdVT5o76hLZM1RkcEtDeJEfCuYlDK-nEg",
    authDomain: "thangquocproject.firebaseapp.com",
    projectId: "thangquocproject",
    storageBucket: "thangquocproject.appspot.com",
    messagingSenderId: "1056348119656",
    appId: "1:1056348119656:web:19639bc4aa441af953d1dd"
};
const app = initializeApp(firebaseConfig);
const storage = getStorage(app);
const inps = document.querySelector(".inps");
let filter = document.getElementById("waiting");

async function handleUpload() {
    filter.style.display = "flex";
    let links = "";
    for (let i = 0; i < inps.files.length; i++) {
        let file = inps.files[i];
        if (file) {
            const snapshot = await uploadImage(file);
            const downloadURL = await getDownloadURL(snapshot.ref);
            links += (downloadURL + " ");
            insertPicture(downloadURL);
        } else {
            console.error("No file selected.");
        }
    }
    displayDownloadDetailLink(links.trim());
}

async function uploadImage(file) {
    const storageRef = ref(storage, 'images/' + file.name);
    const snapshot = await uploadBytes(storageRef, file);
    return snapshot;
}

function displayDownloadDetailLink(url){
    document.getElementById("linkNewImg").value = url;
    filter.style.display = "none";
}
function insertPicture(url){
    let data =  `<div class="div-main filler hover-border boxshadow-outset"
                    style="background-image: url('/packing_lot_css/icon/new-picture.png')">
                    <div class="div-branch" style="background-image: url('${url}')"></div></div>`
    document.getElementById("array-picture-edit").innerHTML += data;
}

window.handleUpload = handleUpload;