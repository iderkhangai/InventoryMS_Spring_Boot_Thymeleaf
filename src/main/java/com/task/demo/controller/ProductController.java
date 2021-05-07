package com.task.demo.controller;

import com.task.demo.model.Product;
import com.task.demo.repository.BrandRepository;
import com.task.demo.repository.CategoryRepository;
import com.task.demo.repository.ProductRepository;
import com.task.demo.repository.SupplierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final String UPLOAD_DIR = "./uploads/";
    Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private BrandRepository brandRepository;

    @GetMapping()
    public String index(Model model) {
        log.info("---Product List---");
        productRepository.updateId("HP T110");
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("suppliers", supplierRepository.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        log.info("All product - " + String.valueOf(productRepository.findAll()));
        return "product";

    }

    @PostMapping(value = "/save")
    public String insert(@ModelAttribute("product") Product product, RedirectAttributes rd,
                         @RequestParam("file") MultipartFile file) throws IOException {
        log.info("Save() duudagdsan");
        if (file.isEmpty()) {
            rd.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/";
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        log.info("File name - " + fileName);
        product.setImage(fileName);
        Product savedProduct = productRepository.insert(product);
        log.info("Response repo - " + savedProduct.getImage());

        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Path filePath = path.resolve(fileName);
            log.info("image path - " + filePath.toString());
            log.info("Absolute path - " + filePath.toFile().getAbsolutePath());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return success response
        rd.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');
        return "redirect:/product";
    }

    @PostMapping(value = "/edit")
    public String update(@ModelAttribute("product") Product product, RedirectAttributes rd,
                         @RequestParam("file") MultipartFile file) throws IOException {
        log.info("Update() duudagdsan - " + product.getId());
        log.info("File - " + file);
        if (file.isEmpty()) {
            rd.addFlashAttribute("message", "Please select a image to upload.");
            return "redirect:/product";
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        log.info("File name - " + fileName);
        product.setImage(fileName);
        productRepository.save(product);
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Path filePath = path.resolve(fileName);
            log.info("image path - " + filePath.toString());
            log.info("Absolute path - " + filePath.toFile().getAbsolutePath());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return success response
        rd.addFlashAttribute("message", "Image successfully uploaded " + fileName + '!');
        return "redirect:/product";
    }


    @RequestMapping(value = "/delete/{productId}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(RedirectAttributes rd, @PathVariable String productId) {
        log.info("DELETING IN PRODUCT ID - " + productId);
        productRepository.deleteById(productId);
        rd.addFlashAttribute("flash_message", "Deleted successfully!");
        log.info("Product deleted");
        return "redirect:/product";

    }

    @GetMapping(value = "/view/{productId}")
    public String view(Model model, RedirectAttributes rd, @PathVariable String productId) {
        log.info("View product - " + productId);
        Optional<Product> productData = productRepository.findById(productId);
        if (productData.isPresent()) {
            Product result = productData.get();
            log.info("Chosen product - " + result);
            model.addAttribute("product", result);
        }
        return "productDetail";

    }

    @GetMapping(value = "/find/{productId}")
    @ResponseBody
    public Optional<Product> view(@PathVariable String productId) {
        log.info("Find product ID with - " + productId);
        return productRepository.findById(productId);
    }


}
