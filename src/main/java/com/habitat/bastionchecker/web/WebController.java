package com.habitat.bastionchecker.web;

import com.habitat.bastionchecker.model.view.ServiceInfo;
import com.habitat.bastionchecker.services.AWSBastionService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WebController {

    @Value("${bastion.endpoint}")
    private String bastionEndpoint;

    @Autowired
    private AWSBastionService awsBastionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start(Model model) {
        model.addAttribute("environment", bastionEndpoint);
        return "index";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getServicesInfo(@RequestParam(name = "type", required = false) String sortBy) {
        try {
            List<ServiceInfo> servicesInfo = awsBastionService.getInfo();
            if (CollectionUtils.isEmpty(servicesInfo)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            if (sortBy != null) {
                switch (sortBy) {
                    case "name":
                        return new ResponseEntity<>(servicesInfo.stream().sorted(Comparator.comparing(ServiceInfo::getServiceName)).collect(Collectors.toList()), HttpStatus.OK);
                    case "group":
                        return new ResponseEntity<>(servicesInfo.stream().sorted(Comparator.comparing(ServiceInfo::getServiceGroup)).collect(Collectors.toList()), HttpStatus.OK);
                    case "ip":
                        return new ResponseEntity<>(servicesInfo.stream().sorted((s1, s2) -> s1.getSystem().getServiceIP().compareTo(s2.getSystem().getServiceIP())).collect(Collectors.toList()), HttpStatus.OK);
                    case "host":
                        return new ResponseEntity<>(servicesInfo.stream().sorted((s1, s2) -> s1.getSystem().getServiceHost().compareTo(s2.getSystem().getServiceHost())).collect(Collectors.toList()), HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(servicesInfo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
