package com.travel.assistance.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Country implements Serializable {
	
	private static final long serialVersionUID = -4301422399093325272L;

	private String id;
	
	private String value;
	
	private GdpPerCapita gdpPerCapita;

}
