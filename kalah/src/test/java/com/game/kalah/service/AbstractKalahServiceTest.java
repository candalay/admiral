package com.game.kalah.service;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.game.kalah.controller.KalahController;

public abstract class AbstractKalahServiceTest {
	
	@Autowired
	protected WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;

	@InjectMocks
	protected KalahController kalahController;
	
	Logger logger = LoggerFactory.getLogger(AbstractKalahServiceTest.class);

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		
	}

}
