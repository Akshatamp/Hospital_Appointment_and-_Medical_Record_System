package com.Hospital_Apponintment_And_Medical_Record_System.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Hospital_Apponintment_And_Medical_Record_System.entity.Department;
import com.Hospital_Apponintment_And_Medical_Record_System.repository.DepartmentRepository;

import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;

@Repository
public class DepartmentDao {
	@Autowired
	private DepartmentRepository departmentRepository;

	// create Department
	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}

	// get all Department
	public List<Department> getAllDepartment() {
		List<Department> departments = departmentRepository.findAll();

		if (departments.isEmpty()) {
			throw new NoRecordAvailableException("No books available in database");
		}
		return departments;
	}

	// get Department by id
	public Department getById(Integer id) {
		Optional<Department> opt = departmentRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new IdNotFoundException("Book not found with id: " + id);
		}
	}

	// update Department
	public Department updateDepartment(Department department) {

		if (department.getDepartmentId() == null) {
			throw new IdNotFoundException("ID must be passed to update a record");
		}

		Optional<Department> opt = departmentRepository.findById(department.getDepartmentId());

		if (opt.isPresent()) {
			return departmentRepository.save(department);
		} else {
			throw new IdNotFoundException("Id not available in database");
		}
	}

	// delete Department
	public void deleteDepartment(Integer id) {
		Optional<Department> opt = departmentRepository.findById(id);

		if (opt.isPresent()) {
			departmentRepository.delete(opt.get());
		} else {
			throw new IdNotFoundException("Id does not exist in database");
		}
	}

	// 7. Get Department by departmentName
	public List<Department> getDepartmentByDepartmentName(String departmentName) {
		List<Department> departments = departmentRepository.findByDepartmentName(departmentName);
		if (departments.isEmpty())
			throw new NoRecordAvailableException("No department available in database");
		return departments;
	}
}
