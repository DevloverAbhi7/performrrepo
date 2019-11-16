package com.devlover.app.performr.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devlover.app.performr.model.Users;



@Repository
public interface UserDao extends CrudRepository<Users, Integer> {
Optional <Users> findByUsername(String username);
}