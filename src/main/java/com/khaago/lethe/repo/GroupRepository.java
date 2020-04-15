package com.khaago.lethe.repo;

import com.khaago.lethe.dto.Group;
import org.apache.ignite.springdata20.repository.IgniteRepository;
import org.apache.ignite.springdata20.repository.config.RepositoryConfig;

@RepositoryConfig(cacheName = "GroupCache")
public interface GroupRepository extends IgniteRepository<Group, Long> {

}
