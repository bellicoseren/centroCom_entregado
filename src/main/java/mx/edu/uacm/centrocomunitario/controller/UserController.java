package mx.edu.uacm.centrocomunitario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mx.edu.uacm.centrocomunitario.entity.User;
import mx.edu.uacm.centrocomunitario.service.UserService;



@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/asistencia")
	public String viewHomePage(Model model) {
		return findPaginated(1, "nombre", "asc", model);		
	}
	
	@GetMapping("/showNewUserForm")
	public String showNewUserForm(Model model) {
		mx.edu.uacm.centrocomunitario.entity.User user = new User();
		model.addAttribute("user", user);
		return "new_user";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user) {
		userService.saveUser(user);
		return "redirect:update_user";
	}
	
	@GetMapping("/showFormForUpdate/{folio}")
	public String showFormForUpdate(@PathVariable ( value = "folio") long folio, Model model) {
		
		User user = userService.getUserById(folio);
		
		model.addAttribute("user", user);
		return "update_user";
	}
	
	@GetMapping("/deleteUser/{folio}")
	public String deleteUser(@PathVariable (value = "folio") long folio) {
		
		this.userService.deleteUserById(folio);
		return "redirect:asistencia";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<User> listUser = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listUser", listUser);
		return "asistencia";
	}
}
