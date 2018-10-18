import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

class EfficientWordMarkov extends EfficientMarkov {
//implement and override setTraining and getFollows with WordGram objects
// string myText from BaseMarkov becomes String[] myWords
	String[] myWords;
	HashMap<WordGram, ArrayList<String>> myMap;
	@Override
	public void setTraining(String text) {
		myWords= text.split("\\s+");
		//creates new blank map
		myMap = new HashMap<WordGram, ArrayList<String>>() ;
		myMap.clear();
		
		for (int index=0; index < (myWords.length -myOrder+1); index++) {
			WordGram curGram= new WordGram(myWords, index, myOrder);
			//checks if map contains key, if not adds key and empty array list
			if (!myMap.containsKey(curGram)) {
				ArrayList<String> nList = new ArrayList<String>();
				myMap.put(curGram,  nList);
			}
			//adds string to array list, placed in corresponding key values
			if (index+myOrder < myWords.length) {
				myMap.get(curGram).add(myWords[index+myOrder]);	
			}
			//makes last key value equal pseudo thing
			if(index+myOrder >= myWords.length) {
				myMap.get(curGram).add(PSEUDO_EOS);
			}

		}
	}
	

	
	
	@Override
	public ArrayList<String> getFollows(String key){
		if (!myMap.keySet().contains(key)) {
			throw new NoSuchElementException(key+ " not in map");			
		}
		return myMap.get(key);

}
}