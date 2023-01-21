package com.trainingprojects.projectapp.empms.util;

import com.trainingprojects.projectapp.empms.dto.EmployeeDetails;
import com.trainingprojects.projectapp.empms.entity.Employee;
import com.trainingprojects.projectapp.empms.exceptions.InvalidArgumentException;

/**
 * Utility class containing commonly used operations of project
 */
public class Util {


    public EmployeeDetails toEmployeeDetails(Employee employee) {
        EmployeeDetails desired = new EmployeeDetails();
        desired.setFirstName(employee.getFirstName());
        desired.setLastName(employee.getLastName());
        desired.setId(employee.getId());
        return desired;

    }

    public void validateId(long arg) throws InvalidArgumentException {
        if (arg <= 0) {
            throw new InvalidArgumentException("id can't be smaller or equal to zero");
        }
    }

    public void validateNotEmpty(String arg, String fieldName) throws InvalidArgumentException {
        if (arg == null || arg.trim().isEmpty()) {
            throw new InvalidArgumentException(fieldName + " can't be null or empty");
        }
    }

   public void validateLength(String arg, int min, int max, String fieldName) throws InvalidArgumentException {
        if (arg.length() < 3 || arg.length() > 20) {
            throw new InvalidArgumentException(fieldName + " should be between 3 and 20");
        }
    }
}
