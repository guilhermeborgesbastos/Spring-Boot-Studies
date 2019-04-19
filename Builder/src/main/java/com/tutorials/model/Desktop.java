package com.tutorials.model;

import org.springframework.stereotype.Component;

@Component
public class Desktop implements Computer {

	private String hardDisk;
	private Double memoryRam;
	
	public Desktop() {
	}
	
	public Desktop(String hardDisk, Double memoryRam) {
		this.hardDisk = hardDisk;
		this.memoryRam = memoryRam;
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
	
}
