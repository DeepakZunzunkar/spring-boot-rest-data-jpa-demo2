package com.dz.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dz.app.entities.Department;

public interface DepartmentRepository extends CrudRepository<Department, Long>, PagingAndSortingRepository<Department, Long> {

	public Department findByDid(Long did);
	
	public List<Department> findByNameIgnoreCase(String name);
	
	@Query("select dp from Department dp Order By did desc")
	public List<Department> findAll();
}
