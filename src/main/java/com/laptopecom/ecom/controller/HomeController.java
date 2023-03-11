package com.laptopecom.ecom.controller;

import com.laptopecom.ecom.global.GlobalData;
import com.laptopecom.ecom.model.Product;
import com.laptopecom.ecom.service.CategoryService;
import com.laptopecom.ecom.service.ManufacturerService;
import com.laptopecom.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    ProductService productService;



    @GetMapping({"/", "/store"})
    public String getStore(Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("manufacturers",manufacturerService.getAllManufacturer());
        model.addAttribute("products",productService.getAllProduct());
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("carts",GlobalData.cart);
        return "fe_store";
    }

    @GetMapping("/store/category/{id}")
    public String storeByCategory(Model model, @PathVariable int id){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("manufacturers",manufacturerService.getAllManufacturer());
        model.addAttribute("products",productService.getAllProductByCategoryID(id));
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("carts",GlobalData.cart);
        return "fe_store";
    }

    @GetMapping("/store/manufacturer/{id}")
    public String storeByManufacturer(Model model, @PathVariable int id){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("manufacturers",manufacturerService.getAllManufacturer());
        model.addAttribute("products",productService.getAllProductByManufacturerID(id));
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("carts",GlobalData.cart);
        return "fe_store";
    }

    @GetMapping("/store/viewproduct/{id}")
    public String viewProduct(Model model,@PathVariable Long id){
        model.addAttribute("product",productService.getProductByID(id).get());
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("carts",GlobalData.cart);
        return "fe_product";
    }
}
