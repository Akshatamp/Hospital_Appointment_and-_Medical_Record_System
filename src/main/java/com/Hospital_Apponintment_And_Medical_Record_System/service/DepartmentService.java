package com.Hospital_Apponintment_And_Medical_Record_System.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Hospital_Apponintment_And_Medical_Record_System.dao.DepartmentDao;
import com.Hospital_Apponintment_And_Medical_Record_System.dto.ResponseStructure;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Department;

import jsp.springboot.dto.ResponeseStructure;
import jsp.springboot.entity.Book;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;
	//1.
	public ResponseEntity<ResponseStructure<Department>> saveDepartment(Department department) {
		ResponseStructure<Department> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Department saved successfully");
		response.setData(departmentDao.saveDepartment(department));
		return new ResponseEntity<ResponseStructure<Department>>(response, HttpStatus.CREATED);
	}
	//2.
	public ResponseEntity<ResponeseStructure<List<Department>>> getAllDepartment() {
		ResponeseStructure<List<Department>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Department fetched successfully");
		r.setData(departmentDao.getAllDepartment());
		return new ResponseEntity<ResponeseStructure<List<Department>>>(r, HttpStatus.OK);
	}

	// 3. Get book by id
	public ResponseEntity<ResponeseStructure<Department>> getById(Integer id) {
		ResponeseStructure<Department> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Department fetched successfully");
		r.setData(departmentDao.getById(id));
		return new ResponseEntity<ResponeseStructure<Department>>(r, HttpStatus.OK);
	}

	// 4. Update book
	public ResponseEntity<ResponeseStructure<Department>> updateDepartment(Department department) {
		ResponeseStructure<Department> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Department updated successfully");
		r.setData(departmentDao.updateDepartment(department));
		return new ResponseEntity<ResponeseStructure<Department>>(r, HttpStatus.OK);
	}

	// 5. Delete book
	public ResponseEntity<ResponeseStructure<String>> deleteDepartment(Integer id) {
		ResponeseStructure<String> r = new ResponeseStructure<>();
		departmentDao.deleteDepartment(id);
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Department deleted successfully");
		r.setData("Deleted");
		return new ResponseEntity<ResponeseStructure<String>>(r, HttpStatus.OK);
	}

	// 6. Get book by title
	public ResponseEntity<ResponeseStructure<List<Department>>> getDepartmentByDepartmentName(String departmentName) {
		ResponeseStructure<List<Department>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Department fetched successfully");
		r.setData(departmentDao.getDepartmentByDepartmentName(departmentName));
		return new ResponseEntity<ResponeseStructure<List<Department>>>(r, HttpStatus.OK);
	}
}
