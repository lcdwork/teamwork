package com.teamwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class TeamworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamworkApplication.class, args);
    }

}
