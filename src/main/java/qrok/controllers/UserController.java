package qrok.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import qrok.entitties.User;
import qrok.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcome(Model model) {
		return "welcome";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null) {
			model.addAttribute("error", "Username or password is incorrect.");
		}

		if (logout != null) {
			model.addAttribute("message", "Logged out successfully.");
		}

		return "login";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") @Validated User userForm, BindingResult bindingResult,
			Model model) {
		// userValidator.validate(userForm, bindingResult);
		User userExists = userService.findByUsername(userForm.getUsername());
		if(userExists!=null){
			bindingResult
			.rejectValue("username", "error.user",
					"There is already a user registered with the username provided");
		}
		
		if (bindingResult.hasErrors()) {
			return "registration";
		}

		userService.add(userForm);

		// securityService.autoLogin(userForm.getUsername(),
		// userForm.getConfirmPassword());

		return "redirect:/welcome";
	}
}
