package com.vaitkevicius.main;

import com.vaitkevicius.main.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@SpringBootApplication(scanBasePackages = "com.vaitkevicius.main")
public class MainApplication extends AbstractSecurityWebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
	public MainApplication() {
		super(SecurityConfig.class);
	}
}
