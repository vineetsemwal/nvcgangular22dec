package com.trainingprojects.projectapp.empms.dto;

/**
 * Request DTata Transfer for creating employee
 */
public class CreateEmployeeRequest {
   private  String firstName;
   private  String lastName;
   private  String department;

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getDepartment() {
      return department;
   }

   public void setDepartment(String department) {
      this.department = department;
   }
}
