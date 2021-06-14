package mx.edu.uacm.centrocomunitario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.edu.uacm.centrocomunitario.entity.Permiso;
import mx.edu.uacm.centrocomunitario.entity.Rol;
import mx.edu.uacm.centrocomunitario.entity.User;
import mx.edu.uacm.centrocomunitario.service.PermisoService;
import mx.edu.uacm.centrocomunitario.service.RolService;
import mx.edu.uacm.centrocomunitario.service.UserService;

@Controller
@RequestMapping("/registro")


public class RegistroController {
	@Autowired
	private UserService userService;
	@Autowired
	private RolService rolService;
	Rol rol = new Rol();
	@Autowired
	private PermisoService permisoService;
	Permiso permiso = new Permiso();
	
	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	
    public String basicForm(String nombre, String apellidoPaterno, String apellidoMaterno, int edad, String direccion,
    	String correo,String password, String telefono, String sexo, Model model) {
		System.out.println("entre a registro");
		permiso.setId(1L);
		permiso.setNombre("lectura");
		rol.setId(1L);
		rol.setNombre("alumno");
		if(!permisoService.existePermiso(1L)) {
			permisoService.guardarPermiso(permiso);
		}
		if(!rolService.existeRol(1L)) {
			rol.getPermisos().add(permiso);
			rolService.guardarRol(rol);
		}
		User u = new User();
		u.setNombre(nombre);
		u.setApellidoPaterno(apellidoPaterno);
		u.setApellidoMaterno(apellidoMaterno);
		u.setEdad(edad);
		u.setDireccion(direccion);
		u.setCorreo(correo);
		u.setTelefono(telefono);
		u.setSexo(sexo);
		u.setPassword(password);
		//u.getRoles().add(rol);
		u=userService.guardarUsuario(u);
		model.addAttribute("usuario", u);
		return "registro :: #rest"; 
	}
}

