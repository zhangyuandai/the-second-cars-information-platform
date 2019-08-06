package com.keshe.dao;

import com.keshe.entities.cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarsDao extends JpaRepository<cars,Long> {

    cars findUserById(Long id);
}
