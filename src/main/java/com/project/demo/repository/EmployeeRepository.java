package com.project.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.demo.entity.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Integer> {

	@Query(value="select e from Employee e where e.name like %:name%")
	List<Employee> findAllByname(@Param("name") String name);
	
	List<Employee> findAllBysalary(String salary);
	
	@Query(value="select e from Employee e where e.email =:email")
	List<Employee> findAllByemail(@Param("email") String email);

	
	@Query(value=" select e from Employee e where e.name =:name and e.email =:email")
	List<Employee> filter(@Param("name") String name, @Param("email") String email);

	Integer countByemail(String email);
	Integer countByphone(String phone);
	
	
	
	
	
	Page<Employee> findAll(Pageable pageable);
	
	
	

}
