package com.keshe.dao;

import com.keshe.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);
    User findUserById(Long id);

}
