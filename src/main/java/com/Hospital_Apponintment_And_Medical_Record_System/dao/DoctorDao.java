package com.Hospital_Apponintment_And_Medical_Record_System.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Hospital_Apponintment_And_Medical_Record_System.entity.Department;
import com.Hospital_Apponintment_And_Medical_Record_System.entity.Doctor;
import com.Hospital_Apponintment_And_Medical_Record_System.repository.DoctorRepository;

import jsp.springboot.entity.Book;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;

@Repository
public class DoctorDao {
	@Autowired
	private DoctorRepository doctorRepository;

	public Doctor saveDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	// get all Doctor
	public List<Doctor> getAllDoctors() {
		List<Doctor> doctors = doctorRepository.findAll();
		if (doctors.isEmpty()) {
			throw new NoRecordAvailableException("No doctor available in database");
		}
		return doctors;
	}

	// get Doctor by id
	public Doctor getById(Integer id) {
		Optional<Doctor> opt = doctorRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new IdNotFoundException("doctor not found with id: " + id);
		}
	}
	// get Doctor by specilization

	public List<Doctor> getDoctorBySpecilization(String specilization) {
		List<Doctor> doctors = doctorRepository.findBySpecialization(specilization);
		if (doctors.isEmpty())
			throw new NoRecordAvailableException("No doctor available in database");
		return doctors;
	}

	// get Doctor by department
	public List<Doctor> getByDepartmentId(Integer departmentId) {
		List<Doctor> doctors = doctorRepository.findByDepartmentDepartmentId(departmentId);
		if (doctors.isEmpty())
			throw new NoRecordAvailableException("No doctor available in database");
		return doctors;
	}

	// get Doctor by patientId
	public List<Doctor> getByPatientId(Integer patientId) {
		List<Doctor> doctors = doctorRepository.findDoctorsByPatientId(patientId);
		if (doctors.isEmpty())
			throw new NoRecordAvailableException("No doctor available in database");
		return doctors;
	}

	public Doctor getDoctorByAppointmentId(Integer appointmentId) {

	    Doctor doctor = doctorRepository.findDoctorByAppointmentId(appointmentId);

	    if (doctor == null) {
	        throw new NoRecordAvailableException("No doctor found for this appointment");
	    }

	    return doctor;
	}


	public List<Doctor> getDoctorByAvailableDays(List<String> availableDays) {
		List<Doctor> doctors = doctorRepository.findByAvailableDays(availableDays);
		if (doctors.isEmpty())
			throw new NoRecordAvailableException("No doctor available in database");
		return doctors;
	}

	// update Doctor
	public Doctor updateDoctor(Doctor doctor) {

		if (doctor.getDoctorId() == null) {
			throw new IdNotFoundException("ID must be passed to update a record");
		}

		Optional<Doctor> opt = doctorRepository.findById(doctor.getDoctorId());

		if (opt.isPresent()) {
			return doctorRepository.save(doctor);
		} else {
			throw new IdNotFoundException("Id not available in database");
		}
	}

	// delete Doctor
	public void deleteDoctor(Integer id) {
		Optional<Doctor> opt = doctorRepository.findById(id);

		if (opt.isPresent()) {
			doctorRepository.delete(opt.get());
		} else {
			throw new IdNotFoundException("Id does not exist in database");
		}
	}

}
