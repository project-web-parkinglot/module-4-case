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
let arrayPoint1 = [
    new parkingLot(26.1,5.2,23.5,5.2,23.5,11.9,26.1,11.9,"B1-01"),
    new parkingLot(28.8,5.2,26.1,5.2,26.1,11.9,28.8,11.9,"B1-02"),
    new parkingLot(31.4,5.2,28.8,5.2,28.8,11.9,31.4,11.9,"B1-03"),
    new parkingLot(34.1,5.2,31.4,5.2,31.4,11.9,34.1,11.9,"B1-04"),
    new parkingLot(36.8,5.2,34.1,5.2,34.1,11.9,36.8,11.9,"B1-05"),
    new parkingLot(39.4,5.2,36.8,5.2,36.8,11.9,39.4,11.9,"B1-06"),
    new parkingLot(42.1,5.2,39.4,5.2,39.4,11.9,42.1,11.9,"B1-07"),
    new parkingLot(44.6,5.2,42.1,5.2,42.1,11.9,44.6,11.9,"B1-08"),

    new parkingLot(20.4,16.5,15.4,16.2,15.3,19.7,20.32,19.9,"B1-09"),
    new parkingLot(20.32,19.9,15.3,19.7,15.2,23.2,20.22,23.4,"B1-10"),
    new parkingLot(20.22,23.4,15.2,23.2,15.1,26.8,20.12,27,"B1-11"),
    new parkingLot(20.12,27,15.1,26.8,15,30.3,20.02,30.6,"B1-12"),
    new parkingLot(20.02,30.6,15,30.3,14.9,33.8,19.92,34.1,"B1-13"),
    new parkingLot(19.92,34.1,14.9,33.8,14.8,37.3,19.82,37.6,"B1-14"),
    new parkingLot(19.82,37.6,14.8,37.3,14.7,40.8,19.72,41.15,"B1-15"),
    new parkingLot(19.72,41.15,14.7,40.8,14.6,44.6,19.62,44.8,"B1-16"),
    new parkingLot(19.62,44.8,14.6,44.6,14.5,47.9,19.52,48.1,"B1-17"),

    new parkingLot(29.9,17.7,24.9,17.6,24.8,21,29.8,21.3,"B1-18"),
    new parkingLot(29.8,21.3,24.8,21,24.7,24.6,29.7,24.8,"B1-19"),
    new parkingLot(29.6,26.8,24.6,26.4,24.5,29.9,29.5,30.2,"B1-20"),
    new parkingLot(29.5,30.2,24.5,29.9,24.4,33.5,29.4,33.8,"B1-21"),
    new parkingLot(29.4,35.9,24.3,35.7,24.2,39.1,29.25,39.4,"B1-22"),
    new parkingLot(29.25,39.4,24.2,39.1,24.1,42.8,29.1,42.9,"B1-23"),
    new parkingLot(35,20.52,29.85,20.2,29.7,23.8,34.9,24.2,"B1-24"),
    new parkingLot(34.9,24.2,29.8,23.8,29.7,27.3,34.8,27.6,"B1-25"),
    new parkingLot(32,38.05,29.36,37.9,29.2,44.7,31.79,44.8,"B1-26"),
    new parkingLot(34.65,38.29,32,38.05,31.79,44.8,34.4,44.89,"B1-27"),
    new parkingLot(48.2,13.75,44.6,18.56,46.45,21.08,50.05,16.27,"B1-28"),
    new parkingLot(50.05,16.27,46.45,21.08,48.35,23.56,51.9,18.73,"B1-29"),
    new parkingLot(53.21,20.55,49.66,25.35,51.43,27.73,55.09,23,"B1-30"),
    new parkingLot(57,25.45,53.47,30.36,55.3,32.89,58.89,28.05,"B1-31"),
    new parkingLot(58.89,28.05,55.3,32.89,57.25,35.22,60.78,30.58,"B1-32"),
    new parkingLot(45.4,19.73,41.71,24.44,43.65,26.95,47.25,22.16,"B1-33"),
    new parkingLot(47.25,22.16,43.65,26.95,45.52,29.48,49.06,24.69,"B1-34"),

    new parkingLot(49.06,24.69,45.52,29.48,47.39,31.98,50.99,27.15,"B1-35"),
    new parkingLot(50.99,27.15,47.39,31.98,49.26,34.52,52.82,29.71,"B1-36"),
    new parkingLot(52.82,29.71,49.26,34.52,51.23,37.05,54.8,32.2,"B1-37"),
    new parkingLot(54.8,32.2,51.23,37.05,53.05,39.55,56.6,34.71,"B1-38"),
    new parkingLot(56.6,34.71,53.05,39.55,54.97,42.03,58.51,37.2,"B1-39"),
    new parkingLot(58.51,37.2,54.97,42.03,56.84,44.56,60.4,39.7,"B1-40"),

    new parkingLot(30.2,53.57,25.1,53.57,25.1,57.05,30.2,57.05,"B1-41"),
    new parkingLot(30.2,57.05,25.1,57.05,25.1,60.55,30.2,60.55,"B1-42"),

    new parkingLot(36.45,52.45,33.85,52.45,33.85,59.24,36.45,59.24,"B1-43"),
    new parkingLot(39.04,52.45,36.45,52.45,36.45,59.24,39.04,59.24,"B1-44"),
    new parkingLot(49.48,50.29,47.62,52.8,51.29,57.59,53.1,55.1,"B1-45"),
    new parkingLot(51.32,47.9,49.48,50.29,53.1,55.1,55,52.59,"B1-46"),

    new parkingLot(68.65,37.35,63.62,38.8,64.15,42.3,69.21,40.76,"B1-47"),
    new parkingLot(69.21,40.76,64.15,42.3,64.76,45.68,69.75,44.25,"B1-48"),
    new parkingLot(69.75,44.25,64.76,45.68,65.27,49.22,70.35,47.72,"B1-49"),
    new parkingLot(40.75,70,38.22,71.1,39.84,77.55,42.38,76.41,"B1-50"),
    new parkingLot(43.25,68.88,40.75,70,42.38,76.41,44.88,75.28,"B1-51"),
    new parkingLot(45.8,67.7,43.25,68.88,44.88,75.28,47.45,74.19,"B1-52"),

    new parkingLot(51.04,67.89,48.38,67.51,47.91,74.16,50.56,74.47,"B1-53"),
    new parkingLot(53.71,68.24,51.04,67.89,50.56,74.47,53.25,74.79,"B1-54"),
    new parkingLot(56.38,68.59,53.71,68.24,53.25,74.79,55.94,75.11,"B1-55"),
    new parkingLot(59,68.94,56.38,68.59,55.94,75.11,58.53,75.53,"B1-56"),
    new parkingLot(61.72,69.29,59,68.94,58.53,75.53,61.22,75.85,"B1-57"),
    new parkingLot(64.29,69.64,61.72,69.29,61.22,75.85,63.81,76.17,"B1-58"),
    new parkingLot(66.96,69.99,64.29,69.64,63.81,76.17,66.45,76.59,"B1-59"),
    new parkingLot(69.63,70.34,66.96,69.99,66.45,76.59,69.15,76.91,"B1-60"),
    new parkingLot(72.3,70.69,69.63,70.34,69.15,76.91,71.78,77.23,"B1-61"),
    new parkingLot(74.77,71.03,72.3,70.69,71.78,77.23,74.4,77.65,"B1-62")
]
let arrayPoint2 = [
    new parkingLot(26.5,5.2,23.9,5.2,23.9,12,26.5,12,"B2-01"),
    new parkingLot(29.2,5.2,26.5,5.2,26.5,12,29.2,12,"B2-02"),
    new parkingLot(31.8,5.2,29.2,5.2,29.2,12,31.8,12,"B2-03"),
    new parkingLot(34.5,5.2,31.8,5.2,31.8,12,34.5,12,"B2-04"),
    new parkingLot(37.3,5.2,34.5,5.2,34.5,12,37.2,12,"B2-05"),
    new parkingLot(39.9,5.2,37.2,5.2,37.2,12,39.8,12,"B2-06"),
    new parkingLot(42.6,5.2,39.8,5.2,39.8,12,42.5,12,"B2-07"),
    new parkingLot(45,5.2,42.5,5.2,42.5,12,45,12,"B2-08"),
    new parkingLot(47.7,5.2,45,5.2,45,12,47.7,12,"B2-09"),
    new parkingLot(50.3,5.2,47.7,5.2,47.7,12,50.3,12,"B2-10"),
    new parkingLot(52.9,5.2,50.3,5.2,50.3,12,52.9,12,"B2-11"),
    new parkingLot(55.5,5.2,52.9,5.2,52.9,12,55.5,12,"B2-12"),
    new parkingLot(58.2,5.2,55.5,5.2,55.5,12,58.2,12,"B2-13"),


    new parkingLot(20.9,16.5,15.9,16.2,15.8,19.7,20.82,19.9,"B2-14"),
    new parkingLot(20.82,19.9,15.8,19.7,15.7,23.2,20.72,23.4,"B2-15"),
    new parkingLot(20.72,23.4,15.7,23.2,15.6,26.7,20.62,26.9,"B2-16"),
    new parkingLot(20.62,26.9,15.6,26.7,15.5,30.2,20.52,30.5,"B2-17"),
    new parkingLot(20.52,30.5,15.5,30.2,15.4,33.7,20.42,34,"B2-18"),
    new parkingLot(20.42,34,15.4,33.7,15.3,37.2,20.32,37.5,"B2-19"),
    new parkingLot(20.32,37.5,15.3,37.2,15.2,40.7,20.22,40.95,"B2-20"),
    new parkingLot(20.22,40.95,15.2,40.7,15.1,44.3,20.12,44.5,"B2-21"),
    new parkingLot(20.12,44.5,15.1,44.3,15,47.7,20.02,48,"B2-22"),
    new parkingLot(20.02,48,15,47.7,14.9,51.1,19.92,51.5,"B2-23"),

    new parkingLot(19.8,53.95,14.7,53.7,14.6,57.25,19.7,57.5,"B2-24"),
    new parkingLot(19.7,57.5,14.6,57.25,14.5,60.8,19.6,61.05,"B2-25"),
    new parkingLot(19.6,61.05,14.5,60.8,14.4,64.35,19.5,64.6,"B2-26"),
    new parkingLot(19.5,64.6,14.4,64.35,14.3,67.9,19.4,68.15,"B2-27"),
    new parkingLot(19.4,68.15,14.3,67.9,14.2,71.45,19.3,71.7,"B2-28"),
    new parkingLot(19.3,71.7,14.2,71.45,14.1,75,19.2,75.25,"B2-29"),
    new parkingLot(19.2,75.25,14.1,75,14,78.45,19.1,78.7,"B2-30"),

    new parkingLot(30.3,17.8,25.3,17.6,25.2,21,30.3,21.3,"B2-31"),
    new parkingLot(30.3,21.3,25.2,21,25.1,24.65,30.2,24.95,"B2-32"),
    new parkingLot(30.1,26.6,25,26.3,24.9,29.85,30,30.15,"B2-33"),
    new parkingLot(30,30.15,24.9,29.85,24.8,33.4,29.9,33.65,"B2-34"),
    new parkingLot(29.8,35.72,24.76,35.47,24.64,39,29.7,39.27,"B2-35"),
    new parkingLot(29.7,39.27,24.64,39,24.54,42.5,29.6,42.8,"B2-36"),
    new parkingLot(29.3,53.29,24.2,53.04,24.15,56.52,29.2,56.79,"B2-37"),
    new parkingLot(29.2,56.79,24.15,56.52,24,60.05,29.07,60.36,"B2-38"),

    new parkingLot(34,20.76,31.43,20.69,31.17,27.42,33.9,27.59,"B2-39"),
    new parkingLot(36.69,20.9,34,20.76,33.9,27.59,36.5,27.7,"B2-40"),
    new parkingLot(32.46,35.88,29.78,35.68,29.62,42.5,32.3,42.68,"B2-41"),
    new parkingLot(35.1,36,32.46,35.88,32.3,42.68,34.88,42.78,"B2-42"),
    new parkingLot(32,47.9,29.46,47.69,29.28,54.38,31.88,54.48,"B2-43"),
    new parkingLot(34.69,47.97,32,47.9,31.88,54.48,34.51,54.69,"B2-44"),
    new parkingLot(34.3,54.69,29.28,54.41,29.18,57.92,34.22,58.2,"B2-45"),

    new parkingLot(17.17,85.57,14.62,84.73,13.37,91.28,15.97,92.12,"B2-46"),
    new parkingLot(19.72,86.48,17.17,85.57,15.97,92.12,18.54,93,"B2-47"),
    new parkingLot(24.11,87.92,21.53,87.04,20.3,93.63,22.9,94.47,"B2-48"),
    new parkingLot(26.68,88.76,24.11,87.92,22.9,94.47,25.47,95.27,"B2-49"),
    new parkingLot(31.7,64.15,28.1,68.94,29.94,71.43,33.51,66.67,"B2-50"),
    new parkingLot(33.51,66.67,29.94,71.43,31.83,73.91,35.4,69.15,"B2-51"),

    new parkingLot(45.56,19.64,42,24.43,43.86,26.86,47.43,22.06,"B2-52"),
    new parkingLot(47.43,22.06,43.86,26.86,45.75,29.34,49.34,24.51,"B2-53"),
    new parkingLot(49.34,24.51,45.75,29.34,47.58,31.83,51.18,27.1,"B2-54"),
    new parkingLot(51.18,27.1,47.58,31.83,49.42,34.31,53.02,29.55,"B2-55"),
    new parkingLot(53.02,29.55,49.42,34.31,51.34,36.8,54.86,32.07,"B2-56"),
    new parkingLot(54.86,32.07,51.34,36.8,53.2,39.29,56.7,34.49,"B2-57"),
    new parkingLot(56.7,34.49,53.2,39.29,55.07,41.7,58.61,36.94,"B2-58"),
    new parkingLot(58.61,36.94,55.07,41.7,56.93,44.26,60.5,39.5,"B2-59"),
    new parkingLot(53.49,20.62,49.97,25.39,51.79,27.87,55.38,23.08,"B2-60"),
    new parkingLot(57.25,25.6,53.68,30.36,55.54,32.84,59.11,27.98,"B2-61"),
    new parkingLot(59.11,27.98,55.54,32.84,57.43,35.26,61,30.5,"B2-62"),

    new parkingLot(68.59,37.11,63.63,38.62,64.18,41.98,69.17,40.62,"B2-63"),
    new parkingLot(69.17,40.62,64.18,41.98,64.78,45.45,69.69,43.98,"B2-64"),
    new parkingLot(49.82,50.42,46.22,55.11,48.06,57.63,51.63,52.9,"B2-65"),
    new parkingLot(51.63,52.9,48.06,57.63,49.92,60.08,53.52,55.36,"B2-66"),
    new parkingLot(54.41,56.44,50.81,61.24,52.68,63.76,56.25,58.96,"B2-67"),

    new parkingLot(41.02,69.47,38.55,70.66,40.13,77,42.62,75.91,"B2-68"),
    new parkingLot(43.51,68.38,41.02,69.47,42.62,75.91,45.12,74.79,"B2-69"),
    new parkingLot(46.03,67.23,43.51,68.38,45.12,74.79,47.64,73.67,"B2-70"),
    new parkingLot(51.23,67.33,48.58,66.98,48.08,73.7,50.71,74.05,"B2-71"),
    new parkingLot(53.89,67.65,51.23,67.33,50.71,74.05,53.36,74.44,"B2-72"),
    new parkingLot(56.51,68.03,53.89,67.65,53.36,74.44,55.99,74.75,"B2-73"),
    new parkingLot(59.06,68.38,56.51,68.03,55.99,74.75,58.61,75.14,"B2-74"),
    new parkingLot(61.74,68.66,59.06,68.38,58.61,75.14,61.24,75.39,"B2-75"),
    new parkingLot(64.34,69.08,61.74,68.66,61.24,75.39,63.84,75.74,"B2-76"),
    new parkingLot(67,69.43,64.34,69.08,63.84,75.74,66.47,76.16,"B2-77"),
    new parkingLot(69.62,69.78,67,69.43,66.47,76.16,69.1,76.5,"B2-78"),
    new parkingLot(72.19,70.13,69.62,69.78,69.1,76.5,71.74,76.79,"B2-79"),
    new parkingLot(74.84,70.52,72.19,70.13,71.74,76.79,74.32,77.17,"B2-80"),
    new parkingLot(77.47,70.8,74.84,70.52,74.32,77.17,77,77.56,"B2-81")
]
function setupMap(level){
    let areaControl = document.getElementById("map-control");
    let width = areaControl.offsetWidth;
    let height = width * 3 / 4;
    areaControl.style.height = height + "px";

    let array = [];
    let data = "";
    if (level == 1){
        array = arrayPoint1;
        areaControl.style.backgroundImage = `url("/packing_lot_css/img/b1.png")`
        document.getElementById("button-level").innerHTML = `<div class="color4 filler boxshadow-outset">B1</div>
                                                                      <div class="hover color2 filler boxshadow-outset" onclick="setupMap(2)">B2</div>`
    } else {
        array = arrayPoint2;
        areaControl.style.backgroundImage = `url("/packing_lot_css/img/b2.png")`
        document.getElementById("button-level").innerHTML = `<div class="hover color2 filler boxshadow-outset" onclick="setupMap(1)">B1</div>
                                                                      <div class="color4 filler boxshadow-outset">B2</div>`
    }


    for (let i = 0; i < array.length; i++){
        let point = array[i];
        data += `<polygon id="${point.alt}" points="${width * point.x1  / 100},${height * point.y1 / 100} 
                                  ${width * point.x2 / 100},${height * point.y2 / 100} 
                                  ${width * point.x3 / 100},${height * point.y3 / 100} 
                                  ${width * point.x4 / 100},${height * point.y4 / 100}
                                  " onclick="locationAction('${point.alt}', event)"/>`
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
function locationAction(id, event){
    cancelOption();
    let poligon = document.getElementById(id);
    poligon.style.fill = "green";
    let x = event.clientX;
    let y = event.clientY;
    let table = document.getElementById("option");
    table.style.display = "block";
    table.style.left = x + "px";
    table.style.top = y + "px";

    //Check role và giá trị ô
    let data = `<p>${id}</p>`;
    data += `<div>Login</div>`

    data += `<div onclick="cancelOption()">Cancel</div>`
    table.innerHTML = data;
}
function cancelOption(){
    let table = document.getElementById("option");
    table.style.display = "none";
    let polyList = document.getElementsByTagName("polygon");
    for (let i = 0; i < polyList.length; i++){
        polyList[i].style.fill = "";
    }
}
function buttonOption(action){
    switch (action){
        case "prohibition":
            break;
    }
}