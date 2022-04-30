package com.vti.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.entity.Management;
import com.vti.entity.Resident;
import com.vti.form.resident.CreatingResidentForm;
import com.vti.form.resident.ResidentFilterForm;
import com.vti.repository.IResidentRepository;
import com.vti.specification.management.ManagementSpecification;
import com.vti.specification.resident.ResidentSpecification;


@Service
public class ResidentService implements IResidentService {

	@Autowired
	private IResidentRepository repository;

	@Autowired
	private ModelMapper modelMapper;
	
	public Page<Resident> getAllResidents(Pageable pageable, String search, ResidentFilterForm filterForm) {
		Specification<Resident> where = ResidentSpecification.buildWhere(search, filterForm);
		
		return repository.findAll(where, pageable);
	}

	@Override
	public Resident getResidentByID(int id) {
		return repository.findById(id).get();
	}


	public void createResident(CreatingResidentForm form) {
		
		// omit id field
		TypeMap<CreatingResidentForm, Resident> typeMap = modelMapper.getTypeMap(CreatingResidentForm.class, Resident.class);
		if (typeMap == null) { // if not already added
			// skip field
			modelMapper.addMappings(new PropertyMap<CreatingResidentForm, Resident>() {
				@Override
				protected void configure() {
					skip(destination.getId());
				}
			});
		}

		// convert form to entity
		Resident resident = modelMapper.map(form, Resident.class);
		
		repository.save(resident);
	}

	@Override
	public boolean isResidentExistsByUsername(String username) {
		return repository.existsByUsername(username);
	}
}


