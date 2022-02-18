package com.learning.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity 
@Table(name = "Login")
public class Login {
	@Id 
	@Column(name = "email")
	@Email
	private String email;
	@Size(max=50)
	@NotBlank
	private String password;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
    @JoinColumn(name = "regId")
    @JsonProperty(access = Access.WRITE_ONLY)
	private User register;
}
