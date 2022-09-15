package com.myhotel.hoteldatabase.repository;

import com.myhotel.hoteldatabase.entity.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, String> {
    public boolean existsRoomBookingByUserUuidAndRoomNo(String userUuid, String roomNo);

    @Modifying
    @Transactional
    @Query(value = "update room_booking u set u.room_no='' where u.end_date < ?1 and u.room_no = ?2", nativeQuery = true)
    public void reRoom(Date endDate, String roomNumber);

    public List<RoomBooking> findByUserUuid(String userUuid);

    public List<RoomBooking> findByStartDateAfterAndUserUuidAndRoomNo(Date startDate, String userUuid, String roomNo);

}
