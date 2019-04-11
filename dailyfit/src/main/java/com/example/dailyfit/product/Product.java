package com.example.dailyfit.product;


import javax.persistence.*;
import java.util.Objects;

@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double kcal;
    private double protein;
    private double carbohydrates;
    private double fat;
    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    private ProductType productType;
    @Column(nullable = true)
    private String imageUrl;

    public Product(String name, double kcal, double protein,
                   double carbohydrates, double fat, ProductType productType, String imageUrl) {
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.productType = productType;
        this.imageUrl = imageUrl;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.getKcal(), getKcal()) == 0 &&
                Double.compare(product.getProtein(), getProtein()) == 0 &&
                Double.compare(product.getCarbohydrates(), getCarbohydrates()) == 0 &&
                Double.compare(product.getFat(), getFat()) == 0 &&
                Objects.equals(getId(), product.getId()) &&
                Objects.equals(getName(), product.getName()) &&
                getProductType() == product.getProductType() &&
                Objects.equals(getImageUrl(), product.getImageUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getKcal(), getProtein(), getCarbohydrates(), getFat(), getProductType(), getImageUrl());
    }
}
