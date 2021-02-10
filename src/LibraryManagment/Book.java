package LibraryManagment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;



public class Book implements Comparable<Book> {
	private int  id;
	private String name;
	private String author;
	private int  price;
	private int  noOfCopies;
	
	public Book(int id, String name, String author, int price, int noOfCopies) {
		
		this.id=id;
		this.name= name;
		this.author=author;
		this.price=price;
		this.noOfCopies=noOfCopies;
		
	}
	
	public void getBookDetails() throws NumberFormatException, IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter Book ID:");
		id = Integer.parseInt(br.readLine());
		setId(id);
		System.out.println("Enter Name of the Book");
		name = br.readLine();
		name.toLowerCase();
		System.out.println("Enter Price of the Book:");
		price = Integer.parseInt(br.readLine());
		System.out.println("Enter Author name of "+name);
		author =  br.readLine();
		author.toLowerCase();
		System.out.println("Enter no Of Copies available for "+name);
		noOfCopies = Integer.parseInt(br.readLine());
		setNoOfCopies(noOfCopies);
		
		
	}
	
//	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//	    System.out.println("Enter Employee ID:");
//	    int id = Integer.parseInt(br.readLine());
//
//	    System.out.println("Enter Employee Name:");
//	    String name = br.readLine();
//
//	    System.out.println("Enter Employee Salary:");
//	    double salary = Double.parseDouble(br.readLine());
//
//	    stmt.executeUpdate("insert into Employee values("+id+ ", '"+name+"',"+salary+")");
//	                                                      =// now record inserted (of Insert Record JDBC Keyboard Input)
//	    System.out.println(name + " record inserted");
//
//	
	public void insertBookDetails() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DbmsConnection dbmsCon = new DbmsConnection("jdbc:mysql://localhost:3306/library","root","8237446759");
		Connection con=dbmsCon.getConnection();
		String sql =" insert into book values(?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		st.setString(2,name);
		st.setString(3,author);
		st.setInt(4, price);
		st.setInt(5, noOfCopies);
		st.execute();
		System.out.println("Record inserted successfully");
		dbmsCon.closeConnection(con, st);
	}
	
	public void removeBook(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		//Scanner sc = new Scanner(System.in);
		try {
		DbmsConnection dbmsCon = new DbmsConnection("jdbc:mysql://localhost:3306/library","root","8237446759");
		Connection con=dbmsCon.getConnection();
//		System.out.println("Enter the name of the book you want to delete");
//		name=sc.nextLine().toLowerCase();
		name.toLowerCase();
		String sql="delete from books where name =?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, name);
		int i = st.executeUpdate();
		if(i>0)
			System.out.println("Deleted successfully");
		else
			System.out.println(" No such record was found");
		dbmsCon.closeConnection(con, st);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void updateRecord(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		try{
			DbmsConnection dbmsCon2 = new DbmsConnection("jdbc:mysql://localhost:3306/library","root","8237446759");
			Connection con2=dbmsCon2.getConnection();
			String sql="UPDATE books SET `No. Of Copies` = `No. Of Copies` - 1 WHERE Id=?";
			PreparedStatement st= con2.prepareStatement(sql);
			st.setInt(1, id);
			int i=st.executeUpdate();
			if(i>0)
				System.out.println("No of copies updated succesfully!");
		dbmsCon2.closeConnection(con2, st);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean searchBooks(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		//Scanner sc = new Scanner(System.in);
		boolean flag=true;
		try {
		DbmsConnection dbmsCon = new DbmsConnection("jdbc:mysql://localhost:3306/library","root","8237446759");
		Connection con=dbmsCon.getConnection();
//		System.out.println("Enter the name of the book you want to search");
//		name=sc.nextLine().toLowerCase();
		name.toLowerCase();
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = st.executeQuery("select * from book where name ='"+name+"'");
		if(rs.next()==false) {
			System.out.println("There is no such record");
			flag= false;
		}
			
		else
		{	
			flag= true;
			rs.previous();
			while(rs.next()) {
				System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5));
			}
		}
		dbmsCon.closeConnection(con, st);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return flag;
		
	}
	public Book() {
		
	}
	public void setId(int id) {
		this.id= id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

	public int getId()
	{
		return id;
	}
	
	public void setName(String name) {
		this.name= name;
	}
	public String getName()
	{
		return name;
	}
	
	public void setAuthor(String author) {
		this.author= author;
	}
	public String getAuthor()
	{
		return author;
	}
	
	public void setPrice(int  price) {
		this.price= price;
	}
	public int getPrice()
	{
		return price;
	}
	
	public void setNoOfCopies(int  noOfCopies) {
		this.noOfCopies =noOfCopies;
		
	}
	public int getNoOfCopies()
	{
		return noOfCopies;
	}
	
	@Override 
	public String toString() {
		return "\nBook: [id= " + getId() + ", name= " + getName() + ", author= " + getAuthor() + ", price= " + getPrice() + ", noOfCopies=" +getNoOfCopies()+"]";
		
		
	}
	
	@Override
	public int compareTo(Book b) {
		
		return id>b.id?1:-1;
	}
	
	
}	
 class magazine extends Book{
	 

	private int noOfAllowedIssueDays;
	private int year;
	private String genre; 
	
	
}
 class Journals extends Book{
	
	 private int volume;
	
}

 class Newspaper extends Book{
	 
	
	 private int bill;
	
}

 class RefrenceBooks extends Book{
	
	

	int noOfAllowedIssueDays;
	int referenceId;
	int edition;
	String isbn;
	String fieldOfStudy;
	
	
}

//toString in class: issuebooks, library and books: done
//exception handling and return type in overriding
//read about super 
//study about: collection- 2 topics, & numbers and strings

	
	
	

