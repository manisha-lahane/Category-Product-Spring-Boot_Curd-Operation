package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<Page<Category>> getAllCategories(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		Page<Category> categories = categoryService.getAllCategories(page, size);
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		Category savedCategory = categoryService.saveCategory(category);
		return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
		return categoryService.getCategoryById(id).map(category -> new ResponseEntity<>(category, HttpStatus.OK))
				.orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
		if (!categoryService.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		category.setId(id);
		Category updatedCategory = categoryService.saveCategory(category);
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
		try {
			categoryService.deleteCategory(id);
			return new ResponseEntity<>("Category deleted successfully.", HttpStatus.NO_CONTENT);
		} catch (IllegalStateException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
