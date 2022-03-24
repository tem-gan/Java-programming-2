package lab06;

public class Test {
	
		
	
	
		
		public static void main(String[] args) {
			 AnagramUtil test = new AnagramUtil();
			  String test1 = "Tem";
			  String test2 = "meT";
			  String test3 = "Magi";
					  
				System.out.println(test.sort(test1));
				System.out.println(test.areAnagrams(test1, test2));
				System.out.println(test.areAnagrams(test1, test3));
				
				OrderStrings tester = new OrderStrings();
				System.out.println(tester.compare(test1, test3));
				
				String[] testing= {"Tem", "James", "Nick"}; 
				test.insertionSort(testing);
				String output= "";
				for (int i=0; i<testing.length; i++){
					output += testing[i]+ " ";
				}
				System.out.println(output);
				
				AnagramUtil tester4 =  new AnagramUtil();
				String[] testing2= {"Tem", "Kalina", "Amanda", "meT", "etm"}; 
				String[] words_group = tester4.getLargestAnagramGroup(testing2);
				String output3 = "";
				for (int i=0; i<words_group.length; i++){
					output3 += words_group[i]+ " ";
				}
				System.out.println(output3);
				
				String[] testing3= {""}; 
				String[] words_group2 = tester4.getLargestAnagramGroup(testing3);
				String output4 = "";
				for (int i=0; i<words_group2.length; i++){
					output4 += words_group2[i]+ " ";
				}
				System.out.println(output4);
				
				AnagramUtil tester5 =  new AnagramUtil();
				
				String[] words_copy = tester5.getLargestAnagramGroup("sample_word_list.txt");
				String output2 = "";
				for (int i=0; i<words_copy.length; i++){
					output2 += words_copy[i]+ " ";
				}
				System.out.println(output2);
				
	    }
		}	

