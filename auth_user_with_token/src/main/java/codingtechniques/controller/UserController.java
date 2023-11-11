package codingtechniques.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import codingtechniques.model.Otp;
import codingtechniques.model.User;
import codingtechniques.repository.UserRepository;
import codingtechniques.service.UserService;

@Controller
public class UserController {
    
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/registration")
	public String getRegistrationPage (@ModelAttribute("user") User user) {
		return "registrationForm";
	}
	
	@GetMapping("/user-authentication")
	public String getAuthPage (@ModelAttribute("user") User user) {
		return "auth";
	}
	
	
	@GetMapping("/otp-validation")
	public String validationPage (@ModelAttribute("otp") Otp otp) {
		return "validationPage";
	}
	
	@GetMapping("/home")
	public String home () {
		return "home";
	}
	
	@PostMapping("/otp-validation")
	public String validateUser (@ModelAttribute("otp") Otp otp, Model model) {
		
		if (userService.checkOtpValidity(otp) == false) {
			model.addAttribute("message", "invalid OTP");
			return "validationPage";
		}
		return "redirect:/home";
	}
	
	@PostMapping("/user-authentication") 
	public String authencateUser(@ModelAttribute("user") User user, Model model) {
		return userService.authenticateUser(user, model);
	}
	
	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user") User user, Model model) {
		Optional<User> findUser = userRepository.findUserByEmail(user.getEmail());
		if (findUser.isPresent()) {
			model.addAttribute("message", "User with this email exist");
			return "registrationForm";
		}
		
		userService.saveUser(user);
		model.addAttribute("message", "Save Successfully");
		return "registrationForm";
	}
	
	
}
