package com.danieldias.smart_wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
public class SmartWalletApplication {
	static void main(String[] args) {
		SpringApplication.run(SmartWalletApplication.class, args);
	}
}
