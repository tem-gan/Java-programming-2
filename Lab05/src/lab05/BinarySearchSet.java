package lab05;

public class BinarySearchSet {
	/** represent the simple array that holds the list values */
	public double[] storage;
	/** holds the length of the storage array */
	private int capacity;
	/** holds the number of elements in the list */
	private int size;

	/** keep this TRUE for lab **/
	public boolean labFind = false;

	/** default constructor */
	public BinarySearchSet(){
		capacity = 6;
		storage = new double[capacity];
		size = 0;
	}

	public BinarySearchSet(double[] input){
		for (int i = 0; i < input.length; i++) {
			insert(input[i]);
		}
	}

	public boolean isEmpty(){
		
		if(size == 0) {
			return true;
		}
		return false;
	}

	public int size(){
		return size;
	}

	public void grow(){
		double[]grow = new double[capacity*2];
		for(int i=0; i<capacity; i++) {
			grow[i]=storage[i];
		}
		storage = grow;
		capacity =capacity*2;
	}

	public String toString(){
		String elements = "The elemnents are:";
		for(int i=0; i<size; i++) {
			elements +=storage[i]+ " ";
		}
		return elements + "\ncapacity: "+ capacity+ "\nsize: "+size;
	}

	public void clear() {
		double[] empty= new double[capacity];
		storage=empty;
		size=0;
	}

	public boolean insert(double newVal){
		if(size+1 == capacity) {
			grow();
		}
		int index = findIndex(newVal);
		if(index < 0) {
			index = Math.abs(index)-1;
			double val = newVal;
			for(int i = index; i < storage.length; i++){
				double temp = storage[i];
				storage[i] = val;
				val = temp;
			}
			size++;
			return true;
		}
		return false;
	}
	public boolean remove(double element){
		int erase = findIndex(element);

		if (storage[erase] != element) {
			return false;

		}
		for (int i = erase; i < size - 1; i++) {

			storage[i] = storage[i + 1];

		}

		size--;
		return true;
	}


	private int sequentialFind(double target) {
		for(int i = 0; i < capacity; i++) {
			if(storage[i] == target) {
				return i;
			}else if(target < storage[i] || storage[i] == 0.0){
				return (-i-1);
			}
		}
		return 0; //placeholder
	}

	private int binaryFind(double target) {
		
		int first, last;
		first=0;
		last=size-1;
		int mid=(size-1)/2;
		while(first<=last) {
			
			if(storage[mid]==target) {
				return mid;
			}else if(storage[mid]<target) {
				first=mid+1;
			}else{
				last=mid-1;
			}
		}
		return mid;
		
	}

	public int findIndex(double target) {
		if (labFind) {
			return binaryFind(target);
		} else {
			return sequentialFind(target);
		}
	}

	public boolean containsAll(double[] elements){
		for (double i: elements) {
			 if(contains(i) == false)
				 return false;
		 }	 
			return true;
	}

	public boolean contains(double element){
		int min = 0;
		int max = size - 1;
		int mid = (max + min) / 2;
		while (min <= max) {
			
			mid = (max + min) / 2;
			
			if (storage[mid] == element) {
				return true;
			} else if (storage[mid] > element) {
				max = mid - 1;

			} else { 
				min = mid + 1;
			}
		}
		return false;
	}

}
