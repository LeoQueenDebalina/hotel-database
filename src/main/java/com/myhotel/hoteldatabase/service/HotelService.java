package com.myhotel.hoteldatabase.service;

import com.myhotel.hoteldatabase.entity.HotelRoom;
import com.myhotel.hoteldatabase.entity.RoomBooking;
import com.myhotel.hoteldatabase.exception.NoDataFoundException;
import com.myhotel.hoteldatabase.model.*;
import com.myhotel.hoteldatabase.repository.HotelRoomRepository;
import com.myhotel.hoteldatabase.repository.RoomBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HotelService {
    @Autowired
    private HotelRoomRepository hotelRoomRepository;
    @Autowired
    private RoomBookingRepository roomBookingRepository;

    public OutputResponse saveHotelData(RoomRequest roomRequest) {
        try {
            this.hotelRoomRepository.save(HotelRoom.builder()
                    .uuid(UUID.randomUUID().toString())
                    .roomNo(roomRequest.getRoomNo())
                    .roomType(roomRequest.getRoomType())
                    .roomDescription(roomRequest.getRoomDescription())
                    .roomRent(roomRequest.getRoomRent())
                    .startDate(new Date())
                    .endDate(new Date())
                    .build());
            return new OutputResponse(false, "All data are saved");
        } catch (Exception e) {
            return new OutputResponse(true, "Internal server error");
        }
    }

    public List<RoomSearchResponse> searchData(RoomSearchRequest roomSearchRequest) {
        List<RoomSearchResponse> list = new ArrayList<>();
        for (HotelRoom hotelRoom : this.hotelRoomRepository.searchRoom(roomSearchRequest.getStartDate(), roomSearchRequest.getEndDate(), roomSearchRequest.getRoomType())) {
            list.add(new RoomSearchResponse(hotelRoom.getRoomNo(), hotelRoom.getRoomType(), hotelRoom.getRoomDescription(), hotelRoom.getRoomRent()));
        }
        return list;
    }

    public RoomBookResponse bookRoom(String id, RoomBookRequest roomBookRequest) {
        try {
            UUID uuid = UUID.randomUUID();
            String roomId = this.hotelRoomRepository.getRoomIdByNo(roomBookRequest.getStartDate(), roomBookRequest.getEndDate(), roomBookRequest.getRoomNo());
            if (roomId != null) {
                this.roomBookingRepository.reRoom(new Date(), roomBookRequest.getRoomNo());
                if (!this.roomBookingRepository.existsRoomBookingByUserUuidAndRoomNo(id, roomBookRequest.getRoomNo())) {
                    RoomBooking list = new RoomBooking(String.valueOf(uuid), roomId, id, roomBookRequest.getStartDate(), roomBookRequest.getEndDate(), true, new Date(), roomBookRequest.getRoomNo());
                    this.roomBookingRepository.save(list);
                    this.hotelRoomRepository.updateRoom(roomBookRequest.getStartDate(), roomBookRequest.getEndDate(), roomId);
                    return new RoomBookResponse(false, "Your room is Booked At " + new Date() + " Your room no is " + roomBookRequest.getRoomNo());
                } else {
                    return new RoomBookResponse(true, "Room is already booked");
                }
            } else {
                return new RoomBookResponse(true, "Room is not available");
            }
        } catch (Exception e) {
            return new RoomBookResponse(true, "Internal server error");
        }
    }

    public List<ViewDetailsResponse> getDetails(String id) throws NoDataFoundException {
        List<ViewDetailsResponse> list = new ArrayList<>();
        for (RoomBooking roomBooking : this.roomBookingRepository.findByUserUuid(id)) {
            Optional<HotelRoom> hotelRoom = this.hotelRoomRepository.findById(roomBooking.getRoomUuid());
            if (hotelRoom.isPresent()) {
                list.add(new ViewDetailsResponse(roomBooking.getStartDate(), roomBooking.getEndDate(), roomBooking.getReservationDate(), hotelRoom.get().getRoomNo(), hotelRoom.get().getRoomType(), hotelRoom.get().getRoomRent(), hotelRoom.get().getRoomDescription(), roomBooking.getReservationStatus()));
            } else {
                throw new NoDataFoundException("Data not found");
            }
        }
        return list;
    }

    public OutputResponse cancelById(String id, String roomNo) {
        try {
            for (RoomBooking roomBooking : this.roomBookingRepository.findByStartDateAfterAndUserUuidAndRoomNo(new Date(), id, roomNo)) {
                roomBooking.setRoomNo("");
                roomBooking.setReservationStatus(false);
                this.roomBookingRepository.save(roomBooking);
                this.hotelRoomRepository.unReservedRoom(roomBooking.getRoomUuid(), roomBooking.getRoomNo());
                return new OutputResponse(false, "Your room is canceled");
            }
        } catch (Exception e) {
            return new OutputResponse(true, "Internal server error");
        }
        return new OutputResponse(true, "Cancellation time out");
    }
}
