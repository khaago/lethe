package com.khaago.lethe.repo;

import com.khaago.lethe.dto.Topic;
import org.apache.ignite.springdata20.repository.IgniteRepository;
import org.apache.ignite.springdata20.repository.config.RepositoryConfig;

import javax.cache.Cache;
import java.util.List;

@RepositoryConfig(cacheName = "TopicCache")
public interface TopicRepository extends IgniteRepository<Topic, Long> {
    public List<Topic> findByName(String name);

    /**
     * Returns top Top with the specified name.
     */
    public Cache.Entry<Long, Topic> findTopByNameLike(String name);

//    /**
//     * Getting ids of all the Person satisfying the custom query from {@link Query} annotation.
//     *
//     * @param orgId Query parameter.
//     * @param pageable Pageable interface.
//     * @return A list of Persons' ids.
//     */
//    @Query("SELECT id FROM Person WHERE orgId > ?")
//    public List<Long> selectId(long orgId, Pageable pageable);
}