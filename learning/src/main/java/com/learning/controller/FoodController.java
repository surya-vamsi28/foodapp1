package com.learning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.Food;
import com.learning.exception.AlreadyExistsException;
import com.learning.repository.FoodRepository;
import com.learning.service.FoodService;

@RestController
@RequestMapping("/api/auth/")
public class FoodController {

	@Autowired
	FoodService foodservice;
	@Autowired
	FoodRepository foodrepository;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addfood")
	public ResponseEntity<?> addFood(@Valid @RequestBody Food food) throws AlreadyExistsException {
		Food result = foodservice.addfood(food);
			System.out.println(result);
			return ResponseEntity.status(201).body(result);
}
	 @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("getfood/{id}")
	public ResponseEntity<?> getFoodById(@PathVariable("id") Integer id) throws com.learning.exception.IdNotFoundException{
		Food result = foodservice.getfoodbyid(id);
		return ResponseEntity.status(201).body(result);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/updatefood/{id}")
	public ResponseEntity<?> updatefoodbyid(@PathVariable("id") Integer id,@RequestBody Food food){
		Food res = foodservice.updatefood(id, food);
		if(res != null) {
			return ResponseEntity.status(201).body(res);
		}
		Map<String,String> map = new HashMap<String, String>();
		map.put("message","fail");
		return ResponseEntity.status(201).body(map);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("food/foods")
	public ResponseEntity<?> getAllFoodDetails(){
		Optional<List<Food>> optional = foodservice.getAllfoodDetails();
		if(optional.isEmpty()) {
			Map<String,String> map = new HashMap<String, String>();
			map.put("message","no record found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.of(optional);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deletefood/{id}")
	public ResponseEntity<?> deletefoodbyid(@PathVariable("id") Integer id){
		String res = foodservice.deletefoodrById(id);
		System.out.println(res);
		Map<String,String> map = new HashMap<String, String>();
		map.put("message",res);
		return ResponseEntity.status(201).body(map);
	}
	
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/foodbytype")
	public ResponseEntity<?> getfooddetailsbyType(@RequestBody Food food){
		Optional<List<Food>> optional = foodservice.getAllfoodbytypes(food.getFoodType());
		
		if(optional.isEmpty()) {
			Map<String,String> map = new HashMap<String, String>();
			map.put("message","no record found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		
		return ResponseEntity.of(optional);
	}



	
	}

	


