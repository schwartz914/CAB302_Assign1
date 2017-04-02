package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerLeague;
import asgn1SoccerCompetition.SoccerTeam;


/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerLeage class
 *
 * @author Alan Woodley
 *
 */
public class SoccerLeagueTests {
	SoccerLeague sl;
	
	@Before
	public void setup() {
		sl = new SoccerLeague(4);
	}
	
	@Test
	public void getRequiredTeams() {
		int numTeams = sl.getRequiredNumTeams();
		assertEquals(4, numTeams);
	}
	
	@Test(expected = LeagueException.class)
	public void getBottomTeamError() throws LeagueException{
		SoccerTeam botTeam = sl.getBottomTeam();
		assertNull(botTeam);
	}
	
	@Test(expected = LeagueException.class)
	public void getTopTeamErrorThreeTeams() throws TeamException, LeagueException {
		SoccerTeam team1 = new SoccerTeam("Chelsea", "Blues");
		SoccerTeam team2 = new SoccerTeam("Manchester City", "City");
		SoccerTeam team3 = new SoccerTeam("Manchester United", "Red Devils");
		sl.registerTeam(team1);
		sl.registerTeam(team2);
		sl.registerTeam(team3);
		assertEquals(team1, sl.getTopTeam());
	}
	
	@Test(expected = LeagueException.class)
	public void getBottomTeamErrorThreeTeams() throws TeamException, LeagueException {
		SoccerTeam team1 = new SoccerTeam("Chelsea", "Blues");
		SoccerTeam team2 = new SoccerTeam("Manchester City", "City");
		SoccerTeam team3 = new SoccerTeam("Manchester United", "Red Devils");
		sl.registerTeam(team1);
		sl.registerTeam(team2);
		sl.registerTeam(team3);
		assertEquals(team3, sl.getBottomTeam());
	}
	
	@Test(expected = LeagueException.class)
	public void getTopTeamError() throws LeagueException{
		SoccerTeam topTeam = sl.getTopTeam();
		assertNull(topTeam);
	}
	
	
	@Test
	public void getBottomTeamOneMatch() throws TeamException, LeagueException {
		SoccerTeam team1 = new SoccerTeam("Chelsea", "Blues");
		SoccerTeam team2 = new SoccerTeam("Manchester City", "City");
		SoccerTeam team3 = new SoccerTeam("Manchester United", "Red Devils");
		SoccerTeam team4 = new SoccerTeam("Southampton", "Saints");
		sl.registerTeam(team1);
		sl.registerTeam(team2);
		sl.registerTeam(team3);
		sl.registerTeam(team4);
		sl.startNewSeason();
		sl.playMatch("Chelsea", 4, "Manchester", 0);
		assertEquals(team4, sl.getBottomTeam());
	}
	
	@Test
	public void getTopTeamOneMatch() throws TeamException, LeagueException {
		SoccerTeam team1 = new SoccerTeam("Chelsea", "Blues");
		SoccerTeam team2 = new SoccerTeam("Manchester City", "City");
		SoccerTeam team3 = new SoccerTeam("Manchester United", "Red Devils");
		SoccerTeam team4 = new SoccerTeam("Southampton", "Saints");
		sl.registerTeam(team1);
		sl.registerTeam(team2);
		sl.registerTeam(team3);
		sl.registerTeam(team4);
		sl.startNewSeason();
		sl.playMatch("Chelsea", 4, "Manchester", 0);
		assertEquals(team1, sl.getTopTeam());
	}
	
	@Test
	public void containsTeamTestTrue() throws TeamException, LeagueException {
		SoccerTeam team1 = new SoccerTeam("Chelsea", "Blues");
		SoccerTeam team2 = new SoccerTeam("Manchester City", "City");
		sl.registerTeam(team1);
		sl.registerTeam(team2);
		assertTrue(sl.containsTeam("Chelsea"));
	}
	
	@Test
	public void containsTeamTestFalse() throws TeamException, LeagueException {
		SoccerTeam team1 = new SoccerTeam("Chelsea", "Blues");
		SoccerTeam team2 = new SoccerTeam("Manchester City", "City");
		sl.registerTeam(team1);
		sl.registerTeam(team2);
		assertFalse(sl.containsTeam("Southampton"));
	}
	
	/*Play Match Tests */
	/*A method to add the teams to test */
	SoccerTeam team1;
	SoccerTeam team2;
	SoccerTeam team3;
	SoccerTeam team4;
	
	public void registerTeams() throws TeamException, LeagueException {
		team1 = new SoccerTeam("Chelsea", "Blues");
		team2 = new SoccerTeam("Manchester City", "City");
		team3 = new SoccerTeam("Manchester United", "Red Devils");
		team4 = new SoccerTeam("Southampton", "Saints");
		sl.registerTeam(team1);
		sl.registerTeam(team2);
		sl.registerTeam(team3);
		sl.registerTeam(team4);
	}
	@Test(expected = LeagueException.class)
	public void sameNamePlayMatch() throws TeamException, LeagueException {
		registerTeams();
		sl.startNewSeason();
		sl.playMatch("Chelsea", 5, "Chelsea", 2);
		assertEquals(3, team1.getCompetitionPoints());

	}
	
	@Test(expected = LeagueException.class)
	public void seasonNotStartedPlayMatch() throws TeamException, LeagueException {
		registerTeams();
		sl.playMatch("Chelsea", 5, "Southampton", 2);
		assertEquals(0, team4.getCompetitionPoints());
	}
	
	@Test(expected = TeamException.class)
	public void excessGoalsPlayMatch() throws TeamException, LeagueException {
		registerTeams();
		sl.startNewSeason();
		sl.playMatch("Chelsea", 21, "Southampton", 2);
		assertEquals(0, team4.getCompetitionPoints());
	}
	
	@Test(expected = TeamException.class)
	public void negativeGoalsPlayMatch() throws TeamException, LeagueException {
		registerTeams();
		sl.startNewSeason();
		sl.playMatch("Chelsea", 5, "Southampton", -2);
		assertEquals(0, team4.getCompetitionPoints());
	}
	
	@Test
	public void edgeCaseGoalsPlayMatch() throws TeamException, LeagueException {
		registerTeams();
		sl.startNewSeason();
		sl.playMatch("Chelsea", 20, "Southampton", 2);
		assertEquals(20, team1.getGoalsScoredSeason());
	}
}

