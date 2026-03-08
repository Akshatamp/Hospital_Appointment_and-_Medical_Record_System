package com.Hospital_Apponintment_And_Medical_Record_System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Hospital_Apponintment_And_Medical_Record_System.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer>{

	List<Department> findByDepartmentName(String departmentName);

}
