package com.khaago.lethe.dto;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;
public class Topic implements Serializable {
    @QuerySqlField(index = true)
    private Long id;
    @QuerySqlField (index = true)
    private String name;

    public Topic() {
        // No-op.
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
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}