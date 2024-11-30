package com.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.product.model.Product;

public interface ProductRepository extends CrudRepository<Product,Integer>{
	
	public Product findByProductId(int id);
	
	@Query("select p.category from Product p")
	public List<String> findCategory();
	
	public List<Product> findTop4ByOrderByPriceDesc();
	public List<Product> findTop5ByOrderByPriceAsc();
    public List<Product> findTop10ByOrderByDateAsc();
    
    // Method to count total products
    public long count();

    // Custom query to count distinct categories
    @Query("SELECT COUNT(DISTINCT p.category) FROM Product p")
    public int countDistinctCategories();
}
