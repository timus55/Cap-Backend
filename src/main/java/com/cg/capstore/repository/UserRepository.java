package com.cg.capstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.capstore.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}