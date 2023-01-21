package com.trainingprojects.projectapp.empms.frontend;

import com.trainingprojects.projectapp.empms.dto.CreateEmployeeRequest;
import com.trainingprojects.projectapp.empms.dto.EmployeeDetails;
import com.trainingprojects.projectapp.empms.exceptions.EmployeeNotFoundException;
import com.trainingprojects.projectapp.empms.exceptions.InvalidArgumentException;
import com.trainingprojects.projectapp.empms.service.EmployeeServiceImpl;
import com.trainingprojects.projectapp.empms.service.IEmployeeService;

public class FrontEndUI {
    private IEmployeeService service=new EmployeeServiceImpl();

    public static void main(String args[]){
        FrontEndUI ui=new FrontEndUI();
        ui.runUI();
    }

    void runUI(){
        try {
            CreateEmployeeRequest request = new CreateEmployeeRequest();
            request.setFirstName("srushti");
            request.setLastName("patil");
            request.setDepartment("testing");
            System.out.println("****registering employee");
            EmployeeDetails registerResponse = service.registerEmployee(request);
            display(registerResponse);
            System.out.println("********fetch employee by id");
            long empId=registerResponse.getId();
            EmployeeDetails found=service.findDetailsById(empId);
            display(registerResponse);

        }catch (InvalidArgumentException | EmployeeNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

    void display(EmployeeDetails result){
        System.out.println(result.getId()+"-"+result.getFirstName()+"-"+result.getLastName());
    }



}
