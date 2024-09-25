package org.albert.ergon_graphos.controller;

import lombok.RequiredArgsConstructor;
import org.albert.ergon_graphos.entity.Problem;
import org.albert.ergon_graphos.service.ProblemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/problem")
@CrossOrigin("*")
public class ProblemController
{
    private final ProblemService problemService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<Problem>> readAll()
    {
        return ResponseEntity.ok(problemService.readAll());
    }
}
