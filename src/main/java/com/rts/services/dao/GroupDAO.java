package com.rts.services.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rts.services.model.Group;

public interface GroupDAO extends JpaRepository<Group,Integer>, JpaSpecificationExecutor {
	
	
	@Query(value = "select new com.rts.services.model.Group(poc,count(*)) FROM AidCase a where (:poc is null or a.poc = :poc) and (:volunteer is null or a.volunteer = :volunteer) group by a.poc order by a.poc desc ")
	List<Group> groupByPOC(@Param("poc") String poc,@Param("volunteer") String volunteer);
	
	@Query(value = "select new com.rts.services.model.Group(callercategory,count(*)) FROM AidCase a group by a.callercategory order by a.callercategory desc ")
	List<Group> groupByCallerCategory();
	
	@Query(value = "select new com.rts.services.model.Group(casecategory,count(*)) FROM AidCase a group by a.casecategory order by a.casecategory desc ")
	List<Group> groupByCaseCategory();
	
	
	//@Query(value = "select new com.rts.services.model.Group(areaname,count(*)) FROM AidCase a where  (:poc is null or a.poc = :poc) and (:operator is null or a.operator = :operator) and (:volunteer is null or a.volunteer = :volunteer) group by a.areaname order by a.areaname asc ")
	@Query(value = "select new com.rts.services.model.Group(areaname,count(*)) FROM AidCase a where  (:poc is null or a.poc = :poc) and (:volunteer is null or a.volunteer = :volunteer) and (:status is null or a.status = :status)group by a.areaname order by a.areaname asc ")
	List<Group> groupByArea(@Param("poc") String poc,@Param("volunteer") String volunteer,@Param("status") String status);
	
	//operator view role based not individual based @Query(value = "select new com.rts.services.model.Group(status,count(*)) FROM AidCase a where  (:poc is null or a.poc = :poc) and (:operator is null or a.operator = :operator) and (:volunteer is null or a.volunteer = :volunteer) and (:areaname is null or a.areaname = :areaname) group by a.status order by a.status desc ")
	@Query(value = "select new com.rts.services.model.Group(status,count(*)) FROM AidCase a where  (:poc is null or a.poc = :poc)  and (:volunteer is null or a.volunteer = :volunteer) group by a.status order by a.status desc ")
	List<Group> groupByStatus(@Param("poc") String poc,@Param("volunteer") String volunteer);
	
	//@Query(value = "select new com.rts.services.model.Group(casepriority,count(*)) FROM AidCase a where  (:poc is null or a.poc = :poc) and (:operator is null or a.operator = :operator) and (:volunteer is null or a.volunteer = :volunteer) and (:status is null or a.status = :status) and (:areaname is null or a.areaname = :areaname) group by a.casepriority order by a.casepriority desc ")
	@Query(value = "select new com.rts.services.model.Group(casepriority,count(*)) FROM AidCase a where  (:poc is null or a.poc = :poc) and (:volunteer is null or a.volunteer = :volunteer) and (:status is null or a.status = :status) and (:areaname is null or a.areaname = :areaname) group by a.casepriority order by a.casepriority desc ")
	List<Group> groupByPriority(@Param("poc") String poc,@Param("volunteer") String volunteer,@Param("status") String status,@Param("areaname") String areaname);
	
}
