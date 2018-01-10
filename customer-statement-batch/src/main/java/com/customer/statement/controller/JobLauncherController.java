package com.customer.statement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLauncherController {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job xmlJob;
	
	@Autowired
	Job csvJob;

	@RequestMapping("/xmltocsv")
	public String handleXML() throws Exception {

		Logger logger = LoggerFactory.getLogger(this.getClass());
		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
					.toJobParameters();
			jobLauncher.run(xmlJob, jobParameters);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return "xml statement processing is started";
	}
	
	@RequestMapping("/csvtocsv")
	public String handleCSV() throws Exception {

		Logger logger = LoggerFactory.getLogger(this.getClass());
		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
					.toJobParameters();
			jobLauncher.run(csvJob, jobParameters);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return "csv statement processing is started";
	}
}
