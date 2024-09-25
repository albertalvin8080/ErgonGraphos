package org.albert.ergon_graphos.repository;

import lombok.RequiredArgsConstructor;
import org.albert.ergon_graphos.entity.Report;
import org.albert.ergon_graphos.repository.connection.ConnectionFactory;
import org.albert.ergon_graphos.repository.contract.IRepository;
import org.albert.ergon_graphos.service.DateConverter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReportRepository implements IRepository<Report, Integer>
{
    private final DateConverter dateConverter;

    @Override
    public void create(Report report) throws SQLException
    {
        String sql = """
                INSERT INTO 
                    report (report_date, report_employee_id, report_problem_id)
                VALUES 
                    (?, ?, ?);
                """;

        try (final PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            pstm.setDate(1, dateConverter.convertJavaDateToSqlDate(report.getDate()));
            pstm.setInt(2, report.getEmployee().getId());
            pstm.setInt(3, report.getProblem().getId());
            pstm.execute();
        }
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
