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
	SoccerCompetition soccerCompetition;
	
	@Before
	public void setup() {
		soccerCompetition = new SoccerCompetition("Test", 1, 4);
	}
	
	@Test
	public void GetLeagueNumWrong() throws CompetitionException{
		SoccerLeague soccerLeague = new SoccerLeague(1);
				//soccerCompetition.getLeague(1);;
		assertEquals(soccerLeague, soccerCompetition.getLeague(1));
	}
	
	@Test
	public void GetLeagueNumCorrect() throws CompetitionException{
		SoccerLeague soccerLeague = soccerCompetition.getLeague(0);
		//assertThat(soccerLeague, is(soccerCompetition.getLeague(0)));
	}
	

}

