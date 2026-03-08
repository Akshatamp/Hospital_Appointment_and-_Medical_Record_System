package com.Hospital_Apponintment_And_Medical_Record_System.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Hospital_Apponintment_And_Medical_Record_System.entity.Appointment;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Appointment.AppointmentStatus;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Patient;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer>{


	List<Appointment> findByAppointmentDateTime(LocalDateTime appointmentDateTime);

	List<Appointment> findByStatus(AppointmentStatus status);

	List<Appointment> findByDoctorDoctorId(Integer doctorId);

	List<Appointment> findByPatientPatientId(Integer patientId);

	boolean existsByDoctorDoctorIdAndAppointmentDateTime(Integer doctorId, LocalDateTime appointmentDateTime);

	boolean existsByPatientPatientIdAndAppointmentDateTime(Integer patientId, LocalDateTime appointmentDate);




	
	

}
