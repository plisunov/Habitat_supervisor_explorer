package com.habitat.bastionchecker.services;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.habitat.bastionchecker.model.BastionCensus;
import com.habitat.bastionchecker.model.BastionCensusGroup;
import com.habitat.bastionchecker.model.ServicePopulation;
import com.habitat.bastionchecker.model.view.ServiceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AWSBastionServiceImpl implements AWSBastionService {

    private static final String SERVICE_ENDPOINT = "/census";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${bastion.endpoint.bearer}")
    private String bearer;

    @Value("${bastion.endpoint}")
    private String bastionEndpoint;

    @Override
    public List<ServiceInfo> getInfo() throws Exception {
        String url = bastionEndpoint + SERVICE_ENDPOINT;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(bearer);
        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
        BastionCensus bastionCensus = restTemplate.exchange(url, HttpMethod.GET, entity, BastionCensus.class).getBody();
        return generateServiceIfo(bastionCensus.getCensusGroup());
    }

    private List<ServiceInfo> generateServiceIfo(Map<String, BastionCensusGroup> censusGroup) {
        List<ServiceInfo> serviceInfos = Lists.newArrayList();
        List<BastionCensusGroup> services = censusGroup.values().stream().collect(Collectors.toList());
        services.forEach(service -> {
            List<ServicePopulation> populations = service.getPopulation().values().stream().distinct().collect(Collectors.toList());
            for (ServicePopulation population : populations) {
                if (population.getAlive() || populations.size() == 1) {
                    ServiceInfo serviceInfo = new ServiceInfo();
                    serviceInfo.setMemberId(population.getMemberId());
                    serviceInfo.setPkg(population.getPkg());
                    serviceInfo.setServiceName(population.getServiceName());
                    serviceInfo.setPackageString(population.getPackageString());
                    serviceInfo.setApplication(population.getApplication());
                    serviceInfo.setEnvironment(population.getEnvironment());
                    serviceInfo.setServiceGroup(population.getServiceGroup());
                    serviceInfo.setPersistent(population.getPersistent());
                    serviceInfo.setLeader(population.getLeader());
                    serviceInfo.setFollower(population.getFollower());
                    serviceInfo.setUpdateleader(population.getUpdateleader());
                    serviceInfo.setUpdateFollower(population.getUpdateFollower());
                    serviceInfo.setElectionIsRunning(population.getElectionIsRunning());
                    serviceInfo.setElectionIsNoQuorum(population.getElectionIsNoQuorum());
                    serviceInfo.setElectionIsFinished(population.getElectionIsFinished());
                    serviceInfo.setUpdateElectionIsNoQuorum(population.getUpdateElectionIsNoQuorum());
                    serviceInfo.setUpdateElectionIsFinished(population.getUpdateElectionIsFinished());
                    serviceInfo.setUpdateElectionIsRunning(population.getUpdateElectionIsRunning());
                    serviceInfo.setSystem(population.getSystem());
                    serviceInfo.setAlive(population.getAlive());
                    serviceInfo.setSuspect(population.getSuspect());
                    serviceInfo.setConfirmed(population.getConfirmed());
                    serviceInfo.setDeparted(population.getDeparted());
                    serviceInfo.setConfiguration(Maps.newHashMap());
                    Map<String, Object> populationConfiguration = population.getConfiguration();
                    for (String configurationName : populationConfiguration.keySet()) {
                        serviceInfo.getConfiguration().put(configurationName, populationConfiguration.get(configurationName).toString());
                    }
                    serviceInfos.add(serviceInfo);
                }
            }
        });
        return serviceInfos.stream().sorted(Comparator.comparing(ServiceInfo::getServiceName)).collect(Collectors.toList());
    }

}
