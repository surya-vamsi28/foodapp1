package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.dto.Food;
import com.learning.dto.Type;
import com.learning.exception.IdNotFoundException;


@Service
public interface FoodService {
	public Food addfood(Food food);
	public Food updatefood(Integer id,Food food);
	public Food getfoodbyid(Integer id) throws IdNotFoundException;
	public Optional<List<Food>> getAllfoodDetails() ;
	public Optional<List<Food>> getAllfoodbytypes(Type type); 
	public String deletefoodrById(Integer id);
}
