package com.Hospital_Apponintment_And_Medical_Record_System.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Hospital_Apponintment_And_Medical_Record_System.entity.Appointment;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Appointment.AppointmentStatus;
import com.Hospital_Apponintment_And_Medical_Record_System.repository.AppointmentRepository;

import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;

@Repository
public class AppointmentDao {

	@Autowired
	private AppointmentRepository appointmentRepository;

	// SAVE
	public Appointment saveAppointment(Appointment appointment) {
		// Rule 1: Doctor cannot have 2 appointments at same time
		boolean doctorBusy = appointmentRepository.existsByDoctorDoctorIdAndAppointmentDateTime(
				appointment.getDoctor().getDoctorId(), appointment.getAppointmentDateTime());
		if (doctorBusy) {
			throw new RuntimeException("Doctor already has an appointment at this time");
		}

		// Rule 2: Patient cannot have 2 appointments on same day
		LocalDateTime appointmentDate = appointment.getAppointmentDateTime();
		boolean patientHasAppointment = appointmentRepository
				.existsByDoctorDoctorIdAndAppointmentDateTime(appointment.getPatient().getPatientId(), appointmentDate);
		if (patientHasAppointment) {
			throw new RuntimeException("Patient already has an appointment on this day");
		}
		return appointmentRepository.save(appointment);
	}

	//  GET ALL

	public List<Appointment> getAllAppointments() {
		List<Appointment> appointments = appointmentRepository.findAll();
		if (appointments.isEmpty()) {
			throw new NoRecordAvailableException("No appointments available in database");
		}
		return appointments;
	}

	//GET BY ID 

	public Appointment getById(Integer id) {
		Optional<Appointment> opt = appointmentRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new IdNotFoundException("Appointment not found with id: " + id);
		}
	}

	// GET BY DATE

	public List<Appointment> getAppointmentByAppointmentDateTime(LocalDateTime appointmentDateTime) {
		List<Appointment> appointments = appointmentRepository.findByAppointmentDateTime(appointmentDateTime);
		if (appointments.isEmpty()) {
			throw new NoRecordAvailableException("No appointments found for given date and time");
		}
		return appointments;
	}

	//GET BY STATUS 

	public List<Appointment> getAppointmentByStatus(AppointmentStatus status) {
		List<Appointment> appointments = appointmentRepository.findByStatus(status);
		if (appointments.isEmpty()) {
			throw new NoRecordAvailableException("No appointments found with status: " + status);
		}
		return appointments;
	}

	//  GET BY DOCTOR

	public List<Appointment> getAppointmentByDoctor(Integer doctorId) {
		List<Appointment> appointments = appointmentRepository.findByDoctorDoctorId(doctorId);
		if (appointments.isEmpty()) {
			throw new NoRecordAvailableException("No appointments found for doctor id: " + doctorId);
		}
		return appointments;
	}

	// GET BY PATIENT 

	public List<Appointment> getAppointmentByPatient(Integer patientId) {
		List<Appointment> appointments = appointmentRepository.findByPatientPatientId(patientId);
		if (appointments.isEmpty()) {
			throw new NoRecordAvailableException("No appointments found for patient id: " + patientId);
		}
		return appointments;
	}

	//  UPDATE

	public Appointment updateAppointment(Appointment appointment) {
		if (appointment.getAppointmentId() == null) {
			throw new IdNotFoundException("Appointment ID must be provided for update");
		}
		Optional<Appointment> opt = appointmentRepository.findById(appointment.getAppointmentId());
		if (opt.isPresent()) {
			return appointmentRepository.save(appointment);
		} else {
			throw new IdNotFoundException("Appointment not found in database");
		}
	}

	// DELETE

	public void deleteAppointment(Integer id) {
		Optional<Appointment> opt = appointmentRepository.findById(id);
		if (opt.isPresent()) {
			appointmentRepository.delete(opt.get());
		} else {
			throw new IdNotFoundException("Appointment not found with id: " + id);
		}
	}
}
