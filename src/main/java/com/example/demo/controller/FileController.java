package com.example.demo.controller;

import com.example.demo.model.LogRequest;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping("/logging")
    public String logging(@RequestBody LogRequest logRequest) {
        String log = logRequest.toString() ;

        System.out.println(log);

        producerTemplate.sendBody("direct:writeLog", log);
        return "Write Log process started!";
    }
}