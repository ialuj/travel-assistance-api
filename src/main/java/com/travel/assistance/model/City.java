package com.travel.assistance.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class City implements Serializable {

	private static final long serialVersionUID = 6960610723279326026L;
	
	private String name;
	
	private String countryCode;
	
	private Country country;
	
	private Weather weather;

}
