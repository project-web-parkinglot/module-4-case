import firebase from "./configulation-firebase.js";

const uploadedURLs = [];


async function handleUpload(e) {
    console.dir(e);
    const ref = firebase.storage().ref();
    const files = e.target.files;

    for (const file of files) {
        const name = +new Date() + "-" + file.name;
        const metadata = {
            contentType: file.type
        };

        try {
            const snapshot = await ref.child(name).put(file, metadata);
            const url = await snapshot.ref.getDownloadURL();

            console.log(url);
            uploadedURLs.push(url);

            if (uploadedURLs.length === files.length) {
                alert('Tất cả ảnh đã được tải lên thành công');
                document.getElementById("imageLicense1").value = uploadedURLs[0];
            }
        } catch (error) {
            console.error(error);
        }
    }
}
document.getElementById("upload-file").addEventListener("change", function (e) {
    handleUpload(e)
});