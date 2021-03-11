package com.consumer.employee.service;

import com.consumer.employee.client.MasGlobalClient;
import com.consumer.employee.dto.EmployeeDTO;
import com.consumer.employee.exception.FetchEmployeeException;
import com.consumer.employee.model.Employee;
import com.consumer.employee.model.HourlyEmployee;
import com.consumer.employee.model.MonthlyEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service to provide employees information.
 *
 * @author Dario Chacon
 * @version 1.0
 * @since 2021-03-10
 */
@Service
public class EmployeeService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MasGlobalClient masGlobalClient;

    /**
     * Gets Employees.
     *
     * @return - List<Employee> - All Employees.
     */
    public List<Employee> getEmployees() throws FetchEmployeeException {
        List<Employee> employeesResultList = new ArrayList<>();
        List<EmployeeDTO> employeeDTOS = masGlobalClient.getEmployees();
        LOG.info("Array : !!!!! : " + employeeDTOS.toString());
        for (EmployeeDTO employeeDTO : employeeDTOS) {
            employeesResultList.add(employeeFactory(employeeDTO));
        }
        return employeesResultList;
    }

    /**
     * Get Employee by id.
     *
     * @param -employeeId- Employee id.
     * @return - Employee - Employee.
     */
    public Employee getEmployeeById(Integer employeeId) throws FetchEmployeeException {
        EmployeeDTO employeeDTO = masGlobalClient.getEmployees().stream().
                filter(empl -> empl.getId() == employeeId).findFirst().orElse(null);
        return employeeFactory(employeeDTO);
    }

    /**
     * Get Employee from employeeDTO by contract type.
     *
     * @param -EmployeeDTO- Employee id.
     * @return - Employee - Employee.
     */
    private Employee employeeFactory(EmployeeDTO employeeDTO) {
        Employee employee = null;
        if (employeeDTO != null) {
            if (employeeDTO.getContractTypeName() != null && "monthlySalary".equals(employeeDTO.getContractTypeName())) {
                employee = new MonthlyEmployee();
            } else {
                employee = new HourlyEmployee();
            }
            employee.setHourlySalary(employeeDTO.getHourlySalary());
            employee.setMonthlySalary(employeeDTO.getMonthlySalary());
            employee.setId(employeeDTO.getId());
            employee.setContractTypeName(employeeDTO.getContractTypeName());
            employee.setName(employeeDTO.getName());
            employee.setRoleDescription(employeeDTO.getRoleDescription());
            employee.setRoleName(employeeDTO.getRoleName());
            employee.setRoleId(employeeDTO.getRoleId());
        }
        return employee;
    }
}
