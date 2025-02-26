package org.jspiders.springdatasecurityproductapp.controller;

import org.jspiders.springdatasecurityproductapp.model.Product;
import org.jspiders.springdatasecurityproductapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductWebController {
    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String getAllProducts(Model model)
    {
        model.addAttribute("products",service.getAllProducts());
        return "products";
    }

    @GetMapping("/get-product-form")
    public String getProductForm(Model model)
    {
        model.addAttribute("product",new Product());
        return "productform";
    }

    @PostMapping("/save-product")
    public String saveProduct(Product product){
        service.saveProduct(product);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String getUpdateForm(Model model, @PathVariable int id)
    {
        model.addAttribute("updateproduct",service.getProductById(id));
        return "updateproduct";
    }

    @PostMapping("updateproduct")
    public String updateProduct(Product product)
    {
        service.saveProduct(product);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id)
    {
        service.removeProductById(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchProduct(Model model, @RequestParam(name = "name") String name)
    {
        model.addAttribute("products",service.searchProduct(name));
        return "/products";
    }
}
