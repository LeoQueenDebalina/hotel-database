package com.myhotel.hoteldatabase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelBookingRequest {
    private String roomNo;
    private String userPh;
}
