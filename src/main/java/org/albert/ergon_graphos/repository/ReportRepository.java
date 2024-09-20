package org.albert.ergon_graphos.repository;

import org.albert.ergon_graphos.entity.Report;
import org.albert.ergon_graphos.repository.contract.IRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportRepository implements IRepository<Report, Integer>
{
    @Override
    public void create(Report report)
    {

    }

    @Override
    public void delete(Integer integer)
    {

    }

    @Override
    public void update(Report report)
    {

    }

    @Override
    public Report read(Integer integer)
    {
        return null;
    }

    @Override
    public List<Report> readAll()
    {
        return null;
    }
}
