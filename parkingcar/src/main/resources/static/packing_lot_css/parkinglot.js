let blockParkingB1 = [];
let blockParkingB2 = [];
let availableParkingB1 = [];
let availableParkingB2 = [];
let ownParkingB1 = [];
let ownParkingB2 = [];
let arrayMy = [];
class parkingLot{
    constructor(x1,y1,x2,y2,x3,y3,x4,y4,alt) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;
        this.alt = alt;
    }
}
class parkingLotMine{
    constructor(x1,y1,x2,y2,x3,y3,x4,y4,alt,dueDate,carImg,licensePlate,username,
                birthday,address,gender,avata,phone,room) {
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
    blockParkingB1 = eval(document.getElementById("arrayBlockB1").value);
    blockParkingB2 = eval(document.getElementById("arrayBlockB2").value);
    availableParkingB1 = eval(document.getElementById("arrayAvailableB1").value);
    availableParkingB2 = eval(document.getElementById("arrayAvailableB2").value);
    ownParkingB1 = eval(document.getElementById("ownParkingB1").value);
    ownParkingB2 = eval(document.getElementById("ownParkingB2").value);
}
convertData()


function setupMap(level){

    let areaControl = document.getElementById("map-control");
    let width = areaControl.offsetWidth;
    let height = width * 3 / 4;
    areaControl.style.height = height + "px";

    let arrayBlock;
    let arrayAvailable;
    let arrayNotAvailable = [];

    let data = "";

    if (level == 1){
        arrayBlock = blockParkingB1;
        arrayAvailable = availableParkingB1;
        arrayMy = ownParkingB1;

        areaControl.style.backgroundImage = `url("/packing_lot_css/img/b1.png")`
        document.getElementById("button-level").innerHTML = `<div class="color4 filler boxshadow-outset">B1</div>
                                                                      <div class="hover color2 filler boxshadow-outset" onclick="setupMap(2)">B2</div>`
    } else {
        arrayBlock = blockParkingB2;
        arrayAvailable = availableParkingB2;
        arrayMy = ownParkingB2;

        areaControl.style.backgroundImage = `url("/packing_lot_css/img/b2.png")`
        document.getElementById("button-level").innerHTML = `<div class="hover color2 filler boxshadow-outset" onclick="setupMap(1)">B1</div>
                                                                      <div class="color4 filler boxshadow-outset">B2</div>`
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
                                  " onclick="locationAction('${point.alt}', event, 3)"/>`
    }


    document.getElementById("parkinglot").innerHTML = data;
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

    //lấy role hiện tại

    // 0 => chưa đăng nhập
    // 1 => khách
    // 2 => nhân viên
    // 3 => admin

    // 0 => cấm thuê
    // 1 => chưa ai thuê
    // 2 => đã có người thuê (bảo mật thông tin)
    // 3 => mine (đã có người thuê có thông tin chính chủ)
    status = +status

    cancelOption();
    let poligon = document.getElementById(id);
    poligon.style.fill = "chocolate";
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
                    data += `<span class="not-available">Not Available</span>`
                    break;
                case 1:
                    data += `<div onclick="buttonOption('login')">Login</div>`
                    break;
                case 2:
                    data += `<span class="not-available">Has Been Owned</span>`
                    break;
            }
            break;
        case '1':
            switch (status){
                case 0:
                    data += `<span class="not-available">Not Available</span>`
                    break;
                case 1:
                    data += `<div onclick="buttonOption('rental')">Rental</div>`
                    break;
                case 2:
                    data += `<span class="not-available">Has Been Owned</span>`
                    break;
                case 3:
                    data += `<div onclick="buttonOption('extension')">Extension</div>`
                    data += `<div onclick="buttonOption('end lease')">End Lease</div>`
                    break;
            }
            break;
        case '2':
            switch (status){
                case 0:
                case 1:
                    data += `<span class="not-available">Not Available</span>`
                    break;
                case 3:
                    showInfo(id);
                    data += `<div onclick="buttonOption('extension')">Extension</div>`
                    data += `<div onclick="buttonOption('end lease')">End Lease</div>`
                    break;
            }
            break;
        case '3':
            switch (status){
                case 0:
                    data += `<div onclick="buttonOption('permission')">Permission</div>`
                    break;
                case 1:
                    data += `<div onclick="buttonOption('prohibition')">Prohibition</div>`
                    break;
                case 3:
                    showInfo(id);
                    data += `<div onclick="buttonOption('extension')">Extension</div>`
                    data += `<div onclick="buttonOption('end lease')">End Lease</div>`
                    break;
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
function buttonOption(action){
    switch (action){
        case "prohibition":
            break;
        case "permission":
            break;
        case "extension":
            break;
        case "end lease":
            break;
        case "login":
            break;
        case "rental":
            break;
        case "cancel":
            cancelOption();
            break
    }
}
function showInfo(id){
    let detail = getClass(id);
    let dateRemaining = getTimeRemaining(detail.dueDate);
    let data = `<div id="parking-name" class="color2 filler boxshadow-outset">Parkinglot : 
                    <span style="color: green">${detail.alt}</span></div>
                <img class="boxshadow-outset filler" src="${detail.carImg}">
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
function getClass(id){
    for (let i = 0; i < arrayMy.length; i++){
        if(id == arrayMy[i].alt){
            return arrayMy[i];
        }
    }
}
function getTimeRemaining(date){
    let now = new Date();
    let dayRemain = Math.floor((date - now) / (1000 * 60 * 60 * 24));
    return dayRemain;
}
function printDay(date){
    let day = String(date.getDate()).padStart(2,'0');
    let month = String(date.getMonth() + 1).padStart(2,'0');
    let year = date.getFullYear();
    return day + " - " + month + " - " + year;
}