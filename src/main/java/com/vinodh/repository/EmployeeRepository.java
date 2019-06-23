package com.vinodh.repository;

import com.vinodh.domain.Employee;
import org.springframework.data.redis.repository.support.QueryByExampleRedisExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

/**
 * @author thimmv
 * @createdAt 21-06-2019 17:33
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long>, QueryByExampleExecutor<Employee> {
    List<Employee> findByName(String name);
}