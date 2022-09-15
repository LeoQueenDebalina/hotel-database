package com.myhotel.hoteldatabase.controller;

import com.myhotel.hoteldatabase.exception.NoDataFoundException;
import com.myhotel.hoteldatabase.model.*;
import com.myhotel.hoteldatabase.service.HotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hotel/v1")
@Api(value = "This is a hotel room booking service")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "", hidden = false)
    @PostMapping("/addRoom")
    public OutputResponse addRoom(@Valid @RequestBody RoomRequest roomRequest) {
        return this.hotelService.saveHotelData(roomRequest);
    }

    @ApiOperation(value = "Search the rooms", notes = "Search the rooms")
    @GetMapping("/search")
    public List<RoomSearchResponse> roomSearch(@Valid @RequestBody RoomSearchRequest roomSearchRequest) {
        return this.hotelService.searchData(roomSearchRequest);
    }

    @ApiOperation(value = "Book the rooms", notes = "Book the rooms")
    @PostMapping("/roomBooking")
    public RoomBookResponse bookRoom(@Valid @RequestBody RoomBookRequest roomBookRequest) {
        String userId = this.restTemplate.getForObject("http://localhost:8081/user/v1/getUserByNumber/" + roomBookRequest.getUserPhoneNumber(), String.class);
        if (userId != null) {
            return this.hotelService.bookRoom(userId, roomBookRequest);
        } else {
            return new RoomBookResponse(true, "Your mobile number not register");
        }
    }

    @ApiOperation(value = "View the details", notes = "View the details")
    @GetMapping("/viewDetails")
    public List<ViewDetailsResponse> viewDetails(@Valid @RequestBody ViewDetailsRequest viewDetailsRequest) throws NoDataFoundException {
        String userId = this.restTemplate.getForObject("http://localhost:8081/user/v1/getUserByNumber/" + viewDetailsRequest.getUserPhoneNumber(), String.class);
        if (userId != null) {
            return this.hotelService.getDetails(userId);
        } else {
            throw new NoDataFoundException("User Not Found");
        }
    }

    @ApiOperation(value = "Cancel the room", notes = "Cancel the room")
    @DeleteMapping("/cancelReservation")
    public OutputResponse cancelReservation(@Valid @RequestBody CancelBookingRequest cancelBookingRequest) {
        String userId = this.restTemplate.getForObject("http://localhost:8081/user/v1/getUserByNumber/" + cancelBookingRequest.getUserPhoneNumber(), String.class);
        if (userId != null) {
            return this.hotelService.cancelById(userId, cancelBookingRequest.getRoomNo());
        } else {
            return new OutputResponse(true, "Your mobile number not register");
        }
    }

}
