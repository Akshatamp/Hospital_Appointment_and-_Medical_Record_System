package com.Hospital_Apponintment_And_Medical_Record_System.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Hospital_Apponintment_And_Medical_Record_System.entity.Appointment;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.MedicalRecord;
import com.Hospital_Apponintment_And_Medical_Record_System.repository.AppointmentRepository;
import com.Hospital_Apponintment_And_Medical_Record_System.repository.MedicalRecordRepository;

import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;

@Repository
public class MedicalRecordDao {
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	 @Autowired
	 private AppointmentRepository appointmentRepository;

//	public MedicalRecord saveMedicalRecord(MedicalRecord record, Integer appointmentId) {
//		Optional<Appointment> optional = appointmentRepository.findById(appointmentId);
//		if (optional.isPresent()) {
//			Appointment appointment = optional.get();
//			if (appointment.getStatus() == Appointment.AppointmentStatus.COMPLETED) {
//				return medicalRecordRepository.save(record);
//			} else {
//				throw new RuntimeException("Appointment is not completed yet");
//			}
//		} else {
//			throw new RuntimeException("Appointment not found");
//		}
//	}
	 
	 public MedicalRecord saveMedicalRecord(MedicalRecord record, Integer appointmentId) {

		    Optional<Appointment> optional = appointmentRepository.findById(appointmentId);

		    if (!optional.isPresent()) {
		        throw new RuntimeException("Appointment not found");
		    }

		    Appointment appointment = optional.get();

		    // Check appointment completed
		    if (appointment.getStatus() != Appointment.AppointmentStatus.COMPLETED) {
		        throw new RuntimeException("Appointment is not completed yet");
		    }

		    record.setPatient(appointment.getPatient());
		    record.setDoctor(appointment.getDoctor());
		    

		    return medicalRecordRepository.save(record);
		}

	// get all MedicalRecord
	public List<MedicalRecord> getAllMedicalRecords() {
		List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();

		if (medicalRecords.isEmpty()) {
			throw new NoRecordAvailableException("No MedicalRecord available in database");
		}
		return medicalRecords;
	}

	// get MedicalRecord by id
	public MedicalRecord getById(Integer id) {
		Optional<MedicalRecord> opt = medicalRecordRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new IdNotFoundException("MedicalRecord not found with id: " + id);
		}
	}
	// get MedicalRecord by visitDate

	public List<MedicalRecord> getMedicalRecordByVisitDate(LocalDate visitDate) {
		List<MedicalRecord> medicalRecords = medicalRecordRepository.findByVisitDate(visitDate);
		if (medicalRecords.isEmpty())
			throw new NoRecordAvailableException("No MedicalRecord available in database");
		return medicalRecords;
	}

	// get MedicalRecord by doctorId
	public List<MedicalRecord> getMedicalRecordByDoctorId(Integer doctorId) {
		List<MedicalRecord> medicalRecords = medicalRecordRepository.findByDoctorDoctorId(doctorId);
		if (medicalRecords.isEmpty())
			throw new NoRecordAvailableException("No MedicalRecord available in database");
		return medicalRecords;
	}

	// get MedicalRecord by patientId
	public List<MedicalRecord> getMedicalRecordByPatientId(Integer patientId) {
		List<MedicalRecord> medicalRecords = medicalRecordRepository.findByPatientPatientId(patientId);
		if (medicalRecords.isEmpty())
			throw new NoRecordAvailableException("No MedicalRecord available in database");
		return medicalRecords;
	}
	
	// get MedicalRecord by appointmentId
	public List<MedicalRecord> getMedicalRecordByAppointmentId(Integer appointmentId) {
	    Optional<Appointment> optionalAppointment =
	            appointmentRepository.findById(appointmentId);
	    if (!optionalAppointment.isPresent()) {
	        throw new NoRecordAvailableException("Appointment not found");
	    }
	    Appointment appointment = optionalAppointment.get();
	    Integer patientId = appointment.getPatient().getPatientId();
	    List<MedicalRecord> records =
	            medicalRecordRepository.findByPatientPatientId(patientId);
	    if (records == null || records.isEmpty()) {
	        throw new NoRecordAvailableException("No Medical Records found for this patient");
	    }
	    return records;
	}


	


}
