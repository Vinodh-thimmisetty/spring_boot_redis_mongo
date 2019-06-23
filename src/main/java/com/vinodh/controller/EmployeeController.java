package com.vinodh.controller;

import com.vinodh.domain.Employee;
import com.vinodh.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thimmv
 * @createdAt 21-06-2019 17:31
 */
@RestController
@RequestMapping("/employee")
@Api(value = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Employees.")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/findAll")
    @ApiOperation(value = "Find All available key-values in Redis")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : employeeService.findAll()) {
            employees.add(employee);
        }
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/findByKey")
    @ApiOperation(value = "Find key-value in Redis based on KEY")
    public ResponseEntity<Object> findByKey(@ApiParam(value = "Find Employee based on key.", required = true) @RequestParam String key) {
        log.info("Getting redis-value with Key {}.", key);
        return ResponseEntity.ok(stringRedisTemplate.opsForHash().entries(key));
    }

    @GetMapping("/findById")
    @ApiOperation(value = "Find key-values in Redis based on ID")
    public ResponseEntity<Employee> findById(@ApiParam(value = "Find Employee based on ID.", required = true) @RequestParam Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping("/findByName")
    @ApiOperation(value = "Find key-values in Redis based on NAME")
    public ResponseEntity<List<Employee>> findByName(@ApiParam(value = "Find Employee based on Name.", required = true) @RequestParam String employeeName) {
        return ResponseEntity.ok(employeeService.findByName(employeeName));
    }

    @PostMapping("/save")
    @ApiOperation(value = "Save new Employee details into Redis")
    public ResponseEntity<Employee> save(@ApiParam(value = "Employee information to be created.", required = true) @RequestBody Employee employee) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        employee.setId(valueOperations.increment("sequence", 1l));
        log.info("New employee saved with ID {}.", employee.getId());
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update Employee details into Redis")
    public ResponseEntity<Employee> update(@ApiParam(value = "Employee information to be updated.", required = true) @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.update(employee));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Delete Employee details into Redis")
    public void deleteUserByID(@ApiParam(value = "Delete Employee based on ID.", required = true) @RequestParam Long id) {
        employeeService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    @ApiOperation(value = "Delete All Employee details into Redis")
    public void deleteAll() {
        employeeService.deleteAll();
    }
}