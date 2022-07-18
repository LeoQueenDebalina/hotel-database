package com.myhotel.hoteldatabase.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Hotel")
@Builder
public class HotelDatabase {
    @Id
    @Column(name = "uuid",length = 100)
    private String uuid;
    @Column(name = "room-no",length = 100)
    private String roomNo;
    @Column(name = "room-type",length = 100)
    private String roomType;
    @Column(name = "room-description",length = 100)
    private String roomDescription;
    @Column(name = "room-rent",length = 100)
    private String roomRent;
    @Column(name="start date",length = 10,nullable = true)
    private String startDate;
    @Column(name="end date",length = 10,nullable = true)
    private String endDate;
    @Column(name="reservation-status",insertable = true)
    private boolean reservationStatus;


}
