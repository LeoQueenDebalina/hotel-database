package com.myhotel.hoteldatabase.repository;

import com.myhotel.hoteldatabase.entity.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom, String> {

    @Query(value = "select * from hotel u where ?1 not between u.start_date and u.end_date and ?2 not between u.start_date and u.end_date and u.room_type = ?3", nativeQuery = true)
    public List<HotelRoom> searchRoom(Date startDate, Date endDate, String roomType);

    @Query(value = "select u.uuid from hotel u where ?1 not between u.start_date and u.end_date and ?2 not between u.start_date and u.end_date and u.room_no = ?5", nativeQuery = true)
    public String getRoomIdByNo(Date startDate, Date endDate, String roomNo);

    @Transactional
    @Modifying
    @Query(value = "update hotel u set u.start_date=?1 , u.end_date=?2 where u.uuid = ?3", nativeQuery = true)
    public void updateRoom(Date startDate, Date endDate, String uuId);

    @Transactional
    @Modifying
    @Query(value = "update hotel u set u.start_date='', u.end_date='' where u.uuid = ?1 and u.room_no = ?2", nativeQuery = true)
    public void unReservedRoom(String uuId, String roomNumber);
}