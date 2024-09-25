package org.albert.ergon_graphos.repository;

import org.albert.ergon_graphos.entity.Problem;
import org.albert.ergon_graphos.repository.connection.ConnectionFactory;
import org.albert.ergon_graphos.repository.contract.IRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<Problem> readAll() throws SQLException
    {
        List<Problem> problemList = new ArrayList<>();
        String sql = "SELECT * FROM problem;";

        try (final PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            final ResultSet resultSet = pstm.executeQuery();
            while(resultSet.next())
            {
                final Problem problem = Problem.builder()
                        .id(resultSet.getInt("problem_id"))
                        .description(resultSet.getString("problem_description"))
                        .build();
                problemList.add(problem);
            }
        }
        return problemList;
    }
}
