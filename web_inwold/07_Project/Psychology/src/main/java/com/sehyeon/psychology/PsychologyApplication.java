package com.sehyeon.psychology;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = {"com.sehyeon.psychology"})
@EntityScan("com.sehyeon.psychology.entity")
@ComponentScan("com.sehyeon.psychology")
@EnableJpaRepositories("com.sehyeon.psychology.repository")
public class PsychologyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsychologyApplication.class, args);
	}

}
