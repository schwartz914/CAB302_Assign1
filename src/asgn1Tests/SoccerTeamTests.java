package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerTeam;



/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerLeage class
 *
 * @author Alan Woodley
 *
 */
public class SoccerTeamTests {
SoccerTeam soccerTeam;
	
	@Before
	public void setup() throws TeamException{
		soccerTeam = new SoccerTeam("Chelsea", "Blues");
	}
	
	@Test
	public void getOfficialName() {
		String name = soccerTeam.getOfficialName();
		assertEquals(name, "Chelsea");
	}
	
	@Test
	public void getNickname() {
		String nick = soccerTeam.getNickName();
		assertEquals("Blues",nick);
	}
	
	@Test
	public void getGoalsScoredDefault() {
		int goalScore = soccerTeam.getGoalsScoredSeason();
		assertEquals(0,goalScore);
	}
	
	@Test
	public void getGoalsScoredStandard() throws TeamException {
		soccerTeam.playMatch(10,2);
		int goalScore = soccerTeam.getGoalsScoredSeason();
		assertEquals(10,goalScore);
	}
	
	@Test
	public void getGoalsScoredEdge() throws TeamException {
		soccerTeam.playMatch(20,20);
		int goalScore = soccerTeam.getGoalsScoredSeason();
		assertEquals(20,goalScore);
	}
	
	@Test(expected = Exception.class)
	public void getGoalsScoredOutsidePositive() throws TeamException {
		soccerTeam.playMatch(21,21);
		int goalScore = soccerTeam.getGoalsScoredSeason();
		assertEquals(21,goalScore);
	}
	
	@Test(expected = Exception.class)
	public void getGoalsScoredOutsideNegative() throws TeamException {
		soccerTeam.playMatch(-1,-5);
		int goalScore = soccerTeam.getGoalsScoredSeason();
		assertEquals(-1,goalScore);
	}
	
	@Test(expected = Exception.class)
	public void getGoalsScoredMixedException() throws TeamException {
		soccerTeam.playMatch(25,-5);
		int goalScore = soccerTeam.getGoalsScoredSeason();
		assertEquals(25,goalScore);
	}
	
	@Test
	public void getGoalsConceededDefault() {
		int goalConc = soccerTeam.getGoalsConcededSeason();
		assertEquals(0,goalConc);
	}
	
	@Test
	public void getGoalsConceededNormal() throws TeamException {
		soccerTeam.playMatch(2,1);
		int goalConc = soccerTeam.getGoalsConcededSeason();
		assertEquals(1,goalConc);
	}
	
	@Test
	public void getGoalsConceededEdgeCase() throws TeamException {
		soccerTeam.playMatch(0,20);
		int goalConc = soccerTeam.getGoalsConcededSeason();
		assertEquals(20,goalConc);
	}

	@Test(expected = TeamException.class)
	public void getGoalsConceededPosExceeded() throws TeamException {
		soccerTeam.playMatch(0,21);
		int goalConc = soccerTeam.getGoalsConcededSeason();
		assertEquals(21,goalConc);
	}
	
	@Test(expected = TeamException.class)
	public void getGoalsConceededNegExceeded() throws TeamException {
		soccerTeam.playMatch(-1,-2);
		int goalConc = soccerTeam.getGoalsConcededSeason();
		assertEquals(-2,goalConc);
	}
	
	@Test(expected = TeamException.class)
	public void getGoalsConceededMixedExceeded() throws TeamException {
		soccerTeam.playMatch(31,-2);
		int goalConc = soccerTeam.getGoalsConcededSeason();
		assertEquals(-2,goalConc);
	}
	
	@Test
	public void getMatchesWonDefault() {
		int matchesWon = soccerTeam.getMatchesWon();
		assertEquals(0,matchesWon);
	}
	
	@Test 
	public void getMatchesWonAfterOne() throws TeamException {
		soccerTeam.playMatch(3,2);
		int matchesWon = soccerTeam.getMatchesWon();
		assertEquals(1,matchesWon);
	}
	
	@Test 
	public void getMatchesWonAfterSix() throws TeamException {
		soccerTeam.playMatch(3,2);
		soccerTeam.playMatch(2,2);
		soccerTeam.playMatch(3,2);
		soccerTeam.playMatch(4,2);
		soccerTeam.playMatch(2,4);
		soccerTeam.playMatch(2,1);
		int matchesWon = soccerTeam.getMatchesWon();
		assertEquals(4,matchesWon);
	}
	
	@Test
	public void getMatchesLostDefault() {
		int matchesLost = soccerTeam.getMatchesLost();
		assertEquals(0,matchesLost);
	}	
	
	@Test
	public void getMatchesLostAfterOne() throws TeamException{
		soccerTeam.playMatch(1,2);
		int matchesLost = soccerTeam.getMatchesLost();
		assertEquals(1,matchesLost);
	}	
	@Test
	public void getMatchesLostAfterSix() throws TeamException{
		soccerTeam.playMatch(1,2);
		soccerTeam.playMatch(1,2);
		soccerTeam.playMatch(1,2);
		soccerTeam.playMatch(4,2);
		soccerTeam.playMatch(2,4);
		soccerTeam.playMatch(1,1);
		int matchesLost = soccerTeam.getMatchesLost();
		assertEquals(4,matchesLost);
	}	
	
	@Test
	public void getMatchesDrawnDefault() {
		int matchesDrawn = soccerTeam.getMatchesDrawn();
		assertEquals(0,matchesDrawn);
	}
	
	@Test
	public void getMatchesDrawnAfterOne() throws TeamException {
		soccerTeam.playMatch(1,1);
		int matchesDrawn = soccerTeam.getMatchesDrawn();
		assertEquals(1,matchesDrawn);
	}
	
	@Test
	public void getMatchesDrawnAfterSix() throws TeamException {
		soccerTeam.playMatch(3,2);
		soccerTeam.playMatch(2,2);
		soccerTeam.playMatch(2,2);
		soccerTeam.playMatch(2,2);
		soccerTeam.playMatch(2,4);
		soccerTeam.playMatch(1,1);
		int matchesDrawn = soccerTeam.getMatchesDrawn();
		assertEquals(4,matchesDrawn);
	}
	
	@Test
	public void getCompetitionPointsDefault() {
		int points = soccerTeam.getCompetitionPoints();
		assertEquals(0, points);
	}
	
	@Test
	public void getCompetitionPointsOneMatch() throws TeamException {
		soccerTeam.playMatch(3,2);
		int points = soccerTeam.getCompetitionPoints();
		assertEquals(3, points);
	}
	
	@Test
	public void getCompetitionPointsFiveMatch() throws TeamException {
		soccerTeam.playMatch(3,2);
		soccerTeam.playMatch(2,2);
		soccerTeam.playMatch(2,2);
		soccerTeam.playMatch(2,2);
		soccerTeam.playMatch(2,4);
		soccerTeam.playMatch(1,1);
		int points = soccerTeam.getCompetitionPoints();
		assertEquals(7, points);
	}
	
	@Test
	public void getGoalDifferenceDefault() {
		int scoreDiff = soccerTeam.getGoalDifference();
		assertEquals(0,scoreDiff);
	}
	
	@Test
	public void getGoalDifferenceOneMatch() throws TeamException {
		soccerTeam.playMatch(3,2);
		int scoreDiff = soccerTeam.getGoalDifference();
		assertEquals(1,scoreDiff);
	}
	
	@Test
	public void getFormString() {
		String teamString = soccerTeam.getFormString();
		String expectedString = "-----";
		assertEquals(expectedString, teamString);
	}
}
