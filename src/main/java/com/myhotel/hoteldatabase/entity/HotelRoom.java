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
@Table(name="hotel")
@Builder
public class HotelRoom {
    @Id
    @Column(name = "uuid",length = 100, nullable = false)
    private String uuid;
    @Column(name = "roomNo",length = 100, nullable = false)
    private String roomNo;
    @Column(name = "roomType",length = 100, nullable = false)
    private String roomType;
    @Column(name = "roomDescription",length = 100, nullable = false)
    private String roomDescription;
    @Column(name = "roomRent",length = 100, nullable = false)
    private String roomRent;
    @Column(name="startDate",length = 10)
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name="endDate",length = 10)
    @Temporal(TemporalType.DATE)
    private Date endDate;
}
