package com.cwjt.repository;

import com.cwjt.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductRepository extends RevisionRepository<Product,Integer,Integer>, JpaRepository<Product, Integer> {
    List<Product> findByName(String name);

    List<Product> findByProductType(String productType);

    List<Product> findByProductTypeAndPrice(String productType, double price);

//    List<Product> getProductByPrice(double price);// using bellow @query
//    @Query(value = "SELECT * FROM product where price = ?1",nativeQuery = true)
    @Query(value = "SELECT * FROM Product where price = ?1",nativeQuery = true)// position based parameter------- Always use entity name not tblName
//    @Query("FROM product p where p.price = :price")// named parameter based index
    List<Product> getProductByPrice(double price);

    // prefix + fieldName + operatorName
    List<Product> findByPriceIn(List<Double> price);// it will work on postman post() and pass body in [] form IN operator

    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    List<Product> findByPriceGreaterThan(double price);

    List<Product> findByPriceLessThan(double price);

    List<Product> findByNameIgnoreCaseContaining(String name);
}
