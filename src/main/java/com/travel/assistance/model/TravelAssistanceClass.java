package com.travel.assistance.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TravelAssistanceClass implements Serializable {

	private static final long serialVersionUID = -6182488602887001176L;
	
	private GdpPerCapita gdpPerCapita;
	
	private City city;
	
	private ExchangeRate exchangeRate;

}
