package com.rohit.footballleagueapi.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.rohit.footballleagueapi.config.FootballLeagueConfig;
import com.rohit.footballleagueapi.model.Country;
import com.rohit.footballleagueapi.model.League;
import com.rohit.footballleagueapi.model.Standings;

@Service
public class FootballLeagueApiService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private FootballLeagueConfig footballConfig;

	public List<Country> getAllCountries() {
		String url = new StringBuilder().append(footballConfig.getHostUrl()).toString();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("action", footballConfig.getApiCountries()).queryParam("APIkey", footballConfig.getApiKey());

		ResponseEntity<List<Country>> countries = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Country>>() {
				});

		return countries.getBody();
	}

	public List<League> getLeageForCountry(String country) {
		String url = new StringBuilder().append(footballConfig.getHostUrl()).toString();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("action", footballConfig.getApiLeagues()).queryParam("country_id", country)
				.queryParam("APIkey", footballConfig.getApiKey());

		ResponseEntity<List<League>> countries = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<League>>() {
				});

		return countries.getBody();
	}

	public List<Standings> getStandingForCountry(String league) {
		String url = new StringBuilder().append(footballConfig.getHostUrl()).toString();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("action", footballConfig.getApiStandings()).queryParam("league_id", league)
				.queryParam("APIkey", footballConfig.getApiKey());

		ResponseEntity<List<Standings>> countries = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Standings>>() {
				});

		return countries.getBody();
	}
}
