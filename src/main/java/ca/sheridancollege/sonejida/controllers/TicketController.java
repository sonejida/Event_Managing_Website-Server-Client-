package ca.sheridancollege.sonejida.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ca.sheridancollege.sonejida.beans.Ticket;
import ca.sheridancollege.sonejida.beans.User;
import ca.sheridancollege.sonejida.repositories.TicketRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
@AllArgsConstructor

public class TicketController {
	
	@Autowired
	private TicketRepository ticketRepo;
	
	@GetMapping("/")
	public String rootToot() {
		
		return "home.html";
	}
	@GetMapping("/add")
	public String goAdd(Model model) {
		model.addAttribute("ticket", new Ticket());
		List<User> userName = ticketRepo.getAllUsernames();
		model.addAttribute("userName", userName);
		return "addTicket.html";
	}
	@PostMapping("/add")
	public String processAdd(@ModelAttribute Ticket ticket) {
		ticketRepo.addTicket(ticket);
		return "redirect:/add";  
	}
	
	@GetMapping("/view")
	public String viewTickets(Model model, Authentication authentication) {
		//model.getAttribute(null)
		model.addAttribute("currentUser", authentication.getName());
		List<GrantedAuthority> roles=(List<GrantedAuthority>) authentication.getAuthorities();
		for(GrantedAuthority role : roles) {
			model.addAttribute("role",role.toString());
		}
		model.addAttribute("mytickets", ticketRepo.getTickets2());
		return "viewTickets.html";
	}
	
	@GetMapping("/edit/{id}")
	public String editTicket(Model model, @PathVariable int id) {
		List<User> userName = ticketRepo.getAllUsernames();
		model.addAttribute("userName", userName);
		Ticket ticket = ticketRepo.getTicketById(id);
		model.addAttribute("ticket", ticket);
		
		return "editTickets.html";
	}
	
	@PostMapping("/edit")
	public String processEdit(Model model,@ModelAttribute Ticket ticket) {
		ticketRepo.editTicket(ticket);
		return "redirect:/view";  
	}
	
	@GetMapping("/delete/{id}")
	public String deleteContact(Model model, @PathVariable int id) {
		ticketRepo.deleteTicketById(id);
		return "redirect:/view";
	}
}
