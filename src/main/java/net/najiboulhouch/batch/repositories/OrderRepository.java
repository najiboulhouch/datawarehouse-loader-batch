package net.najiboulhouch.batch.repositories;

import org.springframework.data.repository.CrudRepository;

import net.najiboulhouch.batch.entities.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

	
}
