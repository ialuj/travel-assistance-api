package com.travel.assistance.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Indicator implements Serializable {

	private static final long serialVersionUID = -5951756904318801464L;
	
	private String id;
	
	private String value;

}
