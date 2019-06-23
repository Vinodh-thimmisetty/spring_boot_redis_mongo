package com.vinodh.repo;

import com.vinodh.domain.Employee;
import com.vinodh.repository.EmployeeRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @author thimmv
 * @createdAt 21-06-2019 23:06
 */
@RunWith(SpringRunner.class)
@DataRedisTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void save() {

        Employee employee = new Employee();
        employee.setId(123l);
        employee.setName("Sample");
        employee.setAge("26");
        employeeRepository.save(employee);
    }


    @Test
    public void test_findAll() {
        for (Employee employee : employeeRepository.findAll()) {
            System.out.println("Employee :: " + employee);
        }
    }

    @Test
    public void test_findById() {
        Optional<Employee> byId = employeeRepository.findById(123l);
        byId.ifPresent(System.out::println);
    }

    @Test
    public void test_findByName() {
        List<Employee> vinodh = employeeRepository.findByName("Thimmisetty");

        if (vinodh.isEmpty()) {
            System.out.println("error");
        } else {
            for (Employee employee : vinodh) {
                System.out.println("Employee :: " + employee);
            }
        }
    }
}