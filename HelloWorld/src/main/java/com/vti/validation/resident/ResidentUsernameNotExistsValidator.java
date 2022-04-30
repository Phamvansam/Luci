package com.vti.validation.resident;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vti.service.IResidentService;

public class ResidentUsernameNotExistsValidator implements ConstraintValidator<ResidentUsernameNotExists, String> {

	@Autowired
	private IResidentService service;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {

		if (StringUtils.isEmpty(username)) {
			return true;
		}

		return !service.isResidentExistsByUsername(username);
	}
}