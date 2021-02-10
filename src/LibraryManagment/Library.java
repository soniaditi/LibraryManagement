package LibraryManagment;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
//import java.sql.Connection;


public class Library  {
	//issue books function-> parameter -> stud id , book id
	//return book Function-> book id, stud id
	//private list<Book> collections;
	private static List<Book> booklist = new ArrayList<Book>();
	private static List<String> booklist2 = new ArrayList<String>();
	private static List<Student> studentlist= new ArrayList<Student>();
	private static List<IssueBooks> issueBooks= new ArrayList<IssueBooks>();
	static int count=0;
	//static String databaseName="";
	static String url= "jdbc:mysql://localhost:3306/books" ;
	static String username ="root";
	static String password ="8237446759";
    
	/*
	public Library() {
		List<Book> booklist = new ArrayList<Book>();
		List<Student> studentlist = new ArrayList<Student>();
	}
	
	

	public static void addBook(Book book) {
		booklist.add(book);
		Collections.sort(booklist);
		System.out.println("hhhu");
	
	}
	
	public void addStudent(Student student) {
		
		studentlist.add(student);
		Collections.sort(studentlist,(a,b)->a.getRollNo()>b.getRollNo()?1:-1);
	
	}
	*/
	
//	public static void issuebook(int bookId, int rollNo)   {
//		
//		
//		IssueBooks ib = new IssueBooks();
//		for(int i=0;i<studentlist.size();i++) {
//			Student tempStudent=studentlist.get(i);
//			if(tempStudent.getRollNo()==rollNo) {
//				System.out.printf("\nStudent with rollNo %d is enrolled in the Library\n",rollNo);
//				ib.setStudent(tempStudent);
//			}
//			
//			 
//		}
//		for(int i=0;i<booklist.size();i++) {
//				Book book = booklist.get(i);
//			if(book.getId()==bookId) {
//				if(book.getNoOfCopies()>0) {
//						
//						System.out.printf("\nBook with ID %d is available in the Library ",bookId);
//						Date dateOfIssue = new Date();
//						SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//						System.out.print("\nDate of issue is: ");
//						System.out.println(formatter.format(dateOfIssue));
//						ib.setDateOfIssue(dateOfIssue);
//						count++;
//						ib.setSrNo(count);
//						ib.setBook(book);
//						int j=book.getNoOfCopies();
//						j=j-1;
//						book.setNoOfCopies(j);
//						System.out.println("Book issued succesfully!!");
//						issueBooks.add(ib);
//						System.out.println(issueBooks);
//						
//					}
//				try {//else throw exception
//					if(book.getNoOfCopies()==0) {
//						throw new NoBookAvailable();
//					}
//				}
//				catch(NoBookAvailable e) {
//					e.printStackTrace();
//				}
//				finally {
//					System.out.println("\nList of books available in the library:");
//					System.out.println(booklist);
//				}
//				}
//			}
//			
//		
//	}
	
//	public static void returnBook() {
//		Scanner sc= new Scanner(System.in);
//		System.out.println("Enter the name of the student");
//		String str= sc.nextLine();
//		int n= issueBooks.size();
//		//display all the issued books by the name
//		//which book to return? and return that
//		for(int i=0;i<n;i++) {
//			IssueBooks ib = issueBooks.get(i);
//			String s= ib.getStudent().getName();
//			String s1=new String(s);
//			if(s1.equalsIgnoreCase(str)) {
//				Date dateOfReturn = new Date();
//				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//				System.out.print("\nDate of return is: ");
//				System.out.println(formatter.format(dateOfReturn));
//				ib.setDateOfReturn(dateOfReturn);
//				int j=ib.getBook().getNoOfCopies();       
//				j=j+1;
//				ib.getBook().setNoOfCopies(j);
//				Date dateIssue = ib.getDateOfIssue();
//				long diff = dateOfReturn.getTime() - dateIssue.getTime();
//				System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
//				long diffDays = diff / (24 * 60 * 60 * 1000);
//				int daysdiff = (int) diffDays;
//				if(daysdiff>15) {
//					int result= (daysdiff-15)*10;
//					ib.setFine(result);
//				}
//				System.out.println("Book returned succesfully!!");
//				System.out.println(issueBooks);
//				System.out.println(booklist);
//				
//				
//			}
//		}
//	}
		
	public static void Search() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the book name you want to search");
		String str= sc.nextLine();
		str=str.toLowerCase();
		int n= booklist.size();
		for(int i=0;i<n;i++) {
			Book b = booklist.get(i);
			if(b.getName().contains(str))
				System.out.println(b.getName());
				
		}
//		for(int j=0;j<n;j++) {
//			for (int k= 1; k<= n-j; k++) {
//				 int m = k + j - 1;  
//				 if(booklist.get(j).getName().substring(j, k)==str) {
//					 System.out.println(booklist.get(j).getName());
//				 }
//		            
//			}
//		}
	}


	public static void main(String args[]) throws Exception {
		
//		Book book = new Book();
//		book.getBookDetails();
//		book.insertBookDetails();
		//book.removeBook();
		//book.searchBooks();
		
//		Student student= new Student();
//		student.getStudentDetails();
//		student.insertStudentDetails();
		IssueBooks issueBooks= new IssueBooks();
		issueBooks.issueBook();
//		booklist.add(new Book(1,"math book","rs aggrawal",200,2));
//		booklist.add(new Book(2,"Head first java","rs aggrawal",400,6));
//		booklist.add(new Book(4,"Alchemist","dan brown",200,3));
//		booklist.add(new Book(3,"techmax","amish",200,20));
//		booklist.add(new Book(5,"A brief history of time","stephen hawkings",200,10));
//		Collections.sort(booklist);
//		System.out.println(booklist);
//		
//		/*
//		Iterator<Book> i = booklist.iterator();
//		while(i.hasNext()){
//			System.out.println(i.next());
//			
//		}
//		*/
//		studentlist.add(new Student(6,"Aditi","comp",3));
//		studentlist.add(new Student(4,"Riya","Electronics",5));
//		studentlist.add(new Student(2,"Shreya","Mechanical",3));
//		studentlist.add(new Student(3,"Sourabh","Mechanical",6));
//		studentlist.add(new Student(1,"Mansoor","IT",1));
//		Collections.sort(studentlist, (a,b)->a.getRollNo()>b.getRollNo()?1:-1);
//		
//		System.out.println();
//		Iterator<Student> j = studentlist.iterator();
//		while(j.hasNext()){
//			System.out.println(j.next());
//		}
//		
//		//Search();
//		
////		issuebook(1,4);
////		returnBook();
////		issuebook(1,6);
////		issuebook(1,3);
////		Search();
//	
//		
//		/*
//		Set<Book> bookset = new HashSet<Book>();
//		bookset.add(new Book(1,"techmax","amish",200,20));
//		//bookset.add(new Book(3,"techmax","amish",200,20));
//		System.out.println(bookset.hashCode());
//		
//		Set<Book> booksetb = new HashSet<Book>();
//		booksetb.add(new Book(1,"techmax","amish",200,20));
//		System.out.println(booksetb.hashCode());
//		System.out.println(booksetb.equals(bookset));
//		*/
	}
}

class NoBookAvailable extends Exception{
	
	NoBookAvailable(){
		super("\nThe book you requested is not available in the library right now. Please issue another one or come again later..");
	}
}