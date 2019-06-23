package com.vinodh.service;

import com.vinodh.domain.Employee;

import java.util.List;

/**
 * @author thimmv
 * @createdAt 23-06-2019 11:02
 */
public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(Long id);

    List<Employee> findByName(String name);

    Employee save(Employee employee);

    Employee update(Employee employee);

    void deleteById(Long id);

    void deleteAll();
}