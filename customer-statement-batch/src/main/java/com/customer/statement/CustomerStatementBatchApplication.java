package com.customer.statement;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableBatchProcessing
@ImportResource("classpath*:batchjob-context.xml")
public class CustomerStatementBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerStatementBatchApplication.class, args);
	}
}
