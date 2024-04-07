package ca.sheridancollege.sonejida.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ca.sheridancollege.sonejida.repositories.SecurityRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Controller
public class SecurityController {
	@Autowired
	private SecurityRepository secRepo;
	
		@GetMapping("/login")
		public String login() {
			return "login.html";
			}
		@GetMapping("/accessD")
		public String AccesDenied() {
			return "accessDenied.html";
			
	}
		@GetMapping("/register")
		public String register() {
			return "registration.html";
			}
	@PostMapping("/register")
		public String doRegistration(@RequestParam String username,
				@RequestParam String password){
		secRepo.register(username,password);
		secRepo.assignRoles(secRepo.findUserByUsername(username).getUserId(), 2);
		
		
		return "redirect:/login";
		}
}
