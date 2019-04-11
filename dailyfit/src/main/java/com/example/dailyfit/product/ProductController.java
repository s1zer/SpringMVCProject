package com.example.dailyfit.product;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private ProductRepository productRepository;
    private ProductService productService;

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public String goProducts(@RequestParam(required = false) String findName, Model model) {
        if (findName != null && findName.length() > 0) {
            List<Product> products = productRepository.findByNameContainingIgnoreCase(findName);
            model.addAttribute("products", products);
            return "products";
        } else {
            System.out.println("else");
            List<Product> products = productRepository.findAll();
            model.addAttribute("products", products);
            return "products";
        }
    }

    @GetMapping("/product/{name}")
    public String getProductDetails(@PathVariable String name, Model model) {
        Optional<Product> product = productRepository.findByNameIgnoreCase(name.replaceAll("-", " "));
        String defaultImageUrl = "/img/defaultImage.png";
        product.stream().forEach(p -> System.out.println(p.getImageUrl()));
        product.stream().filter(p -> p.getImageUrl().isEmpty())
                .forEach(p -> p.setImageUrl(defaultImageUrl));
        product.ifPresent(p -> model.addAttribute("product", p));
        return product.map(p -> "product").orElse("redirect:/");
    }

    @PostMapping("/product/delete")
    public String removeProductFromDatabase(@RequestParam Long idProductToDelete) {
        productService.deleteProduct(idProductToDelete);
        return "redirect:/products";
    }

    @PostMapping("/product/add")
    public String addNewProduct(@RequestParam MultipartFile file, String name, double kcal,
                                double protein, double carbohydrates,
                                double fat, ProductType type) {
        productService.addProduct(file, name, kcal, protein, carbohydrates, fat, type);
        return "redirect:/products";
    }
}
