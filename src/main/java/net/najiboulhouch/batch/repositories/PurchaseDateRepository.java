package net.najiboulhouch.batch.repositories;

import java.util.Date;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import net.najiboulhouch.batch.entities.PurchaseDate;

public interface PurchaseDateRepository extends CrudRepository<PurchaseDate, Long> {

	@Query("select * from DIM_DATE where DATE_TIME = :date")
	public PurchaseDate findByDate(@Param("date") Date date);
}
