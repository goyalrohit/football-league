package com.rohit.footballleagueapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.rohit.footballleagueapi.data.FootballLeagueApiService;
import com.rohit.footballleagueapi.exception.BadRequestException;
import com.rohit.footballleagueapi.model.Country;
import com.rohit.footballleagueapi.model.FootballLeagueStanding;
import com.rohit.footballleagueapi.model.League;
import com.rohit.footballleagueapi.model.Standings;

@Service
public class FootballLeagueServiceImpl implements FootballLeagueService {

	@Autowired
	private FootballLeagueApiService footballApiService;

	@Override
	public FootballLeagueStanding getStandings(String countryName, String leagueName, String teamName) {
		FootballLeagueStanding response = new FootballLeagueStanding();
		Country c = findCountryByName(countryName);
		if (c == null) {
			throw new BadRequestException("No such country is found");
		}
		String country = "(" + c.getCountryId() + ")" + " - " + countryName;
		response.setCountry(country);

		League league = findLeagueByCountryId(c.getCountryId());

		if (league == null) {
			throw new BadRequestException("No such leagues is found");
		}
		
		String leagues = "(" + league.getLeagueId() + ")" + " - " + leagueName;
		response.setLeague(leagues);
		
		List<Standings> standings = findStandingForTeam(league.getLeagueId());

		standings = standings.stream().filter(
				standing -> standing.getCountryName().equals(countryName) && standing.getTeamName().equals(teamName))
				.collect(Collectors.toList());

		if (!CollectionUtils.isEmpty(standings)) {
			Standings standing = standings.get(0);

			String team = "(" + standing.getTeamId() + ")" + " - " + standing.getTeamName();
			response.setTeam(team);
			response.setOverallPosition(standing.getOverallLeaguePosition());
		} else {
			throw new BadRequestException("No such country or team found in this League");
		}

		return response;
	}

	public Country findCountryByName(String name) {
		List<Country> allCountries = footballApiService.getAllCountries();

		Optional<Country> countryPrd = allCountries.stream().filter(country -> name.equals(country.getCountryName()))
				.findFirst();
		return countryPrd.isPresent() ? countryPrd.get() : null;
	}

	public League findLeagueByCountryId(String countryId) {
		List<League> allLeagues = footballApiService.getLeageForCountry(countryId);

		Optional<League> leaguePrd = allLeagues.stream().filter(league -> countryId.equals(league.getCountryId()))
				.findFirst();
		return leaguePrd.isPresent() ? leaguePrd.get() : null;
	}

	public List<Standings> findStandingForTeam(String leagueId) {
		return footballApiService.getStandingForCountry(leagueId);
	}
}
