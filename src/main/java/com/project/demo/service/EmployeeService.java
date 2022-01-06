package com.project.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.demo.controller.EmployeeController;
import com.project.demo.entity.Certification;
import com.project.demo.entity.Employee;
import com.project.demo.entity.EmployeePage;
import com.project.demo.entity.EmployeeSearchCriteria;
import com.project.demo.exception.InvalidIdException;
import com.project.demo.repository.EmployeeCriteriaRepository;
import com.project.demo.repository.EmployeeRepository;

@Service
public class EmployeeService 
{
	 private static final org.slf4j.Logger log = 
			    org.slf4j.LoggerFactory.getLogger(EmployeeController.class);
	 
	@Autowired
	EmployeeRepository repo;
	
	@Autowired
	EmployeeCriteriaRepository crepo;
	
	
	
	
	public Exception saveEmployee( Employee employee) throws InvalidIdException
	{
		
		//List<Employee> list = repo.findAllByemail(employee.getEmail());
		//int emailCount=list.size();
		
		int ecount=repo.countByemail(employee.getEmail());
		int pcount=repo.countByphone(employee.getPhone());
		//List<Certification> list=employee.getCertificates();
		String name=employee.getName().toUpperCase();
		String address=employee.getAddress().toUpperCase();
		
		
//		 HashSet<Certification> hashSet = new HashSet<>();
//	        hashSet.addAll(list);
//	        
//	        List<Certification> arr = new ArrayList<>();
//	        arr.addAll(hashSet);
//	        
//	        
//	        employee.setCertificates(arr);
	        log.info(employee.getCertificates()+"here is the list");
	        
	        
	        

		
		
		//String perror="";
		
		log.info("count "+ ecount);
		try
		{
			if(ecount>=1)
			{
				
			 throw new InvalidIdException(employee.getEmail()+" is already registered !!! try with some other email");
			}
			else if(pcount>=1)
			{
				
			 throw new InvalidIdException(employee.getPhone()+" is already registered !!! try with some other number");
			}
			
			
			else {
				employee.setName(name);
				employee.setAddress(address);
			
			repo.save(employee);
			}
			
		}
		catch(InvalidIdException e)
		{
			
			log.error("System Error: "+e.getMessage());
			return e;
			
		}
		
		return null;
		
	}
	
	
	public Page<Employee> getAllEmployee(int pageSize,int pageNumber)
	{
		Pageable paging = PageRequest.of(pageNumber, pageSize);
		
		Page<Employee> employees =repo.findAll(paging);
		
		return employees;
		
		
		 //return (List<Employee>)repo.findAll();
		 
		
	}
	

	public void deleteEmployee(int id) {
		 repo.deleteById(id);
		
	}

	public Employee updateEmployee(Employee employee, int id) {
		employee.setId(id);
		
		String name=employee.getName().toUpperCase();
		String address=employee.getAddress().toUpperCase();
		//List<Certification> list = employee.getCertificates();
				
	    try {
//	    	for(int i=0;i<list.size();i++)
//	    	{
//	    		int Id = list.get(i).getId();
//	    		for(int j=i+1;j<list.size();j++)
//		    	{
//		    			
//	    	       if(Id==list.get(j).getId())
//	    	        {
//	    		      throw new InvalidIdException("Cerification already exists with this employee");
//	    		 
//	    	       }
//		    	}
//	    	}
	    log.info("havnt saved yet!!!!");
	    employee.setName(name);	
	    employee.setAddress(address);
		repo.save(employee);
		
		
		return employee;
	    }
	    catch(Exception e)
	    {
	    	employee=null;
	    	log.error("System Error: "+e.getMessage());
	    	
	    }
		return employee;
		
	}

	public Employee findEmployeeById(int id) {
		
		Employee employee=repo.findById(id).orElseThrow();
		return employee;
	}

	public List<Employee> filterByName(String name) {
		
		return repo.findAllByname(name);
	}

	public List<Employee> filter(String name, String email) {
		
		return repo.filter(name,email);
	}
	
     public List<Employee> filterBySalary(String name) {
		
		return repo.findAllBysalary(name);
	}
     
     
     
     public Page<Employee> getEmployees(EmployeePage employeePage, EmployeeSearchCriteria employeeSearchCriteria)
     {
    	 return  crepo.FindAllWithFilters(employeePage, employeeSearchCriteria);
     }
     
     

	public boolean findId(int id) {
		
		return repo.existsById(id);
	}

	 
}
