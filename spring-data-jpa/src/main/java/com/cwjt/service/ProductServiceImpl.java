package com.cwjt.service;

import com.cwjt.entity.Product;
import com.cwjt.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    
    @Override
    public List<Product> getAllProducts() {
        logger.debug("ProductServiceImpl :: getAllProducts response ");
        return productRepository.findAll();
    }

    @Override
    public Product getSingleItem(Integer id) {
        logger.info("ProductServiceImpl :: getSingleItem byId {}",id);
        return productRepository.findById(id).get();
    }

    @Override
    public  List<Product> getByName(String name) {
        logger.info("ProductServiceImpl :: getByName {}",name);
        return productRepository.findByName(name);
    }

    public List<Product> getByProductType(String productType){
        logger.debug("ProductServiceImpl :: getByProductType -{}",productType);
        return productRepository.findByProductType(productType);
    }

    public List<Product> getProductByPrice(double price){
        logger.debug("ProductServiceImpl :: getProductByPrice -{}",price);
        return productRepository.getProductByPrice(price);
    }

    public List<Product> getItemProductTypeAndPrice(String productType,double price){
        logger.debug("ProductServiceImpl :: getItemProductTypeAndPrice - {},{}",productType,price);
        return productRepository.findByProductTypeAndPrice(productType,price);
    }
    @Override
    public Product storeProduct(Product product) {
        logger.debug("ProductServiceImpl :: saveProduct in db - {}",product.getName());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(int id,Product productRequest) {
        logger.debug("ProductServiceImpl :: updateProduct getById - {}",id);
        // first get product from db by id
        // update with new value getting from request
        Product existingProduct = productRepository.findById(id).get();
        logger.info("ProductServiceImpl :: updatedINewItem -by its Id {}",existingProduct.getName());
        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setProductType(productRequest.getProductType());
        return productRepository.save(existingProduct);
    }

    @Override
    public String deleteProduct(Integer id) {
        logger.debug("ProductServiceImpl :: deleteProduct from db byId {} ",id);
        productRepository.deleteById(id);
        return null;
    }
    //OPERATOR
/*        prefix + fieldName + operatorName
        get items IN this prices
       it will work on postman post() and pass body in [] form IN operator*/
    public List<Product> getProductByMultiplePriceValue(List<Double> price){
        return productRepository.findByPriceIn(price);
    }

    // getList of item between the range
    public List<Product> getProductWithinRange(double minPrice, double maxPrice){
        return productRepository.findByPriceBetween(minPrice,maxPrice);
    }

    public List<Product> getProductsWithHigherPrice(double price){
        return productRepository.findByPriceGreaterThan(price);
    }

    public List<Product> getProductsLessPrice(double price){
        return productRepository.findByPriceLessThan(price);
    }

    public List<Product> getProductWithLike(String name){
        return productRepository.findByNameIgnoreCaseContaining(name);
    }
        //SORTING
    public List<Product> getSortingBasedOnName(String fieldName){
        return productRepository.findAll(Sort.by(Sort.Direction.ASC,fieldName));
    }

    // PAGINATION
    public Page<Product> getProductsWithPagingRange(int offSet, int limit){
        return productRepository.findAll(PageRequest.of(offSet,limit));
    }

    // PAGINATION AND SORTING >> ONE
    public Page<Product> getProductsWithSortingAndPagination(String fieldName, int offSet, int limit){
        return  productRepository
                .findAll(
                        PageRequest.of(offSet,limit)
                            .withSort(Sort.by(fieldName)));
    }
}
