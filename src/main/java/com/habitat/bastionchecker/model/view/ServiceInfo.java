package com.habitat.bastionchecker.model.view;

import com.habitat.bastionchecker.model.ServicePackage;
import com.habitat.bastionchecker.model.ServiceSystem;
import lombok.Data;

import java.util.Map;

@Data
public class ServiceInfo {

    private String memberId;

    private ServicePackage pkg;

    private String serviceName;

    private String packageString;

    private Object application;

    private Object environment;

    private String serviceGroup;

    private Boolean persistent;

    private Boolean leader;

    private Boolean follower;

    private Boolean updateleader;

    private Boolean updateFollower;

    private Boolean electionIsRunning;

    private Boolean electionIsNoQuorum;

    private Boolean electionIsFinished;

    private Boolean updateElectionIsNoQuorum;

    private Boolean updateElectionIsFinished;

    private Boolean updateElectionIsRunning;

    private ServiceSystem system;

    private Boolean alive;

    private Boolean suspect;

    private Boolean confirmed;

    private Boolean departed;

    private Map<String, String> configuration;

    private Map<String, Object> properties;
}
