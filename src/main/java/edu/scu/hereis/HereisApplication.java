package edu.scu.hereis;

import edu.scu.hereis.configuration.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Configuration.class)
public class HereisApplication {

    public static void main(String[] args) {
        SpringApplication.run(HereisApplication.class, args);
    }
}
