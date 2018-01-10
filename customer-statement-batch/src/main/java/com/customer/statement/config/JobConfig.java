package com.customer.statement.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.customer.statement.model.CustomerStatement;
import com.customer.statement.model.InValidCustomerStatementRecord;

public class JobConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job csvJob(Step step) {
		return jobBuilderFactory.get("csvJob").start(step).build();
	}
	
	@Bean
	public Job xmljob(Step step) {
		return jobBuilderFactory.get("xmlJob").start(step).build();
	}

	@Bean
	public Step step(StaxEventItemReader<CustomerStatement> staxEventItemReader,
			ItemWriter<InValidCustomerStatementRecord> itemWriter) {

		return stepBuilderFactory.get("xmlStep").<CustomerStatement, InValidCustomerStatementRecord>chunk(10)
				.reader(staxEventItemReader)
				.writer(itemWriter)
				.build();
	}
	
	@Bean
	public Step csvStep(FlatFileItemReader<CustomerStatement> flatItemReader,
			ItemWriter<InValidCustomerStatementRecord> itemWriter) {

		return stepBuilderFactory.get("csvStep").<CustomerStatement, InValidCustomerStatementRecord>chunk(10)
				.reader(flatItemReader)
				.writer(itemWriter)
				.build();
	}

}
