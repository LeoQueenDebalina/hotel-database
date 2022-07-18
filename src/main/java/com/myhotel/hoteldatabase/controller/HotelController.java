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

    @PostMapping("/addroom")
    public OutputResponse addRoom(@RequestBody RoomRequest roomRequest){
        return new OutputResponse(this.hotelService.saveHotelData(roomRequest));
    }
    @GetMapping("/search")
    public List<RoomSearchResponse> roomSearch(@RequestBody RoomSearchRequest roomSearchRequest){
        return this.hotelService.searchData(roomSearchRequest);
    }
    @PostMapping("/roombooking")
    public RoomBookResponse bookRoom(@RequestBody RoomBookRequest roomBookRequest){
        String userId = this.restTemplate.getForObject("http://localhost:8081/user/v1/getuserbynumber/"+roomBookRequest.getUserPh(), String.class);
        if(userId!=null){
            return new RoomBookResponse(this.hotelService.bookRoom(userId,roomBookRequest));
        } else {
            return new RoomBookResponse("Your mobile number not register");
        }
    }

    @GetMapping("/viewdetails")
    public List<ViewDetailsResponse> viewDetails(@RequestBody ViewDetailsRequest viewDetailsRequest){
        String userId = this.restTemplate.getForObject("http://localhost:8081/user/v1/getuserbynumber/"+viewDetailsRequest.getUserPh(), String.class);
        if(userId!=null){
            return this.hotelService.getDetails(userId);
        } else {
            return null;
        }
    }
    @DeleteMapping("/cancelreservation")
    public OutputResponse cancelReservation(@RequestBody CancelBookingRequest cancelBookingRequest){
        String userId = this.restTemplate.getForObject("http://localhost:8081/user/v1/getuserbynumber/"+cancelBookingRequest.getUserPh(), String.class);
        if(userId!=null){
            return new OutputResponse(this.hotelService.cancelById(userId,cancelBookingRequest.getRoomNo()));
        } else {
            return new OutputResponse("Your mobile number not register");
        }
    }

}
