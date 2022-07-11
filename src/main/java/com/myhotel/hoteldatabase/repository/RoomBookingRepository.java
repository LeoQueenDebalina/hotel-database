package com.myhotel.hoteldatabase.repository;

import com.myhotel.hoteldatabase.entity.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, String> {
    @Query("select case when count(u)>0 then true else false end from RoomBooking u where u.userUuid = :n and u.roomNo = :m")
    public boolean findByUIdRNo(@Param("n") String UId, @Param("m") String rNumber);
    @Modifying
    @Transactional
    @Query(value = "update room_booking u set u.room_no='' where u.end_date < date?1 and u.room_no = ?2", nativeQuery = true)
    public void reRoom(String n,String m);
    @Query("select u from RoomBooking u where u.userUuid = :n")
    public List<RoomBooking> findAllRoomById(@Param("n") String id);
    @Query(value = "select * from room_booking u where u.start_date > date?1 and u.user_uuid = ?2 and u.room_no = ?3", nativeQuery = true)
    public List<RoomBooking> findRoomById(String date,String id,String no);
    @Modifying
    @Transactional
    @Query(value = "update room_booking u set u.room_no='', u.reservation_status = false where u.registration_uuid = ?1", nativeQuery = true)
    public void cancelRoom(String n);
}
