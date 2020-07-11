package com.khaago.lethe.dto;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ClientDto implements Serializable {
    @QuerySqlField(index = true)
    private Long id;

    @QuerySqlField
    private String name;
    private String address;
    private Map<String, String> properties;

    private List<TopicDto> contributions;
    private List<TopicDto> subscriptions;

    public ClientDto() {
        // No-op.
    }

    public ClientDto(Long id, String address) {
        this.id = id;
        this.address = address;
    }

    public ClientDto(Long id, String name, String address, Map<String, String> properties) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.properties = properties;
    }

    public ClientDto(String name, String address, Map<String, String> properties) {
        this.name = name;
        this.address = address;
        this.properties = properties;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", properties=" + properties +
                '}';
    }
}