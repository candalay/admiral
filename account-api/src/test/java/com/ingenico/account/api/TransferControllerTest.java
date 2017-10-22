package com.ingenico.account.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ingenico.account.engine.AccountEngine;
import com.ingenico.account.engine.TransferEngine;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class TransferControllerTest {

	@Autowired
	WebApplicationContext webApplicationContext;
	
	MockMvc mockMvc;

	@InjectMocks
	private AccountController accountController;
	
	@InjectMocks
	private TransferController transferController;

	@SpyBean
	private AccountEngine accountEngine;
	
	@SpyBean
	private TransferEngine transferEngine;
	
	

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void transferMoneyTestWhenAcoountsAreNotCreated() throws Exception {

		String object = "{\"senderAccountNumber\":\"NLABNA0001\","
				          + "\"receiverAccountNumber\":\"NLABNA0002\",\"amount\":42.0}";
		MvcResult result = mockMvc.perform(post("/account/transfer").contentType(MediaType.APPLICATION_JSON).content(object))
				.andExpect(request().asyncStarted()).andReturn();
		
		result.getRequest().getAsyncContext().setTimeout(10000);
		result.getAsyncResult();

		 mockMvc
	    .perform(asyncDispatch(result))
	    .andDo(print())
	    .andExpect(status().isBadRequest());

	}
	
	@Test
	public void transferMoneySuccess() throws Exception {
		
		String account1 = "{\"accountNumber\":\"NLABNA0001\",\"balance\":42.0}";
		MvcResult resultAccount1 = mockMvc.perform(post("/account")
				.contentType(MediaType.APPLICATION_JSON).content(account1))
				.andExpect(status().isCreated()).andReturn();

		String account2 = "{\"accountNumber\":\"NLABNA0002\",\"balance\":102.0}";
		MvcResult resultAccount2 =  mockMvc.perform(post("/account").contentType(MediaType.APPLICATION_JSON).content(account2))
				.andExpect(status().isCreated()).andReturn();

		Thread.sleep(5000);
		
		String transfer = "{\"senderAccountNumber\":\"NLABNA0001\","
				          + "\"receiverAccountNumber\":\"NLABNA0002\",\"amount\":42.0}";
		MvcResult result = mockMvc.perform(post("/account/transfer").contentType(MediaType.APPLICATION_JSON).content(transfer))
				.andExpect(request().asyncStarted()).andReturn();
		
		result.getRequest().getAsyncContext().setTimeout(10000);
		result.getAsyncResult();

		 mockMvc
	    .perform(asyncDispatch(result))
	    .andDo(print())
	    .andExpect(status().isOk());
	}
	
	@Test
	public void transferMoneyInSufficientBalance() throws Exception {
		
		String account1 = "{\"accountNumber\":\"NLABNA0001\",\"balance\":42.0}";
		MvcResult resultAccount1 = mockMvc.perform(post("/account")
				.contentType(MediaType.APPLICATION_JSON).content(account1))
				.andExpect(status().isCreated()).andReturn();

		String account2 = "{\"accountNumber\":\"NLABNA0002\",\"balance\":102.0}";
		MvcResult resultAccount2 =  mockMvc.perform(post("/account").contentType(MediaType.APPLICATION_JSON).content(account2))
				.andExpect(status().isCreated()).andReturn();

		Thread.sleep(5000);
		
		String transfer = "{\"senderAccountNumber\":\"NLABNA0001\","
				          + "\"receiverAccountNumber\":\"NLABNA0002\",\"amount\":85.0}";
		MvcResult result = mockMvc.perform(post("/account/transfer").contentType(MediaType.APPLICATION_JSON).content(transfer))
				.andExpect(request().asyncStarted()).andReturn();
		
		result.getRequest().getAsyncContext().setTimeout(10000);
		result.getAsyncResult();

		 mockMvc
	    .perform(asyncDispatch(result))
	    .andDo(print())
	    .andExpect(status().isForbidden());

	}
}
	
