package hu.petrusjt.thesis.dkv.schedule.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/health")
public class HealthCheckController {

    @GetMapping
    public String getHealth() {
        return "OK";
    }
}
