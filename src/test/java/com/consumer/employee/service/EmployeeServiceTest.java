package com.consumer.employee.service;

import com.consumer.employee.client.MasGlobalClient;
import com.consumer.employee.dto.EmployeeDTO;
import com.consumer.employee.exception.FetchEmployeeException;
import com.consumer.employee.model.Employee;
import com.consumer.employee.model.HourlyEmployee;
import com.consumer.employee.model.MonthlyEmployee;
import org.junit.jupiter.api.*;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EmployeeServiceTest
{
   @Autowired
   EmployeeService employeeService;
   @MockBean
   MasGlobalClient masGlobalClient;
   List<EmployeeDTO> employees;

   @BeforeEach
   void setUp()
   {
      employees = new ArrayList<>();
      EmployeeDTO employeeDTO1 =  new EmployeeDTO();
      EmployeeDTO employeeDTO2 =  new EmployeeDTO();
      employeeDTO1.setId(1);
      employeeDTO1.setName("Name1");
      employeeDTO1.setContractTypeName("monthlySalary");
      employeeDTO1.setHourlySalary(300.2);
      employeeDTO1.setMonthlySalary(40000.2);
      employeeDTO2.setId(2);
      employeeDTO2.setName("Name2");
      employeeDTO2.setContractTypeName("HourlySalaryEmployee");
      employeeDTO2.setHourlySalary(300.2);
      employeeDTO2.setMonthlySalary(40000.2);
      employees.add(employeeDTO1);
      employees.add(employeeDTO2);
   }

   @Test
   @DisplayName("Test getEmployeeById success")
   void getEmployeeById() throws FetchEmployeeException {
      //Mock client
      Mockito.doReturn(employees).when(masGlobalClient).getEmployees();
      //Call service method
      Employee returnedEmployee = employeeService.getEmployeeById(1);
      //Validate test
      Assertions.assertTrue(returnedEmployee.getId() == 1 && "Name1".equals(returnedEmployee.getName()));
   }
   @Test
   @DisplayName("Test getEmployeeById validate HourlySalaryEmployee")
   void getHourlyEmployeeById() throws FetchEmployeeException {
      //Mock client
      Mockito.doReturn(employees).when(masGlobalClient).getEmployees();
      //Call service method
      Employee returnedEmployee = employeeService.getEmployeeById(2);
      //Validate test
      Assertions.assertTrue(returnedEmployee instanceof HourlyEmployee);
      Assertions.assertTrue(returnedEmployee.getAnnualSalary() == 120 * returnedEmployee.getHourlySalary() * 12 );
   }

   @Test
   @DisplayName("Test getEmployeeById validate MonthlySalaryEmployee")
   void getMonthlyEmployeeById() throws FetchEmployeeException {
      //Mock client
      Mockito.doReturn(employees).when(masGlobalClient).getEmployees();
      //Call service method
      Employee returnedEmployee = employeeService.getEmployeeById(1);
      //Validate test
      Assertions.assertTrue(returnedEmployee instanceof MonthlyEmployee);
      Assertions.assertTrue(returnedEmployee.getAnnualSalary() == returnedEmployee.getMonthlySalary() * 12 );
   }

   @Test
   @DisplayName("Test getEmployeeById no result case")
   void getEmployeeByIdNoResult() throws FetchEmployeeException {
      //Mock client
      Mockito.doReturn(employees).when(masGlobalClient).getEmployees();
      //Call service method
      Employee returnedEmployee = employeeService.getEmployeeById(3);
      //Validate test
      Assertions.assertTrue(returnedEmployee == null);
   }

   @Test
   @DisplayName("Test getEmployeeById empty list")
   void getEmployeeByIdEmptyList() throws FetchEmployeeException {
      //Mock client
      Mockito.doReturn(new ArrayList<EmployeeDTO>()).when(masGlobalClient).getEmployees();
      //Call service method
      Employee returnedEmployee = employeeService.getEmployeeById(3);
      //Validate test
      Assertions.assertTrue(returnedEmployee == null);
   }

   @Test
   @DisplayName("Test getEmployeeById exception case")
   void getEmployeeWithException() throws FetchEmployeeException {
      //Mock client
      Mockito.when(masGlobalClient.getEmployees())
            .thenThrow(FetchEmployeeException.class);
      //Call service method
      Assertions.assertThrows(FetchEmployeeException.class, () -> {
         employeeService.getEmployeeById(1);
      });
   }

   @Test
   @DisplayName("Test getEmployees success")
   void getEmployees() throws FetchEmployeeException {
      //Mock client
      Mockito.doReturn(employees).when(masGlobalClient).getEmployees();
      //Call service method
      List<Employee> returnedEmployees = employeeService.getEmployees();
      //Validate test
      Assertions.assertTrue(returnedEmployees.size() == 2);
   }

   @Test
   @DisplayName("Test getEmployees validate monthly and hourly employees")
   void getMonthlyAndHourlyEmployees() throws FetchEmployeeException {
      //Mock client
      Mockito.doReturn(employees).when(masGlobalClient).getEmployees();
      //Call service method
      List<Employee> returnedEmployees = employeeService.getEmployees();
      //Validate test
      Employee monthlyEmployee = returnedEmployees.get(0);
      Employee hourlyEmployee = returnedEmployees.get(1);
      Assertions.assertTrue(returnedEmployees.size() == 2);
      Assertions.assertTrue(monthlyEmployee instanceof MonthlyEmployee);
      Assertions.assertTrue(monthlyEmployee.getAnnualSalary() == monthlyEmployee.getMonthlySalary() * 12 );
      Assertions.assertTrue(hourlyEmployee instanceof HourlyEmployee);
      Assertions.assertTrue(hourlyEmployee.getAnnualSalary() == 120 * hourlyEmployee.getHourlySalary() * 12 );
   }

   @Test
   @DisplayName("Test getEmployeeById empty list")
   void getEmployeesEmptyList() throws FetchEmployeeException {
      //Mock client
      Mockito.doReturn(new ArrayList<EmployeeDTO>()).when(masGlobalClient).getEmployees();
      //Call service method
      List<Employee> returnedEmployees = employeeService.getEmployees();
      //Validate test
      Assertions.assertTrue(returnedEmployees.size() == 0);
   }

   @Test
   @DisplayName("Test getEmployees exception case")
   void getEmployeesWithException() throws FetchEmployeeException {
      //Mock client
      Mockito.when(masGlobalClient.getEmployees())
            .thenThrow(FetchEmployeeException.class);
      //Call service method
      Assertions.assertThrows(FetchEmployeeException.class, () -> {
         employeeService.getEmployees();
      });
   }


}