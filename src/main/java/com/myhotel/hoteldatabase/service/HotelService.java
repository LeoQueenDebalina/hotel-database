package com.myhotel.hoteldatabase.service;

import com.myhotel.hoteldatabase.entity.HotelRoom;
import com.myhotel.hoteldatabase.entity.RoomBooking;
import com.myhotel.hoteldatabase.model.*;
import com.myhotel.hoteldatabase.repository.HotelRoomRepository;
import com.myhotel.hoteldatabase.repository.RoomBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HotelService{
    private String message;
    @Autowired
    private HotelRoomRepository hotelRoomRepository;
    @Autowired
    private RoomBookingRepository roomBookingRepository;

    public String saveHotelData(RoomRequest roomRequest) {
        try {
            UUID uuid = UUID.randomUUID();
            List list = Arrays.asList(roomRequest);
            List<HotelRoom> test = (List<HotelRoom>) list.stream().map(e -> HotelRoom.builder()
                    .uuid(String.valueOf(uuid))
                    .roomNo(roomRequest.getRoomNo())
                    .roomType(roomRequest.getRoomType())
                    .roomDescription(roomRequest.getRoomDescription())
                    .roomRent(roomRequest.getRoomRent())
                    .startDate(roomRequest.getStartDate())
                    .endDate(roomRequest.getEndDate())
                    .build()).collect(Collectors.toList());
            hotelRoomRepository.saveAll(test);
            message = "All data are saved";
        } catch (Exception e){
            message = "Internal server error";
        }
        return message;
    }

    public List<RoomSearchResponse> searchData(RoomSearchRequest roomSearchRequest) {
        List<RoomSearchResponse> list = new ArrayList<>();
        for (HotelRoom r : this.hotelRoomRepository.searchRoom(roomSearchRequest.getStartDate(),roomSearchRequest.getEndDate(),roomSearchRequest.getStartDate(),roomSearchRequest.getEndDate(),roomSearchRequest.getRoomType())){
            list.add(new RoomSearchResponse(r.getRoomNo(),r.getRoomType(),r.getRoomDescription(),r.getRoomRent()));
        }
        return list;
    }

    public String bookRoom(String id, RoomBookRequest roomBookRequest) {
        try {
            UUID uuid = UUID.randomUUID();
            String roomId = this.hotelRoomRepository.getRoomIdByNo(roomBookRequest.getStartDate(),roomBookRequest.getEndDate(),roomBookRequest.getStartDate(),roomBookRequest.getEndDate(),roomBookRequest.getRoomNo());
            if (roomId!=null){
                this.roomBookingRepository.reRoom(String.valueOf(java.time.LocalDate.now()),roomBookRequest.getRoomNo());
                if (!this.roomBookingRepository.findByUIdRNo(id,roomBookRequest.getRoomNo())) {
                    RoomBooking list = new RoomBooking(String.valueOf(uuid), roomId, id, roomBookRequest.getStartDate(), roomBookRequest.getEndDate(), true, String.valueOf(new Date()), roomBookRequest.getRoomNo());
                    this.roomBookingRepository.save(list);
                    this.hotelRoomRepository.updateRoom(roomBookRequest.getStartDate(),roomBookRequest.getEndDate(),roomId);
                    message = "Your room is Booked At " + new Date() + " Your room no is " + roomBookRequest.getRoomNo();
                }else {
                    message = "Room is already booked";
                }
            }else {
                message = "Room is not available";
            }
        } catch (Exception e) {
            message = "Internal server error";
            e.printStackTrace();
        }
        return message;
    }

    public List<ViewDetailsResponse> getDetails(String id) {
        List<ViewDetailsResponse> list = new ArrayList<>();
        for (RoomBooking r: this.roomBookingRepository.findAllRoomById(id)) {
            Optional<HotelRoom> ripo = this.hotelRoomRepository.findById(r.getRoomUuid());
            if (ripo.isPresent()) {
                list.add(new ViewDetailsResponse(r.getStartDate(), r.getEndDate(), r.getReservationDate(), ripo.get().getRoomNo(),ripo.get().getRoomType(),ripo.get().getRoomRent(),ripo.get().getRoomDescription(),r.getReservationStatus()));
            } else {
                return null;
            }
        }
        return list;
    }

    public String cancelById(String id, String roomNo) {
        try {
            for (RoomBooking r: this.roomBookingRepository.findRoomById(String.valueOf(java.time.LocalDate.now()),id,roomNo)) {
                this.roomBookingRepository.cancelRoom(r.getRegistrationUuid());
                this.hotelRoomRepository.unReservedRoom(r.getRoomUuid(),r.getRoomNo());
                message = "Your room is canceled";
            }
        } catch (Exception e){
            message = "Internal server error";
            e.printStackTrace();
        }
        return message;
    }
}
