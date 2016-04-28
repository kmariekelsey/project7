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
//constructor for duplicating a guess
	public guess(guess dup){
		combination=dup.getCombination();
		blackPegs=dup.getBlackPegs();
		whitePegs=dup.getWhitePegs();
	}
	
// check how close the combo is to the key
	public void updateCombination(String newCombo){
		combination=newCombo;
		setBlackPegs();
		setWhitePegs();
	}

// black pegs are equal to the amount of colors in the guess that exactly matches the key
	private void setBlackPegs(){
		blackPegs = 0;
		for(int i=0;i<4;i++){
			if(combination.substring(i, i+1).equals(masterDriver.keyCode.getCombination().substring(i,i+1))) blackPegs++;
		}
	}

//white pegs are the amount of colors that aren't in the same spot
	private void setWhitePegs(){
		String keyTemp= masterDriver.keyCode.getCombination();
		whitePegs = 0;
		for(int j=0;j<4;j++){
			for(int k=0;k<keyTemp.length();k++){
				if(combination.substring(j,j+1).equals(keyTemp.substring(k,k+1))) {
					keyTemp=keyTemp.substring(0,k)+keyTemp.substring(k+1);  //remove colors in the temp key that match the guess
					whitePegs++;
				}
			}
		}
		whitePegs=whitePegs-blackPegs; //white pegs can't also be black pegs
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
