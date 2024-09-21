package org.albert.ergon_graphos.repository;

import org.albert.ergon_graphos.entity.Problem;
import org.albert.ergon_graphos.repository.contract.IRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProblemRepository implements IRepository<Problem, Integer>
{

    @Override
    public void create(Problem problem)
    {

    }

    @Override
    public void delete(Integer integer)
    {

    }

    @Override
    public void update(Problem problem)
    {

    }

    @Override
    public Problem read(Integer integer)
    {
        return null;
    }

    @Override
    public List<Problem> readAll()
    {
        return null;
    }
}
