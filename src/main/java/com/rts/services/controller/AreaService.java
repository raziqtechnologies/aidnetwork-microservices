package com.rts.services.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rts.services.dao.AreaDAO;
import com.rts.services.extension.StartsWith;
import com.rts.services.model.Area;

import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
@RequestMapping("/area")
public class AreaService {
	
	@Autowired
	private AreaDAO areaDAO;
	
	@GetMapping
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public List<Area> list(@RequestParam(required = false,name = "page",defaultValue = "1") int page, 
			  @RequestParam(required = false, name = "size",defaultValue = "1") int size,
        @Spec(path="area", spec=StartsWith.class) Specification<Area> spec)
	{
		
		if(spec != null)
		{
			return areaDAO.findAll(spec);
		}
		
		PageRequest paging  = PageRequest.of(page, size,Sort.by("area"));
	
		Page<Area> pagedResult = areaDAO.findAll(spec,paging);
        
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Area>();
        }
		
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public void addArea(@RequestBody Area area)
	{
		areaDAO.save(area);
	}

}
