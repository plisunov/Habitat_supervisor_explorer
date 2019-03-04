package com.ticketmaster.bastionchecker.web;

import com.ticketmaster.bastionchecker.model.view.ServiceInfo;
import com.ticketmaster.bastionchecker.services.AWSBastionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("#{'${my.list.of.strings}'.split(',')}")
    private List<String> myList;

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
    public List<ServiceInfo> getServicesInfo(@RequestParam(name = "type", required = false) String sortBy) {
        List<ServiceInfo> servicesInfo = awsBastionService.getInfo();
        if (sortBy != null) {

            switch (sortBy) {
                case "name":
                    return servicesInfo.stream().sorted(Comparator.comparing(ServiceInfo::getServiceName)).collect(Collectors.toList());
                case "group":
                    return servicesInfo.stream().sorted(Comparator.comparing(ServiceInfo::getServiceGroup)).collect(Collectors.toList());
                case "ip":
                    return servicesInfo.stream().sorted((s1, s2) -> s1.getSystem().getServiceIP().compareTo(s2.getSystem().getServiceIP())).collect(Collectors.toList());
                case "host":
                    return servicesInfo.stream().sorted((s1, s2) -> s1.getSystem().getServiceHost().compareTo(s2.getSystem().getServiceHost())).collect(Collectors.toList());
            }
        }
        return servicesInfo;
    }

}
