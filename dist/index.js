function func() {
    var text;
    if (confirm("Ets major d'edat?")){
          text="Confirmant...";
     }
     else {
          text="Permís denegat";
     }
     document.getElementById("demo").innerHTML = text;
}