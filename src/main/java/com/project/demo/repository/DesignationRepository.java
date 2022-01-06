package com.project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.demo.entity.Designation;

@Repository
public interface DesignationRepository extends JpaRepository<Designation,Integer> {

}
