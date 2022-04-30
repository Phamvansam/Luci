package com.vti.specification.resident;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.vti.entity.Resident;
import com.vti.form.resident.ResidentFilterForm;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class ResidentSpecification {

	@SuppressWarnings("deprecation")
	public static Specification<Resident> buildWhere(String search, ResidentFilterForm filterForm) {
		
		Specification<Resident> where = null;
		
		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
			CustomSpecification name = new CustomSpecification("username", search);
			where = Specification.where(name);
		}
		
		// if there is filter by min id
		if (filterForm != null && filterForm.getMinId() != null) {
			CustomSpecification minId = new CustomSpecification("minId", filterForm.getMinId());
			if (where == null) {
				where = minId;
			} else {
				where = where.and(minId);
			}
		}
		
		// if there is filter by max id
		if (filterForm != null && filterForm.getMaxId() != null) {
			CustomSpecification maxId = new CustomSpecification("maxId", filterForm.getMaxId());
			if (where == null) {
				where = maxId;
			} else {
				where = where.and(maxId);
			}
		}

		return where;
	}
}

@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecification implements Specification<Resident> {

	@NonNull
	private String field;
	@NonNull
	private Object value;

	@Override
	public Predicate toPredicate(
			Root<Resident> root, 
			CriteriaQuery<?> query, 
			CriteriaBuilder criteriaBuilder) {

		if (field.equalsIgnoreCase("username")) {
			return criteriaBuilder.like(root.get("username"), "%" + value.toString() + "%");
		}
		
		if (field.equalsIgnoreCase("minId")) {
			return criteriaBuilder.greaterThanOrEqualTo(root.get("id"), value.toString());
		}
		
		if (field.equalsIgnoreCase("maxId")) {
			return criteriaBuilder.lessThanOrEqualTo(root.get("id"), value.toString());
		}

		return null;
	}
}

