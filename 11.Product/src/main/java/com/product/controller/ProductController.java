package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.exception.ProductException;
import com.product.model.Product;
import com.product.service.ProductService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<String> addProduct(@RequestBody Product product){
		productService.addProduct(product);
		String msg="Product Addedd Successfully";
		return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
	}
	@GetMapping
	public ResponseEntity<List<Product>> getAllProduct(){
		List<Product> result = productService.getAllProduct();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) throws ProductException{
		Product result = productService.getById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable int id) throws ProductException{
		productService.delete(id);
		String msg = "Product Deleted Successfully";
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestBody Product product) throws ProductException{
		productService.update(id, product);
		String msg = "Product Updated Successfully";
		return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value="/category")
	public ResponseEntity<List<String>> getCategory(){
		List<String> result = productService.getCategory();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping(value="/top/4")
	public ResponseEntity<List<Product>> getTop4Product(){
		List<Product> result = productService.getTop4Product();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping(value="/bottom/5")
	public ResponseEntity<List<Product>> getBottom5Product(){
		List<Product> result = productService.getBottom5Product();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping(value="/first/10")
	public ResponseEntity<List<Product>> getFirst10Product(){
		List<Product> result = productService.getFirst10Product();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping(value="/count")
	public ResponseEntity<Long> getProductCount(){
		long result = productService.getTotalProductCount();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping(value="/category/count")
	public ResponseEntity<Integer> getDistinctCategory(){
		int result = productService.getDistinctCategories();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	
	
}
