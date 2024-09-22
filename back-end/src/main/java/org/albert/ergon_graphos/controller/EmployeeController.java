package org.albert.ergon_graphos.controller;

import lombok.RequiredArgsConstructor;
import org.albert.ergon_graphos.entity.Employee;
import org.albert.ergon_graphos.entity.dto.EmployeeDto;
import org.albert.ergon_graphos.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/employee")
@CrossOrigin("*")
public class EmployeeController
{
    private final EmployeeService employeeService;

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody EmployeeDto employeeDto)
    {
        employeeService.create(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
