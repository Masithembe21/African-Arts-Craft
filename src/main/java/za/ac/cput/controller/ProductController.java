package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.Products;
import za.ac.cput.service.IProductService;
import za.ac.cput.service.ProductService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * ProductController.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 26-Jul-24
 */

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    /**
     * Endpoint: http://localhost:8080/store/product/create
     */
    @PostMapping("/create")
    public ResponseEntity<Products> createProduct(@RequestBody Products product) {
        System.out.println("Received product: " + product);
        try {
            Products createdProduct = productService.create(product);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Endpoint: http://localhost:8080/store/product/getid/{id}
     */
    @GetMapping("/getid/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        Products product = productService.read(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint: http://localhost:8080/store/product/update/{id}
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable Long id, @RequestBody Products product) {
        Products existingProduct = productService.read(id);
        if (existingProduct != null) {
            //update the fields that can be changed
            Products updatedProduct = new Products.Builder()
                    .copy(existingProduct)
                    .setName(product.getName()) //update fields
                    .setDescription(product.getDescription())
                    .setPrice(product.getPrice())
                    .setStockQuantity(product.getStockQuantity())
                    .setCategoryId(product.getCategoryId())
                    .setCreatedAt(product.getCreatedAt())
                    .setUpdatedAt(product.getUpdatedAt())
                    .setImagePath(product.getImagePath())
                    .build();
            //product.setProductId(id); // Ensure we are updating the correct product
            Products updatedProductResponse = productService.update(updatedProduct);
            return new ResponseEntity<>(updatedProductResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint: http://localhost:8080/store/product/delete/{id}
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Products existingProduct = productService.read(id);
        if (existingProduct != null) {
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint: http://localhost:8080/store/product/getAll
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Endpoint: http://localhost:8080/store/product/name/{name}
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Products>> getProductsByName(@PathVariable String name) {
        List<Products> products = productService.findByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Endpoint: http://localhost:8080/store/product/category/{categoryId}
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Products>> getProductsByCategoryId(@PathVariable long categoryId) {
        List<Products> products = productService.findByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Endpoint: http://localhost:8080/store/product/price
     */
    @GetMapping("/price")
    public ResponseEntity<List<Products>> getProductsByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        List<Products> products = productService.findByPriceBetween(minPrice, maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Endpoint: http://localhost:8080/store/product/stock
     */
    @GetMapping("/stock")
    public ResponseEntity<List<Products>> getProductsByStockQuantityGreaterThan(@RequestParam int stockQuantity) {
        List<Products> products = productService.findByStockQuantityGreaterThan(stockQuantity);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Endpoint: http://localhost:8080/store/product/created-after
     */
    @GetMapping("/created-after")
    public ResponseEntity<List<Products>> getProductsCreatedAfter(@RequestParam LocalDate createdAt) {
        List<Products> products = productService.findByCreatedAtAfter(createdAt);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Endpoint: http://localhost:8080/store/product/updated-before
     */
    @GetMapping("/updated-before")
    public ResponseEntity<List<Products>> getProductsUpdatedBefore(@RequestParam LocalDate updatedAt) {
        List<Products> products = productService.findByUpdatedAtBefore(updatedAt);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
