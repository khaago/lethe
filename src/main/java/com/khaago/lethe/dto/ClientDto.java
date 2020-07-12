package com.khaago.lethe.dto;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ClientDto implements Serializable {
    @QuerySqlField
    private String name;
    private String address;
    private Map<String, String> properties;

    private List<TopicDto> contributions;
    private List<TopicDto> subscriptions;

    public ClientDto() {
        // No-op.
    }

    public ClientDto(String address) {
        this.address = address;
    }

    public ClientDto(String name, String address, Map<String, String> properties) {
        this.name = name;
        this.address = address;
        this.properties = properties;
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

    public List<TopicDto> getContributions() {
        return contributions;
    }

    public void setContributions(List<TopicDto> contributions) {
        this.contributions = contributions;
    }

    public List<TopicDto> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<TopicDto> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", properties=" + properties +
                '}';
    }
}