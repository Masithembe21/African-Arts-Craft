package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Categories;
import za.ac.cput.service.CategoriesService;

import java.util.List;

/**
 * CategoriesController.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 30-Jul-24
 */

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
// Allow CORS for this controller this is optional allows requests from port 3000
public class CategoriesController {
    private final CategoriesService categoriesService;

    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    /**
     * Endpoint: http://localhost:8080/store/categories/create
     */
    @PostMapping("/create")
    public ResponseEntity<Categories> createCategory(@RequestBody Categories category) {
        Categories createdCategory = categoriesService.create(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    /**
     * Endpoint: http://localhost:8080/store/categories/get/{id}
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable Long id) {
        Categories category = categoriesService.read(id);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint: http://localhost:8080/store/categories/update
     */
    @PutMapping("/update")
    public ResponseEntity<Categories> updateCategory(@RequestBody Categories category) {
        Categories updatedCategory = categoriesService.update(category);
        if (updatedCategory != null) {
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint: http://localhost:8080/store/categories/getAll
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Categories>> getAllCategories() {
        List<Categories> categoriesList = categoriesService.findAll();
        return new ResponseEntity<>(categoriesList, HttpStatus.OK);
    }
}
