package com.trainingprojects.projectapp.empms.service;

import com.trainingprojects.projectapp.empms.dto.CreateEmployeeRequest;
import com.trainingprojects.projectapp.empms.dto.EmployeeDetails;
import com.trainingprojects.projectapp.empms.exceptions.EmployeeNotFoundException;
import com.trainingprojects.projectapp.empms.exceptions.InvalidArgumentException;

public interface IEmployeeService {
    //Employee Details is a response dto, CreateEmployeeRequest is request dto
    EmployeeDetails registerEmployee(CreateEmployeeRequest request)throws InvalidArgumentException;
    /*
    //update employee name
    EmployeeDetails updateName(UpdateNameRequest request)
    */
    //fetches project details
    EmployeeDetails findDetailsById( long employeeId) throws InvalidArgumentException, EmployeeNotFoundException;

}
