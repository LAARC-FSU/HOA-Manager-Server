package com.laarc.hoamanagerserver.api.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShiftTimeDTO {

    private Long shiftTimeId;
    private String start;
    private String end;
    private boolean enabled;
}
