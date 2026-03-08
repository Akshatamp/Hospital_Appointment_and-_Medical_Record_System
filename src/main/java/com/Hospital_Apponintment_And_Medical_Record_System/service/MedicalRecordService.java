package com.Hospital_Apponintment_And_Medical_Record_System.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Hospital_Apponintment_And_Medical_Record_System.dao.DoctorDao;
import com.Hospital_Apponintment_And_Medical_Record_System.dao.MedicalRecordDao;
import com.Hospital_Apponintment_And_Medical_Record_System.dao.PatientDao;
import com.Hospital_Apponintment_And_Medical_Record_System.dto.ResponseStructure;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Doctor;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.MedicalRecord;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Patient;
import com.Hospital_Apponintment_And_Medical_Record_System.exception.AppointmentIdNotFoundException;
import com.Hospital_Apponintment_And_Medical_Record_System.exception.DoctorIdNotFoundException;
import com.Hospital_Apponintment_And_Medical_Record_System.exception.PatientIdNotFoundException;

import jsp.springboot.dto.ResponeseStructure;

@Service
public class MedicalRecordService {
	@Autowired
	private MedicalRecordDao medicalRecordDao;
	@Autowired
	private DoctorDao doctorDao;
	
	@Autowired
	private PatientDao patientDao;


	
	public ResponseEntity<ResponseStructure<MedicalRecord>> 
	saveMedicalRecord(MedicalRecord medicalRecord, Integer appointmentId) {

	    if (appointmentId == null) {
	        throw new AppointmentIdNotFoundException("Please provide Appointment Id");
	    }
	    if (medicalRecord.getDoctor() == null || medicalRecord.getDoctor().getDoctorId() == null) {
	        throw new DoctorIdNotFoundException("Please enter Doctor Id");
	    }
	    Doctor doctor = doctorDao.getById(medicalRecord.getDoctor().getDoctorId());
	    if (doctor == null) {
	        throw new DoctorIdNotFoundException("Doctor not found");
	    }

	    // Validate Patient
	    if (medicalRecord.getPatient() == null || medicalRecord.getPatient().getPatientId() == null) {
	        throw new PatientIdNotFoundException("Please enter Patient Id");
	    }
	    Patient patient = patientDao.getById(medicalRecord.getPatient().getPatientId());
	    if (patient == null) {
	        throw new PatientIdNotFoundException("Patient not found");
	    }

	    ResponseStructure<MedicalRecord> response = new ResponseStructure<>();

	    response.setStatusCode(HttpStatus.CREATED.value());
	    response.setMessage("MedicalRecord saved successfully");
	    response.setData(
	            medicalRecordDao.saveMedicalRecord(medicalRecord, appointmentId)
	    );

	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	// 2.

	public ResponseEntity<ResponeseStructure<List<MedicalRecord>>> getAllMedicalRecords() {
		ResponeseStructure<List<MedicalRecord>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("MedicalRecord fetched successfully");
		r.setData(medicalRecordDao.getAllMedicalRecords());
		return new ResponseEntity<ResponeseStructure<List<MedicalRecord>>>(r, HttpStatus.OK);
	}

	// 3. Get MedicalRecord by id
	public ResponseEntity<ResponeseStructure<MedicalRecord>> getById(Integer id) {
		ResponeseStructure<MedicalRecord> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("MedicalRecord fetched successfully");
		r.setData(medicalRecordDao.getById(id));
		return new ResponseEntity<ResponeseStructure<MedicalRecord>>(r, HttpStatus.OK);
	}

	// 4. Get MedicalRecord by visitDate
	public ResponseEntity<ResponeseStructure<List<MedicalRecord>>> getMedicalRecordByVisitDate(LocalDate visitDate) {
		ResponeseStructure<List<MedicalRecord>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("MedicalRecord fetched successfully");
		r.setData(medicalRecordDao.getMedicalRecordByVisitDate(visitDate));
		return new ResponseEntity<ResponeseStructure<List<MedicalRecord>>>(r, HttpStatus.OK);
	}

	public ResponseEntity<ResponeseStructure<List<MedicalRecord>>> getMedicalRecordByDoctorId(Integer doctorId) {
		ResponeseStructure<List<MedicalRecord>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("MedicalRecord fetched successfully");
		r.setData(medicalRecordDao.getMedicalRecordByDoctorId(doctorId));
		return new ResponseEntity<ResponeseStructure<List<MedicalRecord>>>(r, HttpStatus.OK);
	}

	public ResponseEntity<ResponeseStructure<List<MedicalRecord>>> getMedicalRecordByPatientId(Integer patientId) {
		ResponeseStructure<List<MedicalRecord>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("MedicalRecord fetched successfully");
		r.setData(medicalRecordDao.getMedicalRecordByPatientId(patientId));
		return new ResponseEntity<ResponeseStructure<List<MedicalRecord>>>(r, HttpStatus.OK);
	}

	
	public ResponseEntity<ResponeseStructure<List<MedicalRecord>>> 
	getMedicalRecordByAppointmentId(Integer appointmentId) {

	    List<MedicalRecord> records = medicalRecordDao.getMedicalRecordByAppointmentId(appointmentId);

	    ResponeseStructure<List<MedicalRecord>> response = new ResponeseStructure<>();
	    response.setStatusCode(HttpStatus.OK.value());
	    response.setMessage("Medical Records fetched successfully");
	    response.setData(records);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	




}
