package com.task.demo.controller;

import com.task.demo.model.Category;
import com.task.demo.model.Product;
import com.task.demo.repository.CategoryRepository;
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
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
    Logger log = LoggerFactory.getLogger(ProductController.class);

    @GetMapping()
    public String index(Model model) {
        Logger log = LoggerFactory.getLogger(CategoryController.class);
        log.info("---Category List---");
        model.addAttribute("Categories", categoryRepository.findAll());
        log.info("All Category - " + String.valueOf(categoryRepository.findAll()));
        return "category";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String insert(RedirectAttributes rd, Model model, Category category) {
        System.out.println("insert()");
        model.addAttribute("category", categoryRepository.save(category));
        rd.addFlashAttribute("flash_message", "Inserted successfully!");
        return "redirect:/category";
    }

    @RequestMapping(value = "/delete/{catId}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(RedirectAttributes rd, @PathVariable String catId) {
        log.info("DELETING IN category  ID - " + catId);
        categoryRepository.deleteById(catId);
        rd.addFlashAttribute("flash_message", "Deleted successfully!");
        log.info("category deleted");
        return "redirect:/category";

    }


}
