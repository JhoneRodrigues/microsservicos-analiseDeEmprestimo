package com.jhonerodrigues.analisecredito;

import com.jhonerodrigues.analisecredito.service.AnaliseCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AnalisecreditoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalisecreditoApplication.class, args);

	}
}
