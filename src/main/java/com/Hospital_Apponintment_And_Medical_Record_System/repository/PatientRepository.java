package com.Hospital_Apponintment_And_Medical_Record_System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Hospital_Apponintment_And_Medical_Record_System.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient,Integer>{


	List<Patient> findByAgeGreaterThan(Integer age);
	
	List<Patient> findByPhone(Long phone);

//	List<Patient> findByPatientAppointmentId(Integer appointmentId);
//
//	List<Patient> findByPatientMedicalRecord(Integer recordId);



	
	

}
