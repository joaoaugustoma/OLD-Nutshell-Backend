package br.ueg.nutshell.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;


@SpringBootApplication
@EntityScan(basePackageClasses = { Jsr310JpaConverters.class }, basePackages = "br.ueg.nutshell.*")
public class NutshellApplication {

    public static void main(String[] args) {
        SpringApplication.run(NutshellApplication.class, args);
    }

}
