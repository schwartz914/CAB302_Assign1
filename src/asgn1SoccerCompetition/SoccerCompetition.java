/**
 * 
 */
package asgn1SoccerCompetition;

import java.util.ArrayList;

import asgn1Exceptions.CompetitionException;
import asgn1Exceptions.LeagueException;

/**
 * A class to model a soccer competition. The competition contains one or more number of leagues, 
 * each of which contain a number of teams. Over the course a season matches are played between
 * teams in each league. At the end of the season a premier (top ranked team) and wooden spooner 
 * (bottom ranked team) are declared for each league. If there are multiple leagues then relegation 
 * and promotions occur between the leagues.
 * 
 * @author Alan Woodley
 * @version 1.0
 *
 */
public class SoccerCompetition implements SportsCompetition{
	
	String name;
	private ArrayList<SoccerLeague> competition;
	//private ArrayList<SoccerTeam> league;

	/**
	 * Creates the model for a new soccer competition with a specific name,
	 * number of leagues, and number of games to display to indicate the teams
	 * form. 
	 * 
	 * @param name The name of the competition.
	 * @param numLeagues The number of leagues in the competition.
	 * @param numTeams The number of teams in each league.
	 */
	public SoccerCompetition(String name, int numLeagues, int numTeams){
		this.name = name;
		competition = new ArrayList<SoccerLeague>(numLeagues);
		//league = new ArrayList<SoccerTeam>(numTeams);
		//league = new SoccerLeague(numTeams);
		// TO DO Complete 
	}
	
	/**
	 * Retrieves a league with a specific number (indexed from 0). Returns an exception if the 
	 * league number is invalid.
	 * 
	 * @param leagueNum The number of the league to return.
	 * @return A league specified by leagueNum.
	 * @throws CompetitionException if the specified league number is less than 0.
	 *  or equal to or greater than the number of leagues in the competition.
	 */
	public SoccerLeague getLeague(int leagueNum) throws CompetitionException{
		SoccerLeague league = null;
		
		if(competition.contains(leagueNum)) {
			league = competition.get(leagueNum);
		}
		
		 
		return league;
		
	}
	

	/**
	 * Starts a new soccer season for each league in the competition.
	 */
	public void startSeason() {
		try {
			for (SoccerLeague league : competition) {
				league.startNewSeason();
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	
	/** 
	 * Ends the season of each of the leagues in the competition. 
	 * If there is more than one league then it handles promotion
	 * and relegation between the leagues.  
	 * 
	 */
	public void endSeason()  {
		try {
			for (SoccerLeague league : competition) {
				league.endSeason();
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		

	}

	/** 
	 * For each league displays the competition standings.
	 */
	public void displayCompetitionStandings(){
		System.out.println("+++++" + this.name + "+++++");
		//System.out.println("---- League" + (i +1) + " ----");
		System.out.println("OfficialName"  + '\t' + "Nick Name" + '\t' + "Form" + '\t' +  "Played" + '\t' + "Won" + '\t' + "Lost" +'\t' + "Drawn" + '\t' + "For" + '\t' + "Against" + '\t' + "GlDiff" + '\t' + "Points");
		for (SoccerLeague league : competition) {
			league.displayLeagueTable();
		}
		// TO DO (optional)
		// HINT The heading for each league is
		//	System.out.println("---- League" + (i +1) + " ----");
		//  System.out.println("Official Name" +  '\t' +  "Nick Name" + '\t' + "Form" + '\t' +  "Played" + '\t' + "Won" + '\t' + "Lost" + '\t' + "Drawn" + '\t' + "For" + '\t' + "Against" + '\t' + "GlDiff" + '\t' + "Points");
	}
	

}
