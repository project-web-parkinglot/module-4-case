let availableParkingB1 = [];
let availableParkingB2 = [];
let countdownInterval;
class parkingLot{
    constructor(x1,y1,x2,y2,x3,y3,x4,y4,alt,id) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;
        this.alt = alt;
        this.id = id;
    }
}
function convertData(){
    let dataAvailableB1 = document.getElementById("arrayAvailableB1").value;
    if (dataAvailableB1 != ""){
        availableParkingB1 = eval(dataAvailableB1);
    }
    let dataAvailableB2 = document.getElementById("arrayAvailableB2").value;
    if (dataAvailableB2 != ""){
        availableParkingB2 = eval(dataAvailableB2);
    }
}
function setupMap(level){

    let areaControl = document.getElementById("map-control");
    let width = areaControl.offsetWidth;
    let height = width * 3 / 4;
    areaControl.style.height = height + "px";
    document.getElementById("array-picture").style.height = height - 215 + "px";



    let arrayAvailable;
    let data = "";
    if (level == 1){
        arrayAvailable = availableParkingB1;

        areaControl.style.backgroundImage = `url("/packing_lot_css/img/b1.png")`
        document.getElementById("button-level").innerHTML =
            `<div class="border color4 filler boxshadow-outset">B1</div>
            <div class="border hover color2 filler boxshadow-outset" onclick="setupMap(2)">B2</div>`
    } else {
        arrayAvailable = availableParkingB2;

        areaControl.style.backgroundImage = `url("/packing_lot_css/img/b2.png")`
        document.getElementById("button-level").innerHTML =
            `<div class="border hover color2 filler boxshadow-outset" onclick="setupMap(1)">B1</div>
            <div class="border color4 filler boxshadow-outset">B2</div>`
    }

    for (let i = 0; i < arrayAvailable.length; i++){
        let point = arrayAvailable[i];
        data += `<polygon class="availableParking" id="${point.alt}" points="
                                  ${width * point.x1  / 100},${height * point.y1 / 100} 
                                  ${width * point.x2 / 100},${height * point.y2 / 100} 
                                  ${width * point.x3 / 100},${height * point.y3 / 100} 
                                  ${width * point.x4 / 100},${height * point.y4 / 100}
                                  " onclick="locationChoose('${point.alt}', ${point.id})"/>`
    }

    document.getElementById("parkinglot").innerHTML = data;
    changeColorParking();
}
function locationChoose(location, id){
    document.getElementById("parking-lot-name").innerHTML = location;
    document.getElementById("id-parkinglot").value = id;
    changeColorParking();
}
function changeColorParking(){
    let name = document.getElementById("parking-lot-name").innerHTML;
    let allPoly = document.getElementsByTagName("polygon");
    for (let i = 0; i < allPoly.length; i++){
        let poly = allPoly[i];
        poly.style.fill = "";
    }
    let parking = document.getElementById(name);
    parking.style.fill = "purple";
}

function transferDataConfirm(action){
    let content = document.getElementById("content-alert");
    let table = document.getElementById("alert-content");
    switch (action){
        case "back":
            content.innerHTML = `Are you sure about <span class="target-text">BACK TO MAINPAGE</span><br><br>
                                 -- your data is not save --`;
            document.getElementById("hidden-button").innerHTML =
                `<div id="button" onclick="confirmTable('back')"></div>`;
            break;
        case "submit":
            content.innerHTML = `Are you sure about<br>
                    <span class="target-text">SUBMIT THIS INFOMATION</span><br><br>
                    <small>we will process the request as soon as possible</small>`;
            document.getElementById("hidden-button").innerHTML =
                `<div id="button" onclick="confirmTable('submit')"></div>`;
            break;
    }
    table.style.display = "grid";
    cancelCountDown();
}
function clickHiddenButton(){
    document.getElementById("button").click();
    closeTable();
}
function confirmTable(action){
    clearInterval(countdownInterval);
    switch (action){
        case "back":
            window.location.href = "/";
            break;
        case "submit":
            document.getElementById("submit-hidden").click();
            break;
    }
}
function cancelCountDown(){
    let buttonElement = document.getElementById("cancel-button");
    let countdown = 10;
    buttonElement.innerHTML = "Cancel 10";
    countdownInterval = setInterval(function() {
        countdown--;
        document.getElementById("cancel-button").innerHTML = "Cancel " + countdown;
        if (countdown == 0) {
            clearInterval(countdownInterval);
            buttonElement.removeAttribute('disabled');
            buttonElement.click();
        }
    }, 1000);
}
function setupTimeLeft(){
    let dayList = document.getElementsByTagName("text");
    for (let i = 0; i < dayList.length; i++){
        let day = dayList[i].innerHTML;
        let dayNum = +day.substring(0,day.length - 1);
        if (dayNum <= 10){
            dayList[i].style.fill = "red";
        } else {
            dayList[i].style.fill = "green";
        }
    }
}
function closeTable(){
    let table = document.getElementById("alert-content");
    table.style.display = "none";
}
function addImgToInput(){
    document.getElementById("input-img").click();
}
function check(){
    let linkImg = document.getElementById("carimage").value;
    let id = document.getElementById("id-parkinglot").value;
    let plate = document.getElementById("plate").value;

    if (linkImg == "" || id == "" || plate == ""){
        document.getElementById("alert-table").style.display = "grid";
    } else {
        transferDataConfirm('submit');
    }
}
function closeAlertTable(){
    document.getElementById("alert-table").style.display = "none";
}

convertData();
setupMap(1);
changeColorParking();