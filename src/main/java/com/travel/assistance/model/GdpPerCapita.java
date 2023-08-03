package com.travel.assistance.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GdpPerCapita implements Serializable {

	private static final long serialVersionUID = -2512931978627146787L;
	
	private String countryiso3code;
	
	private String year;
	
	private double value;
				
	private Country country;
	
	private Indicator indicator;

}
