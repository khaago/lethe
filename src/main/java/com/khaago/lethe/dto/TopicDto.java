package com.khaago.lethe.dto;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TopicDto implements Serializable {
    @QuerySqlField(index = true)
    private Long id;
    @QuerySqlField(index = true)
    private String name;

    private String created;
    private long retention;
    private Map<String, String> properties;

    @QuerySqlField(index = true)
    private List<ClientDto> contributors = new ArrayList<>();
    @QuerySqlField(index = true)
    private List<ClientDto> subscribers = new ArrayList<>();

    public TopicDto() {
        // No-op.
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public long getRetention() {
        return retention;
    }

    public void setRetention(long retention) {
        this.retention = retention;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public List<ClientDto> getContributors() {
        return contributors;
    }

    public void setContributors(List<ClientDto> contributors) {
        this.contributors = contributors;
    }

    public List<ClientDto> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<ClientDto> subscribers) {
        this.subscribers = subscribers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TopicDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created='" + created + '\'' +
                ", retention=" + retention +
                ", properties=" + properties +
                ", contributors=" + contributors +
                ", subscribers=" + subscribers +
                '}';
    }
}
