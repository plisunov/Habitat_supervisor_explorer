package com.habitat.bastionchecker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@JsonIgnoreProperties
@Data
public class ServiceConfig {

    @JsonProperty("incarnation")
    private Integer incarnation;

    @JsonProperty("value")
    private Map<String, Object> properties;
}
