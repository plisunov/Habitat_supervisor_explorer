package com.habitat.bastionchecker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@JsonIgnoreProperties
@Data
public class BastionCensusGroup {

    @JsonProperty("service_group")
    private Object serviceGroup;

    @JsonProperty("election_status")
    private Object electionStatus;

    @JsonProperty("update_election_status")
    private Object updateElectionStatus;

    @JsonProperty("leader_id")
    private Object leaderId;

    @JsonProperty("service_config")
    private ServiceConfig serviceConfig;

    @JsonProperty("local_member_id")
    private Object localMemberId;

    @JsonProperty("population")
    private Map<String, ServicePopulation> population;

    @JsonProperty("update_leader_id")
    private Object updateLeaderId;

    @JsonProperty("changed_service_files")
    private Object changedServiceFiles;

    @JsonProperty("service_files")
    private Object serviceFiles;

}
