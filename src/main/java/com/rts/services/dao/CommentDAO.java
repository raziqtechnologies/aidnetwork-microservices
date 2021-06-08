package com.rts.services.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.rts.services.model.AidCase;
import com.rts.services.model.Comment;
import com.rts.services.model.Group;

public interface CommentDAO extends JpaRepository<Comment,Integer>, JpaSpecificationExecutor {
	
	List<Comment> findByCaseid(String caseid);

	
}
