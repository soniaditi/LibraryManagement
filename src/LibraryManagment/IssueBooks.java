package LibraryManagment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.Scanner;




import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class IssueBooks {
	//issue book details
	
	private int srNo;  //primary key
	private  java.sql.Date issueDate ;
	private java.sql.Date returnDate;
	private int fine;
	private Book book ;
	private Student student ;
	
	
	
	public void issueBook() throws InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Book name you want to issue");
		String bName= sc.nextLine().toLowerCase();
		Book book= new Book();
		if(book.searchBooks(bName)==true) {
			System.out.println("Enter the Student name you want to issue the book '"+bName+"'to:");
			String sName= sc.nextLine().toLowerCase();
			Student student = new Student();
			if(student.searchStudent(sName)==true) {
				try {
					DbmsConnection dbmsCon = new DbmsConnection("jdbc:mysql://localhost:3306/Library","root","8237446759");
					Connection con=dbmsCon.getConnection();
					Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = st.executeQuery("select * from bookissue");
					if(rs.next()==false) {
						System.out.println("There is no record in the table yet");
						srNo=1;
					}
					else
					{
						 rs.last();
						 srNo=rs.getRow()+1;
					}
					int quantity = 0;
					Statement st2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					ResultSet rs2 = st2.executeQuery("select NoOfCopies from book where name='"+bName+"'");
					while(rs2.next()) {
						System.out.println(rs2.getInt(1));
						quantity=rs2.getInt(1);
					}
					if((quantity>0)) {
						String sql="UPDATE book SET NoOfCopies = NoOfCopies - 1 WHERE name=?";
						PreparedStatement st1= con.prepareStatement(sql);
						st1.setString(1, bName);
						int i=st1.executeUpdate();
						if(i>0)
							System.out.println("No of copies updated succesfully!");
						long millis=System.currentTimeMillis();  
					    java.sql.Date issueDate=new java.sql.Date(millis);  
					    System.out.println("Issue Date:"+issueDate);  
						String sql2 =" insert into bookissue values(?,?,?,?,?,?)";
						PreparedStatement stmt = con.prepareStatement(sql2);
						stmt.setInt(1, srNo);
						stmt.setString(2,bName);
						stmt.setString(3,sName);
						stmt.setDate(4,issueDate);
						stmt.setDate(5, returnDate);
						stmt.setInt(6, fine);
						stmt.execute();
						System.out.println("Book Issued successfully");
						dbmsCon.closeConnection(con, stmt);
					}
					else
						System.out.println("This book is not available in the library as of now");
						
					
//					
				}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	//user input
	//convert schema to library and to tables
	//toString, getter setter of student and book:done
	
	public IssueBooks(int srNo, String bookName, String studentName,  java.sql.Date issueDate, java.sql.Date returnDate, int fine, Book book,Student student) {
		
		this.srNo=srNo;
		this.issueDate= issueDate;
		this.returnDate=returnDate;
		this.fine=fine;
	}
		
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public void setSrNo(int srNo) {
		this.srNo= srNo;
	}
	public int getSrNo()
	{
		return srNo;
	}
	
//	public void setDateOfIssue(String dateOfIssue) {
//		this.dateOfIssue= dateOfIssue;
//	}
//	public String getDateOfIssue()
//	{
//		return dateOfIssue;
//	}
//	public void setDateOfReturn(String dateOfReturn) {
//		this.dateOfReturn= dateOfReturn;
//	}
//	public String getDateOfReturn()
//	{
//		return dateOfReturn;
//	}
	
	public void setFine(int fine) {
		this.fine= fine;
	}
	public int getFine()
	{
		return fine;
	}
	
	
//	@Override 
//	public String toString() {
//		return "\nIssueBook: [srNo= " + getSrNo() + ", dateOfIssue= " + getDateOfIssue() +", bookName= "  + getBook().getName() + ", studentName= "  + getStudent().getName() + ", dateOfReturn= " + getDateOfReturn() +  ", fine="+ getFine() +"]";
//	}
	IssueBooks() {
			
		}
}


