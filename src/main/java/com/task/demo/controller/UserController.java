package com.task.demo.controller;

import com.task.demo.model.Category;
import com.task.demo.model.User;
import com.task.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @GetMapping()
    public String index(Model model) {
        log.info("user main");
        model.addAttribute("users", userRepository.findAll());
        log.info("All Users - " + String.valueOf(userRepository.findAll()));
        return "user";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String insert(RedirectAttributes rd, Model model, User user) {
        System.out.println("insert(user)");
        model.addAttribute("user", userRepository.save(user));
        rd.addFlashAttribute("flash_message", "Inserted successfully!");
        return "redirect:/user";
    }

    @RequestMapping(value = "/delete/{userId}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(RedirectAttributes rd, @PathVariable String userId) {
        log.info("DELETING IN category  ID - " + userId);
        userRepository.deleteById(userId);
        rd.addFlashAttribute("flash_message", "User Deleted successfully!");
        log.info("User deleted");
        return "redirect:/user";

    }
}

