package com.habitat.bastionchecker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class ServiceSystem {

    @JsonProperty("ip")
    private String serviceIP;

    @JsonProperty("hostname")
    private String serviceHost;

    @JsonProperty("gossip_ip")
    private String gossipIP;

    @JsonProperty("gossip_port")
    private String gossipPort;

    @JsonProperty("http_gateway_ip")
    private String httpGatewayIP;

    @JsonProperty("http_gateway_port")
    private String httpGatewayPort;

    @JsonProperty("ctl_gateway_ip")
    private String ctlGatewayIP;

    @JsonProperty("ctl_gateway_port")
    private String ctlGatewayPort;
}
