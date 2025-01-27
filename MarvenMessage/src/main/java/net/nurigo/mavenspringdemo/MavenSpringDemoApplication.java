package net.nurigo.mavenspringdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class MavenSpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MavenSpringDemoApplication.class, args);
    }

}
