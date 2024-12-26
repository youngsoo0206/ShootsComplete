package com.Shoots.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LocationController {

    @GetMapping("/location")
    public String location(){
        return "map/location";
    }
}
