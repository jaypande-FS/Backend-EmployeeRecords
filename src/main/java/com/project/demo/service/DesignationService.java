package com.project.demo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.entity.Designation;
import com.project.demo.repository.DesignationRepository;

@Service
public class DesignationService {

	@Autowired
	DesignationRepository repo;
	
	
	public void addDesignation(Designation designation) {
		
		repo.save(designation);
	}


	public List<Designation> getDesignations() {
		
		return repo.findAll();
	}


	public void deleteDesignation(int id) {
		repo.deleteById(id);
		
	}


	public  Designation update(@Valid Designation designation, int id) {
		designation.setId(id);
		repo.save(designation);
		return designation;
	}
	
	

}
