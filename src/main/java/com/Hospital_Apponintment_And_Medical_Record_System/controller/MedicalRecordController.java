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
import com.Hospital_Apponintment_And_Medical_Record_System.entity.MedicalRecord;
import com.Hospital_Apponintment_And_Medical_Record_System.repository.MedicalRecordRepository;
import com.Hospital_Apponintment_And_Medical_Record_System.service.MedicalRecordService;

import jsp.springboot.dto.ResponeseStructure;

@RestController
@RequestMapping("/medicalRecords")
public class MedicalRecordController {
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	@Autowired
	private MedicalRecordService medicalRecordService;

	@PostMapping("/{appointmentId}")
	public ResponseEntity<ResponseStructure<MedicalRecord>> saveMedicalRecord(@RequestBody MedicalRecord medicalRecord, @PathVariable Integer appointmentId) {
		return medicalRecordService.saveMedicalRecord(medicalRecord,appointmentId);
	}

	// 2. fetch all the record
	@GetMapping
	public ResponseEntity<ResponeseStructure<List<MedicalRecord>>> getAllMedicalRecords() {

		return medicalRecordService.getAllMedicalRecords();
	}

	// 3. fetch record by Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponeseStructure<MedicalRecord>> getById(@PathVariable Integer id) {
		return medicalRecordService.getById(id);
	}

	// 4.fetch MedicalRecord by visitDate
	@GetMapping("/visitDate/{visitDate}")
	public ResponseEntity<ResponeseStructure<List<MedicalRecord>>> getMedicalRecordByVisitDate(
			@PathVariable LocalDate visitDate) {
		return medicalRecordService.getMedicalRecordByVisitDate(visitDate);
	}

	// 5.fetch MedicalRecord by doctorId
	@GetMapping("doctorId/{doctorId}")
	public ResponseEntity<ResponeseStructure<List<MedicalRecord>>> getMedicalRecordByDoctorId(@PathVariable Integer doctorId) {
		return medicalRecordService.getMedicalRecordByDoctorId(doctorId);
	}

	// 6.fetch MedicalRecord by patientId
	@GetMapping("patientId/{patientId}")
	public ResponseEntity<ResponeseStructure<List<MedicalRecord>>> getMedicalRecordByPatientId(@PathVariable Integer patientId) {
		return medicalRecordService.getMedicalRecordByPatientId(patientId);
	}

	// 7.fetch MedicalRecord by appointmentId	
	@GetMapping("appointmentId/{appointmentId}")
	public ResponseEntity<ResponeseStructure<List<MedicalRecord>>> 
	getMedicalRecordByAppointmentId(@PathVariable Integer appointmentId) {

	    return medicalRecordService.getMedicalRecordByAppointmentId(appointmentId);
	}



}
