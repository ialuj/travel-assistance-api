package com.travel.assistance.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Rate implements Serializable {

	private static final long serialVersionUID = -7171996841759933015L;
	
	private String id;
	
	private String value;

}
