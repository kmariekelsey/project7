Solves EE422C programming assignment #7 
@author Katelyn Kelsey kmk2669
	Andrei Bonteanu fab542
@version  2016-04-28
*/

package project7;

public class guess {
	private String combination;
	private int blackPegs;
	private int whitePegs;

//main constructor
	public guess(String userInput){
		combination=userInput;
		whitePegs=0;
		blackPegs=0;	
	}

	

	public int getBlackPegs(){
		return blackPegs;
	}

	public int getWhitePegs(){
		return whitePegs;		
	}

	public String getCombination(){
		return combination;
	}

}
