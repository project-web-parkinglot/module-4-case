import firebase from "./configulation-firebase.js";

const uploadedURLs = [];
const uploadedURLs1 = [];


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
async function handleUpload1(e) {
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
            uploadedURLs1.push(url);

            if (uploadedURLs1.length === files.length) {
                alert('Tất cả ảnh đã được tải lên thành công');
                document.getElementById("image2").value = uploadedURLs1[0];
            }
        } catch (error) {
            console.error(error);
        }
    }
}

// document.getElementById("upload-file").addEventListener("change", function (e) {
//     handleUpload(e)
// });
document.getElementById("upload-file1").addEventListener("change", function (e) {
    handleUpload1(e)
});