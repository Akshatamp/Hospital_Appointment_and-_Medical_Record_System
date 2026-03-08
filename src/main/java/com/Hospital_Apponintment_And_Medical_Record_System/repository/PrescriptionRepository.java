package com.Hospital_Apponintment_And_Medical_Record_System.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Hospital_Apponintment_And_Medical_Record_System.entity.Prescription;


public interface PrescriptionRepository extends JpaRepository<Prescription,Integer>{


//	List<Prescription> findByPatientPatientId(Integer patientId);

	List<Prescription> findByMedicalRecordRecordId(Integer recordId);
	
}
