package net.najiboulhouch.batch.dto;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class InputData {
	private String productName;
	private String productType;
	private String productEanCode;
	private Double productAmount;
	private Integer productQuantity;
	private String purchaserFirstName;
	private String purchaserLastName;
	private String purchaserEmail;
	private String supplierName;
	private String supplierAddress;
	private Date transactionDate;
	

}
