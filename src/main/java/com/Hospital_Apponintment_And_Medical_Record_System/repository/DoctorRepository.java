package com.Hospital_Apponintment_And_Medical_Record_System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Hospital_Apponintment_And_Medical_Record_System.entity.Department;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor,Integer>{

	List<Doctor> findBySpecialization(String specilization);

	List<Doctor> findByDepartmentDepartmentId(Integer departmentId);

	List<Doctor> findByAvailableDays(List<String> availableDays);
	
	@Query("SELECT a.doctor FROM Appointment a WHERE a.patient.patientId = :patientId")
	List<Doctor> findDoctorsByPatientId(Integer patientId);
	
	@Query("SELECT a.doctor FROM Appointment a WHERE a.appointmentId = :appointmentId")
	Doctor findDoctorByAppointmentId(Integer appointmentId);

}
