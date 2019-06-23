package com.vinodh.service;

import com.vinodh.domain.Employee;
import com.vinodh.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thimmv
 * @createdAt 23-06-2019 11:05
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : employeeRepository.findAll()) {
            employees.add(employee);
        }
        return employees;
    }

    @Override
    @Cacheable(value = "Employee_Cache", key = "#id")
    public Employee findById(Long id) {
        log.info("Getting Employee details with id {}.", id);
        return employeeRepository.findById(id).get();
    }

    @Override
//    @Cacheable(value = "Employee_Cache", key = "#employeeName")
    public List<Employee> findByName(String name) {
        log.info("Getting Employee details with NAME {}.", name);
        return employeeRepository.findByName(name);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @CachePut(value = "Employee_Cache", key = "#employee.id")
    public Employee update(Employee employee) {
        log.info("Updated employee details with ID {}.", employee.getId());
        return employeeRepository.save(employee);
    }

    @Override
    @CacheEvict(value = "Employee_Cache", key = "#employee.id")
    public void deleteById(Long id) {
        log.info("deleting Employee with id {}", id);
        employeeRepository.deleteById(id);
    }

    @Override
    @CacheEvict(value = "Employee_Cache", allEntries = false)
    public void deleteAll() {
        log.info("deleting all Employees with");
        employeeRepository.deleteAll();
    }
}