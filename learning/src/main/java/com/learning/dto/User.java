package com.learning.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity 
@Table(name = "register")
public class User {
	@Id //Id must be auto generated
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Size(max=50)
	@NotBlank
	private String name;
	@Email
	private String email;
	@Size(max=100)
	@NotBlank
	private String password;
	@Size(max = 100)
	private String address;
	
	@ManyToMany(fetch = FetchType.LAZY)
	//@JsonIgnore
	//maintain in 3rd table
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "regId"), 
	inverseJoinColumns = @JoinColumn(name = "roleId") )//relationship btwn registered user(regId) and role(roleId)
	private Set<Role> roles = new HashSet<>();
	
	@OneToOne(mappedBy = "register", cascade = CascadeType.ALL)
    //@JsonSerialize(using = CustomListSerializer.class)
	//@JsonIgnore
	//@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
	private Login login;
	
	public User(String name,String email,String password,String address) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
	}
	
}
