package com.rohit.footballleagueapi;


import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.rohit.footballleagueapi.model.FootballLeagueStanding;
import com.rohit.footballleagueapi.service.FootballLeagueService;


@SpringBootTest
class FootballLeagueApiApplicationTests {

	@Test
	void contextLoads() {
	}

	@MockBean
	private FootballLeagueService service;

	@Test
	public void getLeaguethenReturnJson() throws Exception {
			FootballLeagueStanding model = new FootballLeagueStanding();
			Mockito.when(service.getStandings("England", "Championship", "Watford")).thenReturn(model);
			assertTrue(model != null);
		}
}
