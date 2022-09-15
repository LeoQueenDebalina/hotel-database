package com.myhotel.hoteldatabase;

import com.myhotel.hoteldatabase.repository.HotelRoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class HotelDatabaseApplicationTests {
	@Autowired
	private HotelRoomRepository hotelRoomRepository;
	@Test
	void contextLoads() {
	}
}
