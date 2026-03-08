package com.Hospital_Apponintment_And_Medical_Record_System.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Hospital_Apponintment_And_Medical_Record_System.dao.MedicalRecordDao;
import com.Hospital_Apponintment_And_Medical_Record_System.dao.PrescriptionDao;
import com.Hospital_Apponintment_And_Medical_Record_System.dto.ResponseStructure;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.MedicalRecord;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Prescription;

import jsp.springboot.dto.ResponeseStructure;

@Service
public class PrescriptionService {
	@Autowired
	private PrescriptionDao prescriptionDao;
	@Autowired
    private MedicalRecordDao medicalRecordDao;

	// 1.

//	
	public ResponseEntity<ResponseStructure<Prescription>> savePrescription(Prescription prescription, Integer recordId) {
	    ResponseStructure<Prescription> response = new ResponseStructure<>();

	    // Validate MedicalRecord ID
	    if (recordId == null) {
	        throw new RuntimeException("Please provide MedicalRecord ID");
	    }

	    // Fetch MedicalRecord entity from DB
	    MedicalRecord medicalRecord = medicalRecordDao.getById(recordId);
	    if (medicalRecord == null) {
	        throw new RuntimeException("MedicalRecord not found for ID: " + recordId);
	    }

	    // Attach the real MedicalRecord to prescription
	    prescription.setMedicalRecord(medicalRecord);

	    // Optionally, set default values if any field is null
	    if (prescription.getMedicine() == null) prescription.setMedicine("Not provided");
	    if (prescription.getDosage() == null) prescription.setDosage("Not provided");
	    if (prescription.getInstruction() == null) prescription.setInstruction("Not provided");

	    // Save prescription
	    Prescription savedPrescription = prescriptionDao.savePrescription(prescription, recordId);

	    // Prepare response
	    response.setStatusCode(HttpStatus.CREATED.value());
	    response.setMessage("Prescription saved successfully");
	    response.setData(savedPrescription);

	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// 2.

	public ResponseEntity<ResponeseStructure<List<Prescription>>> getAllPrescriptions() {
		ResponeseStructure<List<Prescription>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Prescription fetched successfully");
		r.setData(prescriptionDao.getAllPrescriptions());
		return new ResponseEntity<ResponeseStructure<List<Prescription>>>(r, HttpStatus.OK);
	}

	// 3. Get MedicalRecord by id
	public ResponseEntity<ResponeseStructure<Prescription>> getById(Integer id) {
		ResponeseStructure<Prescription> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Prescription fetched successfully");
		r.setData(prescriptionDao.getById(id));
		return new ResponseEntity<ResponeseStructure<Prescription>>(r, HttpStatus.OK);
	}


	public ResponseEntity<ResponeseStructure<List<Prescription>>> getPrescriptionByMedicalRecord(Integer recordId) {
		ResponeseStructure<List<Prescription>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Prescription fetched successfully");
		r.setData(prescriptionDao.getPrescriptionByMedicalRecord(recordId));
		return new ResponseEntity<ResponeseStructure<List<Prescription>>>(r, HttpStatus.OK);
	}

	public ResponseEntity<ResponeseStructure<List<Prescription>>> 
	getPrescriptionByPatientId(Integer patientId) {
	    List<Prescription> prescriptions =
	            prescriptionDao.getPrescriptionByPatientId(patientId);
	    ResponeseStructure<List<Prescription>> response = new ResponeseStructure<>();
	    response.setStatusCode(HttpStatus.OK.value());
	    response.setMessage("Prescription fetched successfully");
	    response.setData(prescriptions);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}






}
