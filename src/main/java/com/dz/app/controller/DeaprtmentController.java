package com.dz.app.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dz.app.entities.BaseProperties;
import com.dz.app.entities.Department;
import com.dz.app.repo.DepartmentRepository;

@RestController
@RequestMapping("department")
public class DeaprtmentController {

	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping
	public ResponseEntity<List<Department>> getAll() {
		return ResponseEntity.of(Optional.of(departmentRepository.findAll()));
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Department> get(@PathVariable("id") Long did) {

		Department findByDid = departmentRepository.findByDid(did);
		return ResponseEntity.ok(findByDid);
	}

	@PostMapping
	public ResponseEntity<Department> add(@RequestBody Department department) {
		try {

			List<Department> sqlTrn = departmentRepository.findByNameIgnoreCase(department.getName());
			if (sqlTrn != null && !sqlTrn.isEmpty()) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else {
				department.setBaseProperties(new BaseProperties("A", new Date(), "spring-boot-rest-demo2", null, null));
				Department save = departmentRepository.save(department);
				return ResponseEntity.status(HttpStatus.CREATED).body(save);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping(value = "{did}")
	public ResponseEntity<Boolean> delete(@PathVariable("did") Long did) {

		try {
			Department sqlTrn = departmentRepository.findByDid(did);
			if (sqlTrn != null) {
				departmentRepository.delete(sqlTrn);
				return ResponseEntity.ok().body(true);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
