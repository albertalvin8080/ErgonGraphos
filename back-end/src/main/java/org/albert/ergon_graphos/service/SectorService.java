package org.albert.ergon_graphos.service;

import lombok.RequiredArgsConstructor;
import org.albert.ergon_graphos.entity.Sector;
import org.albert.ergon_graphos.entity.dto.SectorDailyReport;
import org.albert.ergon_graphos.repository.SectorRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SectorService
{
    private final SectorRepository sectorRepository;

    public List<Sector> readAll()
    {
        try
        {
            return sectorRepository.readAll();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void create(Sector sector)
    {
        try
        {
            sectorRepository.create(sector);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void delete(Integer id)
    {
        try
        {
            sectorRepository.delete(id);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public List<SectorDailyReport> readSectorDailyReport(Integer id)
    {
        try
        {
            return sectorRepository.readSectorDailyReport(id);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
