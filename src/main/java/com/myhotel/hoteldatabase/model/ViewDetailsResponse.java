package com.myhotel.hoteldatabase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewDetailsResponse {
    private String startDate;
    private String endDate;
    private String reservationDate;
    private String roomNo;
    private String roomType;
    private String roomRent;
    private String roomDescription;
    private Boolean reservationStatus;
}
