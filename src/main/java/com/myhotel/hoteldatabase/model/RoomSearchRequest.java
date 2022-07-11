package com.myhotel.hoteldatabase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomSearchRequest {
    private String startDate;
    private String endDate;
    private String roomType;
}
