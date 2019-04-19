package com.tutorials.model;

import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer, Portable {

	private String hardDisk;
	private Double memoryRam;
	private Double baterryLife;
	
	public Laptop() {
	}
	
	public Laptop(String hardDisk, Double memoryRam, Double baterryLife) {
		this.hardDisk = hardDisk;
		this.memoryRam = memoryRam;
		this.baterryLife = baterryLife;
	}

	public String getHardDisk() {
		return hardDisk;
	}

	public void setHardDisk(String hardDisk) {
		this.hardDisk = hardDisk;
	}

	public void setMemoryRam(Double memoryRam) {
		this.memoryRam = memoryRam;
	}


	public Double getMemoryRam() {
		return memoryRam;
	}

	@Override
	public String retrieveDescription() {
		return this.toString();
	}
	
	@Override
	public String toString() {
		return "InjectDependency [getHardDisk()=" + getHardDisk() + ", getMemoryRam()=" + getMemoryRam()
		+ "]";
	}

	public Double getBaterryLife() {
		return baterryLife;
	}

	public void setBaterryLife(Double baterryLife) {
		this.baterryLife = baterryLife;
	}

	@Override
	public String retrieveBaterryCycleDuration() {
		return "Duration of " + ( getBaterryLife() / 60 ) + " hours.";
	}

}
