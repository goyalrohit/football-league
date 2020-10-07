package com.rohit.footballleagueapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rohit.footballleagueapi.model.FootballLeagueStanding;
import com.rohit.footballleagueapi.service.FootballLeagueService;

@RestController
@RequestMapping("/footballApp")
public class FootballStandingController {

	@Autowired
	FootballLeagueService service;

	@RequestMapping(method = RequestMethod.GET, path = "/teamStandings", produces = "application/JSON")
	public FootballLeagueStanding getStandingDetails(@RequestParam String countryName,
			@RequestParam String leagueName, @RequestParam String teamName) {
		FootballLeagueStanding response = new FootballLeagueStanding();
		response = service.getStandings(countryName, leagueName, teamName);

		return response;
	}

}