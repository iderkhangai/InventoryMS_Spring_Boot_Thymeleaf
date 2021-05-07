package com.task.demo.controller;

import com.task.demo.model.Brand;
import com.task.demo.model.Product;
import com.task.demo.model.Sales;
import com.task.demo.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class DashboardController {
    Logger log = LoggerFactory.getLogger(DashboardController.class);
    @Autowired
    DashboardRepository dashboardRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    SalesRepository salesRepository;
//    @GetMapping("login")
//    public String login() {
//        log.info("***Redirecting custom login page***");
//        return "login";
//    }

    @GetMapping("/")
    public String DashboardPage(Model model) throws ParseException {
        log.info("--- Dashboard ---");
        log.info("Total Product --" + dashboardRepository.count());
        model.addAttribute("totalBrand", "Brands: " + brandRepository.count());
        model.addAttribute("totalProduct", "Products: " + productRepository.count());
        model.addAttribute("totalCategory", "Categories: " + categoryRepository.count());
        model.addAttribute("totalSupplier", "Suppliers: " + supplierRepository.count());
        log.info("Pending sales - " + salesRepository.getPendingProducts());
        model.addAttribute("sales", salesRepository.getPendingProducts());
//        List<String> dates = new ArrayList<>();
//        List<String> amounts = new ArrayList<>();
//        List<Sales> sales = salesRepository.findAll();
//        log.info("amounts- " + amounts);
//        log.info("dates- " + dates);
//        model.addAttribute("dates", dates);
//        model.addAttribute("amounts", amounts);
        return "index";
    }
}
