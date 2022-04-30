package com.vti.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entity.Management;
import com.vti.entity.Resident;
import com.vti.form.management.CreatingManagementForm;
import com.vti.form.management.ManagementFilterForm;
import com.vti.form.management.UpdatingManagementForm;
import com.vti.repository.IResidentRepository;
import com.vti.specification.management.ManagementSpecification;
import com.vti.repository.IManagementRepository;


@Service
public class ManagementService implements IManagementService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IManagementRepository repository;
	
	@Autowired
	private IResidentRepository residentRepository;

	public Page<Management> getAllManagements(Pageable pageable, String search,ManagementFilterForm filterForm) {
		
		Specification<Management> where = ManagementSpecification.buildWhere(search,filterForm);
		return repository.findAll(where, pageable);
	}

	
	public Management getManagementByID(int id) {
		return repository.findById(id).get();
	}
	
	@Transactional
	public void createManagement(CreatingManagementForm form) {
		
		// convert form to entity
		Management managementEntity = modelMapper.map(form, Management.class);
		
		// create management
		Management management = repository.save(managementEntity);
		
		// create residents
		List<Resident> residentEntities = managementEntity.getResidents();
		for (Resident resident : residentEntities) {
			resident.setManagement(management);
		}
		residentRepository.saveAll(residentEntities);
	}
	
	public void updateManagement(UpdatingManagementForm form) {
		
		// convert form to entity
		Management management = modelMapper.map(form, Management.class);
		
		repository.save(management);
	}


	@Override
	public boolean isManagementExistsByName(String name) {
		return repository.existsByName(name);
	}


	@Override
	public boolean isManagementExistsByID(Integer id) {
		return repository.existsById(id);
	}
	


}
