package org.albert.ergon_graphos.service;

import lombok.RequiredArgsConstructor;
import org.albert.ergon_graphos.entity.Employee;
import org.albert.ergon_graphos.entity.Sector;
import org.albert.ergon_graphos.entity.dto.EmployeeDto;
import org.albert.ergon_graphos.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService
{
    private final EmployeeRepository employeeRepository;

    public void create(EmployeeDto employeeDto)
    {
        try
        {
            final Sector sector = Sector.builder()
                    .id(employeeDto.getSectorId())
                    .build();
            final Employee employee = Employee.builder()
                    .name(employeeDto.getName())
                    .sector(sector)
                    .build();
            employeeRepository.create(employee);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Employee> readAll()
    {
        try
        {
            return employeeRepository.readAll();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Employee> filterBySector(Integer sectorId)
    {
        try
        {
            return employeeRepository.filterBySector(sectorId);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
