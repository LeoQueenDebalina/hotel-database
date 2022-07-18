package com.myhotel.hoteldatabase.controller;

import com.myhotel.hoteldatabase.model.InputRequest;
import com.myhotel.hoteldatabase.model.OutputRequest;
import com.myhotel.hoteldatabase.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelController {
    @Autowired
    private HotelService hotelService;



    @PostMapping(value = "/datas")
    public OutputRequest saveHotelData(@RequestBody InputRequest inputRequest){
        return new OutputRequest(this.hotelService.saveHotelData(inputRequest));
    }
}
