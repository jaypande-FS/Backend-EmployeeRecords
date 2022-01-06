package com.project.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.entity.Designation;
import com.project.demo.service.DesignationService;

@RestController
@RequestMapping("/designation")
@Validated
@CrossOrigin
public class DesignationController {
	
	@Autowired
	private DesignationService dservice;
	
	@PostMapping("/")
	public String addDesignation(@RequestBody @Valid Designation designation)
	{
		dservice.addDesignation(designation);
		return "added";
	}
	
	@GetMapping("/")
	public List<Designation> getDesignations()
	{
		
		return dservice.getDesignations();
		
	}
	

	@DeleteMapping(value="/{id}")
	public String deleteDesignation(@PathVariable("id") int id)
	{
		
		
		 dservice.deleteDesignation(id);
		
		 return "deleted row with id = "+ id;
		
	}
	
	@PutMapping(value="/{id}")
	public Designation update( @RequestBody @Valid Designation designation, @PathVariable("id") int id)
	{
		
		 designation= dservice.update(designation, id);
		
		 return designation;
		
	}
	
	
	
	

}
