package com.laptopecom.ecom.controller;

import com.laptopecom.ecom.dto.ProductDTO;
import com.laptopecom.ecom.model.Category;
import com.laptopecom.ecom.model.Manufacturer;
import com.laptopecom.ecom.model.Product;
import com.laptopecom.ecom.service.CategoryService;
import com.laptopecom.ecom.service.ManufacturerService;
import com.laptopecom.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImage";
    @Autowired
    CategoryService categoryService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    ProductService productService;

    @GetMapping("admin")
    public String index(){
        return "admin";
    }

    //Category
    @GetMapping("admin/categories")
    public String getAllCat(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "category-list";
    }

    @GetMapping("admin/categories/add")
    public String getCatAdd(Model model) {
        model.addAttribute("category", new Category());
        return "BE-category-edit";
    }

    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }//form add new category > do add

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable int id){
        categoryService.deleteCategoryById(id);
        return "redirect:/admin/categories";
    }//delete 1 category

    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable int id, Model model){
        Optional<Category> category = categoryService.checkCategoryById(id);
        if(category.isPresent()){
            model.addAttribute("category", category.get());
            return "BE-category-edit";
        }else {
            return "404";
        }
    }//form edit category, fill old data into form



    //Manufacturer
    @GetMapping("admin/manufs")
    public String getAllManuf(Model model){
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturer());
        return "be-manuf-list";
    }

    @GetMapping("admin/manufs/add")
    public String getManufAdd(Model model) {
        model.addAttribute("manufacturer", new Manufacturer());
        return "be-manuf-edit";
    }

    @PostMapping("/admin/manufs/add")
    public String postManufAdd(@ModelAttribute("manufacturer") Manufacturer manufacturer){
        manufacturerService.addManufacturer(manufacturer);
        return "redirect:/admin/manufs";
    }//form add new manufacturer > do add

    @GetMapping("/admin/manufs/delete/{id}")
    public String deleteManuf(@PathVariable int id){
        manufacturerService.deleteManufacturerById(id);
        return "redirect:/admin/manufs";
    }//delete 1 manufacturer

    @GetMapping("/admin/manufs/update/{id}")
    public String updateManuf(@PathVariable int id, Model model){
        Optional<Manufacturer> manufacturer = manufacturerService.checkManufacturerById(id);
        if(manufacturer.isPresent()){
            model.addAttribute("manufacturer", manufacturer.get());
            return "BE-manuf-edit";
        }else {
            return "404";
        }
    }//form edit manufacturer, fill old data into form



    // Product
    @GetMapping("admin/products")
    public String getAllProduct(Model model){
        List<Product> product = productService.getAllProduct();
        model.addAttribute("products", productService.getAllProduct());
        return "be-product-list";
    }

    @GetMapping("/admin/products/add")
    public String getProAdd(Model model){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturer());
        return "be-product-edit";
    }// form add new product

    @PostMapping("/admin/products/add")
    public String postProAdd(@ModelAttribute("productDTO") ProductDTO productDTO,
                             @RequestParam("productImage") MultipartFile fileProductImage,
                             @RequestParam("imgName") String imgName) throws IOException {
        //convert dto > entity
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.checkCategoryById(productDTO.getCategoryId()).get());
        product.setManufacturer(manufacturerService.checkManufacturerById(productDTO.getManufacturerId()).get());
        product.setPrice(productDTO.getPrice());
        product.setCpu(productDTO.getCpu());
        product.setBattery(productDTO.getBattery());
        product.setHardDrive(productDTO.getHardDrive());
        product.setQuantity(productDTO.getQuantity());
        product.setRam(productDTO.getRam());
        product.setScreen(productDTO.getScreen());
        product.setWarranty(productDTO.getWarranty());
        product.setOperatingSystem(productDTO.getOperatingSystem());
        product.setDescription(productDTO.getDescription());
        String imageUUID;
        if(!fileProductImage.isEmpty()){
            imageUUID = fileProductImage.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, fileProductImage.getBytes());
        }else {
            imageUUID = imgName;
        }//save image
        product.setImageName(imageUUID);

        productService.addProduct(product);
        return "redirect:/admin/products";
    }//form add new product > do add



    @GetMapping("/admin/products/delete/{id}")
    public String deletePro(@PathVariable long id){
        productService.deleteProductByID(id);
        return "redirect:/admin/products";
    }//delete 1 product

    @GetMapping("/admin/products/update/{id}")
    public String updatePro(@PathVariable long id, Model model){
        Optional<Product> opProduct = productService.getProductByID(id);
        if (opProduct.isPresent()){
            Product product = opProduct.get();
            //convert entity > dto
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setCategoryId(product.getCategory().getId());
            productDTO.setManufacturerId(product.getManufacturer().getId());
            productDTO.setCpu(product.getCpu());
            productDTO.setBattery(product.getBattery());
            productDTO.setHardDrive(product.getHardDrive());
            productDTO.setPrice(product.getPrice());
            productDTO.setQuantity(product.getQuantity());
            productDTO.setRam(product.getRam());
            productDTO.setScreen(product.getScreen());
            productDTO.setWarranty(product.getWarranty());
            productDTO.setOperatingSystem(product.getOperatingSystem());
            productDTO.setDescription(product.getDescription());
            productDTO.setImageName(product.getImageName());

            model.addAttribute("productDTO", productDTO);
            model.addAttribute("categories", categoryService.getAllCategory());
            model.addAttribute("manufacturers", manufacturerService.getAllManufacturer());
            return "be-product-edit";
        }else {
            return "404";
        }

    }//form edit product, fill old data into form
}
