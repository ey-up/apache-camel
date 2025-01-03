package com.example.demo.route;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@CamelSpringBootTest
@SpringBootTest
@MockEndpoints
class LogRouteTest {

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private ProducerTemplate producerTemplate;

    @Test
    void testFileMoveRoute() throws Exception {
        // Test edilecek veri
        String testMessage = "{\"message\": \"Test log entry\"}";

        // Dosya oluşturulacağı yolu kontrol et
        Path outputPath = Path.of("./output.txt");
        Files.deleteIfExists(outputPath); // Önceden var olan dosyayı sil

        // Rotayı tetikle
        producerTemplate.sendBody("direct:fileMoveRoute", testMessage);

        // Dosyanın oluşturulup oluşturulmadığını kontrol et
        assertTrue(Files.exists(outputPath), "Output file should exist");

        // Dosya içeriğini kontrol et
        String fileContent = Files.readString(outputPath);
        assertTrue(fileContent.contains(testMessage), "File should contain the test message");
    }

    @Test
    void testRestPostRoute() throws Exception {
        // Test edilecek veri
        String testMessage = "{\"message\": \"Rest log entry\"}";

        // Rotayı tetikle
        String response = producerTemplate.requestBody("direct:post:/log", testMessage, String.class);

        // Yanıtı kontrol et
        assertTrue(response.contains("Message logged to console."), "Response should indicate success");
    }
}
