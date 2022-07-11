package com.myhotel.hoteldatabase.service;

import com.myhotel.hoteldatabase.entity.HotelDatabase;
import com.myhotel.hoteldatabase.model.InputRequest;
import com.myhotel.hoteldatabase.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelServiceImplement {
    private String massage;
    @Autowired
    private HotelRepository hotelRepository;
    UUID uuid = UUID.randomUUID();
    public String saveHotelData(InputRequest inputRequest) {
        List list = Arrays.asList(inputRequest);
        List<HotelDatabase> test = (List<HotelDatabase>) list.stream().map(e -> HotelDatabase.builder()
                .uuid(String.valueOf(uuid))
                .roomNo(inputRequest.getRoomNo())
                .roomType(inputRequest.getRoomType())
                .roomDescription(inputRequest.getRoomDescription())
                .roomRent(inputRequest.getRoomRent())
                .reservationStatus(inputRequest.isReservationStatus())
                .startDate(inputRequest.getStartDate())
                .endDate(inputRequest.getEndDate())
                .build()).collect(Collectors.toList());
        hotelRepository.saveAll(test);
        return massage = "all data are saved";

    }
}
