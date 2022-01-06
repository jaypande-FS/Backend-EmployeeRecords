package com.project.demo.entity;

import org.springframework.data.domain.Sort;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter(value= AccessLevel.PUBLIC)
@Setter(value= AccessLevel.PUBLIC)
public class EmployeePage {
	
	private int pageNumber=0;
	private int pageSize =10;
	private Sort.Direction sortDirection = Sort.Direction.ASC;
	private String sortBy= "name";
	
	
	
	

}
