package com.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.exception.ProductException;
import com.product.model.Product;
import com.product.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service("productService")
@Transactional
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	
	public List<Product> getAllProduct(){
		return (List<Product>) productRepository.findAll();
	}
	
	public Product getById(int id) throws ProductException { 
		Product prod = productRepository.findByProductId(id);
		if(prod==null) {
			throw new ProductException("Product Not Found");
		}
		else {
			return prod;
		}
	}
	
	public void delete(int id) throws ProductException {
		Product prod = productRepository.findByProductId(id);
		if(prod==null) {
			throw new ProductException("Product Not Found");
		}
		else {
			productRepository.deleteById(id);
		}
	}
	
	public void update(int id, Product prodDetails) throws ProductException {
		Product prod = productRepository.findByProductId(id);
		if(prod==null) {
			throw new ProductException("Product Not Found");
		}
		else {
			prod.setName(prodDetails.getName());
	        prod.setCategory(prodDetails.getCategory());
	        prod.setPrice(prodDetails.getPrice());
	        prod.setDate(prodDetails.getDate());
			
			productRepository.save(prod);
		}	
	}
	
	public List<String> getCategory(){
		return productRepository.findCategory();
	}
	
	public List<Product> getTop4Product(){
		return productRepository.findTop4ByOrderByPriceDesc();
	}
	
	public List<Product> getBottom5Product(){
		return productRepository.findTop5ByOrderByPriceAsc();
	}
	
	public List<Product> getFirst10Product(){
		return productRepository.findTop10ByOrderByDateAsc();
	}
	
	public long getTotalProductCount(){
		return productRepository.count();
	}
	
	public int getDistinctCategories(){
		return productRepository.countDistinctCategories();
	}
}
