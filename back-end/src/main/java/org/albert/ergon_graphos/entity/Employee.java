package org.albert.ergon_graphos.entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee
{
    private Integer id;
    private String name;
    private Sector sector;
}
