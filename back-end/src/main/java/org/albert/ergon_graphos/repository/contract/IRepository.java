package org.albert.ergon_graphos.repository.contract;

import java.sql.SQLException;
import java.util.List;

public interface IRepository<E, ID>
{
    void create(E e) throws SQLException;
    void delete(ID id) throws SQLException;
    void update(E e) throws SQLException;
    E read(ID id) throws SQLException;
    List<E> readAll() throws SQLException;
}
