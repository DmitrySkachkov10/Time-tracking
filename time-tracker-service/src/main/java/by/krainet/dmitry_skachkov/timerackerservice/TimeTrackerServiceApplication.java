package by.krainet.dmitry_skachkov.timerackerservice;

import by.krainet.dmitry_skachkov.timerackerservice.conf.prop.JWTProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JWTProperties.class)
public class TimeTrackerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeTrackerServiceApplication.class, args);
    }

}
