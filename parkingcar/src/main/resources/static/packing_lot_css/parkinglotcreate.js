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
    let arrayAvailable;
    let data = "";
    if (level == 1){
        arrayAvailable = availableParkingB1;

        areaControl.style.backgroundImage = `url("/packing_lot_css/img/b1.png")`
        document.getElementById("button-level").innerHTML = `<div class="border color4 filler boxshadow-outset">B1</div>
                                                                      <div class="border hover color2 filler boxshadow-outset" onclick="setupMap(2)">B2</div>`
    } else {
        arrayAvailable = availableParkingB2;

        areaControl.style.backgroundImage = `url("/packing_lot_css/img/b2.png")`
        document.getElementById("button-level").innerHTML = `<div class="border hover color2 filler boxshadow-outset" onclick="setupMap(1)">B1</div>
                                                                      <div class="border color4 filler boxshadow-outset">B2</div>`
    }

    for (let i = 0; i < arrayAvailable.length; i++){
        let point = arrayAvailable[i];
        data += `<polygon class="availableParking" id="${point.alt}" points="${width * point.x1  / 100},${height * point.y1 / 100} 
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
convertData();
setupMap(1);
changeColorParking();