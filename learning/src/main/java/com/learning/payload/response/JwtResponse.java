package com.learning.payload.response;

import java.util.List;

public class JwtResponse {//it is nothing but a encrypted string which will help us to access the secured endpoints from the server.
	  private String token;
	  private String type = "Bearer";
	  private int id;
	  private String name;
	  private String email;
	  private List<String> roles;

	  public JwtResponse(String accessToken, int id, String name, String email, List<String> roles) {
	    this.token = accessToken;
	    this.id = id;
	    this.name = name;
	    this.email = email;
	    this.roles = roles;
	  }

	  public String getAccessToken() {
	    return token;
	  }

	  public void setAccessToken(String accessToken) {
	    this.token = accessToken;
	  }

	  public String getTokenType() {
	    return type;
	  }

	  public void setTokenType(String tokenType) {
	    this.type = tokenType;
	  }

	  public int getId() {
	    return id;
	  }

	  public void setId(int id) {
	    this.id = id;
	  }

	  public String getEmail() {
	    return email;
	  }

	  public void setEmail(String email) {
	    this.email = email;
	  }

	  public String getname() {
	    return name;
	  }

	  public void setname(String name) {
	    this.name = name;
	  }

	  public List<String> getRoles() {
	    return roles;
	  }
}
