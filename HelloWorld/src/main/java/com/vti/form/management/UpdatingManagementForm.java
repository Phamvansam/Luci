package com.vti.form.management;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatingManagementForm {

	private int id;
	
	private String name;

	private int totalMember;

	private String type;
}
