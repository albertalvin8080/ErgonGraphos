package org.albert.ergon_graphos.service;

import lombok.RequiredArgsConstructor;
import org.albert.ergon_graphos.entity.Problem;
import org.albert.ergon_graphos.repository.ProblemRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemService
{
    private final ProblemRepository problemRepository;

    public List<Problem> readAll()
    {
        try
        {
            return problemRepository.readAll();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
