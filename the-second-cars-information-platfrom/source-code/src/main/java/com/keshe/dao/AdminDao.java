package com.keshe.dao;

import com.keshe.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends JpaRepository<Admin,Integer> {

    Admin findByNameAndPassword(String name,String password);
}
