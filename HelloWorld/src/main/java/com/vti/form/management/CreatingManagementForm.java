package com.vti.form.management;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.vti.entity.Resident;
import com.vti.validation.management.ManagementNameNotExists;
import com.vti.validation.resident.ResidentUsernameNotExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingManagementForm {
	
	@NotBlank(message = "{Management.createManagement.form.name.NotBlank}")
	@Length(max = 50, message = "{Management.createManagement.form.name.Length}")
	@ManagementNameNotExists
	private String name;

	@NotNull(message = "The Total Member mustn't be null value")
	@PositiveOrZero(message = "The Total Member must be greater than or equal 0")
	private int totalMember;

	@Pattern(regexp = "TECHNOLOGY|ADMINISTRATIVE|ACCOUNTING", message = "The type must be TECHNOLOGY, ADMINISTRATIVE or ACCOUNTING")
	private String type;
	
	private List<@Valid Resident> residents;

	@Data
	@NoArgsConstructor
	public static class Resident {
		
		@NotBlank(message = "The name mustn't be null value")
		@Length(max = 50, message = "The name's length is max 50 characters")
		@ResidentUsernameNotExists
		private String username;
	}
}
