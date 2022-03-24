package lab04;
import java.util.GregorianCalendar;
// What imports do you need to include? Put them here.

public class LibraryBook<Type> extends Book { 

	 Type holder;				// A LibraryBook contains a holder (a String) and due date represented by
	 GregorianCalendar dueDate;// a GregorianCalendar
	
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		
	}

	public Type getHolder() {
		
		return this.holder; // placeholder
	}
	
	public GregorianCalendar getDueDate() {
		
		return this.dueDate; // placeholder
	}
	
	public void checkin() {
		this.holder = null;
		this.dueDate = null;
		
	}
	
	public void checkout(Type holder, GregorianCalendar dueDate){
		// FILL IN
		this.holder =  holder;
		this.dueDate = new GregorianCalendar(dueDate.get(GregorianCalendar.YEAR),dueDate.get(GregorianCalendar.MONTH),
				dueDate.get(GregorianCalendar.DATE));
	}	

	// Do not override the equals method in Book

}