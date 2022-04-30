package com.vti.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ManagementTypeConvert implements AttributeConverter<Management.Type, String> {

	@Override
	public String convertToDatabaseColumn(Management.Type type) {
		if (type == null) {
			return null;
		}

		return type.getValue();
	}

	@Override
	public Management.Type convertToEntityAttribute(String sqlValue) {
		if (sqlValue == null) {
			return null;
		}

		return Management.Type.toEnum(sqlValue);
	}

}
