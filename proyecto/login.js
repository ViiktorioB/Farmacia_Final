function enviar(){
    var http = new XMLHttpRequest();

    let mail = document.getElementById("mail").value;
    let pass = document.getElementById("pass").value;


    http.open("GET", "http://localhost:3002/Farmacia1/Login1?mail="+mail+"&pass="+pass, true);

    http.onreadystatechange = function(){
        if(this.readyState==4 && this.status==200){
            var session = http.responseText;
            if(session !== null){
                sessionStorage.setItem("session",session)
                sessionStorage.setItem("mail",mail);
                window.location.href = "gestio.html";
            }else{
                alert("Session interrunpida. Vuelva a intentralo");
            }
        }
    }

    http.send();
}


// PAGINA GESTIO

function logOut() {
    sessionStorage.removeItem("mail");
    sessionStorage.removeItem("session");
    window.location.href = "login.html";
}


function getTable() {

var mail = sessionStorage.getItem('mail');
var session = sessionStorage.getItem('session');

var ehttp = new XMLHttpRequest();

ehttp.open("GET", "http://localhost:3002/Farmacia1/ServeXips?mail="+mail+"&session="+session, true);

ehttp.onreadystatechange = function() {
  if (ehttp.status === 200) {
    var responseHTML = ehttp.responseText;
    let tableContainer = document.getElementById("tableContainer");
    tableContainer.innerHTML = responseHTML;
  }
};
ehttp.send();
}

function goToAlta() {
    window.location.href = "alta.html";
}

// PAGINA ALTA

function goToGestio() {
    window.location.href = "gestio.html";
}

function getPaciente(){
    var mail = sessionStorage.getItem('mail');
    var session = sessionStorage.getItem('session');

    let http = new XMLHttpRequest();

    http.open("GET","http://localhost:3002/Farmacia1/ServePatients?mail="+mail + "&session=" + session, true);
    http.send();

    http.onreadystatechange=function(){
        if(this.readyState==4 && this.status==200){
            console.log(this.responseText);
            var patients = JSON.parse(http.response);
            var select = document.getElementById("patient");

            select.innerHTML = "";

            patients.forEach(function(patient, index) {
                if (index % 2 === 0) {
                var option = document.createElement("option");
                option.text = patient;
                select.add(option);
            }});
        }
    }
}

function getMedicine(){
    var mail = sessionStorage.getItem('mail');
    var session = sessionStorage.getItem('session');

    let http = new XMLHttpRequest();

    http.open("GET","http://localhost:3002/Farmacia1/ServeMedicines?mail="+mail + "&session=" + session, true);
    http.send();

    http.onreadystatechange=function(){
        if(this.readyState==4 && this.status==200){
            console.log(this.responseText);
            var medicines = JSON.parse(http.response);
            var select = document.getElementById("medicine");

            select.innerHTML = "";

            medicines.forEach(function(medicine, index) {
                if (index % 2 === 0) {
                var option = document.createElement("option");
                option.text = medicine;
                select.add(option);
            }});
        }
    }
}


function enviar1() {
    var mail = sessionStorage.getItem("mail");
    var session = sessionStorage.getItem("session");
    var idXip = document.getElementById("idXip").value;
    var mailP = document.getElementById("patient").value;
    var idMed = document.getElementById("medicine").value;
    var date = document.getElementById("fechaLimite").value;


    var http = new XMLHttpRequest();

    http.open("POST", "http://localhost:3002/Farmacia1/Release?mail=" + mail + "&session=" + session + "&idXip=" + idXip + "&patient=" + mailP + "&medicine=" + idMed + "&fechaLimite=" + date, true);

    http.onload = function() {
        if (http.status == 200) {
            var response = http.responseText;
           
                console.info("XIP registrado");
                alert("XIP registrado correctamente");

                document.getElementById("idXip").value = "";
                document.getElementById("patient").value = "";
                document.getElementById("medicine").value = "";
                document.getElementById("fechaLimite").value = "";

        } else {
            console.error("Error en la solicitud Release", http.status);
        }
    };

    http.send();
}
  
  