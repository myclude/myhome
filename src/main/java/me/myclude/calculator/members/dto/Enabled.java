package me.myclude.calculator.members.dto;

import lombok.Getter;

@Getter
public enum Enabled {
	
	Y("Y", "true"),
	N("N", "false");
	
	final private String name;
	final private String enable;
	
	private Enabled(String name, String enable) {

		this.name = name;
		this.enable = enable;
	}
	
}
