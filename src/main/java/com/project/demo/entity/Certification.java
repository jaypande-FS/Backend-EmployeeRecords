package com.project.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name="Certification")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter(value= AccessLevel.PUBLIC)
@Setter(value= AccessLevel.PUBLIC)
@Entity
public class Certification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cert_seq")
	@SequenceGenerator(name = "cert_seq", sequenceName = "cert_seq", initialValue = 1, allocationSize=1)
	private Integer id;
	
	@NotEmpty(message ="Please enter a name")
	@NotNull(message ="Please enter a name")
	private String name;
	
	
	
	
	//@ManyToMany(mappedBy = "certificates")
	//(fetch = FetchType.LAZY,cascade = CascadeType.MERGE,mappedBy = "certificates")
	 // private List<Employee> employee = new ArrayList<>();
	
	
}
