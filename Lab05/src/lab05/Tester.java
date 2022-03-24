package lab05;

public class Tester {

	public static void main(String[] args) {
		BinarySearchSet testerList = new BinarySearchSet(); 
		System.out.println("\nAfter inserting 4 number");
		System.out.println("If list is empty:");
	      System.out.println(testerList.size());
		  System.out.println(testerList.isEmpty());
		  System.out.println("\nAfter inserting 4 number");
		  testerList.insert(77);
		    testerList.insert(2);
		    
		    testerList.insert(99);
		   
		    testerList.insert(15);
		    System.out.println(testerList.size());
			  System.out.println(testerList.isEmpty());
			  System.out.println(testerList.toString());
			  System.out.println("\nTesting grow, adding 8 number");
			  testerList.insert(1);
			  testerList.insert(3);
			  testerList.insert(102);
			  testerList.insert(300);
			  System.out.println(testerList.toString());
			  System.out.println("\nTesting binaryfind");
			  System.out.println(testerList.findIndex(3));
			  System.out.println("\nTesting remove");
			  System.out.println(testerList.remove(3));
			  System.out.println(testerList.contains(2));
			  System.out.println(testerList.toString());
			  System.out.println("\nTesting clear");
			  testerList.clear();
			  System.out.println(testerList.isEmpty());
			  System.out.println(testerList.toString());
	}
}
