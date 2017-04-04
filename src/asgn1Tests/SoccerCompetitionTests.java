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
	
	@Before
	public void setup() {
		sc = new SoccerCompetition("Test", 2, 4);
	}
	
	
	@Test
	public void GetLeagueNumCorrect() throws CompetitionException{
		SoccerLeague expectedLeague = sc.getLeague(0);
		assertEquals(expectedLeague, sc.getLeague(0));
	}
	
	//Why is this and next one not working.
	@Test(expected = LeagueException.class)
	public void startSeasonWithNoTeams() throws LeagueException, CompetitionException{
		sc.getLeague(0).startNewSeason();
		//sc.startSeason();
		//Boolean offSeason = sc.getLeague(0).isOffSeason();
		//assertTrue(offSeason);
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
		SoccerTeam team1 = new SoccerTeam("Chelsea", "Blues");
		SoccerTeam team2 = new SoccerTeam("Manchester City", "City");
		SoccerTeam team3 = new SoccerTeam("Manchester United", "Red Devils");
		SoccerTeam team4 = new SoccerTeam("Southampton", "Saints");
		sc.getLeague(0).registerTeam(team1);
		sc.getLeague(0).registerTeam(team2);
		sc.getLeague(0).registerTeam(team3);
		sc.getLeague(0).registerTeam(team4);
		sc.startSeason();
		Boolean offSeason = sc.getLeague(0).isOffSeason();
		assertFalse(offSeason);
	}

}

