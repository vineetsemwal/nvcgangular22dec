package com.trainingprojects.projectapp.empms.service;

import com.trainingprojects.projectapp.empms.dto.CreateEmployeeRequest;
import com.trainingprojects.projectapp.empms.dto.EmployeeDetails;
import com.trainingprojects.projectapp.empms.entity.Employee;
import com.trainingprojects.projectapp.empms.exceptions.EmployeeNotFoundException;
import com.trainingprojects.projectapp.empms.exceptions.InvalidArgumentException;
import com.trainingprojects.projectapp.empms.repository.EmployeeRepositoryImpl;
import com.trainingprojects.projectapp.empms.repository.IEmployeeRepository;

import java.util.Optional;

/**
 * Service implementation containing business logic for employee management module
 */
public class EmployeeServiceImpl implements IEmployeeService {

    private IEmployeeRepository employeeRepo = new EmployeeRepositoryImpl();

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
        validateNotEmpty(request.getFirstName(), "firstName");
        validateLength(request.getFirstName(), 3, 20, request.getFirstName());
        validateNotEmpty(request.getLastName(), "lastName");
        validateLength(request.getLastName(), 3, 20, request.getLastName());
        validateNotEmpty(request.getDepartment(), "department");
        validateLength(request.getDepartment(), 2, 10, request.getDepartment());
        long newId = generateId();
        Employee employee = new Employee();
        employee.setId(newId);
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setDepartment(request.getDepartment());
        employeeRepo.save(employee);
        EmployeeDetails desired = toEmployeeDetails(employee);
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
        validateId(employeeId);
        Optional<Employee> optional = employeeRepo.findById(employeeId);
        if (!optional.isPresent()) {
            throw new EmployeeNotFoundException("employee not found for id=" + employeeId);
        }
        Employee employee = optional.get();
        EmployeeDetails desired = toEmployeeDetails(employee);
        return desired;
    }

    public EmployeeDetails toEmployeeDetails(Employee employee) {
        EmployeeDetails desired = new EmployeeDetails();
        desired.setFirstName(employee.getFirstName());
        desired.setLastName(employee.getLastName());
        desired.setId(employee.getId());
        return desired;

    }

    void validateId(long arg) throws InvalidArgumentException {
        if (arg <= 0) {
            throw new InvalidArgumentException("id can't be smaller or equal to zero");
        }
    }

    void validateNotEmpty(String arg, String fieldName) throws InvalidArgumentException {
        if (arg == null || arg.trim().isEmpty()) {
            throw new InvalidArgumentException(fieldName + " can't be null or empty");
        }
    }

    void validateLength(String arg, int min, int max, String fieldName) throws InvalidArgumentException {
        if (arg.length() < 3 || arg.length() > 20) {
            throw new InvalidArgumentException(fieldName + " should be between 3 and 20");
        }
    }
}
