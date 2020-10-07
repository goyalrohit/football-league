package com.rohit.footballleagueapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("football-league")
public class FootballLeagueConfig {
	private String hostUrl;
	private String apiKey;
	private String apiCountries;
	private String apiLeagues;
	private String apiTeams;
	private String apiStandings;

	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiCountries() {
		return apiCountries;
	}

	public void setApiCountries(String apiCountries) {
		this.apiCountries = apiCountries;
	}

	public String getApiLeagues() {
		return apiLeagues;
	}

	public void setApiLeagues(String apiLeagues) {
		this.apiLeagues = apiLeagues;
	}

	public String getApiTeams() {
		return apiTeams;
	}

	public void setApiTeams(String apiTeams) {
		this.apiTeams = apiTeams;
	}

	public String getApiStandings() {
		return apiStandings;
	}

	public void setApiStandings(String apiStandings) {
		this.apiStandings = apiStandings;
	}

}
