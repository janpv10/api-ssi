function func(){
     
     const theUrl = "https://bet247.netlify.app/.netlify/functions/api/comprovar-credencial"
     var xhr = new XMLHttpRequest();
     xhr.open("GET", theUrl, true);
     xhr.onload = function (e) {
          if (xhr.readyState === 4) {
               if (xhr.status === 200) {
                    var response = xhr.responseText
                    if(response == '{"boolean":"true"}'){
                         document.location.href = "https://bet247.netlify.app/home.html";
                    }
                    else{
                         window.alert("Permís denegat");
                    }
               } else {
                    window.alert("Not able to connect");
               }
          }
     };
     xhr.onerror = function (e) {
       console.error(xhr.statusText);
     };
     xhr.send(null);
}