package com.learning.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
@Entity 
@Table(name = "food")
public class Food {
	@Id //Id must be auto generated
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Size(max=50)
	@NotBlank
	private String name;
	private int cost;
	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private Type foodType;
	@NotBlank
	private String description;
	@NotBlank
	private String pic;
}
