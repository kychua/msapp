package ebookshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import ebookshop.service.WebBookService;
import ebookshop.web.WebBookController;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters=false)  // want to create controller myself
public class WebServer {

    public static final String BOOKS_SERVICE_URL = "http://BOOKS-SERVICE";

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "web-server");
        SpringApplication.run(WebServer.class, args);
    }

    @LoadBalanced    // Make sure to create the load-balanced template
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebBookService booksService() {
        return new WebBookService(BOOKS_SERVICE_URL);
    }

    @Bean
    public WebBookController booksController() {
         return new WebBookController(booksService());
    }
}