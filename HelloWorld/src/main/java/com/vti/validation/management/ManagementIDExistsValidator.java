package com.vti.validation.management;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;


import com.vti.service.IManagementService;

public class ManagementIDExistsValidator implements ConstraintValidator<ManagementIDExists, Integer> {

	@Autowired
	private IManagementService service;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {

		if (StringUtils.isEmpty(id)) {
			return true;
		}

		return service.isManagementExistsByID(id);
	}
}

