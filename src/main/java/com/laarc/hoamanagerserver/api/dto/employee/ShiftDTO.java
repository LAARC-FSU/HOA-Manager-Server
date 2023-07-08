package com.laarc.hoamanagerserver.api.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShiftDTO {
    private Long shiftId;
    private ShiftTimeDTO firstShiftTime;
    private ShiftTimeDTO secondShiftTime;
    private ShiftTimeDTO thirdShiftTime;
}
