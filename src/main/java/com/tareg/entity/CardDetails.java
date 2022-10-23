package com.tareg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tareg.enums.CardType;
import com.tareg.util.CustomSequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card_visa")
public class CardDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visa_sequence")
	@GenericGenerator(name = "visa_sequence", strategy = "com.tareg.util.CustomSequenceGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = CustomSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@org.hibernate.annotations.Parameter(name = CustomSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "CD"),
			@org.hibernate.annotations.Parameter(name = CustomSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%d") })
	@Column(name = "cardId")
	private String id;
	@NotBlank(message = "Name is mandatory")
	private String name;
	@NotBlank(message = "credNumber is mandatory")
	private String credNumber;
	@JsonFormat(pattern="yy-MM-dd")
	private Date expire;
/*
	@Enumerated(EnumType.STRING)
	@Column(name = "cardType")
	private CardType cardType;
*/

	/*@NotBlank(message = "APIs Granted cannot be blank")
	private List<String> marks;
*/
	/*@ElementCollection // 1
	@CollectionTable(name = "my_list", joinColumns = @JoinColumn(name = "cardId")) // 2
	@Column(name = "list") // 3
	private List<String> list;*/


}
