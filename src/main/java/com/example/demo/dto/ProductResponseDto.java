package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
	private Long id;
	private String name;
	private double price;
	private String categoryName;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setCategoryId(Long id2) {
		// TODO Auto-generated method stub

	}

}
