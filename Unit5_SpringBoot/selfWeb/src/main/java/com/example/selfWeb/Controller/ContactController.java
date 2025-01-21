package com.example.selfWeb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.selfWeb.Model.ContactMsg;
import com.example.selfWeb.Service.ContactMsgService;

@Controller
public class ContactController {
    @Autowired
    ContactMsgService contactService;

    @GetMapping("/contact")
    public String contact( Model model ) {
        model.addAttribute("telefon", "+34 644048205");
        model.addAttribute("email", "veronicasanchezjusticiainfor@gmail.com");
        model.addAttribute("linkedin", 
                "https://www.linkedin.com/in/ver%C3%B3nica-s%C3%A1nchez-justicia-825a7421a/");
        model.addAttribute("github", "https://github.com/VeronicaSj");
        
        return "contact";
    }

    @GetMapping("/contactMsgList")
    public String viewBlogPage(Model model) {
        model.addAttribute("msgs", contactService.getAllMsg());
        return "contactMsgList";
    }
    
    @PostMapping("/contactPost")
    public String savePost(@RequestParam String email, @RequestParam String subject, @RequestParam String msg) {
        ContactMsg contactMsg = new ContactMsg(email, subject, msg);
        contactService.createMsg(contactMsg);
        return "contact";
    }
}
