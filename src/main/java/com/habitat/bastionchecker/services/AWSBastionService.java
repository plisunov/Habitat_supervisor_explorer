package com.habitat.bastionchecker.services;

import com.habitat.bastionchecker.model.view.ServiceInfo;

import java.util.List;

public interface AWSBastionService {

    List<ServiceInfo> getInfo() throws Exception;
}
