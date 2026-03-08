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
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Department;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Doctor;
import com.Hospital_Apponintment_And_Medical_Record_System.repository.DoctorRepository;
import com.Hospital_Apponintment_And_Medical_Record_System.service.DoctorService;

import jsp.springboot.dto.ResponeseStructure;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
	@Autowired
	private DoctorRepository doctortRepository;
	@Autowired
	private DoctorService doctorService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Doctor>> saveDoctor(@RequestBody Doctor doctor) {
		return doctorService.saveDoctor(doctor);
	}

	// 2. fetch all the record
	@GetMapping
	public ResponseEntity<ResponeseStructure<List<Doctor>>> getAllDoctors() {

		return doctorService.getAllDoctors();
	}

	// 3. fetch record by Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponeseStructure<Doctor>> getById(@PathVariable Integer id) {
		return doctorService.getById(id);
	}

	// 4.fetch Doctor by specilization
	@GetMapping("/specilization/{specilization}")
	public ResponseEntity<ResponeseStructure<List<Doctor>>> getDoctorBySpecilization(
			@PathVariable String specilization) {
		return doctorService.getDoctorBySpecilization(specilization);
	}

	// 5.fetch Doctor by departmentId
	@GetMapping("departmentId/{departmentId}")
	public ResponseEntity<ResponeseStructure<List<Doctor>>> getByDepartmentId(@PathVariable Integer departmentId) {
		return doctorService.getByDepartmentId(departmentId);
	}

	// 6.fetch Doctor by patientId
	@GetMapping("patientId/{patientId}")
	public ResponseEntity<ResponeseStructure<List<Doctor>>> getByPatientId(@PathVariable Integer patientId) {
		return doctorService.getByPatientId(patientId);
	}

	// 7.fetch Doctor by appointmentId
	@GetMapping("appointmentId/{appointmentId}")
	public ResponseEntity<ResponeseStructure<Doctor>> getDoctorByAppointmentId(@PathVariable Integer appointmentId) {

		return doctorService.getDoctorByAppointmentId(appointmentId);
	}

	// 8.fetch Doctor by specilization
	@GetMapping("/availableDays/{availableDays}")
	public ResponseEntity<ResponeseStructure<List<Doctor>>> getDoctorByAvailableDays(
			@PathVariable List<String> availableDays) {
		return doctorService.getDoctorByAvailableDays(availableDays);
	}

	// 9. update record
	@PutMapping("/{id}")
	public ResponseEntity<ResponeseStructure<Doctor>> updateDoctor(@RequestBody Doctor doctor) {
		return doctorService.updateDoctor(doctor);
	}

	// 10. delete record
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponeseStructure<String>> deleteDoctor(@PathVariable Integer id) {
		return doctorService.deleteDoctor(id);

	}

}
