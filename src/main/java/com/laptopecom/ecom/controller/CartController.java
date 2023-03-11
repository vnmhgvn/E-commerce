package com.laptopecom.ecom.controller;

import com.laptopecom.ecom.global.GlobalData;
import com.laptopecom.ecom.model.Product;
import com.laptopecom.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    ProductService productService;
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id){
        GlobalData.cart.add(productService.getProductByID(id).get());
        return "redirect:/store";
    }

    @GetMapping("/checkout")
    public String cartGet(Model model){
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("carts",GlobalData.cart);
        return "fe_checkout";
    }

    @GetMapping("/cart/delete/{id}")
    public String cartDelete(@PathVariable int id){
        GlobalData.cart.remove(id);
        return "redirect:/store";
    }

}
