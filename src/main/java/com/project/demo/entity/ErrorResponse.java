package com.project.demo.entity;

import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor(access= AccessLevel.PUBLIC)
@NoArgsConstructor(access= AccessLevel.PUBLIC)
@Getter(value= AccessLevel.PUBLIC)
@Setter(value= AccessLevel.PUBLIC)
public class ErrorResponse {
	
	private Integer status;
	private String message;

}
