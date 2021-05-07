package com.task.demo.controller;

import com.task.demo.model.Category;
import com.task.demo.model.Product;
import com.task.demo.model.Sales;
import com.task.demo.repository.CategoryRepository;
import com.task.demo.repository.ProductRepository;
import com.task.demo.repository.SalesRepository;
import com.task.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sale")
public class SalesController {
    Logger log = LoggerFactory.getLogger(SalesController.class);
    @Autowired
    SalesRepository salesRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping()
    public String index(Model model) {
        Logger log = LoggerFactory.getLogger(CategoryController.class);
        log.info("---Sale List---");
        List<Sales> sales = salesRepository.findAll();
        model.addAttribute("sales", salesRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        log.info("All Sales - " + String.valueOf(salesRepository.findAll()));
        return "sales";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String insert(RedirectAttributes rd, Model model, Sales sale) {
        System.out.println("insert(Sales)");
        log.info("Input parameters - " + sale.toString());
        log.info("Products - " + sale.getProducts());
        salesRepository.save(sale);
        rd.addFlashAttribute("flash_message", "Inserted successfully!");
        return "redirect:/sale";
    }

    @RequestMapping(value = "/delete/{saleId}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(RedirectAttributes rd, @PathVariable String saleId) {
        log.info("DELETING IN Sale ID - " + saleId);
        salesRepository.deleteById(saleId);
        rd.addFlashAttribute("flash_message", "Deleted successfully!");
        log.info("sale deleted");
        return "redirect:/sale";

    }

    @GetMapping(value = "/view/{saleId}")
    public String view(Model model, @PathVariable String saleId) {
        log.info("Find Sale  with - " + saleId);
        Optional<Sales> saleData = salesRepository.findById(saleId);
        if (saleData.isPresent()) {
            Sales result = saleData.get();
            log.info("Chosen Sale - " + result);
            model.addAttribute("sale", result);
        }
        return "saleDetail";
    }


}
