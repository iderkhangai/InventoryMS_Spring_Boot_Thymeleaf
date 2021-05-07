package com.task.demo.controller;

import com.task.demo.model.Brand;
import com.task.demo.model.Supplier;
import com.task.demo.repository.BrandRepository;
import com.task.demo.repository.SupplierRepository;
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
@RequestMapping("/brand")
public class BrandController {
    Logger log = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    BrandRepository brandRepository;

    @GetMapping()
    public String index(Model model) {
        log.info("brand main");
        model.addAttribute("brands", brandRepository.findAll());
        return "brand";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String insert(RedirectAttributes rd, Model model, Brand brand) {
        System.out.println("insert(New Brand)");
        model.addAttribute("brands", brandRepository.save(brand));
        rd.addFlashAttribute("flash_message", "Inserted successfully!");
        return "redirect:/brand";
    }

    @RequestMapping(value = "/delete/{brandId}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(RedirectAttributes rd, @PathVariable String brandId) {
        log.info("DELETING IN Brand ID - " + brandId);
        brandRepository.deleteById(brandId);
        rd.addFlashAttribute("flash_message", "Deleted successfully!");
        log.info("brand deleted");
        return "redirect:/brand";

    }
}
