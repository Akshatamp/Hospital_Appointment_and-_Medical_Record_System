package com.Hospital_Apponintment_And_Medical_Record_System.controller;

import java.time.LocalDateTime;
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
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Appointment;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Appointment.AppointmentStatus;
import com.Hospital_Apponintment_And_Medical_Record_System.repository.AppointmentRepository;
import com.Hospital_Apponintment_And_Medical_Record_System.service.AppointmentService;

import jsp.springboot.dto.ResponeseStructure;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private AppointmentService appointmentService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Appointment>> saveAppointment(@RequestBody Appointment appointment) {
		return appointmentService.saveAppointment(appointment);
	}

	// 2. fetch all the record
	@GetMapping
	public ResponseEntity<ResponeseStructure<List<Appointment>>> getAllAppointments() {

		return appointmentService.getAllAppointments();
	}

	// 3. fetch record by Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponeseStructure<Appointment>> getById(@PathVariable Integer id) {
		return appointmentService.getById(id);
	}

	// 4.fetch patient by phone
	@GetMapping("/appointmentDateTime/{appointmentDateTime}")
	public ResponseEntity<ResponeseStructure<List<Appointment>>> getAppointmentByAppointmentDateTime(
			@PathVariable LocalDateTime appointmentDateTime) {
		return appointmentService.getAppointmentByAppointmentDateTime(appointmentDateTime);
	}

	// 5.fetch patient by age
	@GetMapping("status/{status}")
	public ResponseEntity<ResponeseStructure<List<Appointment>>> getAppointmentByStatus(@PathVariable AppointmentStatus status) {
		return appointmentService.getAppointmentByStatus(status);
	}

	// 6.fetch patient by recordId
	@GetMapping("doctorId/{doctorId}")
	public ResponseEntity<ResponeseStructure<List<Appointment>>> getAppointmentByDoctor(@PathVariable Integer doctorId) {
		return appointmentService.getAppointmentByDoctor(doctorId);
	}

	// 7.fetch patient by appointmentId
	@GetMapping("patientId/{patientId}")
	public ResponseEntity<ResponeseStructure<List<Appointment>>> getAppointmentByPatient(@PathVariable Integer patientId) {
		return appointmentService.getAppointmentByPatient(patientId);
	}


	// 8. update record
	@PutMapping("/{id}")
	public ResponseEntity<ResponeseStructure<Appointment>> updateAppointment(@RequestBody Appointment appointment) {
		return appointmentService.updateAppointment(appointment);
	}

	// 9. delete record
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponeseStructure<String>> deleteAppointment(@PathVariable Integer id) {
		return appointmentService.deleteAppointment(id);

	}

}
