package com.Hospital_Apponintment_And_Medical_Record_System.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Hospital_Apponintment_And_Medical_Record_System.entity.Patient;
import com.Hospital_Apponintment_And_Medical_Record_System.repository.PatientRepository;

import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;

@Repository
public class PatientDao {
	@Autowired
	private PatientRepository patientRepository;

	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	// get all patient
	public List<Patient> getAllPatients() {
		List<Patient> patients = patientRepository.findAll();

		if (patients.isEmpty()) {
			throw new NoRecordAvailableException("No doctor available in database");
		}
		return patients;
	}

	// get patient by id
	public Patient getById(Integer id) {
		Optional<Patient> opt = patientRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new IdNotFoundException("doctor not found with id: " + id);
		}
	}
	// get patient by phone

	public List<Patient> getPatientByPhone(Long phone) {
		List<Patient> patient = patientRepository.findByPhone(phone);
		if (patient.isEmpty())
			throw new NoRecordAvailableException("No doctor available in database");
		return patient;
	}

	// get patient by age
	public List<Patient> getPatientByAge(Integer age) {
		List<Patient> patient = patientRepository.findByAgeGreaterThan(age);
		if (patient.isEmpty())
			throw new NoRecordAvailableException("No doctor available in database");
		return patient;
	}

//	// get patient by appointmentId
//	public List<Patient> getByAppointmentId(Integer appointmentId) {
//		List<Patient> patient = patientRepository.findByPatientAppointmentId(appointmentId);
//		if (patient.isEmpty())
//			throw new NoRecordAvailableException("No doctor available in database");
//		return patient;
//	}
//
//	// get patient by medicalRecord
//	public List<Patient> getByPatientMedicalRecord(Integer recordId) {
//		List<Patient> patient = patientRepository.findByPatientMedicalRecord(recordId);
//		if (patient.isEmpty())
//			throw new NoRecordAvailableException("No doctor available in database");
//		return patient;
//	}

	// update patient
	public Patient updatePatient(Patient patient) {

		if (patient.getPatientId() == null) {
			throw new IdNotFoundException("ID must be passed to update a record");
		}

		Optional<Patient> opt = patientRepository.findById(patient.getPatientId());

		if (opt.isPresent()) {
			return patientRepository.save(patient);
		} else {
			throw new IdNotFoundException("Id not available in database");
		}
	}

	// delete patient
	public void deletePatient(Integer id) {
		Optional<Patient> opt = patientRepository.findById(id);

		if (opt.isPresent()) {
			patientRepository.delete(opt.get());
		} else {
			throw new IdNotFoundException("Id does not exist in database");
		}
	}

}
