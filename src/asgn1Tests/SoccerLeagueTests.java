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
	private SoccerLeague sl;
	private SoccerTeam manU;
	private SoccerTeam manC;
	private SoccerTeam chelsea;
	private SoccerTeam southampton;
	
	@Before
	public void setup() {
		sl = new SoccerLeague(4);
	}
	
	/*A method to add the teams to test */

	private void registerTeams() throws TeamException, LeagueException {
		chelsea = new SoccerTeam("Chelsea", "Blues");
		manC = new SoccerTeam("Manchester City", "City");
		manU = new SoccerTeam("Manchester United", "Red Devils");
		southampton = new SoccerTeam("Southampton", "Saints");
		sl.registerTeam(manU);
		sl.registerTeam(manC);
		sl.registerTeam(chelsea);
		sl.registerTeam(southampton);
	}
	
	@Test
	public void getRequiredTeams() {
		int numTeamsRequired = sl.getRequiredNumTeams();
		assertEquals(4, numTeamsRequired);
	}
	
	@Test
	public void getRegisteredTeams() throws TeamException, LeagueException {
		registerTeams();
		int numRegisteredTeams = sl.getRegisteredNumTeams();
		assertEquals(4, numRegisteredTeams);
	}
	
	@Test
	public void getRegisteredNoTeams() {
		int numRegisteredTeams = sl.getRegisteredNumTeams();
		assertEquals(0, numRegisteredTeams);
	}
	
	@Test(expected = LeagueException.class)
	public void getBottomTeamError() throws LeagueException{
		SoccerTeam botTeam = sl.getBottomTeam();
		assertNull(botTeam);
	}
	
	@Test(expected = LeagueException.class)
	public void getTopTeamErrorThreeTeams() throws TeamException, LeagueException {
		SoccerTeam manU = new SoccerTeam("Chelsea", "Blues");
		SoccerTeam manC = new SoccerTeam("Manchester City", "City");
		SoccerTeam chelsea = new SoccerTeam("Manchester United", "Red Devils");
		sl.registerTeam(manU);
		sl.registerTeam(manC);
		sl.registerTeam(chelsea);
		assertEquals(manU, sl.getTopTeam());
	}
	
	@Test(expected = LeagueException.class)
	public void getBottomTeamErrorThreeTeams() throws TeamException, LeagueException {
		registerTeams();
		sl.removeTeam(southampton);
		assertEquals(chelsea, sl.getBottomTeam());
	}
	
	@Test(expected = LeagueException.class)
	public void getTopTeamError() throws LeagueException{
		SoccerTeam topTeam = sl.getTopTeam();
		assertNull(topTeam);
	}
	
	
	@Test
	public void getBottomTeamOneMatch() throws LeagueException, TeamException {
		registerTeams();
		sl.startNewSeason();
		sl.playMatch("Chelsea", 4, "Manchester United", 0);
		assertEquals(manU, sl.getBottomTeam());
	}
	
	@Test
	public void getTopTeamOneMatch() throws TeamException, LeagueException {
		registerTeams();
		sl.startNewSeason();
		sl.playMatch("Chelsea", 4, "Manchester United", 0);
		assertEquals(chelsea, sl.getTopTeam());
	}
	
	@Test
	public void containsTeamTestTrue() throws TeamException, LeagueException {
		registerTeams();
		assertTrue(sl.containsTeam("Chelsea"));
	}
	
	@Test
	public void containsTeamTestFalse() throws TeamException, LeagueException {
		registerTeams();
		assertFalse(sl.containsTeam("Leeds"));
	}
	

	@Test(expected = LeagueException.class)
	public void sameNamePlayMatch() throws TeamException, LeagueException {
		registerTeams();
		sl.startNewSeason();
		sl.playMatch("Chelsea", 5, "Chelsea", 2);
		assertEquals(3, manU.getCompetitionPoints());

	}
	
	@Test(expected = LeagueException.class)
	public void seasonNotStartedPlayMatch() throws TeamException, LeagueException {
		registerTeams();
		sl.playMatch("Chelsea", 5, "Southampton", 2);
		assertEquals(0, southampton.getCompetitionPoints());
	}
	
	@Test
	public void excessGoalsPlayMatch() throws TeamException, LeagueException {
		registerTeams();
		sl.startNewSeason();
		sl.playMatch("Chelsea", 21, "Southampton", 2);
		assertEquals(0, manU.getCompetitionPoints());
	}
	
	@Test
	public void negativeGoalsPlayMatch() throws TeamException, LeagueException {
		registerTeams();
		sl.startNewSeason();
		sl.playMatch("Chelsea", 5, "Southampton", -2);
		assertEquals(0, manU.getCompetitionPoints());
	}
	
	@Test
	public void edgeCaseGoalsPlayMatch() throws TeamException, LeagueException {
		registerTeams();
		sl.startNewSeason();
		sl.playMatch("Chelsea", 20, "Southampton", 2);
		assertEquals(20, chelsea.getGoalsScoredSeason());
	}
	
	@Test(expected = LeagueException.class)
	public void getTeamTestFail() throws LeagueException, TeamException {
		registerTeams();
		SoccerTeam leeds;
		leeds = sl.getTeamByOfficalName("Leeds");
		assertNotNull(leeds);
	}
	
	@Test
	public void getTeamPass() throws TeamException, LeagueException {
		registerTeams();
		SoccerTeam result;
		result = sl.getTeamByOfficalName("Chelsea");
		assertEquals(chelsea, result);
	}
	
	@Test
	public void startSeasonPass() throws TeamException, LeagueException {
		registerTeams();
		sl.startNewSeason();
		assertFalse(sl.isOffSeason());
	}
	
	@Test(expected = LeagueException.class)
	public void startSeasonNoTeams() throws LeagueException {
		sl.startNewSeason();
	}
	
	@Test
	public void endSeasonWorks() throws TeamException, LeagueException {
		registerTeams();
		sl.startNewSeason();
		sl.endSeason();
		assertTrue(sl.isOffSeason());
	}
	
	@Test(expected = LeagueException.class)
	public void endSeasonFails() throws LeagueException {
		sl.endSeason();
	}
	
	@Test(expected = LeagueException.class)
	public void startSeasonTwice() throws TeamException, LeagueException {
		registerTeams();
		sl.startNewSeason();
		sl.startNewSeason();
	}
	
	@Test
	public void removeTeamWorks() throws TeamException, LeagueException {
		registerTeams();
		sl.removeTeam(chelsea);
		assertFalse(sl.containsTeam("Chelsea"));
	}
	
	@Test(expected = LeagueException.class)
	public void removeTeamNotRegistered() throws TeamException, LeagueException {
		registerTeams();
		SoccerTeam leeds = new SoccerTeam("Leeds United", "The Peacocks");
		sl.removeTeam(leeds);
	}
	
	@Test(expected = LeagueException.class)
	public void removeTeamNotOffSeason() throws TeamException, LeagueException {
		registerTeams();
		sl.startNewSeason();
		sl.removeTeam(manU);
	}
	
	@Test
	public void registerTeamWorks() throws TeamException, LeagueException {
		SoccerTeam leeds = new SoccerTeam("Leeds United", "The Peacocks");
		sl.registerTeam(leeds);
		assertTrue(sl.containsTeam("Leeds United"));
	}
	@Test(expected = LeagueException.class)
	public void registerTeamAlreadyRegistered() throws TeamException, LeagueException {
		registerTeams();
		sl.registerTeam(manU);
	}
	
	@Test(expected = LeagueException.class)
	public void registerTeamSeasonStarted() throws TeamException, LeagueException {
		registerTeams();
		sl.startNewSeason();
		SoccerTeam leeds = new SoccerTeam("Leeds United", "The Peacocks");
		sl.registerTeam(leeds);
	}
	
	@Test(expected = LeagueException.class)
	public void registerTeamMaxTeams() throws TeamException, LeagueException {
		registerTeams();
		SoccerTeam leeds = new SoccerTeam("Leeds United", "The Peacocks");
		sl.registerTeam(leeds);
	}
}

