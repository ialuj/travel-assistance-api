package com.travel.assistance.model;

import java.io.Serializable;
import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExchangeRate implements Serializable {

	private static final long serialVersionUID = 1018545324332609648L;
	
	private String base;
	
	private Collection<Rate> rates;

}
