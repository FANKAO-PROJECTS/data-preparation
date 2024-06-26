package org.data.preparation.controller;

import java.util.List;
import java.util.Map;

import org.data.preparation.service.TestDataPreparationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testdata")
public class DataPreparationController {

    @Autowired
    private TestDataPreparationService testDataPreparationService;

    @PostMapping("/prepare")
    public List<String> prepareTestData(@RequestBody Map<String, String> request) {
        String query = request.get("query");
        String url = request.get("url");
        String username = request.get("username");
        String password = request.get("password");
        String driverClassName = request.get("driverClassName");
        return testDataPreparationService.prepareTestData(query, url, username, password, driverClassName);
    }
}
