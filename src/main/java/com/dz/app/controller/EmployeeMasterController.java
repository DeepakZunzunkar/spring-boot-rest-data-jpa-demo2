package com.dz.app.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dz.app.entities.BaseProperties;
import com.dz.app.entities.Employee;
import com.dz.app.repo.EmployeeRepository;
import com.dz.app.utility.Constant.EmployeeStatus;

@RestController
@RequestMapping("employeemaster")
public class EmployeeMasterController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employeeList = null;
		try {
			employeeList = employeeRepository.findAllEmployees();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.of(Optional.of(employeeList));
	}

	@GetMapping(value = "{eid}")
	public ResponseEntity<Employee> getEmployeeByEid(@PathVariable("eid") Long eid) {

		Employee findByEid = employeeRepository.findByEid(eid);
		if (findByEid != null) {
			return ResponseEntity.status(HttpStatus.OK).body(findByEid);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping(value = "{pagenumber}/{pagesize}")
	public ResponseEntity<Page<Employee>> getAllEmployeeByEid(@PathVariable("pagenumber") Integer pageNumber,
			@PathVariable("pagesize") Integer pageSize) {
		Page<Employee> findAll = employeeRepository.findAll(PageRequest.of(pageNumber, pageSize));
		if (findAll.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(findAll);
	}

	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		try {

			List<Employee> sqlEmps = employeeRepository
					.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(employee.getFirstName(), employee.getLastName());
			if (sqlEmps != null && !sqlEmps.isEmpty()) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else {
				employee.setBaseProperties(new BaseProperties("A", new Date(), "spring-boot-rest-demo2", null, null));
				employee.setStatus(EmployeeStatus.ACTIVE.getEmployeeStatusCode());
				Employee save = employeeRepository.save(employee);
				return ResponseEntity.status(HttpStatus.CREATED).body(save);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee trn) {
		try {
			if (trn.getEid() != null) {

				Employee trnSql = employeeRepository.findByEid(trn.getEid());
				if (trnSql != null) {

					trnSql.getBaseProperties().setUpdatedBy("spring-boot-rest-demo2");
					trnSql.getBaseProperties().setUpdatedOn(new Date());

					trnSql.setFirstName(trn.getFirstName());
					trnSql.setMiddleName(trn.getMiddleName());
					trnSql.setLastName(trn.getLastName());
					trnSql.setStatus(trn.getStatus());
					trnSql.setGender(trn.getGender());
					trnSql.setBirthDate(trn.getBirthDate());
					trnSql.setSalary(trn.getSalary());
					employeeRepository.save(trnSql);
					return ResponseEntity.status(HttpStatus.ACCEPTED).body(trnSql);
				} else {
					return ResponseEntity.status(HttpStatus.CONFLICT).build();
				}

			} else {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("{eid}")
	public ResponseEntity<Boolean> deleteEmployee(@PathVariable("eid") Long eid) {

		try {
			Employee sqlEmp = employeeRepository.findByEid(eid);
			if (sqlEmp != null) {
				employeeRepository.delete(sqlEmp);
				return ResponseEntity.ok().body(true);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
