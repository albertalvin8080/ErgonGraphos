package org.albert.ergon_graphos.entity.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReportDto
{
    private Integer employeeId;
    private Integer problemId;
}
