package com.rts.services.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rts.services.RecordException;
import com.rts.services.dao.AreaDAO;
import com.rts.services.dao.AreaGroupDAO;
import com.rts.services.dao.UserDAO;
import com.rts.services.extension.StartsWith;
import com.rts.services.model.AreaGroup;
import com.rts.services.model.User;

import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
@RequestMapping("/areagroup")
public class AreaGroupService {
	
	@Autowired
	private AreaGroupDAO groupDAO;
	@Autowired
	private AreaDAO areaDAO;
	@Autowired
	private UserDAO userDAO;
	
	@GetMapping
	
	public List<AreaGroup> list(
		        @And(@Spec(path="areagroup", spec=StartsWith.class)) Specification<AreaGroup> spec)
	{
		
		
			List<AreaGroup> areagroups = groupDAO.findAll(spec);
			if(areagroups != null && areagroups.size() > 0)
			{
				loadAreas(areagroups);
				loadPOCs(areagroups);
			}
			else {
				throw new RecordException("No Records Found");
			}
			return areagroups;
		
		
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public void add(@RequestBody AreaGroup area)
	{
		List<AreaGroup> areagroup = groupDAO.findByAreagroup(area.getAreagroup());
		if(areagroup.size() > 0)
		{
			if(areagroup.get(0).getId() == area.getId())
			{	//update
				groupDAO.save(area);
			}
			else
			{
				throw new RecordException("Areagroup already exist");
			}
		}
		else {
			groupDAO.save(area);	
		}
		
	}
	
	private void loadAreas(List<AreaGroup> areagroups)
	{
		
		for (AreaGroup areaGroup : areagroups) {
			int[] areaids = Arrays.stream(areaGroup.getAreasid().split(","))
				    .mapToInt(Integer::parseInt)
				    .toArray();
			for(int i = 0;i<areaids.length;i++)
			{
				areaGroup.getAreas().add(areaDAO.findById(areaids[i]).get());
			}
		}
	}
	
	private void loadPOCs(List<AreaGroup> areagroups)
	{
		
		
		for (AreaGroup areaGroup : areagroups) {
			if(areaGroup.getPocsid() != null)
				{
				int[] ids = Arrays.stream(areaGroup.getPocsid().split(","))
					    .mapToInt(Integer::parseInt)
					    .toArray();
				for(int i = 0;i<ids.length;i++)
				{
					User user =  userDAO.findById(ids[i]).get();
					if(user != null)
					{
						areaGroup.getPocs().add(user);
					}
				}
			}
		}
	}

}
