import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.NoSuchElementException;
	/*
	 * new class that extends BaseMarkov but runs more efficiently
	 * inherits the protected instance variables and methods of base markov
	 */
	class EfficientMarkov extends BaseMarkov {
		HashMap<String, ArrayList<String>> myMap;
		
	/**add two constructors
	 * 1st constructor
	 *  @param is order of markov model
	 *  
	 *  2nd constructor
	 *  calls order of markov model and sets order to 3
	 **/
	//constructor 1
	public EfficientMarkov (int order) {
		super(order);
		// add one instance variable myMap which is a HashMap, should be in construtor
		HashMap<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>() ;
			}
	//constructor 2	
	public EfficientMarkov() {
		  this(3);
		  HashMap<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>() ;
			}
		
	// need to override two methods: setTraining - builds and fills map
	//and getFollows

	@Override
	public void setTraining(String text) {
		myText = text;
		myMap.clear();
		
		//map.getKey gets the value
		for (int index=0; index < myText.length(); index++) {
			String textKey= myText.substring(index, index+myOrder);			
			ArrayList<String> nList = new ArrayList<String>();
			nList.add(String.valueOf(myText.charAt(index+3)));
			myMap.put(textKey,  nList);
				
					//last key value should equal pseudo thing
					if(index+1 == myText.length()) {
					ArrayList<String> aList = new ArrayList<String>();
					aList.add(PSEUDO_EOS);
					myMap.put(textKey, aList);
					}
			
		}
		
		
		
		
		
		
	}

		
	@Override
	public ArrayList<String> getFollows(String key){
		
		
		ArrayList<String> follows = new ArrayList<String>();
		
		int pos = 0;  // location where search for key in text starts
		
		while (pos < myText.length()){
			int start = myText.indexOf(key,pos);
			if (start == -1){
				//System.out.println("didn't find "+key);
				throw new NoSuchElementException(key+ " not in map");
			}
			if (start + key.length() >= myText.length()){
				//System.out.println("found end with "+key);
				follows.add(PSEUDO_EOS);
				break;
			}
			// next line is string equivalent of myText.charAt(start+key.length())
			String next = myText.substring(start+key.length(), start+key.length()+1);
			follows.add(next);
			pos = start+1;  // search continues after this occurrence
		}
		return follows;
	}
		
	}


