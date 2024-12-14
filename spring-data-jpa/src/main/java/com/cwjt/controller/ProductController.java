package com.cwjt.controller;

import com.cwjt.entity.Product;
import com.cwjt.service.ProductService;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable int id){
        logger.info("ProductController :: getSingleProduct by id {}",id);
        return productService.getSingleItem(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProduct(){
        logger.info("ProductController :: getAllProducts ");
        List<Product> listResponse = productService.getAllProducts();
        logger.info("ProductController :: getAllProduct {}",listResponse);
        return new ResponseEntity<>(listResponse, HttpStatus.OK);
    }
    @GetMapping("/byName/{name}")
    public ResponseEntity<List<Product>> findByName(@PathVariable String name){
        logger.info("ProductController :: findByName {}",name);
        List<Product> responseBYName = productService.getByName(name);
        logger.info("ProductController :: findByName response byName  {}",name);
        return  new ResponseEntity<>(responseBYName,HttpStatus.OK);
    }

    @GetMapping("/byPrice/{price}")
    public ResponseEntity<List<Product>> getProductByPrice(@PathVariable double price){
        logger.info("ProductController :: getProductByPrice on price {}",price);
        List<Product> listOfProduct = productService.getProductByPrice(price);
        logger.info("ProductController :: getProductByPrice ByPrice{}",listOfProduct.stream().toList());
        return new ResponseEntity<>(listOfProduct,HttpStatus.OK);
    }

    @GetMapping("/byType/{productType}")
    public ResponseEntity<List<Product>> getByProductTypes(@PathVariable String productType){
        logger.info("ProductController :: getProductTypes to db  {}",productType);
        List<Product> listOfProductType = productService.getByProductType(productType);
        logger.info("ProductController :: getByProductTypes response from db{}",listOfProductType.stream().findFirst());
        return new ResponseEntity<>(listOfProductType,HttpStatus.OK);
    }


    @GetMapping("/productType/{productType}/price/{price}")
    public ResponseEntity<List<Product>> getByProductTypesAndPrice(@PathVariable String productType,@PathVariable double price){
        logger.debug("ProductController :: getByProductTypes request to db {},{}",productType,price);
        List<Product> listOfProductType = productService.getItemProductTypeAndPrice(productType,price);
        logger.info("ProductController :: getByProductTypeAndPrice  response from DB PRODUCTS ; {}",listOfProductType.stream().count());
        return new ResponseEntity<>(listOfProductType,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> addProducts(@RequestBody Product product){
        logger.debug("ProductController :: addProducts request to db save new object ");
        Product saveProduct = productService.storeProduct(product);
        logger.info("ProductController :: addProduct new added item response from db {}",saveProduct.getName());
        return new ResponseEntity<>(saveProduct,HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateItem(@PathVariable Integer id,@RequestBody Product product){
        logger.debug("ProductController :: updateItem request to db {}",id);
        Product updateProduct = productService.updateProduct(id,product);
        logger.debug("ProductController :: updateItem response from db updatedItem {}",updateProduct.getName());
        return new ResponseEntity<>(updateProduct,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id){
        logger.debug("ProductController :: deleteProduct request to removeFroDb {}",id);
        String deleteProduct = productService.deleteProduct(id);
        logger.debug("ProductController :: deleteProduct RESPONSE removed ITEM FROM Db {}",id);
        return "Item successfully deleted";
    }

    // ----OPERATOR

    @PostMapping("/search")// it will work on postman post() and pass body in [] form IN operator
    public List<Product> getProductByMultiplePriceValue(@RequestBody List<Double> prices){
        return productService.getProductByMultiplePriceValue(prices);
    }

    // getList of item between the range
    @GetMapping("/min/{minPrice}/max/{maxPrice}")
    public List<Product> getProductWithinRange(@PathVariable double minPrice,@PathVariable double maxPrice){
        return productService.getProductWithinRange(minPrice, maxPrice);
    }

    @GetMapping("/high/{price}")
    public List<Product> getProductsWithHigherPrice(@PathVariable double price){
        return productService.getProductsWithHigherPrice(price);
    }

    @GetMapping("/less/{price}")
    public List<Product> getProductsLessPrice(@PathVariable double price){
        return productService.getProductsLessPrice(price);
    }

    @GetMapping("/like/{name}")
    public List<Product> getProductWithLike(@PathVariable String name){
        return productService.getProductWithLike(name);
    }

        // SORTING
    @GetMapping("/sort/{fieldName}")
    public ResponseEntity<List<Product>> getProductsWithSorting(@PathVariable String fieldName){
        logger.debug("ProductController :: getProductWithSorting request to db for sorting");
        List<Product> responseWithSorted = productService.getSortingBasedOnName(fieldName);
        logger.debug("ProductController :: getProductWithSorting response from DB sorted items BasedOnNames : {}",responseWithSorted.stream().toList());
        return new ResponseEntity<>(responseWithSorted,HttpStatus.OK);
    }
        // PAGINATION
    @GetMapping("/paging/{offSet}/{limit}")
    public ResponseEntity<Page<Product>> getProductsWithPagination(@PathVariable int offSet,@PathVariable int limit){
        logger.debug("ProductController :: getProductWithPagination() -- request to db ");
        Page<Product> responseOfPaging = productService.getProductsWithPagingRange(offSet, limit);
        logger.debug("ProductController :: getProductWithPagination() Response from DB for paging here {}",responseOfPaging.stream().toList());
        return  new ResponseEntity<>(responseOfPaging,HttpStatus.OK);
    }
        //PAGINATION AND SORTING
    @GetMapping("/sortWithPage/{fieldName}/{offSet}/{limit}")
    public ResponseEntity<Page<Product>> getProductWithSortingAndPagination(@PathVariable String fieldName,@PathVariable int offSet,@PathVariable int limit){
        logger.debug("ProductController :: getProductWithSortAndPaging() request to db {}",fieldName);
        Page<Product> responseSortAndPaging = productService.getProductsWithSortingAndPagination(fieldName, offSet, limit);
        logger.debug("ProductController :: getProductWithSortAndPaging() response from db {}",fieldName);
        return new ResponseEntity<>(responseSortAndPaging,HttpStatus.OK);
    }
}
