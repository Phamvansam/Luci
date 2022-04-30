package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.Management;
import com.vti.form.management.CreatingManagementForm;
import com.vti.form.management.ManagementFilterForm;
import com.vti.form.management.UpdatingManagementForm;


public interface IManagementService {
	
	public Page<Management> getAllManagements(Pageable pageable, String search, ManagementFilterForm filterForm);

	public Management getManagementByID(int id);

	public void createManagement(CreatingManagementForm form);

	public void updateManagement(UpdatingManagementForm form);

	public boolean isManagementExistsByName(String name);

	public boolean isManagementExistsByID(Integer id);

}
