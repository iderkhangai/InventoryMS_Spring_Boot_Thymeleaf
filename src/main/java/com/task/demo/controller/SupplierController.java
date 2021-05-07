package com.task.demo.controller;

import com.task.demo.model.Product;
import com.task.demo.model.Supplier;
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
@RequestMapping("/supplier")
public class SupplierController {
    Logger log = LoggerFactory.getLogger(SupplierController.class);

    @Autowired
    SupplierRepository supplierRepository;

    @GetMapping()
    public String index(Model model) {
        log.info("supplier main");
        model.addAttribute("suppliers", supplierRepository.findAll());
        return "supplier";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String insert(RedirectAttributes rd, Model model, Supplier supplier) {
        System.out.println("insert()");
        model.addAttribute("products", supplierRepository.save(supplier));
        rd.addFlashAttribute("flash_message", "Inserted successfully!");
        return "redirect:/supplier";
    }

    @RequestMapping(value = "/delete/{supplierId}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(RedirectAttributes rd, @PathVariable String supplierId) {
        log.info("DELETING IN Supplier ID - " + supplierId);
        supplierRepository.deleteById(supplierId);
        rd.addFlashAttribute("flash_message", "Deleted successfully!");
        log.info("Supplier deleted");
        return "redirect:/supplier";

    }
}

