package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.models.Trainer;

@Service
public class TrainerServiceImpl implements TrainerService{
	
	private List<Trainer> allTrainers = new ArrayList<>();
	
	private int id = 1;
	
	
	@Override
	public Trainer saveOne(Trainer t) {
		
		//save to "DB"
		allTrainers.add(t);
		//Trainer gets Id from DB generation
		t.setId(id++);
		return t;
	}

}
