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
SoccerTeam team;
	
	@Before
	public void setup() throws TeamException{
		team = new SoccerTeam("Chelsea", "Blues");
	}
	
	@Test
	public void getOfficialName() {
		String name = team.getOfficialName();
		assertEquals("Chelsea", name);
	}
	
	@Test
	public void getNickname() {
		String nick = team.getNickName();
		assertEquals("Blues", nick);
	}
	
	@Test
	public void getGoalsScoredDefault() {
		int goalScore = team.getGoalsScoredSeason();
		assertEquals(0, goalScore);
	}
	
	@Test
	public void getGoalsScoredStandard() throws TeamException {
		team.playMatch(10,2);
		int goalScore = team.getGoalsScoredSeason();
		assertEquals(10,goalScore);
	}
	
	@Test
	public void getGoalsScoredEdge() throws TeamException {
		team.playMatch(20,20);
		int goalScore = team.getGoalsScoredSeason();
		assertEquals(20,goalScore);
	}
	
	@Test(expected = TeamException.class)
	public void getGoalsScoredOutsidePositive() throws TeamException {
		team.playMatch(21,21);
		int goalScore = team.getGoalsScoredSeason();
		assertEquals(21,goalScore);
	}
	
	@Test(expected = TeamException.class)
	public void getGoalsScoredOutsideNegative() throws TeamException {
		team.playMatch(-1,-5);
		int goalScore = team.getGoalsScoredSeason();
		assertEquals(-1,goalScore);
	}
	
	@Test(expected = TeamException.class)
	public void getGoalsScoredMixedException() throws TeamException {
		team.playMatch(25,-5);
		int goalScore = team.getGoalsScoredSeason();
		assertEquals(25,goalScore);
	}
	
	@Test
	public void getGoalsConceededDefault() {
		int goalConc = team.getGoalsConcededSeason();
		assertEquals(0,goalConc);
	}
	
	@Test
	public void getGoalsConceededNormal() throws TeamException {
		team.playMatch(2,1);
		int goalConc = team.getGoalsConcededSeason();
		assertEquals(1,goalConc);
	}
	
	@Test
	public void getGoalsConceededEdgeCase() throws TeamException {
		team.playMatch(0,20);
		int goalConc = team.getGoalsConcededSeason();
		assertEquals(20,goalConc);
	}

	@Test(expected = TeamException.class)
	public void getGoalsConceededPosExceeded() throws TeamException {
		team.playMatch(0,21);
		int goalConc = team.getGoalsConcededSeason();
		assertEquals(21, goalConc);
	}
	
	@Test(expected = TeamException.class)
	public void getGoalsConceededNegExceeded() throws TeamException {
		team.playMatch(-1,-2);
		int goalConc = team.getGoalsConcededSeason();
		assertEquals(-2, goalConc);
	}
	
	@Test(expected = TeamException.class)
	public void getGoalsConceededMixedExceeded() throws TeamException {
		team.playMatch(31,-2);
		int goalConc = team.getGoalsConcededSeason();
		assertEquals(-2,goalConc);
	}
	
	@Test
	public void getMatchesWonDefault() {
		int matchesWon = team.getMatchesWon();
		assertEquals(0,matchesWon);
	}
	
	@Test 
	public void getMatchesWonAfterOne() throws TeamException {
		team.playMatch(3,2);
		int matchesWon = team.getMatchesWon();
		assertEquals(1,matchesWon);
	}
	
	@Test 
	public void getMatchesWonAfterSix() throws TeamException {
		team.playMatch(3,2);
		team.playMatch(2,2);
		team.playMatch(3,2);
		team.playMatch(4,2);
		team.playMatch(2,4);
		team.playMatch(2,1);
		int matchesWon = team.getMatchesWon();
		assertEquals(4,matchesWon);
	}
	
	@Test
	public void getMatchesLostDefault() {
		int matchesLost = team.getMatchesLost();
		assertEquals(0,matchesLost);
	}	
	
	@Test
	public void getMatchesLostAfterOne() throws TeamException{
		team.playMatch(1,2);
		int matchesLost = team.getMatchesLost();
		assertEquals(1,matchesLost);
	}	
	@Test
	public void getMatchesLostAfterSix() throws TeamException{
		team.playMatch(1,2);
		team.playMatch(1,2);
		team.playMatch(1,2);
		team.playMatch(4,2);
		team.playMatch(2,4);
		team.playMatch(1,1);
		int matchesLost = team.getMatchesLost();
		assertEquals(4, matchesLost);
	}	
	
	@Test
	public void getMatchesDrawnDefault() {
		int matchesDrawn = team.getMatchesDrawn();
		assertEquals(0,matchesDrawn);
	}
	
	@Test
	public void getMatchesDrawnAfterOne() throws TeamException {
		team.playMatch(1,1);
		int matchesDrawn = team.getMatchesDrawn();
		assertEquals(1,matchesDrawn);
	}
	
	@Test
	public void getMatchesDrawnAfterSix() throws TeamException {
		team.playMatch(3,2);
		team.playMatch(2,2);
		team.playMatch(2,2);
		team.playMatch(2,2);
		team.playMatch(2,4);
		team.playMatch(1,1);
		int matchesDrawn = team.getMatchesDrawn();
		assertEquals(4,matchesDrawn);
	}
	
	@Test
	public void getCompetitionPointsDefault() {
		int points = team.getCompetitionPoints();
		assertEquals(0, points);
	}
	
	@Test
	public void getCompetitionPointsOneMatch() throws TeamException {
		team.playMatch(3,2);
		int points = team.getCompetitionPoints();
		assertEquals(3, points);
	}
	
	@Test
	public void getCompetitionPointsSixMatch() throws TeamException {
		team.playMatch(3,2);
		team.playMatch(2,2);
		team.playMatch(2,2);
		team.playMatch(2,2);
		team.playMatch(2,4);
		team.playMatch(1,1);
		int points = team.getCompetitionPoints();
		assertEquals(7, points);
	}
	
	@Test
	public void getGoalDifferenceDefault() {
		int scoreDiff = team.getGoalDifference();
		assertEquals(0,scoreDiff);
	}
	
	@Test
	public void getGoalDifferenceOneMatch() throws TeamException {
		team.playMatch(3,2);
		int scoreDiff = team.getGoalDifference();
		assertEquals(1,scoreDiff);
	}
	
	@Test
	public void getFormString() {
		String teamString = team.getFormString();
		String expectedString = "-----";
		assertEquals(expectedString, teamString);
	}
	
	@Test(expected = TeamException.class)
	public void testNameEmpty() throws TeamException {
		SoccerTeam noName = new SoccerTeam("", "");
	}
	
	@Test(expected = TeamException.class)
	public void testNameNull() throws TeamException {
		SoccerTeam noName = new SoccerTeam(" ", " ");
	}
	
}
