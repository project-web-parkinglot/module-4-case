function setButton(role){
    let data = "";
    switch (role){
        case 0:
            data += `
                    <div class="color1 filler boxshadow-outset hover" onclick="actionButton('contact')">Contact</div>
                    <div class="color1 filler boxshadow-outset hover" onclick="actionButton('feedback')">Feedback</div>
                    `
            break;
        case 1:
            data += `
                    <div class="color1 filler boxshadow-outset hover" onclick="actionButton('my account')">My Account</div>
                    <div class="color1 filler boxshadow-outset hover" onclick="actionButton('contact')">Contact</div>
                    <div class="color1 filler boxshadow-outset hover" onclick="actionButton('feedback')">Feedback</div>
                    `
            break;
        case 2:
            data += `
                    <div class="color1 filler boxshadow-outset hover" onclick="actionButton('my account')">My Account</div>
                    <div class="color1 filler boxshadow-outset hover" onclick="actionButton('customer')">Customer Manage</div>
                    <div class="color1 filler boxshadow-outset hover" onclick="actionButton('rental')">Rental Request</div>
                    `
            break;
        case 3:
            data += `
                    <div class="color1 filler boxshadow-outset hover" onclick="actionButton('my account')">My Account</div>
                    <div class="color1 filler boxshadow-outset hover" onclick="actionButton('employee')">Employee Manage</div>
                    <div class="color1 filler boxshadow-outset hover" onclick="actionButton('customer')">Customer Manage</div>
                    <div class="color1 filler boxshadow-outset hover" onclick="actionButton('rental')">Rental Request</div>
                    `
            break;
    }
    document.getElementById("function-key").innerHTML = data;
}
setButton(0);
function actionButton(action){
    switch (action){
        case 'contact':
            window.open('tel:0764843894', '_blank');
            break;
        case 'feedback':
            window.location.href = 'mailto:lequocthang307@gmail.com';
            break;
        case 'my account':
            break;
        case 'employee':
            break;
        case 'customer':
            break;
        case 'rental':
            break;
        case 'facebook':
            window.location.href = 'https://www.facebook.com/ssaphie/';
            break;
    }
}