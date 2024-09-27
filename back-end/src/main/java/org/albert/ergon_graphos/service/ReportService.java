package org.albert.ergon_graphos.service;

import lombok.RequiredArgsConstructor;
import org.albert.ergon_graphos.entity.Employee;
import org.albert.ergon_graphos.entity.Problem;
import org.albert.ergon_graphos.entity.Report;
import org.albert.ergon_graphos.entity.dto.ReportDto;
import org.albert.ergon_graphos.repository.ReportRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class ReportService
{
    private final ReportRepository repository;

    public void create(ReportDto reportDto)
    {
        final Report report = Report.builder()
                .date(new Date())
                .employee(Employee.builder().id(reportDto.getEmployeeId()).build())
                .problem(Problem.builder().id(reportDto.getProblemId()).build())
                .build();

        try
        {
            repository.create(report);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
