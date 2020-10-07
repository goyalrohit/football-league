package com.rohit.footballleagueapi.service;

import com.rohit.footballleagueapi.model.FootballLeagueStanding;

public interface FootballLeagueService {
	public FootballLeagueStanding getStandings(String countryName, String leagueName, String teamName);
}
