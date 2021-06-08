package com.rts.services.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rts.services.model.Area;

public interface AreaDAO  extends JpaRepository<Area,Integer>, JpaSpecificationExecutor {

}
