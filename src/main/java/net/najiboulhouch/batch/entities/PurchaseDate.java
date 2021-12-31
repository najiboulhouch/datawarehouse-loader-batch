package net.najiboulhouch.batch.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Getter
@Setter
@Table("DIM_DATE")
@AllArgsConstructor(staticName="of")
public class PurchaseDate {

	@Id
	@Column("ID")
	private Long id;
	
	@Column("DATE_TIME")
	private Date date;

}
