package com.learning.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

import lombok.Data;
@Data
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String name;

 
  
  
  
  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;
  
  @Size(max=50)
  private String address;
}
