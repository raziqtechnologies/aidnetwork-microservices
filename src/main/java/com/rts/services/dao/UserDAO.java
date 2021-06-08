package com.rts.services.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rts.services.model.User;

public interface UserDAO extends JpaRepository<User,Integer>, JpaSpecificationExecutor {
	  // Your query methods here
	
	Optional<User> findByEmailid(String emailid);
		
	}