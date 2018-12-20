package ebookshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class VotingApplication {

//	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(VotingApplication.class);

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "voting-server");
        SpringApplication.run(VotingApplication.class, args);
    }

}