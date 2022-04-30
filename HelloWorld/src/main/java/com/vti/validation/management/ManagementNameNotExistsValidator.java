package com.vti.validation.management;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vti.service.IManagementService;

public class ManagementNameNotExistsValidator implements ConstraintValidator<ManagementNameNotExists, String> {

	@Autowired
	private IManagementService service;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {

		if (StringUtils.isEmpty(name)) {
			return true;
		}

		return !service.isManagementExistsByName(name);
	}
}