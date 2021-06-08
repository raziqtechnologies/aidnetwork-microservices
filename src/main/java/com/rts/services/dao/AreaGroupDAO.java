package com.rts.services.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rts.services.model.AreaGroup;

public interface AreaGroupDAO extends JpaRepository<AreaGroup,Integer>, JpaSpecificationExecutor {
	
	List<AreaGroup> findByAreagroup(String areagroup);
	
	
}
