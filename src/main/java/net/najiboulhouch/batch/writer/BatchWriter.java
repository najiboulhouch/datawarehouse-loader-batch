package net.najiboulhouch.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import net.najiboulhouch.batch.dto.ConvertedInputData;
import net.najiboulhouch.batch.entities.Product;
import net.najiboulhouch.batch.entities.PurchaseDate;
import net.najiboulhouch.batch.entities.Purchaser;
import net.najiboulhouch.batch.entities.Supplier;
import net.najiboulhouch.batch.repositories.OrderRepository;
import net.najiboulhouch.batch.repositories.ProductRepository;
import net.najiboulhouch.batch.repositories.PurchaseDateRepository;
import net.najiboulhouch.batch.repositories.PurchaserRepository;
import net.najiboulhouch.batch.repositories.SupplierRepository;

public class BatchWriter implements ItemWriter<ConvertedInputData>{

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private PurchaserRepository purchaserRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PurchaseDateRepository purchaseDateRepository;
	
	@Autowired
	private OrderRepository commandRepository;
	
	
	@Override
	public void write(List<? extends ConvertedInputData> arg0) throws Exception {
		arg0.stream().forEach(arg -> {
			Supplier supplier = null;
			Purchaser purchaser = null;
			Product product = null;
			PurchaseDate purchaseDate = null;
			if(arg.getOrder().getSupplierId() == null) {
				supplier = supplierRepository.save(arg.getSupplier());
				arg.getOrder().setPurchaserId(supplier.getId());
			}
			
			if(arg.getOrder().getPurchaserId()== null) {
				purchaser = purchaserRepository.save(arg.getPurchaser());
				arg.getOrder().setPurchaserId(purchaser.getId());
			}
			
			if(arg.getOrder().getProductId() == null) {
				product = productRepository.save(arg.getProduct());
				arg.getOrder().setProductId(product.getId());
			}
			
			if(arg.getOrder().getDateId() == null) {
				purchaseDate = purchaseDateRepository.save(arg.getPurchaseDate());
				arg.getOrder().setDateId(purchaseDate.getId());
			}
			
			commandRepository.save(arg.getOrder());
		});
		
	}

	
	
}
