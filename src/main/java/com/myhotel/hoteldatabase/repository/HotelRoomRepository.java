package com.myhotel.hoteldatabase.repository;

import com.myhotel.hoteldatabase.entity.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom,String> {
    @Query(value = "select * from hotel u where u.start_date not between date?1 and date?2 and u.end_date not between date?3 and date?4 and u.room_type = ?5", nativeQuery = true)
    public List<HotelRoom> searchRoom(String a, String b, String c, String d, String e);
    @Query(value = "select u.uuid from hotel u where u.start_date not between date?1 and date?2 and u.end_date not between date?3 and date?4 and u.room_no = ?5", nativeQuery = true)
    public String getRoomIdByNo(String a,String b,String c,String d,String e);

    @Transactional
    @Modifying
    @Query(value = "update hotel u set u.start_date=?1 , u.end_date=?2 where u.uuid = ?3", nativeQuery = true)
    public void updateRoom(String a,String b, String c);

    @Transactional
    @Modifying
    @Query(value = "update hotel u set u.start_date='', u.end_date='' where u.uuid = ?1 and u.room_no = ?2", nativeQuery = true)
    public void unReservedRoom(String n,String a);
}