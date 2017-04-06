package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1SoccerCompetition.SportsTeamForm;
import asgn1SportsUtils.WLD;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerTeamForm class
 *
 * @author Alan Woodley
 *
 */
public class SportsTeamFormTests {
SportsTeamForm sportsTeamForm;
	
	@Before
	public void setup() {
		sportsTeamForm = new SportsTeamForm();
	}
	
	@Test
	public void defaultGameList() {
		String actualString = sportsTeamForm.toString();
		assertEquals("-----", actualString);
	}
	
	@Test
	public void addOneResult() {
		sportsTeamForm.addResultToForm(WLD.WIN);
		String actualString = sportsTeamForm.toString();
		assertEquals("W----", actualString);
		}
	
	@Test
	public void addSixResults() {
		sportsTeamForm.addResultToForm(WLD.WIN);
		sportsTeamForm.addResultToForm(WLD.LOSS);
		sportsTeamForm.addResultToForm(WLD.WIN);
		sportsTeamForm.addResultToForm(WLD.DRAW);
		sportsTeamForm.addResultToForm(WLD.WIN);
		sportsTeamForm.addResultToForm(WLD.DRAW);

		String resultString = sportsTeamForm.toString();
		assertEquals("DWDWL", resultString);
	}
	
	@Test
	public void numGames() {
		sportsTeamForm.addResultToForm(WLD.WIN);
		sportsTeamForm.addResultToForm(WLD.LOSS);
		sportsTeamForm.addResultToForm(WLD.WIN);
		sportsTeamForm.addResultToForm(WLD.DRAW);
		int numGames = sportsTeamForm.getNumGames();
		assertEquals(4, numGames);
	}
	
	@Test 
	public void resetAfterOneGame() {
		sportsTeamForm.addResultToForm(WLD.WIN);
		sportsTeamForm.resetForm();
		int numGames = sportsTeamForm.getNumGames();
		assertEquals(0, numGames);
	}
	
	@Test 
	public void resetAfterFourGames() {
		sportsTeamForm.addResultToForm(WLD.WIN);
		sportsTeamForm.addResultToForm(WLD.LOSS);
		sportsTeamForm.addResultToForm(WLD.WIN);
		sportsTeamForm.addResultToForm(WLD.DRAW);
		sportsTeamForm.resetForm();
		int numGames = sportsTeamForm.getNumGames();
		assertEquals(0, numGames);
	}
}
