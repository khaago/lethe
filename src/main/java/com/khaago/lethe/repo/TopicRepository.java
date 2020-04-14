package com.khaago.lethe.repo;

import com.khaago.lethe.dto.Topic;
import org.apache.ignite.springdata20.repository.IgniteRepository;
import org.apache.ignite.springdata20.repository.config.Query;
import org.apache.ignite.springdata20.repository.config.RepositoryConfig;
import org.springframework.data.domain.Pageable;

import javax.cache.Cache;
import java.util.List;

// TODO Replace reference Person impl
@RepositoryConfig(cacheName = "TopicCache")
public interface TopicRepository extends IgniteRepository<Topic, Long> {
    /**
     * Gets all the persons with the given name.
     * @param name Person name.
     * @return A list of Persons with the given first name.
     */
    public List<Topic> findByFirstName(String name);

    /**
     * Returns top Person with the specified surname.
     * @param name Person surname.
     * @return Person that satisfy the query.
     */
    public Cache.Entry<Long, Topic> findTopByLastNameLike(String name);

    /**
     * Getting ids of all the Person satisfying the custom query from {@link Query} annotation.
     *
     * @param orgId Query parameter.
     * @param pageable Pageable interface.
     * @return A list of Persons' ids.
     */
    @Query("SELECT id FROM Person WHERE orgId > ?")
    public List<Long> selectId(long orgId, Pageable pageable);
}