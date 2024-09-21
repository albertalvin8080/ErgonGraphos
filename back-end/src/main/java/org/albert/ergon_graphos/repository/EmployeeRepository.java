package org.albert.ergon_graphos.repository;

import org.albert.ergon_graphos.entity.Employee;
import org.albert.ergon_graphos.repository.contract.IRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository implements IRepository<Employee, Integer>
{
    @Override
    public void create(Employee employee)
    {

    }

    @Override
    public void delete(Integer integer)
    {

    }

    @Override
    public void update(Employee employee)
    {

    }

    @Override
    public Employee read(Integer integer)
    {
        return null;
    }

    @Override
    public List<Employee> readAll()
    {
        return null;
    }
}
