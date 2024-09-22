package org.albert.ergon_graphos.repository;

import org.albert.ergon_graphos.entity.Employee;
import org.albert.ergon_graphos.repository.connection.ConnectionFactory;
import org.albert.ergon_graphos.repository.contract.IRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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

        try(final PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql);)
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

    @Override
    public List<Employee> readAll() throws SQLException
    {
        return null;
    }
}
