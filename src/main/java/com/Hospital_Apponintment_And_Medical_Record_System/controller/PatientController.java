package com.Hospital_Apponintment_And_Medical_Record_System.controller;

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
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Patient;
import com.Hospital_Apponintment_And_Medical_Record_System.repository.PatientRepository;
import com.Hospital_Apponintment_And_Medical_Record_System.service.PatientService;

import jsp.springboot.dto.ResponeseStructure;

@RestController
@RequestMapping("/patients")
public class PatientController {
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private PatientService patientService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Patient>> savePatient(@RequestBody Patient patient) {
		return patientService.savePatient(patient);
	}

	// 2. fetch all the record
	@GetMapping
	public ResponseEntity<ResponeseStructure<List<Patient>>> getAllPatients() {

		return patientService.getAllPatients();
	}

	// 3. fetch record by Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponeseStructure<Patient>> getById(@PathVariable Integer id) {
		return patientService.getById(id);
	}

	// 4.fetch patient by phone
	@GetMapping("/phone/{phone}")
	public ResponseEntity<ResponeseStructure<List<Patient>>> getPatientByPhone(
			@PathVariable Long phone) {
		return patientService.getPatientByPhone(phone);
	}

	// 5.fetch patient by age
	@GetMapping("age/{age}")
	public ResponseEntity<ResponeseStructure<List<Patient>>> getPatientByAge(@PathVariable Integer age) {
		return patientService.getPatientByAge(age);
	}

//	// 6.fetch patient by recordId
//	@GetMapping("recordId/{recordId}")
//	public ResponseEntity<ResponeseStructure<List<Patient>>> getByPatientMedicalRecord(@PathVariable Integer recordId) {
//		return patientService.getByPatientMedicalRecord(recordId);
//	}
//
//	// 7.fetch patient by appointmentId
//	@GetMapping("appointmentId/{appointmentId}")
//	public ResponseEntity<ResponeseStructure<List<Patient>>> getByAppointmentId(@PathVariable Integer appointmentId) {
//		return patientService.getByAppointmentId(appointmentId);
//	}


	// 8. update record
	@PutMapping("/{id}")
	public ResponseEntity<ResponeseStructure<Patient>> updatePatient(@RequestBody Patient patient) {
		return patientService.updatePatient(patient);
	}

	// 9. delete record
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponeseStructure<String>> deletePatient(@PathVariable Integer id) {
		return patientService.deletePatient(id);

	}

}
