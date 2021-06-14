$(document).ready(function() {  
$("#formLogin").submit(function(e) {
       
    }).validate({
		
      	rules: {
                   correo: {
                           required: true,
                   },
                password: {
                           required: true
                   }
           },
           messages: {
                   correo: {
						
                           required: "Introduzca correo"
                   },
                   password: {
                   			required: "Introduzca contraseña"
                   }
           },

        //errorPlacement: function(error, element) {
         //     error.appendTo(element.parent());
         //},
         submitHandler: function(form) {
           form.submit();
            return false;
 
         }
	
    });
    
});