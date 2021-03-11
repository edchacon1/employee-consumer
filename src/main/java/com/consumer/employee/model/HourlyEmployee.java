package com.consumer.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * POJO  for hourly employees.
 *
 * @author Dario Chacon
 * @version 1.0
 * @since 2021-03-10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HourlyEmployee extends Employee {
}
