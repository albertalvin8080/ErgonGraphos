package org.albert.ergon_graphos.repository;

import org.albert.ergon_graphos.entity.Employee;
import org.albert.ergon_graphos.entity.Sector;
import org.albert.ergon_graphos.repository.connection.ConnectionFactory;
import org.albert.ergon_graphos.repository.contract.IRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository implements IRepository<Employee, Integer>
{
    @Override
    public void create(Employee employee) throws SQLException
    {
        String sql = """
                INSERT INTO 
                    employee (employee_name, employee_sector_id)
                VALUES 
                    (?, ?);
                """;

        try (final PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql);)
        {
            pstm.setString(1, employee.getName());
            pstm.setInt(2, employee.getSector().getId());
            pstm.execute();
        }
    }

    @Override
    public void delete(Integer integer) throws SQLException
    {

    }

    @Override
    public void update(Employee employee) throws SQLException
    {

    }

    @Override
    public Employee read(Integer integer) throws SQLException
    {
        return null;
    }

    public List<Employee> filterBySector(Integer sectorId) throws SQLException
    {
        List<Employee> employeeList = new ArrayList<>();
        String sql = """
                SELECT * 
                FROM 
                    employee e 
                INNER JOIN 
                    sector s
                ON 
                    e.employee_sector_id = s.sector_id
                WHERE 
                    e.employee_sector_id = ?
                ORDER BY
                    e.employee_name;
                """;

        try(PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            pstm.setInt(1, sectorId);
            final ResultSet resultSet = pstm.executeQuery();
            while(resultSet.next())
            {
                final Employee employee = Employee.builder()
                        .id(resultSet.getInt("employee_id"))
                        .name(resultSet.getString("employee_name"))
                        .sector(
                                Sector.builder()
                                        .id(resultSet.getInt("sector_id"))
                                        .name(resultSet.getString("sector_name"))
                                        .build()
                        )
                        .build();
                employeeList.add(employee);
            }
        }

        return employeeList;
    }

    @Override
    public List<Employee> readAll() throws SQLException
    {
        final List<Employee> employeeList = new ArrayList<>();

        String sql = """
                SELECT 
                    * 
                FROM 
                    employee e 
                INNER JOIN sector s
                    ON e.employee_sector_id = s.sector_id;
                """;

        try (final PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            final ResultSet resultSet = pstm.executeQuery();
            while(resultSet.next())
            {
                final Sector sector = Sector.builder()
                        .id(resultSet.getInt("sector_id"))
                        .name(resultSet.getString("sector_name"))
                        .build();
                final Employee employee = Employee.builder()
                        .id(resultSet.getInt("employee_id"))
                        .name(resultSet.getString("employee_name"))
                        .sector(sector)
                        .build();
                employeeList.add(employee);
            }
        }

        return employeeList;
    }
}
