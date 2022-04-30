package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.vti.entity.Resident;
import com.vti.form.resident.CreatingResidentForm;
import com.vti.form.resident.ResidentFilterForm;


public interface IResidentService {

	public Page<Resident> getAllResidents(Pageable pageable, String search, ResidentFilterForm filterForm);

	public Resident getResidentByID(int id);

	public void createResident(CreatingResidentForm form);

	public boolean isResidentExistsByUsername(String username);

}
