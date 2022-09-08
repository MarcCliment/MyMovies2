package com.example.gfttraining;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.gfttraining.Service.MovieDBService;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters=false)
class MyMovies2ApplicationTests {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	MovieDBService movieService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void getConfiguration() throws Exception {
		HashMap<String, Object> resultFromGetConfig = new HashMap<>();
		resultFromGetConfig.put("images", 0);
		resultFromGetConfig.put("change_keys", 0);
		given(movieService.getConfig()).willReturn(resultFromGetConfig).toString();
		ResultActions response = mockMvc.perform(get("/api/configuration"));
			response.andExpect(status().isOk())
			.andExpect(jsonPath("$.images",is(0)))
			.andExpect(jsonPath("$.change_keys",is(0)))
			.andReturn();
	}
	
	@Test
	void getAllGenres()throws Exception  {
		
		HashMap<String,Object> resultFromGetConfig = new HashMap<>();
		resultFromGetConfig.put("genres",0);
		
		given(movieService.getAllGenres()).willReturn(resultFromGetConfig);
		ResultActions response = mockMvc.perform(get("/api/genre/movie/list"));
		
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.genres",is(0)));
		
	}
	
}
