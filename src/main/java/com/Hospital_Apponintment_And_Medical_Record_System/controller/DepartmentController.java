package com.Hospital_Apponintment_And_Medical_Record_System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hospital_Apponintment_And_Medical_Record_System.dto.ResponseStructure;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Department;
import com.Hospital_Apponintment_And_Medical_Record_System.repository.DepartmentRepository;
import com.Hospital_Apponintment_And_Medical_Record_System.service.DepartmentService;

import jsp.springboot.dto.ResponeseStructure;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private DepartmentService departmentService;
	//1.create dapartment
	@PostMapping
	public ResponseEntity<ResponseStructure<Department>> saveDepartment(@RequestBody Department department) {
		return departmentService.saveDepartment(department);
	}

	//2. fetch all the record
	@GetMapping
	public ResponseEntity<ResponeseStructure<List<Department>>> getAllDepartment() {

		return departmentService.getAllDepartment();
	}

	//3. fetch record by Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponeseStructure<Department>> getById(@PathVariable Integer id) {
		return departmentService.getById(id);
	}

	//4. update record
	@PutMapping("/{id}")
	public ResponseEntity<ResponeseStructure<Department>> updateDepartment(@RequestBody Department department) {
		return departmentService.updateDepartment(department);
	}

	//5. delete record
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponeseStructure<String>> deleteDepartment(@PathVariable Integer id) {
		return departmentService.deleteDepartment(id);

	}
	//6.fetch dept by deptname
	@GetMapping("/departmentName/{departmentName}")
	public ResponseEntity<ResponeseStructure<List<Department>>> getDepartmentByDepartmentName(
			@PathVariable String departmentName) {
		return departmentService.getDepartmentByDepartmentName(departmentName);
	}
}
