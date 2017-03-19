package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.LeagueException;
import asgn1SoccerCompetition.SoccerLeague;
import asgn1SoccerCompetition.SoccerTeam;


/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerLeage class
 *
 * @author Alan Woodley
 *
 */
public class SoccerLeagueTests {
	SoccerLeague soccerLeague;
	
	@Before
	public void setup() {
		soccerLeague = new SoccerLeague(4);
	}
	
	@Test
	public void getRequiredTeams() {
		int numTeams = soccerLeague.getRequiredNumTeams();
		assertEquals(numTeams, 4);
	}
	
	@Test
	public void getBottomTeam() throws LeagueException{
		SoccerTeam botTeam = soccerLeague.getBottomTeam();
		
	}
}

