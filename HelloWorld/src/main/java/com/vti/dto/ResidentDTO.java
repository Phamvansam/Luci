package com.vti.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResidentDTO extends RepresentationModel<ResidentDTO> {

	private int id;

	private String username;

	private String managementName;
}
