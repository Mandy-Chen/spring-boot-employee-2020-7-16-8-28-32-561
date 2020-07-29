package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    //    @GetMapping()
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1));
        employees.add(new Employee(2));
        return employees;
    }

    @GetMapping("{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = new Employee(id);
        return employee;
    }

    //    @GetMapping()
    public List<Employee> getEmployeesByPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        List<Employee> employees = getEmployeesData();
        int total = employees.size();
        if (pageSize * (page - 1) < total) {
            employees = employees.subList(pageSize * (page - 1), (pageSize * page > total ? total : (pageSize * page)));
        }
        return employees;
    }

    @GetMapping()
    public List<Employee> getAllMaleEmployees(@RequestParam("gender") String gender) {
        List<Employee> employees = new ArrayList<>();
        Employee employees1 = new Employee(1);
        employees1.setGender("male");
        Employee employees2 = new Employee(1);
        employees2.setGender("male");
        Employee employees3 = new Employee(1);
        employees2.setGender("female");
        employees.add(employees1);
        employees.add(employees2);
        employees.add(employees3);
        System.out.println(employees.stream().toString());
        return employees.stream().filter(employee -> gender.equals(employee.getGender())).collect(Collectors.toList());
    }

    private List<Employee> getEmployeesData() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(001));
        employees.add(new Employee(002));
        employees.add(new Employee(003));
        return employees;
    }

    @PostMapping()
    public String addEmployees() {
        getEmployeesData();
        return "add success";
    }

    @PutMapping("{id}")
    public Integer updateEmployee(@PathVariable int id) {
        Employee employee = new Employee(id);
        employee.setId(2);
        return employee.getId();
    }

    @DeleteMapping("{id}")
    public String deleteEmployee(@PathVariable int id) {
        List<Employee> employees = getEmployeesData();
        System.out.println(employees.size());
        Employee employee = employees.stream().filter(eachEmployee -> eachEmployee.getId() == id).findFirst().orElse(null);
        employees.remove(employee);
        System.out.println(employees.size());
        return "delete success";
    }
}
