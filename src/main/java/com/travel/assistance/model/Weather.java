package com.travel.assistance.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Weather implements Serializable {

	private static final long serialVersionUID = 9149355338992160390L;
	
	private String currentTemperature;
	
	private String minTemperature;
	
	private String maxTemperature;
	
	private String humidity;
	
	private String countryCode;
	
	private String cityName;

}
