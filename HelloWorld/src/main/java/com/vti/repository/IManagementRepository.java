package com.vti.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vti.entity.Management;
import com.vti.form.management.ManagementFilterForm;

public interface IManagementRepository extends JpaRepository<Management, Integer>, JpaSpecificationExecutor<Management>{

	boolean existsByName(String name);

	
}
