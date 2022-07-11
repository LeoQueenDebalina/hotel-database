package com.myhotel.hoteldatabase.repository;

import com.myhotel.hoteldatabase.entity.HotelDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface HotelRepository extends JpaRepository<HotelDatabase,String> {
    @Query(value = "select u from UserDatabase u where u.uuid = ?1")
    public List<HotelDatabase> selectAllByNumber(String number);
}
