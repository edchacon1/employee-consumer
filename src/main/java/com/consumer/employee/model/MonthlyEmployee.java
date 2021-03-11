package com.consumer.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * POJO  for monthly employees.
 *
 * @author Dario Chacon
 * @version 1.0
 * @since 2021-03-10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MonthlyEmployee extends Employee {

    public Double getAnnualSalary() {
        return  getMonthlySalary() * 12;
    }

}
