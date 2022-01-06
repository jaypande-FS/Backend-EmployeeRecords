package com.project.demo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.entity.Certification;
import com.project.demo.entity.Designation;
import com.project.demo.repository.CertificateRepository;
import com.project.demo.repository.DesignationRepository;

@Service
public class CertificationService {
	
	
	@Autowired
	CertificateRepository repo;
	
	
	public void add(Certification certificate) {
		
		repo.save(certificate);
	}


	public List<Certification> get() {
		
		return repo.findAll();
	}


	public void delete(int id) {
		repo.deleteById(id);
		
	}


	public Certification update(@Valid Certification certificate, int id) {
		certificate.setId(id);
		repo.save(certificate);
		return certificate;
	}
	
	


}
