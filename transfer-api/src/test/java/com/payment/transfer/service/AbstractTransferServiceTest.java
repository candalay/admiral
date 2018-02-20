package com.payment.transfer.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.payment.transfer.controller.TransferServiceController;
import com.payment.transfer.repository.AccountRepository;

public abstract class AbstractTransferServiceTest {
	
	@Autowired
	protected WebApplicationContext webApplicationContext;
	
	@Autowired
	AccountRepository accountRepository;

	protected MockMvc mockMvc;

	@InjectMocks
	protected TransferServiceController transferServiceController;

	Logger logger = LoggerFactory.getLogger(AbstractTransferServiceTest.class);

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	public void createAccount(String createAccountRequest) throws Exception {
		MvcResult result = mockMvc.perform(post("/transfer/v1/api/createAccount")
				.contentType(MediaType.APPLICATION_JSON).content(createAccountRequest))
				.andExpect(request().asyncStarted()).andReturn();

		result.getRequest().getAsyncContext().setTimeout(10000);
		result.getAsyncResult();

		mockMvc.perform(asyncDispatch(result)).andDo(print()).andExpect(status().isOk());
	}

}
