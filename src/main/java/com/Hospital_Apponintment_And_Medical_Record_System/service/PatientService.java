package com.Hospital_Apponintment_And_Medical_Record_System.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Hospital_Apponintment_And_Medical_Record_System.dao.PatientDao;
import com.Hospital_Apponintment_And_Medical_Record_System.dto.ResponseStructure;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Patient;

import jsp.springboot.dto.ResponeseStructure;

@Service
public class PatientService {
	@Autowired
	private PatientDao patientDao;

	// 1.
	public ResponseEntity<ResponseStructure<Patient>> savePatient(Patient patient) {
		ResponseStructure<Patient> response = new ResponseStructure<>();

		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Patient saved successfully");
		response.setData(patientDao.savePatient(patient));

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	// 2.

	public ResponseEntity<ResponeseStructure<List<Patient>>> getAllPatients() {
		ResponeseStructure<List<Patient>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Patient fetched successfully");
		r.setData(patientDao.getAllPatients());
		return new ResponseEntity<ResponeseStructure<List<Patient>>>(r, HttpStatus.OK);
	}

	// 3. Get Patient by id
	public ResponseEntity<ResponeseStructure<Patient>> getById(Integer id) {
		ResponeseStructure<Patient> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Patient fetched successfully");
		r.setData(patientDao.getById(id));
		return new ResponseEntity<ResponeseStructure<Patient>>(r, HttpStatus.OK);
	}

	// 4. Get Patient by phone
	public ResponseEntity<ResponeseStructure<List<Patient>>> getPatientByPhone(Long phone) {
		ResponeseStructure<List<Patient>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Patient fetched successfully");
		r.setData(patientDao.getPatientByPhone(phone));
		return new ResponseEntity<ResponeseStructure<List<Patient>>>(r, HttpStatus.OK);
	}

	// 5. Get Patient by age
	public ResponseEntity<ResponeseStructure<List<Patient>>> getPatientByAge(Integer age) {
		ResponeseStructure<List<Patient>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Patient fetched successfully");
		r.setData(patientDao.getPatientByAge(age));
		return new ResponseEntity<ResponeseStructure<List<Patient>>>(r, HttpStatus.OK);
	}
//
//	// 6. Get Patient by MedicalRecord
//	public ResponseEntity<ResponeseStructure<List<Patient>>> getByPatientMedicalRecord(Integer recordId) {
//		ResponeseStructure<List<Patient>> r = new ResponeseStructure<>();
//		r.setStatusCode(HttpStatus.OK.value());
//		r.setMessage("Patient fetched successfully");
//		r.setData(patientDao.getByPatientMedicalRecord(recordId));
//		return new ResponseEntity<ResponeseStructure<List<Patient>>>(r, HttpStatus.OK);
//	}
//
//	// 7. Get Patient by appointmentId
//	public ResponseEntity<ResponeseStructure<List<Patient>>> getByAppointmentId(Integer appointmentId) {
//		ResponeseStructure<List<Patient>> r = new ResponeseStructure<>();
//		r.setStatusCode(HttpStatus.OK.value());
//		r.setMessage("Patient fetched successfully");
//		r.setData(patientDao.getByAppointmentId(appointmentId));
//		return new ResponseEntity<ResponeseStructure<List<Patient>>>(r, HttpStatus.OK);
//	}

	// 8. Update doctor
	public ResponseEntity<ResponeseStructure<Patient>> updatePatient(Patient doctor) {
		ResponeseStructure<Patient> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Patient updated successfully");
		r.setData(patientDao.updatePatient(doctor));
		return new ResponseEntity<ResponeseStructure<Patient>>(r, HttpStatus.OK);
	}

	// 9. Delete doctor
	public ResponseEntity<ResponeseStructure<String>> deletePatient(Integer id) {
		ResponeseStructure<String> r = new ResponeseStructure<>();
		patientDao.deletePatient(id);
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("Patient deleted successfully");
		r.setData("Deleted");
		return new ResponseEntity<ResponeseStructure<String>>(r, HttpStatus.OK);
	}

}
