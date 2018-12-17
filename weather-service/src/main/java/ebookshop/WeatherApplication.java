package ebookshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class WeatherApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherApplication.class);

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "weather-service");
        SpringApplication.run(WeatherApplication.class, args);
    }

}