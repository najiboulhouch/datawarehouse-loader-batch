package net.najiboulhouch.batch.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Builder
@AllArgsConstructor(staticName="of")
@Getter
@ToString
@Table("DIM_EMPLOYEE_PURCHASER")

public class Purchaser {

	@Id
	@Column("ID")
	private Long id;
	
	@Column("FIRST_NAME")
	private String firstName;
	
	@Column("LAST_NAME")
	private String lastName;
	
	@Column("EMAIL")
	private String email;

}
