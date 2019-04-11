package com.example.dailyfit.product;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ProductService {

    //    Enter here the path to the directory for save uploaded files
    private static String DIRECTORY = "";
    private ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    void addProduct(MultipartFile file, String name, double kcal,
                    double protein, double carbohydrates,
                    double fat, ProductType type) {

        Product product = new Product();
        product.setName(name);
        product.setKcal(kcal);
        product.setProtein(protein);
        product.setCarbohydrates(carbohydrates);
        product.setFat(fat);
        product.setProductType(type);

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(DIRECTORY + file.getOriginalFilename());
                Files.write(path, bytes);
                product.setImageUrl("/img/" + file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            product.setImageUrl("");
        }
        productRepository.save(product);
    }

    void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(p -> productRepository.delete(p));
    }

}
