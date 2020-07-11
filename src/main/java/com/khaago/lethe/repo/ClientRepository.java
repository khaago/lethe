package com.khaago.lethe.repo;

import com.khaago.lethe.dto.ClientDto;
import org.apache.ignite.springdata20.repository.IgniteRepository;
import org.apache.ignite.springdata20.repository.config.RepositoryConfig;

import java.util.List;

@RepositoryConfig(cacheName = "ClientCache")
public interface ClientRepository extends IgniteRepository<ClientDto, Long> {
    List<ClientDto> findByName(String name);
}