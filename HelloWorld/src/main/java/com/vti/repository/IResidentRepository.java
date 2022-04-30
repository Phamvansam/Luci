package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vti.entity.Management;
import com.vti.entity.Resident;

public interface IResidentRepository extends JpaRepository<Resident, Integer>,JpaSpecificationExecutor<Resident> {

	boolean existsByUsername(String username);
}
