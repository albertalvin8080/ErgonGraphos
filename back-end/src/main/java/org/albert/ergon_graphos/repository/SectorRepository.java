package org.albert.ergon_graphos.repository;

import lombok.RequiredArgsConstructor;
import org.albert.ergon_graphos.entity.Sector;
import org.albert.ergon_graphos.entity.dto.SectorDailyReport;
import org.albert.ergon_graphos.repository.connection.ConnectionFactory;
import org.albert.ergon_graphos.repository.contract.IRepository;
import org.albert.ergon_graphos.service.DateConverter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class SectorRepository implements IRepository<Sector, Integer>
{
    private final DateConverter dateConverter;

    @Override
    public void create(Sector sector) throws SQLException
    {
        String sql = "INSERT INTO sector (sector_name) VALUES (?)";
        try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            pstm.setString(1, sector.getName());
            pstm.execute();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException
    {
        String sql = "DELETE FROM sector s WHERE s.sector_id = ?;";
        try (final PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql);)
        {
            pstm.setInt(1, id);
            pstm.execute();
        }
    }

    @Override
    public void update(Sector sector) throws SQLException
    {

    }

    @Override
    public Sector read(Integer id) throws SQLException
    {
        return null;
    }

    @Override
    public List<Sector> readAll() throws SQLException
    {
        final List<Sector> sectorList = new ArrayList<>();
        String sql = "SELECT * FROM sector s ORDER BY s.sector_id;";

        final ResultSet resultSet;
        try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            resultSet = pstm.executeQuery();
            while (resultSet.next())
            {
                final Sector sector = Sector.builder()
                        .id(resultSet.getInt("sector_id"))
                        .name(resultSet.getString("sector_name"))
                        .build();
                sectorList.add(sector);
            }
        }

        return sectorList;
    }

    public List<SectorDailyReport> readSectorDailyReport(Integer sectorId) throws SQLException
    {
        String sql = """
                SELECT
                    s.sector_name, 
                    r.report_date,  
                    p.problem_description,
                    COUNT(r.report_id) AS report_count
                FROM 
                    report r
                JOIN 
                    employee e ON r.report_employee_id = e.employee_id
                JOIN 
                    sector s ON e.employee_sector_id = s.sector_id 
                JOIN 
                    problem p ON r.report_problem_id = p.problem_id 
                WHERE
                    s.sector_id = ?
                GROUP BY 
                    s.sector_name, r.report_date, p.problem_description
                ORDER BY 
                    s.sector_name, r.report_date;
                """;

        List<SectorDailyReport> sectorDailyReportList = new ArrayList<>();

        try (final PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            pstm.setInt(1, sectorId);
            final ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next())
            {
                final SectorDailyReport build = SectorDailyReport
                        .builder()
                        .reportCount(resultSet.getInt("report_count"))
                        .sectorName(resultSet.getString("sector_name"))
                        .problemDescription(resultSet.getString("problem_description"))
                        .reportDate(dateConverter.convertSqlDateToJavaDate(
                                resultSet.getDate("report_date")
                        ))
                        .build();
                sectorDailyReportList.add(build);
            }
        }

        return sectorDailyReportList;
    }

    public List<SectorDailyReport> readAllSectorDailyReport() throws SQLException
    {
        List<SectorDailyReport> sectorDailyReportList = new ArrayList<>();

        String sql = """
                SELECT
                    s.sector_name, 
                    r.report_date,  
                    p.problem_description,
                    COUNT(r.report_id) AS report_count
                FROM 
                    report r
                JOIN 
                    employee e ON r.report_employee_id = e.employee_id
                JOIN 
                    sector s ON e.employee_sector_id = s.sector_id 
                JOIN 
                    problem p ON r.report_problem_id = p.problem_id 
                GROUP BY 
                    s.sector_name, r.report_date, p.problem_description
                ORDER BY 
                    s.sector_name, r.report_date;
                """;

        try (final PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            final ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next())
            {
                final SectorDailyReport build = SectorDailyReport
                        .builder()
                        .reportCount(resultSet.getInt("report_count"))
                        .sectorName(resultSet.getString("sector_name"))
                        .problemDescription(resultSet.getString("problem_description"))
                        .reportDate(dateConverter.convertSqlDateToJavaDate(
                                resultSet.getDate("report_date")
                        ))
                        .build();
                sectorDailyReportList.add(build);
            }
        }

        return sectorDailyReportList;
    }
}
