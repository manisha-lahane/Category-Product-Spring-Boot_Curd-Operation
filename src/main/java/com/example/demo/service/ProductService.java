package com.example.demo.service;

import com.example.demo.dto.ProductResponseDto;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryService categoryService;

	public Page<Product> getAllProducts(int page, int size) {
		return productRepository.findAll(PageRequest.of(page, size));
	}

	public ProductResponseDto getProductById(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
		ProductResponseDto response = new ProductResponseDto();
		response.setId(product.getId());
		response.setName(product.getName());
		response.setPrice(product.getPrice());
		response.setCategoryId(product.getCategory().getId());
		response.setCategoryName(product.getCategory().getName());
		return response;
	}

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
