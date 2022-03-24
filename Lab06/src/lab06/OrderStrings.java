package lab06;

import java.util.Comparator;

public class OrderStrings implements Comparator<String>{
	
	public int compare(String o1, String o2){
	    o1= AnagramUtil.sort(o1);
		o2= AnagramUtil.sort(o2);
		return (o1.compareTo(o2));
	}
}