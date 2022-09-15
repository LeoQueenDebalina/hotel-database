package com.myhotel.hoteldatabase.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="roomBooking")
@Builder
public class RoomBooking {
    @Id
    @Column(name="registrationUuid",length = 100,nullable = false)
    private String registrationUuid;
    @Column(name="roomUuid",length = 100, nullable = false)
    private String roomUuid;
    @Column(name="userUuid",length = 100, nullable = false)
    private String userUuid;
    @Column(name="startDate",length = 100, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name="endDate",length = 100, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name="reservationStatus",length = 100, nullable = false)
    private Boolean reservationStatus;
    @Column(name="reservationDate",length = 100, nullable = false)
    @Temporal(TemporalType.DATE)
    private  Date reservationDate;
    @Column(name="roomNo",length = 100)
    private String roomNo;
}
