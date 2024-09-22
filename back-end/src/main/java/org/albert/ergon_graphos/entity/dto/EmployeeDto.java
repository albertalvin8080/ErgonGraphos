package org.albert.ergon_graphos.entity.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto
{
    private Integer id;
    private String name;
    private Integer sectorId;
}
