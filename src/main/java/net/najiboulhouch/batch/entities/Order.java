package net.najiboulhouch.batch.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table("FACT_ORDER")

public class Order {

	@Id
	@Column("ID")
	private Long id;
	
	@Column("PRODUCT_ID")
	private Long productId;
	
	@Column("EMP_PURCHASER_ID")
	private Long purchaserId;
	
	@Column("DATE_ID")
	private Long dateId;
	
	@Column("SUPPLIER_ID")
	private Long supplierId;
	
	@Column("QUANTITY")
	private Integer quantity;
	
	@Column("TOTAL_AMOUNT")
	private Double amount;

}
