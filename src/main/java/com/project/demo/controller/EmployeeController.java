package com.project.demo.controller;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.*;
import java.io.*;

import javax.validation.Valid;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Reference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.entity.Employee;
import com.project.demo.entity.EmployeePage;
import com.project.demo.entity.EmployeeSearchCriteria;
import com.project.demo.entity.ErrorResponse;
import com.project.demo.exception.InvalidIdException;
import com.project.demo.service.EmployeeService;

import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.Logger;

@RestController
@RequestMapping("/employee")
@Validated
@CrossOrigin
public class EmployeeController {
	
	//private static final Logger logger = LogFactory.getLogger(EmployeeController.class);
//	@Reference(value = LoggerFactory.class)
//	private Logger logger;
	 private static final org.slf4j.Logger log = 
		    org.slf4j.LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeService employeeservice;
	
	@GetMapping(value="/getall")
	public List<Employee> getAll(){
		return employeeservice.getAll();
	}
	
	
	
	@GetMapping(value="/all")
	public Page<Employee> getAllEmployee(@RequestParam("pageSize") Integer pageSize,@RequestParam("pageNumber") Integer pageNumber )
	{ 
		try {
			 if(pageSize<=0 ) {
            	 //log.info("i am working fine");
 
				 throw new InvalidIdException("Page Size should be greater than zero");
 
             }
			
			
			
		
		 return employeeservice.getAllEmployee(pageSize,pageNumber);
		 
		 
		}catch(InvalidIdException e)
		{
			
			 log.error("System Error: "+e.getMessage());
			 
			
			
		}
		 
		return null;
	}
	
	@PostMapping(value="/")
	public ResponseEntity<String> addEmployee( @RequestBody @Valid Employee employee)
	{    
		ResponseEntity<String> response = null;
		try {
			if(employeeservice.saveEmployee(employee)!=null)
			{
				response= new ResponseEntity<String>(employeeservice.saveEmployee(employee).getMessage(),HttpStatus.INTERNAL_SERVER_ERROR); 	
				
			}
			else
			{
				
				response= new ResponseEntity<String>("you are registered!!! Thank you",HttpStatus.OK); 
			}
			
		 
		}
		catch(Exception e)
		{
		 
			 response= new ResponseEntity<String>("You cannot register  ",HttpStatus.INTERNAL_SERVER_ERROR); 
			
		}	 
		 return response;
	}
	
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<ErrorResponse> deleteEmployee(@PathVariable("id") Integer id)
	{
		
		
		 //employeeservice.deleteEmployee(id);
		 ResponseEntity<ErrorResponse> response = null;
		
		try {
       	 
			 
            if(id==0 ) {
           	 //log.info("i am working fine");
                throw new InvalidIdException("0 Employee Id is not valid");

            }
            
            else if(!(employeeservice.findId(id)&& id!=0)) {
           	 throw new InvalidIdException(id +" Employee Id does not Exist");
            }
            
            else {

            	employeeservice.deleteEmployee(id);
            	response = new ResponseEntity<ErrorResponse>(HttpStatus.OK);
            	 return response;

            }

        }

        
        catch(InvalidIdException e) {
       	 
            //log.info(e.getMessage());
        	ErrorResponse eresponse=new ErrorResponse();
        	eresponse.setMessage(e.getMessage());
        	eresponse.setStatus(500);
            log.error("System Error: "+e.getMessage());
            
            response = new ResponseEntity<ErrorResponse>(eresponse,HttpStatus.INTERNAL_SERVER_ERROR);
       	 return response;
            

        }
		
		 
		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Employee> updateEmployee( @RequestBody @Valid Employee employee, @PathVariable("id") Integer id)
	{
		
		 ResponseEntity<Employee> response = null;
			
		 
         try {
        	 
 
             if(id==0 ) {
            	 //log.info("i am working fine");
 
                 throw new InvalidIdException("Employee Id is not valid");
 
             }
             
             if(!(employeeservice.findId(id)&& id!=0)) {
            	 
            	 throw new InvalidIdException("Employee Id does not Exist");
             }
           if(employeeservice.updateEmployee(employee, id)==null) {
        	   employee=null;
        	   response = new ResponseEntity<Employee>(employee,HttpStatus.INTERNAL_SERVER_ERROR);
            	 throw new InvalidIdException("there is some error");
             }
             
 
             employee = employeeservice.updateEmployee(employee, id);
 
             response = new ResponseEntity<Employee>(employee,HttpStatus.OK);
 
         }
 
         
         catch(InvalidIdException e) {
        	 
             //log.info(e.getMessage());
             
             log.error("System Error: "+e.getMessage());
             
 
             response = new ResponseEntity<Employee>(employee,HttpStatus.INTERNAL_SERVER_ERROR);
 
         }
 
         return response;
		
		
		
		
		
		 //employee= employeeservice.updateEmployee(employee, id);
		
		 //return employee;
		
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable("id") Integer id)
	{ 
		Employee employee;
		 ResponseEntity<Employee> response = null;
		
		 
		         try {
		        	 
		 
		             if(id==0 ) {
		            	 //log.info("i am working fine");
		 
		                 throw new InvalidIdException("Employee Id is not valid");
		 
		             }
		             
		             if(!(employeeservice.findId(id)&& id!=0)) {
		            	 
		            	 throw new InvalidIdException("Employee Id does not Exist");
		             }
		 
		             employee = employeeservice.findEmployeeById(id);
		 
		             response = new ResponseEntity<Employee>(employee,HttpStatus.OK);
		 
		         }
		 
		         
		         catch(InvalidIdException e) {
		        	 
		             //log.info(e.getMessage());
		             String errors=e.getMessage();
		             log.error("System Error: "+e.getMessage());
		             
		 
		             response = new ResponseEntity<Employee>(HttpStatus.INTERNAL_SERVER_ERROR);
		 
		         }
		 
		         return response;
		
		 //return employeeservice.findEmployeeById(id);
		
		 
		
	}
	
	@GetMapping(value="/filterByName/{name}")
	public List<Employee> filterByName(@PathVariable("name") String name)
	{
		
		 return employeeservice.filterByName(name);
		
	}
	
	@GetMapping(value="/filterBySalary/{salary}")
	public List<Employee> filterBySalary(@PathVariable("salary") String salary)
	{
		
		 return employeeservice.filterBySalary(salary);
		
	}
	
	
	@GetMapping(value="/filter")
	public List<Employee> filter( @RequestParam("name") String name,@RequestParam("email") String email)
	{
		
		 return employeeservice.filter(name,email);
		
	}
	
	@PostMapping(value="/filters")
	public ResponseEntity<Page<Employee>> filters( EmployeePage employeePage ,@RequestBody EmployeeSearchCriteria employeeSearchCriteria)
	{
		
		 return new ResponseEntity<>(employeeservice.getEmployees(employeePage,employeeSearchCriteria),HttpStatus.OK);
		
	}
	
	
	
	
	
	

}
