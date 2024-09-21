package org.albert.ergon_graphos.entity;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Report
{
    private Integer id;
    private Date date;
    private Employee employee;
    private Sector sector;

    public String getDateFormatted()
    {
        return new SimpleDateFormat("dd/MM/yy").format(date);
    }
}
