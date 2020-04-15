package com.khaago.lethe.dto;

import org.apache.ignite.cache.affinity.AffinityKey;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

public class Client implements Serializable {
    @QuerySqlField(index = true)
    private Long id;

    @QuerySqlField(index = true)
    private Long groupId;

    @QuerySqlField
    private String name;

    private transient AffinityKey<Long> key;

    /**
     * Default constructor.
     */
    public Client() {
        // No-op.
    }

    public Client(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets cache affinity key. Since in some examples topic needs to be collocated with group, we create
     * custom affinity key to guarantee this collocation.
     *
     * @return Custom affinity key to guarantee that topic is always collocated with topic group.
     */
    public AffinityKey<Long> key() {
        if (key == null)
            key = new AffinityKey<>(id, groupId);

        return key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", name='" + name + '\'' +
                ", key=" + key +
                '}';
    }
}