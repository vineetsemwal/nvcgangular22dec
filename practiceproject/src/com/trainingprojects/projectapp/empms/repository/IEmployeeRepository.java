package com.trainingprojects.projectapp.empms.repository;

import com.trainingprojects.projectapp.empms.entity.Employee;

import java.util.Optional;

/**
 *  standard/ contract for data access
 */
public interface IEmployeeRepository {
    Employee save(Employee employee);

    Optional<Employee> findById(long id);

}
