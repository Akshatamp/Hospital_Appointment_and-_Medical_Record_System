package com.Hospital_Apponintment_And_Medical_Record_System.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Hospital_Apponintment_And_Medical_Record_System.entity.Department;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Doctor;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.MedicalRecord;


public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Integer>{

	List<MedicalRecord> findByVisitDate(LocalDate visitDate);
	
	List<MedicalRecord> findByDoctorDoctorId(Integer doctorId);

	List<MedicalRecord> findByPatientPatientId(Integer patientId);

//	MedicalRecord findByAppointmentAppointmentId(Integer appointmentId);

}
