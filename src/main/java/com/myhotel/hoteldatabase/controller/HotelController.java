package com.myhotel.hoteldatabase.controller;

import com.myhotel.hoteldatabase.model.*;
import com.myhotel.hoteldatabase.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/hotel/v1")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RoomBookResponse roomBookResponse;

    @PostMapping("/addroom")
    public OutputResponse saveRoomData(@RequestBody RoomRequest inputRequest){
        return new OutputResponse(this.hotelService.saveHotelData(inputRequest));
    }
    @GetMapping("/search")
    public List<RoomSearchResponse> getAvailableRooms(@RequestBody RoomSearchRequest roomSearchRequest){
        return this.hotelService.searchData(roomSearchRequest);
    }
    @PostMapping("/roombooking")
    public RoomBookResponse bookRoom(@RequestBody RoomBookRequest roomBookRequest) {
        String id = this.restTemplate.getForObject("http://localhost:8081/user/v1/getuserbynumber/"+roomBookRequest.getUserPh(),String.class);
        if (id != null){
            this.roomBookResponse.setMessage(this.hotelService.bookRoom(id,roomBookRequest));
        } else {
            this.roomBookResponse.setMessage("Your Number is not Registered");
        }
        return roomBookResponse;
    }
    @GetMapping("/viewdetails")
    public List<ViewDetailsResponse> viewDetails(@RequestBody ViewDetailsRequest viewDetailsRequest){
        String id = this.restTemplate.getForObject("http://localhost:8081/user/v1/getuserbynumber/"+viewDetailsRequest.getUserPh(),String.class);
        if (id != null){
            return this.hotelService.getDetails(id);
        } else {
            return null;
        }
    }
    @DeleteMapping("/cancelreservation")
    public RoomBookResponse cancelBooking(@RequestBody CancelBookingRequest cancelBookingRequest){
        String id = this.restTemplate.getForObject("http://localhost:8081/user/v1/getuserbynumber/"+cancelBookingRequest.getUserPh(),String.class);
        if (id != null){
            this.roomBookResponse.setMessage(this.hotelService.cancelById(id,cancelBookingRequest.getRoomNo()));
        }else{
            this.roomBookResponse.setMessage("Your Number is not Registered");
        }
        return roomBookResponse;
    }
}
