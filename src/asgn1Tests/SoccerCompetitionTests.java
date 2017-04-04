package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.CompetitionException;
import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerCompetition;
import asgn1SoccerCompetition.SoccerLeague;
import asgn1SoccerCompetition.SoccerTeam;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerCompetition class
 *
 * @author Alan Woodley
 *
 */
public class SoccerCompetitionTests {
	SoccerCompetition sc;
	SoccerTeam team1;
	SoccerTeam team2;
	SoccerTeam team3;
	SoccerTeam team4;
	
	public void registerTeams() throws TeamException, LeagueException, CompetitionException {
		team1 = new SoccerTeam("Chelsea", "Blues");
		team2 = new SoccerTeam("Manchester City", "City");
		team3 = new SoccerTeam("Manchester United", "Red Devils");
		team4 = new SoccerTeam("Southampton", "Saints");
		sc.getLeague(0).registerTeam(team1);
		sc.getLeague(0).registerTeam(team2);
		sc.getLeague(0).registerTeam(team3);
		sc.getLeague(0).registerTeam(team4);
	}
	@Before
	public void setup() {
		sc = new SoccerCompetition("Test", 2, 4);
	}
	
	
	@Test
	public void GetLeagueNumCorrect() throws CompetitionException{
		SoccerLeague expectedLeague = sc.getLeague(0);
		assertEquals(expectedLeague, sc.getLeague(0));
	}
	
	@Test
	public void startSeasonWithNoTeams() throws LeagueException, CompetitionException{
		//sc.getLeague(0).startNewSeason();
		sc.startSeason();
		Boolean offSeason = sc.getLeague(0).isOffSeason();
		assertTrue(offSeason);
	}
	
	@Test(expected = LeagueException.class)
	public void startSeasonWithOneTeam() throws LeagueException, CompetitionException, TeamException{
		SoccerTeam team1 = new SoccerTeam("Chelsea", "Blues");
		sc.getLeague(0).registerTeam(team1);
		sc.getLeague(0).startNewSeason();
		//sc.startSeason();
		//Boolean offSeason = sc.getLeague(0).isOffSeason();
		//assertTrue(offSeason);
	}
	
	@Test(expected = LeagueException.class)
	public void AdddingSameTeamTwice() throws LeagueException, CompetitionException, TeamException{
		SoccerTeam team1 = new SoccerTeam("Chelsea", "Blues");
		sc.getLeague(0).registerTeam(team1);
		SoccerTeam team2 = new SoccerTeam("Chelsea", "Blues");
		sc.getLeague(0).registerTeam(team2);
		//Boolean offSeason = sc.getLeague(0).isOffSeason();
		//assertTrue(offSeason);
	}
	
	@Test
	public void startSeason() throws LeagueException, CompetitionException, TeamException{
		registerTeams();
		sc.startSeason();
		Boolean offSeason = sc.getLeague(0).isOffSeason();
		assertFalse(offSeason);
	}
	
	@Test
	public void endSeasonWork() throws TeamException, LeagueException, CompetitionException {
		registerTeams();
		sc.startSeason();
		sc.endSeason();
		assertEquals(team1, sc.getLeague(0).getTopTeam());
		
		
	}

}

