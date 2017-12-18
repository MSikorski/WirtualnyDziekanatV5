// A $( document ).ready() block.
$(document).ready(function () {
	
    console.log( "ready!" );
    var userPanel = document.getElementById("userName");
    var logout = document.getElementById("logout");
    
    if(userPanel.innerHTML === "Profil uzytkownika Niezarejestrowany"){
    	userPanel.parentNode.removeChild(userPanel);
    	logout.parentNode.removeChild(logout);
    }
});
