package org.albert.ergon_graphos.service;

import lombok.RequiredArgsConstructor;
import org.albert.ergon_graphos.entity.Employee;
import org.albert.ergon_graphos.entity.Sector;
import org.albert.ergon_graphos.entity.dto.EmployeeDto;
import org.albert.ergon_graphos.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

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
                    .id(employeeDto.getId())
                    .build();
            final Employee employee = Employee.builder()
                    .id(employeeDto.getId())
                    .name(employeeDto.getName())
                    .sector(sector)
                    .build();
            employeeRepository.create(employee);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
