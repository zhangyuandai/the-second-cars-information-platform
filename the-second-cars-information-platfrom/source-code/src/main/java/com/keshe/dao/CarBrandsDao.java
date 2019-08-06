package com.keshe.dao;


import com.keshe.entities.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBrandsDao extends JpaRepository<CarBrand,Long> {

    CarBrand findUserById(Long id);


}
