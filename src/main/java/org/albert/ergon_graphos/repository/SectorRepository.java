package org.albert.ergon_graphos.repository;

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
public class SectorRepository implements IRepository<Sector, Integer>
{
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
    public void delete(Integer integer) throws SQLException
    {

    }

    @Override
    public void update(Sector sector) throws SQLException
    {

    }

    @Override
    public Sector read(Integer integer) throws SQLException
    {
        return null;
    }

    @Override
    public List<Sector> readAll() throws SQLException
    {
        String sql = "SELECT * FROM sector;";
        final ResultSet resultSet;
        try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            resultSet = pstm.executeQuery();
        }

        final List<Sector> sectorList = new ArrayList<>();

        while (resultSet.next())
        {
            final Sector sector = Sector.builder()
                    .name(resultSet.getString("sector_name"))
                    .build();
            sectorList.add(sector);
        }

        return sectorList;
    }
}
