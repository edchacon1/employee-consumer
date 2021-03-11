package com.consumer.employee.client;

import com.consumer.employee.dto.EmployeeDTO;
import com.consumer.employee.exception.EmployeeConsumerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * MAS Global client  to provide rest API  employees information.
 * @author  Dario Chacon
 * @version 1.0
 * @since   2021-03-10
 */
@Service
public class MasGlobalClient
{
   private final String EMPLOYEES_PATH = "API/employees";
   private final String DOMAIN_PATH = "http://masglobaltestapi.azurewebsites.net/";
   private final Logger LOG = LoggerFactory.getLogger(this.getClass());

   /**
    * Gets Employees
    * @return - List<EmployeeDTO> - All Employees.
    */
   public List<EmployeeDTO> getEmployees() throws EmployeeConsumerException
   {
      try {
         RestTemplate restTemplate = new RestTemplate();
          return Arrays.asList( restTemplate.getForObject(
                DOMAIN_PATH + EMPLOYEES_PATH,
               EmployeeDTO[].class));
      } catch (Exception e) {
         LOG.error("Failed to get Employees", e);
         throw new EmployeeConsumerException(e.getMessage());
      }
   }
}
