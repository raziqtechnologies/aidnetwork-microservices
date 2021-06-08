
package com.rts.services.extension;

import java.util.Arrays;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
public class StartsWith<T> extends PathSpecification<T> implements WithoutTypeConversion {

	private static final long serialVersionUID = 1L;
	
	protected String pattern;

    public StartsWith(QueryContext queryContext, String path, String... args) {
        super(queryContext, path);
        if (args == null || args.length != 1) {
            throw new IllegalArgumentException("Expected exactly one argument (the fragment to match against), but got: " + Arrays.toString(args));
        } else {
            this.pattern = args[0] + "%";
        }
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.like(this.<String>path(root), pattern);
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