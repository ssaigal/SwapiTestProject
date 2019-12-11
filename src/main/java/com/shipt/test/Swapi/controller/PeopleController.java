package com.shipt.test.Swapi.controller;

import com.shipt.test.Swapi.service.SwapiServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class PeopleController {

    @Autowired
    private SwapiServiceClient swapiServiceClient;

    @GetMapping("/people")
    public String getpeopleList() throws IOException {
        return swapiServiceClient.getPeopleDetails();
    }


}
