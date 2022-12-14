package ru.clevertec.settingsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


@SpringBootApplication
@EnableConfigServer
public class SettingsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SettingsApiApplication.class, args);
    }

}
