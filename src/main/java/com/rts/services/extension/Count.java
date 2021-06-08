
package com.rts.services.extension;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.kaczmarzyk.spring.data.jpa.domain.PathSpecification;
import net.kaczmarzyk.spring.data.jpa.domain.WithoutTypeConversion;
import net.kaczmarzyk.spring.data.jpa.utils.QueryContext;

/**
 * Filters with {@code path like %pattern%} where-clause.
 * 
 * @author Tomasz Kaczmarzyk
 */
public class Count<T> extends PathSpecification<T> implements WithoutTypeConversion {

	private static final long serialVersionUID = 1L;
	
	private String groupBy = "";

    public Count(QueryContext queryContext, String path, String... args) {
    	
        super(queryContext, path);
        groupBy = args[0];
        
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    
    	
    return null;
    }

  
}