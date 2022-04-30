package com.vti.dto;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vti.entity.Resident;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ManagementDTO extends RepresentationModel<ManagementDTO> {

	private int id;

	private String name;

	private String type;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;

	private List<ResidentDTO> residents;

	@Data
	@NoArgsConstructor
	public static class ResidentDTO extends RepresentationModel<ManagementDTO> {

		@JsonProperty("residentId")
		private int id;

		private String username;
	}
}

