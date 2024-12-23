package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository; // Injecting the Product repository to handle related products

	public Page<Category> getAllCategories(int page, int size) {
		return categoryRepository.findAll(PageRequest.of(page, size));
	}

	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	public Optional<Category> getCategoryById(Long id) {
		return categoryRepository.findById(id);
	}

	@Transactional
	public void deleteCategory(Long id) {

		if (productRepository.existsByCategoryId(id)) {
			throw new IllegalStateException("Cannot delete category because it is associated with products.");
		}

		categoryRepository.deleteById(id);
	}

	public boolean existsById(Long id) {
		return categoryRepository.existsById(id);
	}
}
