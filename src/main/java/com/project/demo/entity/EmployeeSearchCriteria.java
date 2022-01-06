package com.project.demo.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter(value= AccessLevel.PUBLIC)
@Setter(value= AccessLevel.PUBLIC)
public class EmployeeSearchCriteria {
	
	private String name;
	private String address;
	private Integer designation;
	private String salary;

	
}
