let blockParkingB1 = [];
let blockParkingB2 = [];
let availableParkingB1 = [];
let availableParkingB2 = [];
let ownParkingB1 = [];
let ownParkingB2 = [];
let otherParkingB1 = [];
let otherParkingB2 = [];
let awatingParkingB1 = [];
let awatingParkingB2 = [];
let arrayMy = [];
let arrayAwait = [];
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
class parkingLotMine{
    constructor(x1,y1,x2,y2,x3,y3,x4,y4,alt,dueDate,carImg,licensePlate,username,
                birthday,address,gender,avata,phone,room,id) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;
        this.alt = alt;
        this.dueDate = new Date(dueDate);
        this.carImg = carImg;
        this.licensePlate = licensePlate;
        this.username = username;
        this.birthday = new Date(birthday);
        this.address = address;
        this.gender = gender;
        this.avata = avata;
        this.phone = phone;
        this.room = room;
        this.id = id;
    }
    getGender(){
        if (this.gender == 1){
            return "Male";
        } else {
            return "Female";
        }
    }
}
let role = document.getElementById("roleTrans").value;
if (role == ""){
    role = 0;
}

function convertData(){
    let datablockB1 = document.getElementById("arrayBlockB1").value;
    if (datablockB1 != ""){
        blockParkingB1 = eval(datablockB1);
    }
    let datablockB2 = document.getElementById("arrayBlockB2").value;
    if (datablockB2 != ""){
        blockParkingB2 = eval(datablockB2);
    }
    let dataAvailableB1 = document.getElementById("arrayAvailableB1").value;
    if (dataAvailableB1 != ""){
        availableParkingB1 = eval(dataAvailableB1);
    }
    let dataAvailableB2 = document.getElementById("arrayAvailableB2").value;
    if (dataAvailableB2 != ""){
        availableParkingB2 = eval(dataAvailableB2);
    }
    let dataOwnB1 = document.getElementById("ownParkingB1").value;
    if (dataOwnB1 != ""){
        ownParkingB1 = eval(dataOwnB1);
    }
    let dataOwnB2 = document.getElementById("ownParkingB2").value;
    if (dataOwnB2 != ""){
        ownParkingB2 = eval(dataOwnB2);
    }
    let dataOtherB1 = document.getElementById("otherParkingB1").value;
    if (dataOtherB1 != ""){
        otherParkingB1 = eval(dataOtherB1);
    }
    let dataOtherB2 = document.getElementById("otherParkingB2").value;
    if (dataOtherB2 != ""){
        otherParkingB2 = eval(dataOtherB2);
    }
    let dataAwaitB1 = document.getElementById("awaitingParkingB1").value;
    if (dataAwaitB1 != ""){
        awatingParkingB1 = eval(dataAwaitB1);
    }
    let dataAwaitB2 = document.getElementById("awaitingParkingB2").value;
    if (dataAwaitB2 != ""){
        awatingParkingB2 = eval(dataAwaitB2);
    }
}
convertData()

function setupMap(level){
    cancelOption();
    let areaControl = document.getElementById("map-control");
    let width = areaControl.offsetWidth;
    let height = width * 3 / 4;
    areaControl.style.height = height + "px";

    let arrayBlock;
    let arrayAvailable;
    let arrayOther;
    let data = "";

    if (level == 1){
        arrayBlock = blockParkingB1;
        arrayAvailable = availableParkingB1;
        arrayOther = otherParkingB1;
        arrayMy = ownParkingB1;
        arrayAwait = awatingParkingB1;

        areaControl.style.backgroundImage = `url("/packing_lot_css/img/b1.png")`
        document.getElementById("button-level").innerHTML = `<div class="border color4 filler boxshadow-outset">B1</div>
                                                                      <div class="border hover color2 filler boxshadow-outset" onclick="setupMap(2)">B2</div>`
    } else {
        arrayBlock = blockParkingB2;
        arrayAvailable = availableParkingB2;
        arrayOther = otherParkingB2;
        arrayMy = ownParkingB2;
        arrayAwait = awatingParkingB2;

        areaControl.style.backgroundImage = `url("/packing_lot_css/img/b2.png")`
        document.getElementById("button-level").innerHTML = `<div class="border hover color2 filler boxshadow-outset" onclick="setupMap(1)">B1</div>
                                                                      <div class="border color4 filler boxshadow-outset">B2</div>`
    }

    for (let i = 0; i < arrayBlock.length; i++){
        let point = arrayBlock[i];
        data += `<polygon class="blockParking" id="${point.alt}" points="${width * point.x1  / 100},${height * point.y1 / 100} 
                                  ${width * point.x2 / 100},${height * point.y2 / 100} 
                                  ${width * point.x3 / 100},${height * point.y3 / 100} 
                                  ${width * point.x4 / 100},${height * point.y4 / 100}
                                  " onclick="locationAction('${point.alt}', event, 0)"/>`
    }
    for (let i = 0; i < arrayAvailable.length; i++){
        let point = arrayAvailable[i];
        data += `<polygon class="availableParking" id="${point.alt}" points="${width * point.x1  / 100},${height * point.y1 / 100} 
                                  ${width * point.x2 / 100},${height * point.y2 / 100} 
                                  ${width * point.x3 / 100},${height * point.y3 / 100} 
                                  ${width * point.x4 / 100},${height * point.y4 / 100}
                                  " onclick="locationAction('${point.alt}', event, 1)"/>`
    }
    for (let i = 0; i < arrayMy.length; i++){
        let point = arrayMy[i];
        data += `<polygon class="myParking" id="${point.alt}" points="${width * point.x1  / 100},${height * point.y1 / 100} 
                                  ${width * point.x2 / 100},${height * point.y2 / 100} 
                                  ${width * point.x3 / 100},${height * point.y3 / 100} 
                                  ${width * point.x4 / 100},${height * point.y4 / 100}
                                  " onclick="locationAction('${point.alt}', event, 3)"/>
                <text x="${width / 400 * (point.x1 + point.x2 + point.x3 + point.x4)}"
                      y="${height / 400 * (point.y1 + point.y2 + point.y3 + point.y4)}">
                ${getTimeRemaining(arrayMy[i].dueDate)}d</text>`
    }
    for (let i = 0; i < arrayOther.length; i++){
        let point = arrayOther[i];
        data += `<polygon class="otherParking" id="${point.alt}" points="${width * point.x1  / 100},${height * point.y1 / 100} 
                                  ${width * point.x2 / 100},${height * point.y2 / 100} 
                                  ${width * point.x3 / 100},${height * point.y3 / 100} 
                                  ${width * point.x4 / 100},${height * point.y4 / 100}
                                  " onclick="locationAction('${point.alt}', event, 2)"/>`
    }
    for (let i = 0; i < arrayAwait.length; i++){
        let point = arrayAwait[i];
        data += `<polygon class="awaitParking" id="${point.alt}" points="${width * point.x1  / 100},${height * point.y1 / 100} 
                                  ${width * point.x2 / 100},${height * point.y2 / 100} 
                                  ${width * point.x3 / 100},${height * point.y3 / 100} 
                                  ${width * point.x4 / 100},${height * point.y4 / 100}
                                  " onclick="locationAction('${point.alt}', event, 4)"/>
                <text x="${width / 400 * (point.x1 + point.x2 + point.x3 + point.x4)}"
                      y="${height / 400 * (point.y1 + point.y2 + point.y3 + point.y4)}">Hold</text>`
    }


    document.getElementById("parkinglot").innerHTML = data;
    setupTimeLeft();
}
setupMap(1)

// document.getElementById('map-control').addEventListener('click', function(event) {
//     var rect = event.target.getBoundingClientRect();
//     var x = ((event.clientX - rect.left) / rect.width) * 100;
//     var y = ((event.clientY - rect.top) / rect.height) * 100;
//
//     alert('x: ' + x + ' y: ' + y);
// });
function locationAction(id, event, status){
    status = +status

    cancelOption();
    let poligon = document.getElementById(id);
    poligon.style.fill = "darkred";
    let x = event.clientX + window.scrollX;
    let y = event.clientY + window.scrollY;
    let table = document.getElementById("option");
    table.style.display = "block";
    table.style.left = x + "px";
    table.style.top = y + "px";

    let data = `<p>${id}</p>`;
    switch (role){
        case '0':
            switch (status){
                case 0:
                    showAlertData("notAvailable", id);
                    data += `<span class="not-available">Not Available</span>`
                    break;
                case 1:
                    showAlertData("login", id);
                    data += `<div onclick="buttonOption('login', '${id}')">Login</div>`
                    break;
                case 2:
                    showAlertData("hasBeenOwned", id);
                    data += `<span class="not-available">Has Been Owned</span>`
                    break;
                case 4:
                    showAlertData("awaiting", id);
                    data += `<span class="not-available">Awaiting </span>`
                    break;
            }
            break;
        case '2':
            switch (status){
                case 0:
                    showAlertData("notAvailable", id);
                    data += `<span class="not-available">Not Available</span>`
                    break;
                case 1:
                    showAlertData("available", id);
                    data += `<div onclick="buttonOption('rental', '${id}')">Rental</div>`
                    break;
                case 2:
                    showAlertData("hasBeenOwned", id);
                    data += `<span class="not-available">Has Been Owned</span>`
                    break;
                case 3:
                    showInfo(id,0);
                    data += `<div onclick="buttonOption('extension','${id}')">Extension</div>`
                    data += `<div onclick="buttonOption('edit','${id}')">Edit Car Info</div>`
                    data += `<div onclick="buttonOption('end lease', '${id}')">End Lease</div>`
                    break;
                case 4:
                    showInfo(id,1);
                    data += `<div onclick="buttonOption('cancel request', '${id}')">Delete Request</div>`
            }
            break;
        case '1':
            switch (status){
                case 0:
                    showAlertData("permission", id);
                    data += `<div onclick="buttonOption('permission', '${id}')">Permission</div>`
                    break;
                case 1:
                    showAlertData("prohibition", id);
                    data += `<div onclick="buttonOption('prohibition', '${id}')">Prohibition</div>`
                    break;
                case 3:
                    showInfo(id,0);
                    data += `<div onclick="buttonOption('extension', '${id}')">Extension</div>`
                    data += `<div onclick="buttonOption('end lease', '${id}')">End Lease</div>`
                    break;
                case 4:
                    showInfo(id,1);
                    data += `<div onclick="buttonOption('detail request', '${id}')">Request Manage</div>`
                    data += `<div onclick="buttonOption('cancel request', '${id}')">Abort Request</div>`
            }
            break;
    }
    data += `<div onclick="buttonOption('cancel')">Cancel</div>`


    table.innerHTML = data;
}
function cancelOption(){
    let table = document.getElementById("option");
    table.style.display = "none";
    let polyList = document.getElementsByTagName("polygon");
    for (let i = 0; i < polyList.length; i++){
        polyList[i].style.fill = "";
    }
    document.getElementById("parking-info").innerHTML = "";
}
function buttonOption(action, id){
    clearInterval(countdownInterval);
    switch (action){
        case "prohibition":
            transferDataConfirm(action, id);
            break;
        case "permission":
            transferDataConfirm(action, id);
            break;
        case "extension":
            window.location.href = "/customer/showbill"
            return;
        case "end lease":
            transferDataConfirm(action, id);
            break;
        case "login":
            break;
        case "rental":
            transferDataConfirm(action, id);
            break;
        case "cancel":
            cancelOption();
            break;
        case "cancel request":
            transferDataConfirm("end lease", id);
            break;
        case "detail request":
            transferDataConfirm(action, id);
            break;
        case "edit":
            showEditTable(id);
            break;
    }
}
function transferDataConfirm(action, id){
    let content = document.getElementById("content-alert");
    let table = document.getElementById("alert-content");
    switch (action){
        case "prohibition":
            content.innerHTML = `Are you sure about <span class="target-text">LOCK</span><br>
                                parkinglot <span class="target-text">${id}</span>`;
            document.getElementById("hidden-button").innerHTML = `<div id="button" onclick="confirmTable('lock','${id}')"></div>`;
            break;
        case "permission":
            content.innerHTML = `Are you sure about <span class="target-text">UNLOCK</span><br>
                                parkinglot <span class="target-text">${id}</span>`;
            document.getElementById("hidden-button").innerHTML = `<div id="button" onclick="confirmTable('unlock','${id}')"></div>`;
            break;
        case "end lease":
            content.innerHTML = `Are you sure about <span class="target-text">END LEASE</span><br>
                                parkinglot <span class="target-text">${id}</span>`;
            document.getElementById("hidden-button").innerHTML = `<div id="button" onclick="confirmTable('end lease','${id}')"></div>`;
            break;
        case "rental":
            window.location.href = "/parking/create/" + id;
            return;
        case "detail request":
            // window.location.href =
            return;
    }
    table.style.display = "grid";
    cancelCountDown();
}
function showEditTable(id){
    document.getElementById("edit-table").style.display = "grid";
    let detail = getClass(id, 0);
    document.getElementById("parking-lot-name").innerHTML = `ParkingLot : <span class="target-text">${id}</span>`;
    document.getElementById("plate-edit").value = detail.licensePlate;

    let dataImg = `<div id="add-icon" class="div-main filler hover-border boxshadow-outset"
                    style="background-image: ; background-position: center" onclick="addImg()"></div>`
    for (let i = 0 ; i < detail.carImg.length; i++){
        let img = detail.carImg[i];
        dataImg += `<div class="div-main filler hover-border boxshadow-outset"
                    style="background-image: url('/packing_lot_css/icon/non-delete-picture.png')"
                    onclick="chooseDeleteImg(this)">
                    <div class="div-branch" style="background-image: url('${img}')"></div></div>`;
    }

    document.getElementById('array-picture-edit').innerHTML = dataImg;
}
function addImg(){
    document.getElementById("add-img-edit").click();
}
function chooseDeleteImg(ele){
    let background = window.getComputedStyle(ele).getPropertyValue('background-image');
    if (background.includes(`non-delete-picture`)) {
        ele.style.backgroundImage = `url("/packing_lot_css/icon/delete-picture.png")`;
    } else {
        ele.style.backgroundImage = `url("/packing_lot_css/icon/non-delete-picture.png")`;
    }
}
function closeEditTable(){
    document.getElementById("edit-table").style.display = "none";
}
function chooseDetailPicture(imgurl){
    let display = document.getElementById("show-detail-img");
    display.style.backgroundImage = `url('${imgurl}')`;
}
function showInfo(id, ind){
    let detail = getClass(id, ind);
    let dateRemaining = getTimeRemaining(detail.dueDate);
    let arrayImg = "";
    for (let i = 0; i < detail.carImg.length; i++){
        let imgDetail = detail.carImg[i];
        arrayImg += `<div class="filler boxshadow-outset hover"
                    style="background-image: url('${imgDetail}')" onclick="chooseDetailPicture('${imgDetail}')"></div>`
    }
    let data = `<div id="parking-name" class="color2 filler boxshadow-outset">Parkinglot : 
                    <span style="color: green">${detail.alt}</span></div>
                <div id="show-detail-img" class="boxshadow-outset filler" style="background-image: url('${detail.carImg[0]}')"></div>
                <div id="array-picture" class="border boxshadow-inset filler">${arrayImg}</div>
                <div id="plate" class="boxshadow-outset filler color2">License Plate : ${detail.licensePlate}</div>
                <table id="parking-detail" class="filler color2 boxshadow-outset">
                    <tr>
                        <td style="width: 40%">Expiration date :</td>
                        <td style="width: 60%">${printDay(detail.dueDate)}</td>
                    </tr>
                    <tr>
                        <td>Time remaining :</td>
                        <td><span class="target-text">${dateRemaining}</span> days</td>
                    </tr>
                </table>
                <div id="customer-id">
<!--                    <img class="boxshadow-outset filler" src="${detail.avata}">-->
                    <div id="avata" class="boxshadow-outset filler" style="background-image: url('https://drive.google.com/uc?id=1IVVkpJfHkcY8HLgzopA5hmRnd_71mLFc')"></div>
                    <div></div>
                    <table id="customer-info" class="color2 filler boxshadow-outset">
                        <tr>
                            <td>User :</td>
                            <td>${detail.username}</td>
                        </tr>
                        <tr>
                            <td>Birthday :</td>
                            <td>${printDay(detail.birthday)}</td>
                        </tr>
                        <tr>
                            <td>Address :</td>
                            <td>${detail.address}</td>
                        </tr>
                        <tr>
                            <td>Gender :</td>
                            <td>${detail.getGender()}</td>
                        </tr>
                        <tr>
                            <td>Phone :</td>
                            <td>${detail.phone}</td>
                        </tr>
                        <tr>
                            <td>Room :</td>
                            <td>${detail.room}</td>
                        </tr>
                    </table>            
                </div>`
    document.getElementById("parking-info").innerHTML = data;
}
function showAlertData(status, parkingId){
    let dataAlert = "";
    switch (status) {
        case "hasBeenOwned":
            dataAlert = "This ParkingLot Has Been Owned";
            break;
        case "notAvailable":
            dataAlert = "This ParkingLot is Blocked by Admin";
            break;
        case "available":
            dataAlert = "This ParkingLot is Ready for Use";
            let data = `<div id="parking-name" class="color2 filler boxshadow-outset">Parkinglot : 
                    <span style="color: green">${parkingId}</span></div>
                <h1 style="color: green" id="alert-parking">${dataAlert}</h1>`
            document.getElementById("parking-info").innerHTML = data;
            return;
        case "login":
            dataAlert = "Please Login for Use";
            break;
        case "permission":
            dataAlert = "Unlocked This ParkingLot";
            break;
        case "prohibition":
            dataAlert = "Lock This ParkingLot";
            break;
    }
    let data = `<div id="parking-name" class="color2 filler boxshadow-outset">Parkinglot : 
                    <span style="color: green">${parkingId}</span></div>
                <h1 id="alert-parking">${dataAlert}</h1>`
    document.getElementById("parking-info").innerHTML = data;
}
function getClass(id,ind){
    if (ind == 0) {
        for (let i = 0; i < arrayMy.length; i++) {
            if (id == arrayMy[i].alt) {
                return arrayMy[i];
            }
        }
    } else {
        for (let i = 0; i < arrayAwait.length; i++) {
            if (id == arrayAwait[i].alt) {
                return arrayAwait[i];
            }
        }
    }
}
function getTimeRemaining(date){
    if (isNaN(date)){
        return "Hold"
    } else {
        let now = new Date();
        let dayRemain = Math.floor((date - now) / (1000 * 60 * 60 * 24));
        return dayRemain;
    }
}
function printDay(date){
    let day = String(date.getDate()).padStart(2,'0');
    let month = String(date.getMonth() + 1).padStart(2,'0');
    let year = date.getFullYear();
    return day + " - " + month + " - " + year;
}
function closeTable(){
    let table = document.getElementById("alert-content");
    table.style.display = "none";
}
function confirmTable(action, id){
    clearInterval(countdownInterval);
    switch (action){
        case "lock":
            window.location.href = "/parking/lock/" + id;
            break;
        case "unlock":
            window.location.href = "/parking/unlock/" + id;
            break;
        case "end lease":
            window.location.href = "/parking/endlease/" + id;
            break;
    }
}
function clickHiddenButton(){
    document.getElementById("button").click();
    closeTable();
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