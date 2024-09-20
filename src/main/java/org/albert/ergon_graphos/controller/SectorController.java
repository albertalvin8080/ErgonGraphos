package org.albert.ergon_graphos.controller;

import lombok.RequiredArgsConstructor;
import org.albert.ergon_graphos.entity.Sector;
import org.albert.ergon_graphos.entity.dto.SectorDiaryReport;
import org.albert.ergon_graphos.service.SectorService;
import org.apache.coyote.Response;
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<Sector> read(@PathVariable("id") Integer id)
    {
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/diary-report/{id}")
    public ResponseEntity<List<SectorDiaryReport>> readSectorDiaryReport(@PathVariable("id") Integer id)
    {
        final List<SectorDiaryReport> sectorDiaryReportList = sectorService.readSectorDiaryReport(id);
        return ResponseEntity.ok(sectorDiaryReportList);
    }
}
