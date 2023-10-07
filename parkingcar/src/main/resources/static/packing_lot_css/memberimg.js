function changeImgMember(index){
    let container = document.getElementById("img-member");
    switch (index){
        case 0:
            container.style.gridTemplateColumns = "3fr 1fr 1fr"
            break;
        case 1:
            container.style.gridTemplateColumns = "1fr 3fr 1fr"
            break;
        case 2:
            container.style.gridTemplateColumns = "1fr 1fr 3fr"
            break;
    }

}
function backImgMember(){
    let container = document.getElementById("img-member");
    container.style.gridTemplateColumns = "1fr 1fr 1fr";
}