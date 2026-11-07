package com.kiet.mse2.controller;

import com.kiet.mse2.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private List<Employee> employees = new ArrayList<>();

    // (b) POST - Add Employee
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        employees.add(employee);
        return employee;
    }

    // (c) GET - Get All Employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // (c) GET - Get Employee By ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int id) {

        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return ResponseEntity.ok(employee);
            }
        }

        return ResponseEntity
                .status(404)
                .body("Employee not found");
    }

    // (d) PUT - Update Employee
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable int id,
            @RequestBody Employee updatedEmployee) {

        for (Employee employee : employees) {

            if (employee.getId() == id) {

                employee.setName(updatedEmployee.getName());
                employee.setDepartment(updatedEmployee.getDepartment());
                employee.setSalary(updatedEmployee.getSalary());

                return ResponseEntity.ok(employee);
            }
        }

        return ResponseEntity
                .status(404)
                .body("Employee not found");
    }

    // (e) DELETE - Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id) {

        for (Employee employee : employees) {

            if (employee.getId() == id) {

                employees.remove(employee);

                return ResponseEntity.ok(
                        "Employee deleted successfully"
                );
            }
        }

        return ResponseEntity
                .status(404)
                .body("Employee not found");
    }
}