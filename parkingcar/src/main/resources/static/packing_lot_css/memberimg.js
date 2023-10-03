function changeImgMember(index){
    let container = document.getElementById("img-member");
    switch (index){
        case 0:
            container.style.gridTemplateColumns = "4fr 1fr 1fr"
            break;
        case 1:
            container.style.gridTemplateColumns = "1fr 4fr 1fr"
            break;
        case 2:
            container.style.gridTemplateColumns = "1fr 1fr 4fr"
            break;
    }

}
function backImgMember(){
    let container = document.getElementById("img-member");
    container.style.gridTemplateColumns = "1fr 1fr 1fr";
}