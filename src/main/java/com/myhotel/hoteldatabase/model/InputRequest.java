package com.myhotel.hoteldatabase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputRequest {
    private String roomNo;
    private String roomType;
    private String roomDescription;
    private String roomRent;
    private String startDate;
    private String endDate;
    private boolean reservationStatus;
}
