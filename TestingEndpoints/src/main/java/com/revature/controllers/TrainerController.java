package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Trainer;
import com.revature.services.TrainerService;

@RestController
@RequestMapping("trainer")
public class TrainerController {
	
	@Autowired
	TrainerService ts;
	
	@PostMapping()
	public ResponseEntity getTrainerById(@RequestBody Trainer t){
		ts.saveOne(t);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
