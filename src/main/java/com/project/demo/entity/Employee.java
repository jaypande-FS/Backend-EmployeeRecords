package com.project.demo.entity;



import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.demo.annotations.Name;
import com.project.demo.annotations.Phone;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;






@Entity
@Data
@AllArgsConstructor(access= AccessLevel.PUBLIC)
@NoArgsConstructor(access= AccessLevel.PUBLIC)
@EqualsAndHashCode
@Getter(value= AccessLevel.PUBLIC)
@Setter(value= AccessLevel.PUBLIC)
@Table(name="Employee", uniqueConstraints=@UniqueConstraint(columnNames= {"email"}))
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="emp_seq")
	@SequenceGenerator(name = "emp_seq", sequenceName = "emp_seq", initialValue = 1, allocationSize=1)
	private Integer id;
	
	@NotEmpty(message ="Please enter a name")
	@NotNull(message ="Please enter a name")
	@Name(message="Name is invalid")
	private String name;
	
	@NotEmpty(message = "Please enter an email")
    @NotNull(message="email field is empty")
	@Email(message="Please enter correct email")
	//@Pattern(regexp="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$", message="Please Enter correct email id")  
	private String email;
	
	@NotEmpty(message = "Please enter a phone number")
	@NotNull(message="phone field is empty")
   // @Max(value=10, message="Incorrect Phone number")
	@Phone(message="please enter numbers only in phone field")
	@Length(min = 10, max = 10, message="Incorrect Phone number")
	private String phone;
	
	@NotEmpty(message = "Please enter an address")
	@NotNull(message="address field is empty")
	private String address;
	
	@NotEmpty(message = "Please enter salary")
	@NotNull(message="Salary field is empty")
	private String salary;
	
	
	
	
	@ManyToOne
//( cascade = CascadeType.MERGE, fetch=FetchType.EAGER)
//    @JoinTable(
//            name = "EmployeeDesignationTable",
//            joinColumns = @JoinColumn(name = "id"),
//            inverseJoinColumns = @JoinColumn(name = "desigination_id")
//            
//            )
	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Designation designation;
	
	
	
	
	@ManyToMany
	//(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
//	 @JoinTable(name = "employee_certifications",
//	 joinColumns = {@JoinColumn(name="employee_id")},
//	 inverseJoinColumns = {@JoinColumn(name="id")}
//	   )
//	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Set<Certification> certificates = new HashSet<Certification>();
	
	
	
	


	


	
	
	
	

}
