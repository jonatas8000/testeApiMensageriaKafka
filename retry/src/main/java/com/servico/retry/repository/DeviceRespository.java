package com.servico.retry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servico.retry.entity.DeviceEntity;

@Repository
public interface DeviceRespository extends JpaRepository<DeviceEntity,Integer>{

}
