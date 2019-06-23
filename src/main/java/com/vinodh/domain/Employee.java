package com.vinodh.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author thimmv
 * @createdAt 21-06-2019 17:29
 */
@RedisHash(value = "employee")
@ApiModel(description = "All details about the Employee. ")
public class Employee implements Serializable {

    public Employee() {
    }

    @Id
    @ApiModelProperty(notes = "Employee id")
    private Long id;

    //    @Indexed
    @ApiModelProperty(notes = "Employee name")
    @NotBlank
    private String name;

    @ApiModelProperty(notes = "Employee age")
    @NotBlank
    private String age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}