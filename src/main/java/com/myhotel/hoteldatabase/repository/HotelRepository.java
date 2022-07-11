package com.myhotel.hoteldatabase.repository;

import com.myhotel.hoteldatabase.entity.HotelDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelDatabase,String> {
}
