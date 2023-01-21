package com.trainingprojects.projectapp.empms.service;

import com.trainingprojects.projectapp.empms.dto.CreateEmployeeRequest;
import com.trainingprojects.projectapp.empms.dto.EmployeeDetails;
import com.trainingprojects.projectapp.empms.entity.Employee;
import com.trainingprojects.projectapp.empms.exceptions.EmployeeNotFoundException;
import com.trainingprojects.projectapp.empms.exceptions.InvalidArgumentException;
import com.trainingprojects.projectapp.empms.repository.EmployeeRepositoryImpl;
import com.trainingprojects.projectapp.empms.repository.IEmployeeRepository;
import com.trainingprojects.projectapp.empms.util.Util;

import java.util.Optional;

/**
 * Service implementation containing business logic for employee management module
 */
public class EmployeeServiceImpl implements IEmployeeService {

    private IEmployeeRepository employeeRepo = new EmployeeRepositoryImpl();
    private Util util =new Util();

    private long generatedId;

    long generateId() {
        return ++generatedId;
    }


    /**
     *  registers new employee
     *
     * @param request request data
     * @return employee details response object
     * @throws InvalidArgumentException if input is invalid
     */

    @Override
    public EmployeeDetails registerEmployee(CreateEmployeeRequest request) throws InvalidArgumentException {
        util.validateNotEmpty(request.getFirstName(), "firstName");
        util.validateLength(request.getFirstName(), 3, 20, request.getFirstName());
        util.validateNotEmpty(request.getLastName(), "lastName");
        util.validateLength(request.getLastName(), 3, 20, request.getLastName());
        util.validateNotEmpty(request.getDepartment(), "department");
        util.validateLength(request.getDepartment(), 2, 10, request.getDepartment());
        long newId = generateId();
        Employee employee = new Employee();
        employee.setId(newId);
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setDepartment(request.getDepartment());
        employeeRepo.save(employee);
        EmployeeDetails desired = util.toEmployeeDetails(employee);
        return desired;

    }

    /**
     *  fetching employee and return its details as EmployeeDetails resposne object
     *
     * @param employeeId
     * @return EmployeeDetails response object
     * @throws InvalidArgumentException if id is not valid
     * @throws EmployeeNotFoundException if employee is not found in store
     */
    @Override
    public EmployeeDetails findDetailsById(long employeeId) throws InvalidArgumentException,EmployeeNotFoundException {
        util.validateId(employeeId);
        Optional<Employee> optional = employeeRepo.findById(employeeId);
        if (!optional.isPresent()) {
            throw new EmployeeNotFoundException("employee not found for id=" + employeeId);
        }
        Employee employee = optional.get();
        EmployeeDetails desired = util.toEmployeeDetails(employee);
        return desired;
    }
}
