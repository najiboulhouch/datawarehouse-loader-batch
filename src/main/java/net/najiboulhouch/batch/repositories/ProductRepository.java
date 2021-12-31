package net.najiboulhouch.batch.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import net.najiboulhouch.batch.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
	
	@Query("select p.id , p.name , p.prdt_type , p.ean_code from DIM_PRODUCT p where EAN_CODE = :eanCode")
	public Product findByEanCode(@Param("eanCode") String eanCode);

}
