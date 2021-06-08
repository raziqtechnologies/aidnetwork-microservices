
package com.rts.services.extension;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import net.kaczmarzyk.spring.data.jpa.domain.PathSpecification;
import net.kaczmarzyk.spring.data.jpa.domain.WithoutTypeConversion;
import net.kaczmarzyk.spring.data.jpa.utils.QueryContext;

/**
 * Filters with {@code path like %pattern%} where-clause.
 * 
 * @author Tomasz Kaczmarzyk
 */
public class ByUser<T> extends PathSpecification<T> implements WithoutTypeConversion {

	private static final long serialVersionUID = 1L;
	
	protected String pattern;

    public ByUser(QueryContext queryContext, String path, String... args) {
        super(queryContext, path);
       
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	 GrantedAuthority role = authentication.getAuthorities().iterator().next();
    	 String rolename = role.getAuthority();
    	 String username = authentication.getName();
    	
    	 if(rolename.equals("ROLE_POC"))
    	 {
    		 Predicate predicate = builder.equal(root.get("poc"), username);
    		 return predicate;
    	 }
    	 else if(rolename.equals("ROLE_VOLUNTEER")){
    		 Predicate predicate = builder.equal(root.get("volunteer"), username);
    		 return predicate;
    	 }
//    	 else if(rolename.equals("ROLE_OPERATOR")){
//    		 Predicate predicate = builder.equal(root.get("operator"), username);
//    		 return predicate;
//    	 }
    	 
    	 return builder.isNotNull(root.get("id"));
        
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((pattern == null) ? 0 : pattern.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        StartsWith<?> other = (StartsWith<?>) obj;
        if (pattern == null) {
            if (other.pattern != null)
                return false;
        } else if (!pattern.equals(other.pattern))
            return false;
        return true;
    }

	@Override
	public String toString() {
		return "Like [pattern=" + pattern + "]";
	}
}