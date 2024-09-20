package org.albert.ergon_graphos.entity.dto;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SectorDiaryReport
{
    private Integer reportCount;
    private String sectorName;
    private String problemDescription;
    private Date reportDate;
}
