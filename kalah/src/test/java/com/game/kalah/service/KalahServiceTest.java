package com.game.kalah.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class KalahServiceTest extends AbstractKalahServiceTest {

	Logger logger = LoggerFactory.getLogger(KalahServiceTest.class);

	@Test
	public void initBoardTest() throws Exception {

		MvcResult result = mockMvc.perform(get("/game/v1/board").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultInJson = result.getResponse().getContentAsString();

		Assert.assertEquals("[0,6,6,6,6,6,6,0,6,6,6,6,6,6]", resultInJson);
	}

	@Test
	public void playKalahTest() throws Exception {

		mockMvc.perform(get("/game/v1/board").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();

		logger.info("board is created");

		// player north plays
		String pit = "2";

		MvcResult result = mockMvc.perform(post("/game/v1/play/" + pit).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultInJson = result.getResponse().getContentAsString();

		logger.info("result: {}", resultInJson);

		Assert.assertEquals("{\"turn\":2,\"board\":[0,6,0,7,7,7,7,1,7,6,6,6,6,6],\"status\":false}", resultInJson);
		
		// all complete game test could be added, test methods could be varied.

	}

}
