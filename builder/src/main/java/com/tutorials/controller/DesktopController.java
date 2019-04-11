package com.tutorials.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorials.model.Desktop;

@RestController
public class DesktopController {
	
	@Autowired
	Desktop computer;
	
	@RequestMapping("/desktop/")
	public String index() {
		computer.setHardDisk("Hd Ssd Samsung Evo 860 1tb Sata3 Model Mz-76e1to");
		return computer.retrieveDescription();
	}

}
