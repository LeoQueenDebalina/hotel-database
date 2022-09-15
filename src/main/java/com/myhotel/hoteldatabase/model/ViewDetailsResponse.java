package com.myhotel.hoteldatabase.model;

import com.fasterxml.jackson.databind.DatabindException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewDetailsResponse {
    private Date startDate;
    private Date endDate;
    private Date reservationDate;
    private String roomNo;
    private String roomType;
    private String roomRent;
    private String roomDescription;
    private Boolean reservationStatus;
}
