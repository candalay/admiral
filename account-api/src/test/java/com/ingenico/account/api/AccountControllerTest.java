package com.ingenico.account.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import javax.inject.Inject;

import org.junit.Assert;
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

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.ingenico.account.engine.AccountEngine;
import com.ingenico.account.repository.AccountRepository;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class AccountControllerTest {

	@Autowired
	WebApplicationContext webApplicationContext;
	
	MockMvc mockMvc;

	@InjectMocks
	private AccountController accountController;

	@SpyBean
	private AccountEngine accountEngine;
	
	@Inject
	AccountRepository accountRepository;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void createAccountTest() throws Exception {

		String object = "{\"accountNumber\":\"NLABNA0001\",\"balance\":42.0}";
		MvcResult result = mockMvc.perform(post("/account").contentType(MediaType.APPLICATION_JSON).content(object))
				.andExpect(status().isCreated()).andReturn();
		String resultInJson = result.getResponse().getContentAsString();
		Gson gson = new Gson();
		LinkedTreeMap<String, String> createdAccountId = (LinkedTreeMap<String, String>) gson.fromJson(resultInJson, Object.class);
		Assert.assertNotNull(accountRepository.findAccountByAccountInstanceId(UUID.fromString(createdAccountId.get("id"))));
	}
	
	@Test
	public void createAccountWhenAccountNumberIsNullTest() throws Exception {

		String object = "{\"balance\":42.0}";
		MvcResult result = mockMvc.perform(post("/account").contentType(MediaType.APPLICATION_JSON).content(object))
				.andExpect(status().isBadRequest()).andReturn();

	}
	
	@Test
	public void createAccountWhenDuplicateAccountCreation() throws Exception {

		String account1 = "{\"accountNumber\":\"NLABNA0001\",\"balance\":42.0}";
		mockMvc.perform(post("/account").contentType(MediaType.APPLICATION_JSON).content(account1))
				.andExpect(status().isCreated()).andReturn();
		

		String account2 = "{\"accountNumber\":\"NLABNA0001\",\"balance\":42.0}";
		mockMvc.perform(post("/account").contentType(MediaType.APPLICATION_JSON).content(account2))
				.andExpect(status().isConflict()).andReturn();

	}

}
