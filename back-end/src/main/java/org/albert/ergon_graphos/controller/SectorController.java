package org.albert.ergon_graphos.controller;

import lombok.RequiredArgsConstructor;
import org.albert.ergon_graphos.entity.Sector;
import org.albert.ergon_graphos.entity.dto.SectorDailyReport;
import org.albert.ergon_graphos.service.SectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sector")
@CrossOrigin("*")
public class SectorController
{
    private final SectorService sectorService;

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sector>> readAll()
    {
        return ResponseEntity.ok(sectorService.readAll());
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody Sector sector)
    {
        sectorService.create(sector);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
    {
        sectorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(path = "/daily-report/{id}")
    public ResponseEntity<List<SectorDailyReport>> readSectorDailyReport(@PathVariable("id") Integer id)
    {
        final List<SectorDailyReport> sectorDailyReportList = sectorService.readSectorDailyReport(id);
        return ResponseEntity.ok(sectorDailyReportList);
    }
}
