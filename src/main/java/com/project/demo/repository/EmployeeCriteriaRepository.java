package com.project.demo.repository;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.project.demo.entity.Employee;
import com.project.demo.entity.Designation;
import com.project.demo.entity.EmployeePage;
import com.project.demo.entity.EmployeeSearchCriteria;

@Repository
public class EmployeeCriteriaRepository {
	
	private final EntityManager entityManager;
	private final CriteriaBuilder criteriaBuilder;
	
	
	
	public EmployeeCriteriaRepository(EntityManager entityManager) {
		
		super();
		this.entityManager = entityManager;
		this.criteriaBuilder=entityManager.getCriteriaBuilder();
		
	}
	
	
	public Page<Employee> FindAllWithFilters(EmployeePage employeePage, EmployeeSearchCriteria employeeSearchCriteria){
		
	CriteriaQuery<Employee>	criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		
	Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
	//Root<Designation> dRoot = criteriaQuery.from(Designation.class);
	
	//Join<Employee, Designation> designation1 = demands.join("designation");
	Predicate predicate =getPredicate(employeeSearchCriteria,employeeRoot);
	Predicate predicate1 =getPredicate1(employeeSearchCriteria,employeeRoot);
	//criteriaQuery.where(predicate);
	
	Predicate finalPredicate
	  = criteriaBuilder
	  .and(predicate, predicate1);
	
	
	criteriaQuery.where(finalPredicate);
	setOrder(employeePage , criteriaQuery , employeeRoot);
	
	
	TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
	typedQuery.setFirstResult(employeePage.getPageNumber()*employeePage.getPageSize());
	typedQuery.setMaxResults(employeePage.getPageSize());
	
	Pageable pageable= getPageable(employeePage);
	
	long employeesCount=getEmployeesCount(predicate);
	
	return new PageImpl<>(typedQuery.getResultList(),pageable, employeesCount);
	}


	


	


	

	private Predicate getPredicate(EmployeeSearchCriteria employeeSearchCriteria, Root<Employee> employeeRoot) {
		List<Predicate> predicate = new ArrayList<>();
		
		
		
		if(Objects.nonNull(employeeSearchCriteria.getName())) {
			predicate.add(criteriaBuilder.like(employeeRoot.get("name"),'%'+employeeSearchCriteria.getName().toUpperCase()+'%'));
			
		}
		
		if(Objects.nonNull(employeeSearchCriteria.getAddress())) {
			predicate.add(criteriaBuilder.like(employeeRoot.get("address"), '%'+employeeSearchCriteria.getAddress().toUpperCase()+'%'));
			
		}
		
		
//	 Join<Employee ,Designation> join = employeeRoot.join("designation");
//	 if(Objects.nonNull(employeeSearchCriteria.getDesignation())) {
//			predicate1.add(criteriaBuilder.equal(join.get("id"), employeeSearchCriteria.getDesignation()));
//			
//		}
		

		if(Objects.nonNull(employeeSearchCriteria.getSalary())) {
			predicate.add(criteriaBuilder.like(employeeRoot.get("salary"),'%'+ employeeSearchCriteria.getSalary()+'%'));
			
		}
		
		
		return criteriaBuilder.and(predicate.toArray(new Predicate[0]));
	}
	
	
//---------------------------------------------------------------------------------------------------------------	
	private Predicate getPredicate1(EmployeeSearchCriteria employeeSearchCriteria, Root<Employee> employeeRoot) {
		List<Predicate> predicate = new ArrayList<>();
		
		
		
		
		
	 Join<Employee ,Designation> join = employeeRoot.join("designation");
	 if(Objects.nonNull(employeeSearchCriteria.getDesignation())) {
			predicate.add(criteriaBuilder.equal(join.get("id"), employeeSearchCriteria.getDesignation()));
			System.out.print(predicate);
			
		}
		

		
		
		
		return criteriaBuilder.and(predicate.toArray(new Predicate[0]));
	}
	
	
	//---------------------------------------------------------------------------------------------------------
	
	private void setOrder(EmployeePage employeePage, CriteriaQuery<Employee> criteriaQuery,
			Root<Employee> employeeRoot) {
		if(employeePage.getSortDirection().equals(Sort.Direction.ASC)) {
			criteriaQuery.orderBy(criteriaBuilder.asc(employeeRoot.get(employeePage.getSortBy())));
		}
		else {
			
			criteriaQuery.orderBy(criteriaBuilder.desc(employeeRoot.get(employeePage.getSortBy())));
			
		}
		
	}
	
	private Pageable getPageable(EmployeePage employeePage) {
		Sort sort = Sort.by(employeePage.getSortDirection(),employeePage.getSortBy());
		return PageRequest.of(employeePage.getPageNumber(), employeePage.getPageSize(),sort);
	}
	
	
	private long getEmployeesCount(Predicate predicate) {
		CriteriaQuery<Long> countQuery= criteriaBuilder.createQuery(Long.class);
		Root<Employee> countRoot = countQuery.from(Employee.class);
		countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
		
		return entityManager.createQuery(countQuery).getSingleResult();
	}

	
	

}
