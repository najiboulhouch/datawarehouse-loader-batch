package net.najiboulhouch.batch.dto;

import lombok.Data;
import net.najiboulhouch.batch.entities.Order;
import net.najiboulhouch.batch.entities.Product;
import net.najiboulhouch.batch.entities.PurchaseDate;
import net.najiboulhouch.batch.entities.Purchaser;
import net.najiboulhouch.batch.entities.Supplier;

@Data
public class ConvertedInputData {

	private Supplier supplier;
	private Purchaser purchaser;
	private Product product;
	private PurchaseDate purchaseDate ;
	private Order order;
	
}
