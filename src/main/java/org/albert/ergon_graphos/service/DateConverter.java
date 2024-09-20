package org.albert.ergon_graphos.service;

import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class DateConverter
{
    public java.sql.Date convertJavaDateToSqlDate(java.util.Date javaDate)
    {
        return new java.sql.Date(javaDate.getTime());
    }

    public Date convertSqlDateToJavaDate(java.sql.Date sqlDate)
    {
        return new Date(sqlDate.getTime());
    }
}