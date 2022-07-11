package com.myhotel.hoteldatabase;

import com.myhotel.hoteldatabase.controller.HotelController;
import com.myhotel.hoteldatabase.model.RoomRequest;
import com.myhotel.hoteldatabase.model.OutputResponse;
import com.myhotel.hoteldatabase.repository.HotelRoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class HotelDatabaseApplicationTests {
	@Autowired
	private HotelRoomRepository hotelRoomRepository;
	@Test
	void contextLoads() {
	}
	@Test
	void te(){
		hotelRoomRepository.updateRoom("2022-07-28","2022-07-30","2d3d9f52-123b-4252-b242-60c9e7cf57b2");
	}
}
