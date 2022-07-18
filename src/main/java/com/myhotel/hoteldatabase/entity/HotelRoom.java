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
@Table(name="hotel")
@Builder
public class HotelRoom {
    @Id
    @Column(name = "uuid",length = 100)
    private String uuid;
    @Column(name = "roomNo",length = 100)
    private String roomNo;
    @Column(name = "roomType",length = 100)
    private String roomType;
    @Column(name = "roomDescription",length = 100)
    private String roomDescription;
    @Column(name = "roomRent",length = 100)
    private String roomRent;
    @Column(name="startDate",length = 10)
    private String startDate;
    @Column(name="endDate",length = 10)
    private String endDate;

}
