package com.Hospital_Apponintment_And_Medical_Record_System.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hospital_Apponintment_And_Medical_Record_System.dto.ResponseStructure;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Prescription;
import com.Hospital_Apponintment_And_Medical_Record_System.repository.PrescriptionRepository;
import com.Hospital_Apponintment_And_Medical_Record_System.service.PrescriptionService;

import jsp.springboot.dto.ResponeseStructure;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {
	@Autowired
	private PrescriptionRepository prescriptionRepository;
	@Autowired
	private PrescriptionService prescriptionService;

	@PostMapping("/{recordId}")
	public ResponseEntity<ResponseStructure<Prescription>> savePrescription(@RequestBody Prescription prescription,@PathVariable Integer recordId) {
		return prescriptionService.savePrescription(prescription,recordId);
	}

	// 2. fetch all the record
	@GetMapping
	public ResponseEntity<ResponeseStructure<List<Prescription>>> getAllPrescriptions() {

		return prescriptionService.getAllPrescriptions();
	}

	// 3. fetch record by Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponeseStructure<Prescription>> getById(@PathVariable Integer id) {
		return prescriptionService.getById(id);
	}


	// 4.fetch Prescription by doctorId
	@GetMapping("recordId/{recordId}")
	public ResponseEntity<ResponeseStructure<List<Prescription>>> getPrescriptionByMedicalRecord(@PathVariable Integer recordId) {
		return prescriptionService.getPrescriptionByMedicalRecord(recordId);
	}

//	// 5.fetch prescription by patientId	
	@GetMapping("patientId/{patientId}")
	public ResponseEntity<ResponeseStructure<List<Prescription>>> 
	getPrescriptionByPatientId(@PathVariable Integer patientId) {

	    return prescriptionService.getPrescriptionByPatientId(patientId);
	}




}
