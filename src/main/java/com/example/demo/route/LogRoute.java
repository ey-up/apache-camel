package com.example.demo.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;


@Component
public class LogRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);
        from("direct:writeLog")
                .log("Processing file: ${body}")
                .to("file:./?fileName=output.txt&fileExist=Append");

        rest("/log")
                .post()
                .route()
                .to("direct:writeLog")
                .log("GET request received!")
                .setBody(constant("Message logged to console."));

    }
}