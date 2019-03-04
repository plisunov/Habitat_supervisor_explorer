package com.ticketmaster.bastionchecker.services;

import com.ticketmaster.bastionchecker.model.view.ServiceInfo;

import java.util.List;

public interface AWSBastionService {

    List<ServiceInfo> getInfo();
}
