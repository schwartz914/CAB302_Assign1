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
	SoccerTeam chelsea;
	SoccerTeam manc;
	SoccerTeam manu;
	SoccerTeam southhampton;
	SoccerTeam leeds;
	SoccerTeam arsenal;
	SoccerTeam everton;
	SoccerTeam liverpool;
	

	@Before
	public void setup() {
		sc = new SoccerCompetition("Test", 2, 4);
	}
	
	public void registerTeams() throws TeamException, LeagueException, CompetitionException {
		chelsea = new SoccerTeam("Chelsea", "Blues");
		manc = new SoccerTeam("Manchester City", "City");
		manu = new SoccerTeam("Manchester United", "Red Devils");
		southhampton = new SoccerTeam("Southampton", "Saints");
		
		leeds = new SoccerTeam("Leeds United", "The Peacocks");
		arsenal = new SoccerTeam("Arsenal", "Gunners");
		everton = new SoccerTeam("Everton", "The Blues");
		liverpool = new SoccerTeam("Liverpool", "The Reds");
		
		sc.getLeague(0).registerTeam(chelsea);
		sc.getLeague(0).registerTeam(manc);
		sc.getLeague(0).registerTeam(manu);
		sc.getLeague(0).registerTeam(southhampton);
		
		sc.getLeague(1).registerTeam(leeds);
		sc.getLeague(1).registerTeam(arsenal);
		sc.getLeague(1).registerTeam(everton);
		sc.getLeague(1).registerTeam(liverpool);
	}
	
	@Test(expected = CompetitionException.class)
	public void getLeagueFail() throws CompetitionException, TeamException, LeagueException {
		registerTeams();
		SoccerLeague league = sc.getLeague(2);
		assertNotNull(league);
	}
	
	@Test(expected = CompetitionException.class)
	public void getLeagueNegFail() throws CompetitionException, TeamException, LeagueException {
		registerTeams();
		SoccerLeague league = sc.getLeague(-1);
		assertNotNull(league);
	}
	
	@Test
	public void endSeasonRelegation() throws CompetitionException, TeamException, LeagueException{
		registerTeams();
		sc.startSeason();
		chelsea.playMatch(19, 2);
		southhampton.playMatch(2, 19);
		
		String relegated = sc.getLeague(0).getBottomTeam().getOfficialName();
		sc.endSeason();
		assertTrue(sc.getLeague(1).containsTeam(relegated));
	}
	
	@Test
	public void endSeasonPromotion() throws CompetitionException, TeamException, LeagueException{
		registerTeams();
		sc.startSeason();
		leeds.playMatch(19, 2);
		liverpool.playMatch(2, 19);
		
		String relegated = sc.getLeague(1).getTopTeam().getOfficialName();
		sc.endSeason();
		assertTrue(sc.getLeague(0).containsTeam(relegated));
	}
	
	@Test
	public void startSeasonWithNoTeams() throws LeagueException, CompetitionException{
		sc.startSeason();
		Boolean offSeason = sc.getLeague(0).isOffSeason();
		assertTrue(offSeason);
	}
	
	@Test
	public void startSeasonWithOneTeam() throws LeagueException, CompetitionException, TeamException{
		SoccerTeam chelsea = new SoccerTeam("Chelsea", "Blues");
		sc.getLeague(0).registerTeam(chelsea);
		sc.startSeason();
		Boolean offSeason = sc.getLeague(0).isOffSeason();
		assertTrue(offSeason);
	}
	
	@Test(expected = LeagueException.class)
	public void AdddingSameTeamTwice() throws LeagueException, CompetitionException, TeamException{
		SoccerTeam chelsea = new SoccerTeam("Chelsea", "Blues");
		sc.getLeague(0).registerTeam(chelsea);
		SoccerTeam manc = new SoccerTeam("Chelsea", "Blues");
		sc.getLeague(0).registerTeam(manc);

	}
	
	@Test
	public void offSeasonException() throws TeamException, LeagueException, CompetitionException {
		registerTeams();
		sc.endSeason();
		assertTrue(sc.getLeague(0).isOffSeason());
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
		chelsea.playMatch(2, 0);
		sc.endSeason();
		assertEquals(chelsea, sc.getLeague(0).getTopTeam());
	}

}

