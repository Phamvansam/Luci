package com.vti.form.management;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.vti.entity.Management;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ManagementFilterForm {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date minCreatedDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date maxCreatedDate;

	private Integer minYear;
	
	private Management.Type type;

}

