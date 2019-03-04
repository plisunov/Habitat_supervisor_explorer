package com.ticketmaster.bastionchecker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ServicePackage {

    @JsonProperty("origin")
    private String packageOrigin;

    @JsonProperty("name")
    private String packsgeName;

    @JsonProperty("version")
    private String packageVersion;

    @JsonProperty("release")
    private String packageRelease;
}
