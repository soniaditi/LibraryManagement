package LibraryManagment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Student {
	private int  rollNo;
	private String name;
	private String  branch;
	private int  semester;
	
	public Student() {
		
	}
	public void getStudentDetails() throws NumberFormatException, IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter Student Roll No:");
		rollNo = Integer.parseInt(br.readLine());
		System.out.println("Enter Name of the Student");
		name=br.readLine();
		name.toLowerCase();
		System.out.println("Enter branch of "+name);
		branch=br.readLine();
		branch.toLowerCase();
		System.out.println("Enter semester of "+name);
		semester = Integer.parseInt(br.readLine());
	}
	

	public void insertStudentDetails() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		try{
			DbmsConnection dbmsCon = new DbmsConnection("jdbc:mysql://localhost:3306/library","root","8237446759");
			Connection con=dbmsCon.getConnection();
			String sql =" insert into student values(?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, rollNo);
			st.setString(2,name);
			st.setString(3,branch);
			st.setInt(4, semester);
			st.execute();
			System.out.println("Record inserted successfully");
			dbmsCon.closeConnection(con, st);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removeStudent(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		try {
		DbmsConnection dbmsCon = new DbmsConnection("jdbc:mysql://localhost:3306/library","root","8237446759");
		Connection con=dbmsCon.getConnection();
//		System.out.println("Enter the name of the student you want to delete");
//		name=sc.nextLine().toLowerCase();
		name.toLowerCase();
		String sql="delete from student where name =?";
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
	
	public boolean searchStudent(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		//Scanner sc = new Scanner(System.in);
		boolean flag= true;
		try {
		DbmsConnection dbmsCon = new DbmsConnection("jdbc:mysql://localhost:3306/library","root","8237446759");
		Connection con=dbmsCon.getConnection();
//		System.out.println("Enter the name of the student you want to search");
//		name=sc.nextLine().toLowerCase();
		name.toLowerCase();
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = st.executeQuery("select * from student where name ='"+name+"'");
		if(rs.next()==false) {
			System.out.println("There is no such record");
			flag=false;
		}
		else
		{	
			flag= true;
			rs.previous();
			while(rs.next()) {
				System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));
			}
		}
		dbmsCon.closeConnection(con, st);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}
	
	public Student(int rollNo, String name, String branch, int semester) {
		this.rollNo = rollNo;
		this.name = name;
		this.branch = branch;
		this.semester = semester;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rollNo;
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
		Student other = (Student) obj;
		if (rollNo != other.rollNo)
			return false;
		return true;
	}

	public void setRollNo(int rollNo) {
		this.rollNo= rollNo;
	}
	public int getRollNo()
	{
		return rollNo;
	}
	
	
	public void setName(String name) {
		this.name= name;
	}
	public String getName()
	{
		return name;
	}
	
	
	public void setBranch(String branch) {
		this.branch= branch;
	}
	public String getBranch()
	{
		return branch;
	}
	
	
	public void setSemester(int semester) {
		this.semester= semester;
	}
	public int getSemester()
	{
		return semester;
	}
	@Override 
	public String toString() {
		return "Student: [rollNo= " + getRollNo() + ", name= " + getName() + ", branch= " + getBranch() + ", semster= " + getSemester() +"]";
  
		
	}
	
}