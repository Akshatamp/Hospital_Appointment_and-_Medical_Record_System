package com.Hospital_Apponintment_And_Medical_Record_System.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Hospital_Apponintment_And_Medical_Record_System.entity.MedicalRecord;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Prescription;
import com.Hospital_Apponintment_And_Medical_Record_System.repository.MedicalRecordRepository;
import com.Hospital_Apponintment_And_Medical_Record_System.repository.PrescriptionRepository;

import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;

@Repository
public class PrescriptionDao {
	@Autowired
	private PrescriptionRepository prescriptionRepository;
	
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;


	public Prescription savePrescription(Prescription prescription, Integer recordId) {
	    Optional<MedicalRecord> optional = medicalRecordRepository.findById(recordId);
	    if (optional.isPresent()) {
	        MedicalRecord record = optional.get();
	        prescription.setMedicalRecord(record);
	        return prescriptionRepository.save(prescription);
	    } else {
	        throw new RuntimeException("Medical record not found");
	    }
	}
	

	// get all Prescription
	public List<Prescription> getAllPrescriptions() {
		List<Prescription> prescriptions = prescriptionRepository.findAll();

		if (prescriptions.isEmpty()) {
			throw new NoRecordAvailableException("No Prescription available in database");
		}
		return prescriptions;
	}

	// get Prescription by id
	public Prescription getById(Integer id) {
		Optional<Prescription> opt = prescriptionRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new IdNotFoundException("Prescription not found with id: " + id);
		}
	}

	// get Prescription by recordId
	public List<Prescription> getPrescriptionByMedicalRecord(Integer recordId) {
		List<Prescription> prescriptions = prescriptionRepository.findByMedicalRecordRecordId(recordId);
		if (prescriptions.isEmpty())
			throw new NoRecordAvailableException("No Prescription available in database");
		return prescriptions;
	}

	// get Prescription by patientId
	public List<Prescription> getPrescriptionByPatientId(Integer patientId) {
	    List<MedicalRecord> records = 
	            medicalRecordRepository.findByPatientPatientId(patientId);
	    if (records.isEmpty()) {
	        throw new NoRecordAvailableException("No Medical Records found for this patient");
	    }
	    List<Prescription> allPrescriptions = new ArrayList<>();
	    for (MedicalRecord record : records) {
	        List<Prescription> prescriptions =
	                prescriptionRepository.findByMedicalRecordRecordId(record.getRecordId());
	        allPrescriptions.addAll(prescriptions);
	    }
	    if (allPrescriptions.isEmpty()) {
	        throw new NoRecordAvailableException("No Prescriptions available for this patient");
	    }
	    return allPrescriptions;
	}




}
