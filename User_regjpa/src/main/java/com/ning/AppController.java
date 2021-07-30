package com.ning;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@Autowired
	private UserService service; 
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<ResUser> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		ResUser user = new ResUser();
		model.addAttribute("user", user);
		
		return "new_user";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") ResUser user) {
		service.save(user);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_user");
		ResUser user = service.get(id);
		mav.addObject("user", user);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}
}
