package mx.edu.uacm.centrocomunitario.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.centrocomunitario.entity.Centro;
import mx.edu.uacm.centrocomunitario.entity.User;
import mx.edu.uacm.centrocomunitario.service.CentroService;
import mx.edu.uacm.centrocomunitario.service.UserService;


@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private CentroService centroService;

	//@ResponseBody
	@RequestMapping(value = "/acceder", method = {RequestMethod.POST,RequestMethod.GET})
    public String basicForm(String correo, String password, Map<String,Object> model,HttpServletRequest request, HttpServletResponse response) {
		ArrayList<User> usuarios= new ArrayList<User>();
		String salida="redirect:/login";
		usuarios=userService.obtenerUsuarios();
		ArrayList<Centro> centros = new ArrayList<Centro>();
		centros = centroService.obtenerCentros();
		for(int i=0;i<usuarios.size();i++) {
			if(usuarios.get(i).getCorreo().equals(correo) && usuarios.get(i).getPassword().equals(password)) {
				log.debug("entre a login");
				httpSession.setAttribute("usuario", salida);
				httpSession.removeAttribute("error");
				model.put("centros", centros);
				request.setAttribute("centros", centros);
				httpSession.setAttribute("centros", centros);
				//redirectAttributes.addAttribute("centros",centros);
				for (i=0;i<centros.size();i++) {
					log.debug(centros.get(i).getNombre());
				}
				salida="redirect:/reportes ";
				break;
			}
			else {
				httpSession.setAttribute("error", "error");
			}
		}
		
		log.debug(salida);
		return salida; 
	}

}