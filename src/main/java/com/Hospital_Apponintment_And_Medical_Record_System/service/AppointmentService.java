package com.Hospital_Apponintment_And_Medical_Record_System.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Hospital_Apponintment_And_Medical_Record_System.dao.AppointmentDao;
import com.Hospital_Apponintment_And_Medical_Record_System.dao.DoctorDao;
import com.Hospital_Apponintment_And_Medical_Record_System.dao.PatientDao;
import com.Hospital_Apponintment_And_Medical_Record_System.dto.ResponseStructure;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Appointment;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Appointment.AppointmentStatus;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Doctor;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Patient;
import com.Hospital_Apponintment_And_Medical_Record_System.exception.DoctorIdNotFoundException;
import com.Hospital_Apponintment_And_Medical_Record_System.exception.PatientIdNotFoundException;

import jsp.springboot.dto.ResponeseStructure;

@Service
public class AppointmentService {
	@Autowired
	private AppointmentDao appointmentDao;

	
	@Autowired
	private DoctorDao doctorDao;

	@Autowired
	private PatientDao patientDao;

	public ResponseEntity<ResponseStructure<Appointment>> saveAppointment(Appointment appointment) {

	    ResponseStructure<Appointment> response = new ResponseStructure<>();

	    Integer doctorId = appointment.getDoctor().getDoctorId();
	    Doctor doctor = doctorDao.getById(doctorId);

	    if (doctor == null) {
	        throw new DoctorIdNotFoundException("Doctor not found");
	    }

	    Integer patientId = appointment.getPatient().getPatientId();
	    Patient patient = patientDao.getById(patientId);

	    if (patient == null) {
	        throw new PatientIdNotFoundException("Patient not found");
	    }
	    
	    if (appointment.getPatient() == null || appointment.getPatient().getPatientId() == null) {
	        throw new PatientIdNotFoundException("Please enter Patient Id");
	    }

	    if (appointment.getDoctor() == null || appointment.getDoctor().getDoctorId() == null) {
	        throw new DoctorIdNotFoundException("Please enter Doctor Id");
	    }

	    appointment.setDoctor(doctor);
	    appointment.setPatient(patient);

	    response.setStatusCode(HttpStatus.CREATED.value());
	    response.setMessage("Appointment saved successfully");
	    response.setData(appointmentDao.saveAppointment(appointment));

	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	// 2.

	public ResponseEntity<ResponeseStructure<List<Appointment>>> getAllAppointments() {
		ResponeseStructure<List<Appointment>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Appointment fetched successfully");
		r.setData(appointmentDao.getAllAppointments());
		return new ResponseEntity<ResponeseStructure<List<Appointment>>>(r, HttpStatus.OK);
	}

	// 3. Get Appointment by id
	public ResponseEntity<ResponeseStructure<Appointment>> getById(Integer id) {
		ResponeseStructure<Appointment> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Appointment fetched successfully");
		r.setData(appointmentDao.getById(id));
		return new ResponseEntity<ResponeseStructure<Appointment>>(r, HttpStatus.OK);
	}

	// 4. Get Appointment by phone
	public ResponseEntity<ResponeseStructure<List<Appointment>>> getAppointmentByAppointmentDateTime(LocalDateTime appointmentDateTime) {
		ResponeseStructure<List<Appointment>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Appointment fetched successfully");
		r.setData(appointmentDao.getAppointmentByAppointmentDateTime(appointmentDateTime));
		return new ResponseEntity<ResponeseStructure<List<Appointment>>>(r, HttpStatus.OK);
	}

	// 5. Get Appointment by age
	public ResponseEntity<ResponeseStructure<List<Appointment>>> getAppointmentByStatus(AppointmentStatus status) {
		ResponeseStructure<List<Appointment>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Appointment fetched successfully");
		r.setData(appointmentDao.getAppointmentByStatus(status));
		return new ResponseEntity<ResponeseStructure<List<Appointment>>>(r, HttpStatus.OK);
	}

	// 6. Get Appointment by MedicalRecord
	public ResponseEntity<ResponeseStructure<List<Appointment>>> getAppointmentByDoctor(Integer doctorId) {
		ResponeseStructure<List<Appointment>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Appointment fetched successfully");
		r.setData(appointmentDao.getAppointmentByDoctor(doctorId));
		return new ResponseEntity<ResponeseStructure<List<Appointment>>>(r, HttpStatus.OK);
	}

	// 7. Get Appointment by appointmentId
	public ResponseEntity<ResponeseStructure<List<Appointment>>> getAppointmentByPatient(Integer patientId) {
		ResponeseStructure<List<Appointment>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Appointment fetched successfully");
		r.setData(appointmentDao.getAppointmentByPatient(patientId));
		return new ResponseEntity<ResponeseStructure<List<Appointment>>>(r, HttpStatus.OK);
	}

	// 8. Update Appointment
	public ResponseEntity<ResponeseStructure<Appointment>> updateAppointment(Appointment appointment) {
		ResponeseStructure<Appointment> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Appointment updated successfully");
		r.setData(appointmentDao.updateAppointment(appointment));
		return new ResponseEntity<ResponeseStructure<Appointment>>(r, HttpStatus.OK);
	}

	// 9. Delete Appointment
	public ResponseEntity<ResponeseStructure<String>> deleteAppointment(Integer id) {
		ResponeseStructure<String> r = new ResponeseStructure<>();
		appointmentDao.deleteAppointment(id);
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Appointment deleted successfully");
		r.setData("Deleted");
		return new ResponseEntity<ResponeseStructure<String>>(r, HttpStatus.OK);
	}

}
