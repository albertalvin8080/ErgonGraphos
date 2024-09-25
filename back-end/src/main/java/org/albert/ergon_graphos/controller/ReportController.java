package org.albert.ergon_graphos.controller;

import lombok.RequiredArgsConstructor;
import org.albert.ergon_graphos.entity.dto.ReportDto;
import org.albert.ergon_graphos.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/report")
@CrossOrigin("*")
public class ReportController
{
    private final ReportService reportService;

    @PostMapping(path = "/create")
    public ResponseEntity<Void> create(@RequestBody ReportDto reportDto)
    {
//        System.out.println(reportDto);
        reportService.create(reportDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
