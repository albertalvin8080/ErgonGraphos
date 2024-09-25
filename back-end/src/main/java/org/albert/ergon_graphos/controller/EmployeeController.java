package org.albert.ergon_graphos.controller;

import lombok.RequiredArgsConstructor;
import org.albert.ergon_graphos.entity.Employee;
import org.albert.ergon_graphos.entity.dto.EmployeeDto;
import org.albert.ergon_graphos.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/employee")
@CrossOrigin("*")
public class EmployeeController
{
    private final EmployeeService employeeService;

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> readAll()
    {
        final List<Employee> employeeList = employeeService.readAll();
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping(path = "/sector/{id}")
    public ResponseEntity<List<Employee>> filterBySector(@PathVariable("id") Integer id)
    {
        final List<Employee> employeeList = employeeService.filterBySector(id);
        return ResponseEntity.ok(employeeList);
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody EmployeeDto employeeDto)
    {
//        System.out.println(employeeDto);
        employeeService.create(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
