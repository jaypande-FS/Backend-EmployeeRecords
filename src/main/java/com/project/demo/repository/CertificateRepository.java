package com.project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.demo.entity.Certification;

@Repository
public interface CertificateRepository extends JpaRepository<Certification,Integer> {

}
