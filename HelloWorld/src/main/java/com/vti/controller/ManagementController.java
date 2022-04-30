package com.vti.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.ManagementDTO;
import com.vti.entity.Management;
import com.vti.form.management.CreatingManagementForm;
import com.vti.form.management.ManagementFilterForm;
import com.vti.form.management.UpdatingManagementForm;
import com.vti.service.IManagementService;
import com.vti.validation.management.ManagementIDExists;

@RestController
@RequestMapping(value = "api/v1/managements")
@Validated
public class ManagementController {

	@Autowired
	private IManagementService service;

	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public Page<ManagementDTO> getAllManagements(Pageable pageable,
			@RequestParam(name = "search", required = false) String search, ManagementFilterForm filterForm) {
		
		Page<Management> entityPages = service.getAllManagements(pageable, search,filterForm);
		
		//	convert entity to dtos
		List<ManagementDTO> dtos = modelMapper.map(entityPages.getContent(), new TypeToken<List<ManagementDTO>>() {}.getType());
		
		// add HATEOAS
				for (ManagementDTO dto : dtos) {
					for (ManagementDTO.ResidentDTO residentDTO : dto.getResidents()) {
						residentDTO.add(linkTo(methodOn(ResidentController.class).getResidentByID(residentDTO.getId())).withSelfRel());
					}
					dto.add(linkTo(methodOn(ManagementController.class).getManagementByID(dto.getId())).withSelfRel());
				}
		
		
		Page<ManagementDTO> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());

		return dtoPages;
		
	}
	
	@GetMapping(value = "/{id}")
	public ManagementDTO getManagementByID(@PathVariable(name = "id") @ManagementIDExists int id) {
		Management entity = service.getManagementByID(id);

		// convert entity to dto
		ManagementDTO dto = modelMapper.map(entity, ManagementDTO.class);
		
		dto.add(linkTo(methodOn(ManagementController.class).getManagementByID(id)).withSelfRel());

		return dto;
	}

	@PostMapping()
	public void createManagement(@RequestBody @Valid CreatingManagementForm form) {
		service.createManagement(form);
	}
	
	@PutMapping(value = "/{id}")
	public void updateManagement(
			@ManagementIDExists
			@PathVariable(name = "id") int id, 
			@RequestBody UpdatingManagementForm form) {
		form.setId(id);
		service.updateManagement(form);
	}
}


