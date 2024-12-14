package com.cwjt.service;

import com.cwjt.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    public List<Product>  getAllProducts();

    public Product getSingleItem(Integer id);

    public  List<Product> getByName(String name);

    public List<Product> getByProductType(String productType);

    public List<Product> getItemProductTypeAndPrice(String productType,double price);

    public List<Product> getProductByPrice(double price);

    public Product storeProduct(Product product);

    public Product updateProduct(int id,Product product);

    public String deleteProduct(Integer id);

    public List<Product> getProductByMultiplePriceValue(List<Double> price);

    public List<Product> getProductWithinRange(double minPrice, double maxPrice);

    public List<Product> getProductsWithHigherPrice(double price);

    public List<Product> getProductsLessPrice(double price);

    public List<Product> getProductWithLike(String name);
    public List<Product> getSortingBasedOnName(String fieldName);
    public Page<Product> getProductsWithPagingRange(int offSet, int limit);
    public Page<Product> getProductsWithSortingAndPagination(String fieldName, int offSet, int limit);

}
