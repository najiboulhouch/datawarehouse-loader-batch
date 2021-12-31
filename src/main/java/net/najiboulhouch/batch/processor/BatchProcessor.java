package net.najiboulhouch.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import net.najiboulhouch.batch.dto.ConvertedInputData;
import net.najiboulhouch.batch.dto.InputData;
import net.najiboulhouch.batch.entities.Order;
import net.najiboulhouch.batch.entities.Product;
import net.najiboulhouch.batch.entities.PurchaseDate;
import net.najiboulhouch.batch.entities.Purchaser;
import net.najiboulhouch.batch.entities.Supplier;
import net.najiboulhouch.batch.repositories.ProductRepository;
import net.najiboulhouch.batch.repositories.PurchaseDateRepository;
import net.najiboulhouch.batch.repositories.PurchaserRepository;
import net.najiboulhouch.batch.repositories.SupplierRepository;

public class BatchProcessor implements ItemProcessor<InputData, ConvertedInputData> {

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private PurchaserRepository purchaserRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PurchaseDateRepository purchaseDateRepository;

	@Override
	public ConvertedInputData process(InputData arg0) throws Exception {
		ConvertedInputData convertedInputData = new ConvertedInputData();
		Order order = new Order();
		Supplier supplier = supplierRepository.findByName(arg0.getSupplierName());
		Purchaser purchaser = purchaserRepository.findByEmail(arg0.getPurchaserEmail());
		Product product = productRepository.findByEanCode(arg0.getProductEanCode());
		PurchaseDate purchaseDate = purchaseDateRepository.findByDate(arg0.getTransactionDate());
		
		if(supplier == null) {
			supplier = Supplier.of(null	, arg0.getSupplierName(), arg0.getSupplierAddress());
			convertedInputData.setSupplier(supplier);
		} else {
			order.setSupplierId(supplier.getId());
		}
		
		if(purchaser == null) {
			purchaser = Purchaser.of(null, arg0.getPurchaserFirstName(), arg0.getPurchaserLastName(), arg0.getPurchaserEmail());
			convertedInputData.setPurchaser(purchaser);
		} else {
			order.setPurchaserId(purchaser.getId());
		}
		
		if(product == null) {
			product = Product.of(null, arg0.getProductName(), arg0.getProductType(), arg0.getProductEanCode());
			convertedInputData.setProduct(product);
			order.setQuantity(arg0.getProductQuantity());
			order.setAmount(arg0.getProductAmount() * arg0.getProductQuantity());
		}else {
			order.setProductId(product.getId());
			order.setQuantity(arg0.getProductQuantity());
			order.setAmount(arg0.getProductAmount() * arg0.getProductQuantity());
		}
		
		if(purchaseDate == null) {
			purchaseDate  = PurchaseDate.of(null, arg0.getTransactionDate());
			convertedInputData.setPurchaseDate(purchaseDate);
		} else {
			order.setDateId(purchaseDate.getId());
		}

		convertedInputData.setOrder(order);
		
		return convertedInputData;
	}
	
	
}
