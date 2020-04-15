package com.khaago.lethe.dto;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

public class Group implements Serializable {
    @QuerySqlField(index = true)
    private Long id;

    @QuerySqlField(index = true)
    private String groupName;

    public Group() {
    }

    public Group(Long id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
