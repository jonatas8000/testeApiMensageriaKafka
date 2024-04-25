package com.servico.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servico.principal.entity.DeviceEntity;

@Repository
public interface DeviceRespository extends JpaRepository<DeviceEntity,Integer>{

}
