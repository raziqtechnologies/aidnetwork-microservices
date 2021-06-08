package com.rts.services.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rts.services.model.AidCase;

public interface CaseDAO extends JpaRepository<AidCase,Integer>, JpaSpecificationExecutor {
	
	@Transactional
	@Modifying
	@Query(value = "update aid_case a set a.status = :status where a.id in (:id)",nativeQuery = true)
	int setStatus(@Param("status") String status, @Param("id") String id);
	
	
	
}
