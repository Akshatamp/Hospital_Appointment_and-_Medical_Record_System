package com.Hospital_Apponintment_And_Medical_Record_System.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Hospital_Apponintment_And_Medical_Record_System.dao.DepartmentDao;
import com.Hospital_Apponintment_And_Medical_Record_System.dao.DoctorDao;
import com.Hospital_Apponintment_And_Medical_Record_System.dto.ResponseStructure;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Department;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Doctor;
import com.Hospital_Apponintment_And_Medical_Record_System.exception.DepartmentIdNotFoundException;

import jsp.springboot.dto.ResponeseStructure;

@Service
public class DoctorService {
	@Autowired
	private DoctorDao doctorDao;


	
	
	@Autowired
	private DepartmentDao departmentDao;

	public ResponseEntity<ResponseStructure<Doctor>> saveDoctor(Doctor doctor) {

	    ResponseStructure<Doctor> response = new ResponseStructure<>();

	    if (doctor.getDepartment() == null || doctor.getDepartment().getDepartmentId() == null) {
	        throw new DepartmentIdNotFoundException("Please enter Department Id");
	    }

	    Department department = departmentDao.getById(
	            doctor.getDepartment().getDepartmentId()
	    );

	    if (department == null) {
	        throw new DepartmentIdNotFoundException("Department not found");
	    }

	    doctor.setDepartment(department);

	    response.setStatusCode(HttpStatus.CREATED.value());
	    response.setMessage("Doctor saved successfully");
	    response.setData(doctorDao.saveDoctor(doctor));

	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	// 2.

	public ResponseEntity<ResponeseStructure<List<Doctor>>> getAllDoctors() {
		ResponeseStructure<List<Doctor>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("doctor fetched successfully");
		r.setData(doctorDao.getAllDoctors());
		return new ResponseEntity<ResponeseStructure<List<Doctor>>>(r, HttpStatus.OK);
	}

	// 3. Get book by id
	public ResponseEntity<ResponeseStructure<Doctor>> getById(Integer id) {
		ResponeseStructure<Doctor> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("doctor fetched successfully");
		r.setData(doctorDao.getById(id));
		return new ResponseEntity<ResponeseStructure<Doctor>>(r, HttpStatus.OK);
	}

	// 4. Get book by specialization
	public ResponseEntity<ResponeseStructure<List<Doctor>>> getDoctorBySpecilization(String specilization) {
		ResponeseStructure<List<Doctor>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("doctor fetched successfully");
		r.setData(doctorDao.getDoctorBySpecilization(specilization));
		return new ResponseEntity<ResponeseStructure<List<Doctor>>>(r, HttpStatus.OK);
	}

	public ResponseEntity<ResponeseStructure<List<Doctor>>> getByDepartmentId(Integer departmentId) {
		ResponeseStructure<List<Doctor>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("doctor fetched successfully");
		r.setData(doctorDao.getByDepartmentId(departmentId));
		return new ResponseEntity<ResponeseStructure<List<Doctor>>>(r, HttpStatus.OK);
	}

	public ResponseEntity<ResponeseStructure<List<Doctor>>> getByPatientId(Integer patientId) {
		ResponeseStructure<List<Doctor>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("doctor fetched successfully");
		r.setData(doctorDao.getByPatientId(patientId));
		return new ResponseEntity<ResponeseStructure<List<Doctor>>>(r, HttpStatus.OK);
	}

	public ResponseEntity<ResponeseStructure<Doctor>> getDoctorByAppointmentId(Integer appointmentId) {

	    ResponeseStructure<Doctor> r = new ResponeseStructure<>();

	    r.setStatusCode(HttpStatus.OK.value());
	    r.setMessage("Doctor fetched successfully");
	    r.setData(doctorDao.getDoctorByAppointmentId(appointmentId));

	    return new ResponseEntity<>(r, HttpStatus.OK);
	}


	public ResponseEntity<ResponeseStructure<List<Doctor>>> getDoctorByAvailableDays(List<String> availableDays) {
		ResponeseStructure<List<Doctor>> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("doctor fetched successfully");
		r.setData(doctorDao.getDoctorByAvailableDays(availableDays));
		return new ResponseEntity<ResponeseStructure<List<Doctor>>>(r, HttpStatus.OK);
	}

	// 5. Update doctor
	public ResponseEntity<ResponeseStructure<Doctor>> updateDoctor(Doctor doctor) {
		ResponeseStructure<Doctor> r = new ResponeseStructure<>();
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("doctor updated successfully");
		r.setData(doctorDao.updateDoctor(doctor));
		return new ResponseEntity<ResponeseStructure<Doctor>>(r, HttpStatus.OK);
	}

	// 6. Delete doctor
	public ResponseEntity<ResponeseStructure<String>> deleteDoctor(Integer id) {
		ResponeseStructure<String> r = new ResponeseStructure<>();
		doctorDao.deleteDoctor(id);
		r.setStatusCode(HttpStatus.OK.value());
		r.setMessage("doctor deleted successfully");
		r.setData("Deleted");
		return new ResponseEntity<ResponeseStructure<String>>(r, HttpStatus.OK);
	}

}
