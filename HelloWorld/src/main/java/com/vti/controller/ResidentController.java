package com.vti.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.ManagementDTO;
import com.vti.dto.ResidentDTO;
import com.vti.entity.Management;
import com.vti.entity.Resident;
import com.vti.form.resident.CreatingResidentForm;
import com.vti.form.resident.ResidentFilterForm;
import com.vti.service.IResidentService;

@RestController
@RequestMapping(value = "api/v1/residents")
public class ResidentController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IResidentService service;

	@GetMapping()
	public Page<ResidentDTO> getAllResidents(Pageable pageable,
			@RequestParam(name = "search", required = false) String search, ResidentFilterForm filterForm) {

		Page<Resident> entityPages = service.getAllResidents(pageable, search,filterForm);
		
			
		//	convert entity to dtos
			List<ResidentDTO> dtos = modelMapper.map(entityPages.getContent(), new TypeToken<List<ResidentDTO>>() {}.getType());
			
			Page<ResidentDTO> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());

			return dtoPages;
	}
	
	@GetMapping(value = "/{id}")
	public ResidentDTO getResidentByID(@PathVariable(name = "id") int id) {
		Resident entity = service.getResidentByID(id);

		// convert entity to dto
		ResidentDTO dto = modelMapper.map(entity, ResidentDTO.class);
		
		dto.add(linkTo(methodOn(ResidentController.class).getResidentByID(id)).withSelfRel());

		return dto;
	}

	@PostMapping()
	public void createResident(@RequestBody CreatingResidentForm form) {
		service.createResident(form);
	}
}

