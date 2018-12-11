package ebookshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class WebServer {

    public static final String BOOKS_SERVICE_URL = "http://BOOKS-SERVICE";

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "web-server");
        SpringApplication.run(WebServer.class, args);
    }

}