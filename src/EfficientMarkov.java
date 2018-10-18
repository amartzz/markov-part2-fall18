import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
/*
 * new class that extends BaseMarkov but runs more efficiently
 * inherits the protected instance variables and methods of base markov
 */
public class EfficientMarkov extends BaseMarkov {
	private HashMap<String, ArrayList<String>> myMap;
	//constructor 1
	public EfficientMarkov (int order) {
		super(order);
	}
	//constructor 2	
	public EfficientMarkov() {
		this(3);
	}

	// need to override two methods: setTraining - builds and fills map
	//and getFollows which throws error if it can't find key in map

	@Override
	public void setTraining(String text) {
		myText = text;
		//creates new blank map
		myMap = new HashMap<String, ArrayList<String>>() ;
		myMap.clear();

		for (int index=0; index < (myText.length()-myOrder+1); index++) {
			String textKey= myText.substring(index, index+myOrder);			
			//checks if map contains key, if not adds key and empty array list
			if (!myMap.containsKey(textKey)) {
				ArrayList<String> nList = new ArrayList<String>();
				myMap.put(textKey,  nList);
			}
			//adds string to array list, placed in corresponding key values
			if (index+myOrder < myText.length()) {
				String nextEl= myText.substring(index + myOrder, index + (myOrder+1));
				myMap.get(textKey).add(nextEl);	
			}
			//makes last key value equal pseudo thing
			if(index+myOrder >= myText.length()) {
				myMap.get(textKey).add(PSEUDO_EOS);
			}

		}
	}


	@Override
	public ArrayList<String> getFollows(String key){
		//if key null throw error
		//else return map key 
		if (!myMap.keySet().contains(key)) {
			throw new NoSuchElementException(key+ " not in map");			
		}
		return myMap.get(key);

	}
}

