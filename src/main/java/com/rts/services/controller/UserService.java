package com.rts.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rts.services.RecordException;
import com.rts.services.dao.UserDAO;
import com.rts.services.model.User;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	

	
	@GetMapping
	@PreAuthorize("hasRole('ADMINISTRATOR') OR hasRole('POC') OR hasRole('OPERATOR') OR hasRole('VOLUNTEER')")
	public List<User> searchUser(
			 		@And({
		            @Spec(path="role", spec=Equal.class),
		            @Spec(path="firstname", spec=Like.class),
		            @Spec(path="emailid", spec=Equal.class),
		            @Spec(path="parentid", spec=Equal.class)
		            }) 
			Specification<User> spec)
	{
		return (List<User>)userDAO.findAll(spec);
	}

	@PostMapping
	public void addUser(@RequestBody User user)
	{
		
		User userDB = userDAO.findByEmailid(user.getEmailid()).orElse(user);
		
		if(userDB.getId() == 0)
		{
			userDAO.save(user);
		}
		else {
			
			if(userDB.getId() == user.getId())
			{
				//update
				userDAO.save(user);
			}
			else {
				throw new RecordException("User already exist");
			}
		}
		
	}
	


}
