package com.consumer.employee.controller;

import com.consumer.employee.dto.EmployeeDTO;
import com.consumer.employee.exception.EmployeeConsumerException;
import com.consumer.employee.model.Employee;
import com.consumer.employee.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MVC controller to provide employees information.
 *
 * @author  Dario Chacon
 * @version 1.0
 * @since   2021-03-10
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/employee")
public class EmployeeController
{
   private final Logger LOG = LoggerFactory.getLogger(this.getClass());
   private EmployeeService employeeService;

   @Autowired
   public EmployeeController(EmployeeService employeeService) {
      this.employeeService = employeeService;
   }
   /**
    * Get Employee by id.
    * @param  -employeeId- Employee id.
    * @return - ResponseEntity<Employee> - Employee and http status.
    */
   @GetMapping("/{employeeId}")
   public ResponseEntity<Employee>  getEmployeeById(@PathVariable("employeeId") int employeeId){
      try{
         return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
      } catch (EmployeeConsumerException consumerException) {
         LOG.error(consumerException.getMessage());
         return new ResponseEntity("Error getting employees.", HttpStatus.BAD_REQUEST);
      }
   }

   /**
    * Get Employee by id.
    * @return - ResponseEntity<List<Employee>> - Employees and http status.
    */
   @GetMapping
   public ResponseEntity<List<Employee>>  getEmployees(){
      try{
         return ResponseEntity.ok(employeeService.getEmployees());
      } catch (EmployeeConsumerException consumerException) {
      LOG.error(consumerException.getMessage());
      return new ResponseEntity("Error getting employees.", HttpStatus.BAD_REQUEST);
      }
   }
}
