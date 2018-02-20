package com.payment.transfer.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import com.payment.transfer.model.Account;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class TransferServiceTest extends AbstractTransferServiceTest {

	Logger logger = LoggerFactory.getLogger(TransferServiceTest.class);

	@Test
	@SuppressWarnings("unchecked")
	public void createAccountTest() throws Exception {

		String createAccountRequest = "{\"balance\": 50, \"name\": \"Customer1\"}";

		MvcResult result = mockMvc.perform(post("/transfer/v1/api/createAccount")
				.contentType(MediaType.APPLICATION_JSON).content(createAccountRequest))
				.andExpect(request().asyncStarted()).andReturn();

		result.getRequest().getAsyncContext().setTimeout(10000);
		result.getAsyncResult();

		mockMvc.perform(asyncDispatch(result)).andDo(print()).andExpect(status().isOk());

		Map<String, Object> resultMap = (Map<String, Object>) result.getAsyncResult();

		Assert.assertTrue("Results did not match", resultMap.get("result").equals("success"));

		Account account = accountRepository.findAccountByAccountName("Customer1");

		Assert.assertNotNull("Account is not created", account);

	}

	@Test
	@SuppressWarnings("unchecked")
	public void makeTransferTest() throws Exception {

		String sender = "{\"balance\": 50, \"name\": \"Sender\"}";
		String receiver = "{\"balance\": 30, \"name\": \"Receiver\"}";
		
		setUpAccounts(sender, receiver);
		
		String makeTransferRequest = "{\"amount\": 10, \"receiverAccountName\": \"Receiver\", \"senderAccountName\": \"Sender\"}";
		
		MvcResult result = mockMvc.perform(post("/transfer/v1/api/makeTransfer")
				.contentType(MediaType.APPLICATION_JSON).content(makeTransferRequest))
				.andExpect(request().asyncStarted()).andReturn();

		result.getRequest().getAsyncContext().setTimeout(10000);
		result.getAsyncResult();

		mockMvc.perform(asyncDispatch(result)).andDo(print()).andExpect(status().isOk());

		Map<String, Object> resultMap = (Map<String, Object>) result.getAsyncResult();
		
		Assert.assertTrue("Results did not match", resultMap.get("result").equals("success"));
		
		Account senderAccount = accountRepository.findAccountByAccountName("Sender");
		Account receiverAccount = accountRepository.findAccountByAccountName("Receiver");
		
		Assert.assertTrue("Last balance of receiver did not match", Double.valueOf(receiverAccount.getBalance()).equals(40.0));
		Assert.assertTrue("Last balance of sender did not match", Double.valueOf(senderAccount.getBalance()).equals(40.0));
	}

	private void setUpAccounts(String senderRequest, String receiverRequest) throws Exception {
	
		createAccount(senderRequest);
		createAccount(receiverRequest);

	}
}
