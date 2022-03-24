package lab11;


public class QuadraticProbingHashTable
{
	
    public HashEntry [ ] HashTable;   // The array that holds the hash table
    public int currentSize;       // The number of occupied cells	

	// constructor to create the HashTable of initial size = size
    // and sets all of its elements to null.
    public QuadraticProbingHashTable( int size )
    {
    	HashTable = new HashEntry[size];
    	for( int i=0; i<HashTable.length; i++){
    		HashTable[i]= null;
    	}
    }
 
    // insert into the hash table
    // if the item is already present, do nothing and simply return
    // be careful you might need to rehash - reshape when the load factor is .75
    // Hint: first check the load factor after add - if the size is beyond rehash first!
    public void insert( int x )
    {
    	if((double)(currentSize+1)/HashTable.length > 0.75)
    	    rehash();
    
    	   int currentPos = findPosition(x);
    	   if(HashTable[currentPos] != null && HashTable[currentPos].isActive && HashTable[currentPos].element == x)
    	       return;
    	   
    	
    	  HashTable[currentPos] = new HashEntry(x, true);
    	  currentSize++;
         
    }
    //new helper method
    public int findPosition(int x){
    	int collisionNum=0;
    	int currPosition = hash(x, HashTable.length);
    	int initialPos = currPosition;
    	
    	while(HashTable[currPosition] !=null 
    		  && HashTable[currPosition].isActive 
    		  && HashTable[currPosition].element != x){
    		
    	   currPosition = (initialPos + (collisionNum * collisionNum++)) % HashTable.length;
    	}
    	
    	return currPosition;
    }
    // this function will increase the size of the hash table by a factor of two
    // and do the rehash of the current elements inside the hash table
    public void rehash( )
    {
    	HashEntry[] oldArray = HashTable;
    	currentSize = 0;
    	HashTable = new HashEntry[2*(oldArray.length)];
    	 for (int i=0; i<oldArray.length; i++)
    	    if (oldArray[i] != null && oldArray[i].isActive){
    	    	insert(oldArray[i].element);
    	    }
    	return;
    }
    
    // a simple hash function for int values
    // the hash value should be a number between 0 and tableSize-1
    // use the mod operator as suggested in class
    public int hash(int value, int tableSize )
    {
    	// FILL IN - DO NOT RETURN -1
    	if(value <0){
    		return -value % tableSize;
    	}
    	else{
    		return value % tableSize;
    	}
    }  
    

    // this function will remove an element from the hash table
    // remember you are not going to remove the element from the hash table (physcially)
    // instead you are supposed to make it inactive
    public void remove( int x )
    {
    	int indexToRemove = find(x);
        
        if(HashTable[indexToRemove].element == x)
     	   HashTable[indexToRemove].isActive = false;
        
        currentSize--;
        
        return;

    }

    // this function finds an element in the hash table
    // x is the value we are looking for
    // This function returns the index in which the value resides
    // if not in the hashtable return -1
    public int find( int x )
    {
    	int indexToReturn = findPosition(x);

		if(HashTable[indexToReturn] !=null && HashTable[indexToReturn].element == x)
			return indexToReturn;
		
	return -1;
    }
    
    
    // DO NOT CHNAGE THIS FUNCTION!
    public String toString(){
    	String toReturn = "";
    	for (int i = 0; i < HashTable.length; i++){
    		if (HashTable[i] == null){
    				toReturn += ("eF ");
    		}else{
    			if (HashTable[i].isActive)
    				toReturn += (HashTable[i].element + "T ");
    			else
    				toReturn += (HashTable[i].element + "F ");
    		}
    	}
    	return toReturn;
    }
    
    
    public static void main(String[] args){
    	
    	
    	// ********************* TESTS FOR LAB ****************************//
    	
    	QuadraticProbingHashTable h1 = new QuadraticProbingHashTable(10);
    	
    	if (!h1.toString().equals("eF eF eF eF eF eF eF eF eF eF "))
    		System.err.print("TEST FAILED: constructor ( 0 )");
    	    	
    	h1.insert(89);
    	h1.insert(58);
    	h1.insert(6);
    	
    	if (!h1.toString().equals("eF eF eF eF eF eF 6T eF 58T 89T "))
    		System.err.println("TEST FAILED: insert ( 1 )");
    	    	
    	h1.insert(16);
    	
    	if (!h1.toString().equals("eF eF eF eF eF eF 6T 16T 58T 89T "))
    			System.err.println("TEST FAILED: insert ( 2 )");
    	
    	h1.insert(9);
    	if (!h1.toString().equals("9T eF eF eF eF eF 6T 16T 58T 89T "))
			System.err.println("TEST FAILED: insert ( 3 )");   
    	
    	QuadraticProbingHashTable h2 = new QuadraticProbingHashTable(7);
    	
    	h2.insert(0);
    	h2.insert(1);
    	h2.insert(2);
    	h2.insert(3);
    	h2.insert(4);
    	h2.insert(5);
    	
    	if (!h2.toString().equals("0T 1T 2T 3T 4T 5T eF eF eF eF eF eF eF eF "))
			System.err.println("TEST FAILED: insert ( 4 )"); 
    	
    	System.out.println("Lab Testing Done!!!");
    	
    	
    	// ********************* TESTS FOR ASSIGNMENT ****************************//
    	
    	QuadraticProbingHashTable h3 = new QuadraticProbingHashTable(11);
    	
    	if (!h3.toString().equals("eF eF eF eF eF eF eF eF eF eF eF "))
			System.err.println("TEST FAILED: insert ( 5 )");    	    	
    	
    	h3.insert(44);    	
    	h3.insert(4);
    	h3.remove(44);
    	
    	if (!h3.toString().equals("44F eF eF eF 4T eF eF eF eF eF eF "))
			System.err.println("TEST FAILED: remove ( 6 )");    	    	
    	
    	h3.insert(77);
    	
    	if (!h3.toString().equals("77T eF eF eF 4T eF eF eF eF eF eF "))
			System.err.println("TEST FAILED: insert ( 7 )");    	    	    	
    	
    	h3.insert(16);    	
    	h3.insert(28);
    	h3.insert(21);    	
    	h3.insert(11);    	
    	h3.insert(22);
    	h3.insert(33);  
    	
    	if (!h3.toString().equals("77T 11T eF 33T 4T 16T 28T eF eF 22T 21T "))
			System.err.println("TEST FAILED: insert ( 8 )");    	    	

    	h3.insert(55);
    	
    	if (!h3.toString().equals("22T eF eF eF 4T eF 28T eF eF eF eF 77T 11T eF eF 33T 16T eF eF eF 55T 21T "))
			System.err.println("TEST FAILED: insert ( 9 )");    	    	    	
    	
    	if (h3.find(4) != 4)
    		System.err.print("TEST FAILED: find ( 10 )");
    	
    	if (h3.find(44) != -1)
    		System.err.print("TEST FAILED: find ( 11 )");
    	
    	if (h3.find(77) != 11)
    		System.err.print("TEST FAILED: find ( 12 )");    
    	
    	System.out.println("Assignment Testing Done!!!");
    	
    }

}