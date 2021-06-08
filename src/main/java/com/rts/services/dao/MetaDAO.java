package com.rts.services.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rts.services.model.Group;
import com.rts.services.model.Meta;

public interface MetaDAO extends JpaRepository<Meta,Integer>, JpaSpecificationExecutor {
	
	
	List<Meta> findByType(int type);
	
	
}
