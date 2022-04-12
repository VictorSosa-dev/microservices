package com.user.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.service.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
