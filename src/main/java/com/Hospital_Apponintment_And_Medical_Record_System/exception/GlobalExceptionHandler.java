package com.Hospital_Apponintment_And_Medical_Record_System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Hospital_Apponintment_And_Medical_Record_System.dto.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(
            IdNotFoundException exception) {

    	ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());
        response.setData(null);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoRecordAvailableException.class)
    public ResponseEntity<ResponseStructure<String>> handleNoRecordException(
            NoRecordAvailableException ex) {

        ResponseStructure<String> r = new ResponseStructure<>();
        r.setStatusCode(HttpStatus.NOT_FOUND.value());
        r.setMessage(ex.getMessage());
        r.setData(null);

        return new ResponseEntity<>(r, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(PatientIdNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleNoRecordException(
    		PatientIdNotFoundException ex) {

        ResponseStructure<String> r = new ResponseStructure<>();
        r.setStatusCode(HttpStatus.NOT_FOUND.value());
        r.setMessage(ex.getMessage());
        r.setData(null);

        return new ResponseEntity<>(r, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AppointmentIdNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleNoRecordException(
    		AppointmentIdNotFoundException ex) {

        ResponseStructure<String> r = new ResponseStructure<>();
        r.setStatusCode(HttpStatus.NOT_FOUND.value());
        r.setMessage(ex.getMessage());
        r.setData(null);

        return new ResponseEntity<>(r, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DepartmentIdNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleNoRecordException(
    		DepartmentIdNotFoundException ex) {

        ResponseStructure<String> r = new ResponseStructure<>();
        r.setStatusCode(HttpStatus.NOT_FOUND.value());
        r.setMessage(ex.getMessage());
        r.setData(null);

        return new ResponseEntity<>(r, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DoctorIdNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleNoRecordException(
    		DoctorIdNotFoundException ex) {

        ResponseStructure<String> r = new ResponseStructure<>();
        r.setStatusCode(HttpStatus.NOT_FOUND.value());
        r.setMessage(ex.getMessage());
        r.setData(null);

        return new ResponseEntity<>(r, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(RecordIdNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleNoRecordException(
    		RecordIdNotFoundException ex) {

        ResponseStructure<String> r = new ResponseStructure<>();
        r.setStatusCode(HttpStatus.NOT_FOUND.value());
        r.setMessage(ex.getMessage());
        r.setData(null);

        return new ResponseEntity<>(r, HttpStatus.NOT_FOUND);
    }
}
