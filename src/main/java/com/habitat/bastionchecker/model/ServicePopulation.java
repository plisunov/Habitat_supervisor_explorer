package com.habitat.bastionchecker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ServicePopulation {

    @JsonProperty("member_id")
    private String memberId;

    @JsonProperty("pkg")
    private ServicePackage pkg;

    @JsonProperty("service")
    private String serviceName;

    @JsonProperty("package")
    private String packageString;

    @JsonProperty("application")
    private Object application;

    @JsonProperty("environment")
    private Object environment;


    @JsonProperty("group")
    private String serviceGroup;

    @JsonProperty("persistent")
    private Boolean persistent;

    @JsonProperty("leader")
    private Boolean leader;

    @JsonProperty("follower")
    private Boolean follower;

    @JsonProperty("update_leader")
    private Boolean updateleader;

    @JsonProperty("update_follower")
    private Boolean updateFollower;

    @JsonProperty("election_is_running")
    private Boolean electionIsRunning;

    @JsonProperty("election_is_no_quorum")
    private Boolean electionIsNoQuorum;

    @JsonProperty("election_is_finished")
    private Boolean electionIsFinished;

    @JsonProperty("update_election_is_no_quorum")
    private Boolean updateElectionIsNoQuorum;

    @JsonProperty("update_election_is_finished")
    private Boolean updateElectionIsFinished;

    @JsonProperty("update_election_is_running")
    private Boolean updateElectionIsRunning;

    @JsonProperty("sys")
    private ServiceSystem system;

    @JsonProperty("alive")
    private Boolean alive;

    @JsonProperty("suspect")
    private Boolean suspect;

    @JsonProperty("confirmed")
    private Boolean confirmed;

    @JsonProperty("departed")
    private Boolean departed;

    @JsonProperty("cfg")
    private Map<String, Object> configuration;

}
