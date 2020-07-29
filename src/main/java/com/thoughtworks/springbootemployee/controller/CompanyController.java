package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @GetMapping()
    public List<Company> getCompaniesConditions(Integer page, Integer pageSize) {
        List<Company> companies = new ArrayList<>();
        if (page != null && pageSize != null) {
            companies = getCompaniesByPage(page, pageSize);
        } else {
            companies = getCompanies();
        }
        return companies;
    }

    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1));
        companies.add(new Company(2));
        return companies;
    }

    public List<Company> getCompaniesByPage(Integer page, Integer pageSize) {
        List<Company> companies = getCompaniesData();
        int total = companies.size();
        if (pageSize * (page - 1) < total) {
            companies = companies.subList(pageSize * (page - 1), (pageSize * page > total ? total : (pageSize * page)));
            return companies;
        }
        return companies;
    }

    @GetMapping("{id}")
    public Company getCompany(@PathVariable int id) {
        Company company = new Company(id);
        return company;
    }

    @GetMapping("{id}/employees")
    public List<Employee> getEmployeesOfCompany(@PathVariable int id) {
        Company company = getAllEmployeesOFCompany(id);
        List<Employee> companyEmployees = company.getEmployees();
        return companyEmployees;
    }

    @PostMapping()
    public List<Company> addCompanies() {
        return getCompaniesData();
    }

    private List<Company> getCompaniesData() {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(001));
        companies.add(new Company(002));
        companies.add(new Company(003));
        return companies;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Company updateCompanyBasicInformation(@PathVariable int id) {
        Company company = new Company(id);
        company.setId(2);
        return company;
    }

    @DeleteMapping("{id}")
    public Company deleteAllEmployeesBelongToThisCompany(@PathVariable int id) {
        Company company = getAllEmployeesOFCompany(id);
        company.deleteAllEmployeesBelongToThisCompany();
        return company;
    }

    private Company getAllEmployeesOFCompany(int companyID) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1));
        employees.add(new Employee(2));
        employees.add(new Employee(3));
        Company company = new Company(companyID);
        company.setEmployees(employees);
        return company;
    }

}
