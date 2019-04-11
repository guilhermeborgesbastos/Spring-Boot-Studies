package com.tutorials.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorials.model.Laptop;

@RestController
public class LaptopController {
	
	@Autowired
	Laptop computer;
	
	@RequestMapping("/laptop/")
	public String index() {
		computer.setHardDisk("Hd Ssd Samsung Evo 860 1tb Sata3 Model Mz-76e1to");
		return computer.retrieveDescription();
	}
	
	@RequestMapping("/laptop/retrieveBaterryCycleDuration/")
	public String getBatteryDuration() {
		computer.setBaterryLife(75.2D);
		return computer.retrieveBaterryCycleDuration();
	}
}
