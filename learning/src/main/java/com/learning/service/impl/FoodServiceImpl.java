package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dto.Food;
import com.learning.dto.Type;
import com.learning.exception.IdNotFoundException;
import com.learning.repository.FoodRepository;
import com.learning.service.FoodService;
@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	FoodRepository repo;
	@Override
	public Food addfood(Food food) {
		// TODO Auto-generated method stub
		Food food2 = repo.save(food);
		if(food2 == null) {
			return null;
		}
		return food2;
	}

	@Override
	public Food updatefood(Integer id, Food food) {
		// TODO Auto-generated method stub
		if(this.repo.existsById(id)==false) {
			return null;
		}
		return food;
	}

	@Override
	public Food getfoodbyid(Integer id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Food> optional = repo.findById(id);
		if(optional.isEmpty()) {
			throw new IdNotFoundException("id not found");
		}
		else {
			return optional.get();
		}
	}

	@Override
	public Optional<List<Food>> getAllfoodDetails() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(repo.findAll());
	}

	@Override
	public Optional<List<Food>> getAllfoodbytypes(Type type) {
		// TODO Auto-generated method stub
		return repo.findByFoodType(type);
	}

	@Override
	public String deletefoodrById(Integer id) {
		// TODO Auto-generated method stub
		try {
			Food optional = this.getfoodbyid(id);
			if(optional == null) {
				throw new IdNotFoundException("record not found");
			}
			else {
				repo.deleteById(id);
				return "Success";
			}
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
