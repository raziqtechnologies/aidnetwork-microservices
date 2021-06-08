package com.rts.services.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rts.services.RecordException;
import com.rts.services.dao.CaseDAO;
import com.rts.services.dao.CommentDAO;
import com.rts.services.dao.GroupDAO;
import com.rts.services.dao.MetaDAO;
import com.rts.services.extension.ByUser;
import com.rts.services.extension.StartsWith;
import com.rts.services.model.AidCase;
import com.rts.services.model.Area;
import com.rts.services.model.Comment;
import com.rts.services.model.Group;
import com.rts.services.model.Meta;
import com.rts.services.security.UserType;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
@RequestMapping("/case")
public class CaseService {
	
	@Autowired
	private CaseDAO caseDAO;
	
	@Autowired
	private MetaDAO metaDAO;
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private GroupDAO groupDAO;
	
	@GetMapping
	@PreAuthorize("hasRole('ADMINISTRATOR') OR hasRole('POC') OR hasRole('OPERATOR') OR hasRole('VOLUNTEER')")
	public List<AidCase> list(@And({
        @Spec(path="poc", spec=Equal.class),
        @Spec(path="status",spec=In.class),
        @Spec(path="id", spec=StartsWith.class),
        @Spec(path="casepriority", spec=Equal.class),
        @Spec(path="areaname", spec=Equal.class),
        @Spec(path="validate", spec=ByUser.class),
        }) 
		Specification<AidCase> spec)
	{
		
		if(spec != null)
		{
	
			return (List<AidCase>)caseDAO.findAll(spec);
		}
		else
		{
			throw new RecordException("No params passed");
		}
	}
	
	@GetMapping("/search")
	@PreAuthorize("hasRole('ADMINISTRATOR') OR hasRole('POC') OR hasRole('OPERATOR') OR hasRole('VOLUNTEER')")
	public List<AidCase> search(@RequestParam(required = false,name = "page",defaultValue = "0") int page, 
			  @RequestParam(required = false, name = "size",defaultValue = "3") int size,@And({@Spec(path="validate", spec=ByUser.class)}) @Or({
        @Spec(path="benname", spec=Like.class),
        @Spec(path="benphone",spec=StartsWith.class),
        @Spec(path="id", spec=Equal.class),
        @Spec(path="areaname", spec=StartsWith.class),
        @Spec(path="status", spec=StartsWith.class),
        @Spec(path="poc", spec=Equal.class),
        
        }) 
		Specification<AidCase> spec)
	{
		
		if(spec == null)
		{
			throw new RecordException("No params passed");
		}
		PageRequest paging  = PageRequest.of(page, size,Sort.by(Direction.DESC, "createdate"));
		
		Page<AidCase> pagedResult = caseDAO.findAll(spec,paging);
        
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<AidCase>();
        }
		
	}
	
	@GetMapping("/security/check")
	public ArrayList<String> securityCheck(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ArrayList<String> user = new ArrayList<String>();
		user.add(auth.getName());
		user.add(auth.getAuthorities().iterator().next().getAuthority());
		return user;
	}
	
	@GetMapping("/group/poc")
	@PreAuthorize("hasRole('ADMINISTRATOR') OR hasRole('POC') OR hasRole('OPERATOR') OR hasRole('VOLUNTEER')")
	public List<Group> groupByPOC() 
	{
		 UserType type = new UserType();
		
		 List<Group> cases = groupDAO.groupByPOC(type.getPOC(),type.getVolunteer());
		 return cases;
	}
	
	
	@GetMapping("/meta/{meta}")
	public List<Meta> meta(@PathVariable int meta) 
	{
		List<Meta> metas = metaDAO.findByType(meta);
		return metas;
	}
	

	@GetMapping("/meta/{meta}/{id}")
	public Meta metaById(@PathVariable int meta,@PathVariable int id) 
	{
		Optional<Meta> metas = metaDAO.findById(id);
		return metas.get();
	}
	

	
	@GetMapping("/group/area")
	@PreAuthorize("hasRole('ADMINISTRATOR') OR hasRole('POC') OR hasRole('OPERATOR') OR hasRole('VOLUNTEER')")
	public List<Group> groupByArea(@RequestParam(name = "poc",required=false) String poc,@RequestParam(name = "status",required=false) String status) 
	{
		 UserType type = new UserType();
		 if(poc != null && poc.length() > 0)
		 type.setPOC(poc);
		List<Group> cases = groupDAO.groupByArea(type.getPOC(),type.getVolunteer(),status);
		return cases;
	}
	
	@GetMapping("/group/status")
	@PreAuthorize("hasRole('ADMINISTRATOR') OR hasRole('POC') OR hasRole('OPERATOR') OR hasRole('VOLUNTEER')")
	public List<Group> groupByStatus(@RequestParam(name = "poc",required=false) String poc) 
	{
		 UserType type = new UserType();
		 if(poc != null && poc.length() > 0)
		 type.setPOC(poc);
		List<Group> cases = groupDAO.groupByStatus(type.getPOC(),type.getVolunteer());
		return cases;
	}
	
	@GetMapping("/group/priority")
	@PreAuthorize("hasRole('ADMINISTRATOR') OR hasRole('POC') OR hasRole('OPERATOR') OR hasRole('VOLUNTEER')")
	public List<Group> groupByPriority(@RequestParam(name = "poc",required=false) String poc,@RequestParam(name = "area",required=false) String area,@RequestParam(name = "status",required=false) String status) 
	{
		 UserType type = new UserType();
		 if(poc != null && poc.length() > 0)
		 type.setPOC(poc);
		List<Group> cases = groupDAO.groupByPriority(type.getPOC(),type.getVolunteer(),status,area);
		return cases;
	}
	
	@PreAuthorize("hasRole('ADMINISTRATOR') OR hasRole('POC') OR hasRole('OPERATOR') OR hasRole('VOLUNTEER')")
	@PostMapping("/status")
	public void updateStatus(@RequestBody AidCase area)
	{
		caseDAO.setStatus(area.getStatus(), area.getId());
	}
	
	@PreAuthorize("hasRole('ADMINISTRATOR') OR hasRole('POC') OR hasRole('OPERATOR') OR hasRole('VOLUNTEER')")
	@PostMapping
	public void add(@RequestBody AidCase area)
	{
		caseDAO.save(area);
	}
	

	
	@PreAuthorize("hasRole('ADMINISTRATOR') OR hasRole('POC') OR hasRole('OPERATOR') OR hasRole('VOLUNTEER')")
	@PostMapping("/comment")
	public void add(@RequestBody Comment area)
	{
		commentDAO.save(area);
	}
	
	@PreAuthorize("hasRole('ADMINISTRATOR') OR hasRole('POC') OR hasRole('OPERATOR') OR hasRole('VOLUNTEER')")
	@GetMapping("/{caseid}/comment")
	public List<Comment> listComments(@PathVariable String caseid) 
	{
		List<Comment> comment = commentDAO.findByCaseid(caseid);
		return comment;
	}
}
