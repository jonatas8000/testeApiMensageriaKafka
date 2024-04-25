package com.rest.testeCadastral.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.testeCadastral.entity.DeviceEntity;

@Repository
public interface DeviceRespository extends JpaRepository<DeviceEntity,Integer>{

}
