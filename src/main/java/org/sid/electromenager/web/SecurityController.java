package org.sid.electromenager.web;

import org.sid.electromenager.dao.ClientRepository;
import org.sid.electromenager.entities.Client;
import org.sid.electromenager.dao.NoteRepository;
import org.sid.electromenager.entities.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SecurityController {

	//clients messages saving logic
    @Autowired
    private NoteRepository noteRepository;
    
    @PostMapping("/saveNote")
    public String saveNote(Note note) {
        noteRepository.save(note);
        return "redirect:/home";
    }    
    @GetMapping("/contact")
    public String contact(Model model) {
    	model.addAttribute("note", new Note());
        return "contact";
    }
    @GetMapping("/messages")
    public String listMessages(Model model) {
        List<Note> notes = noteRepository.findAll();
        model.addAttribute("notes", notes);
        return "ListMessages";
    }
    //displaying admins page
	@GetMapping("/admin")
	public String adminPage(Authentication authentication, Model model) {
	    if (authentication != null) {
	        model.addAttribute("username", authentication.getName());
	    } else {
	        model.addAttribute("error", "You are not authenticated.");
	    }
	    return "index"; 
	}

	//displaying home page
    @GetMapping("/home")
    public String home() {
        return "home"; 
    }

    //displaying about us page
    @GetMapping("/aboutus")
    public String aboutus() {
        return "aboutus"; 
    }
    //login and logout pages 
    @GetMapping("/login")
    public String login() {
        return "login"; 
    }
    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
    @PostMapping(path ="/logout")
    public String doLogout() {
        // Handle login logic
        return "redirect:/login"; 
    }
    
}
