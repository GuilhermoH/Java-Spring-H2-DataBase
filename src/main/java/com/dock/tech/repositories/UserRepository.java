package com.dock.tech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dock.tech.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
