//import java.io.PrintWriter;
import java.io.*;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
//import java.sql.Statement;
//import java.util.*;

public class employeeDao{
	
//Connection to the DB
	public static Connection getConn() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.18.40:1521:ascend";
		String user ="arkumar";
		String password ="arkumar";
		Connection conne = DriverManager.getConnection(url,user,password);
		return conne;
		
		
	}
	
	//closing the connection
	public static void closeConn(Connection connection) throws Exception {
		connection.close();
	}
	
	//Inserting the Employee into the db,From the registration page
	public void insertEmployee(employee e) throws Exception{
		Connection con = getConn();
		String s= "insert into USERTABLE values ( ? , ? , ? , ? , ?, ? )";
		PreparedStatement ps = con.prepareStatement(s);
		ps.setString(1, e.UserName);
		ps.setString(2, e.FirstName);
		ps.setString(3, e.LastName);
		ps.setString(4, e.Password);
		ps.setString(5, e.Email);
		ps.setString(6, e.Dept);
		ps.executeUpdate();
		
		ps.close();
		closeConn(con);
		
		
	}
	
	
	//for matching the passwords entered during the login and the one into the database
	//this is fetched and then encrypted then matched with the entered password
	public String getPassword1(String username)throws Exception{
		Connection con = getConn();
		String password="";
		String s="select password1 from USERTABLE where USERNAME=?";
		PreparedStatement ps = con.prepareStatement(s);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		 if(rs.next()) {
            password = rs.getString("password1");
		}
		ps.close();
		closeConn(con);
		System.out.println(password);
		return password;
		}
	
	//getting the department according to the username
	//this helps in determining if the user is an admin or not
	public String getDept(String username)throws Exception{
		Connection con = getConn();
		String dept="";
		String s="select dept from USERTABLE where USERNAME=?";
		PreparedStatement ps = con.prepareStatement(s);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
            dept = rs.getString("dept");
		}
		ps.close();
		closeConn(con);
		return dept;
		
		}
	
	//Updating the user from the after-login page
	public void updateUser(employee e) throws Exception {
		Connection con = getConn();
		String s="update USERTABLE set FIRSTNAME=?,LASTNAME=?,EMAIL=?,DEPT=? WHERE USERNAME=?";
		employee e1=new employee();
		e1=updateDefaultUserValue(e.UserName);
		PreparedStatement ps = con.prepareStatement(s);
		if(!e.FirstName.isEmpty()) {
		ps.setString(1, e.FirstName);
		}
		else {
			ps.setString(1, e1.FirstName);
		}
		System.out.println("e firstname:"+e.FirstName);
		System.out.println("el firstname:"+e1.FirstName);
		if(!e.LastName.isEmpty()) {
		ps.setString(2, e.LastName);
		}
		else {
			ps.setString(2, e1.LastName);
		}
	/*	if(!e.Password.isEmpty()) {
		ps.setString(3, e.Password);
		}
		else {
			ps.setString(3, e1.Password);
		}*/
		if(!e.Email.isEmpty()) {
		ps.setString(3, e.Email);
		}
		else {
			ps.setString(3, e1.Email);
		}
		if(!e.Dept.isEmpty()) {
		ps.setString(4, e.Dept);
		}
		else {
			ps.setString(4, e1.Dept);
		}
		ps.setString(5, e.UserName);
		ps.executeUpdate();
		
		ps.close();
		closeConn(con);
		
		
		
	}
	
//encrypt the password to store in the db
//the same key is used for enc and dec as well
	//key is hard coded as abcdefg
	
public String encryptPassword(String password) {
	String password1="";
	String strKey="abcdefg";
	try {
		SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
		Cipher cipher=Cipher.getInstance("Blowfish");
		cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
		byte[] encrypted=cipher.doFinal(password.getBytes());
		password1=new String(encrypted);
		
	} catch (Exception e) {
		e.printStackTrace();
		//throw new Exception(e);
	}
	return password1;
}

//decryption of the password from the Db
public static String decrypt(String passwordFromDB) throws Exception{
	String decryptPass="";
	String strKey="abcdefg";
	
	try {
		SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
		Cipher cipher=Cipher.getInstance("Blowfish");
		cipher.init(Cipher.DECRYPT_MODE, skeyspec);
		byte[] decrypted=cipher.doFinal(passwordFromDB.getBytes());
		decryptPass=new String(decrypted);
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new Exception(e);
	}
	return decryptPass;
}

public boolean resetPassword(employee e) throws Exception{
	Connection con = getConn();
	boolean isUser=true;
	String s= "update USERTABLE set password1=? WHERE USERNAME=?";
	PreparedStatement ps = con.prepareStatement(s);
	ps.setString(1, e.Password);
	ps.setString(2, e.UserName);
	isUser=checkForDuplicateUser(e.UserName);
	if(isUser) {
	ps.executeUpdate();
	return true;
	}
	
	ps.close();
	closeConn(con);
	return false;	
}


//inserting the users via a csv file
//password is generated via the genPassword() function
//username is  generated via the genUser() method
public boolean csv_upload(String file)throws Exception{
	Connection con = getConn();
	FileReader fr = new FileReader(file);
	BufferedReader in = new BufferedReader(fr);
	//String line = in.readLine();
	String line="";
	String[] field1;
	
	
	while ((line = in.readLine()) != null) {
	    field1 = line.split(",");
	    String fname=field1[0];
		String lname=field1[1];
		String email=field1[2];
		String dept=field1[3];
		String username=genUserName(fname,lname);
		String password1=genpassword();
		String password=encryptPassword(password1);
		String s= "insert into USERTABLE(username,firstname,lastname,password1,email,dept) values ('"+username+"','"+fname+"','"+lname+"','"+password+"','"+email+"','" +dept+"')";
		//String s= "insert into USERTABLE(username,firstname,lastname,password1,email,dept) values ( ? , ? , ? , ? , ?, ? )";
		Statement stmt=con.createStatement();
		stmt.executeUpdate(s);
//		PreparedStatement ps = con.prepareStatement(s);
//		ps.setString(1, username);
//		ps.setString(2, fname);
//		ps.setString(3, lname);
//		ps.setString(4, password);
//		ps.setString(5, email);
//		ps.setString(6, dept);
//		ps.executeUpdate();
		
		
		//Statement stmt=con.createStatement();
		//stmt.executeUpdate(s);
		
		
	}
	//String fname,lname,email,dept,username,password;
	//stmt.close();
	in.close();
	con.close();
	return true;
}
public ArrayList<employee> view_user() throws Exception {
	Connection con = getConn();
	ArrayList<employee> al = new ArrayList<>();
	String s="select username,firstname,lastname,email,dept from usertable";
	Statement stmt=con.createStatement();
	ResultSet rs = stmt.executeQuery(s);
	//String FIRST,LAST,EMAIL,phone;
    
    while(rs.next()) {
    		employee e = new employee();
    		e.setUserName(rs.getString("username"));
    	    e.setFirstName(rs.getString("firstname"));
    	    e.setLastName(rs.getString("lastname"));
    	    e.setEmail(rs.getString("email"));
    	    e.setDept(rs.getString("dept"));
    	   
    	   al.add(e);
    	   
    }
    rs.close();

    closeConn(con);
    
    
    return al;   
}
//select previous user values in order to not to set null values while updating
//from the normal user tab
public employee updateDefaultUserValue(String username) throws Exception {
	Connection con = getConn();
	String s="select username,firstname,lastname,email,dept from usertable where username=?";
	
	PreparedStatement ps = con.prepareStatement(s);
	ps.setString(1, username);
	ResultSet rs = ps.executeQuery();
	employee e = new employee();
    while(rs.next()) {
    		
    		e.setUserName(rs.getString("username"));
    	    e.setFirstName(rs.getString("firstname"));
    	    e.setLastName(rs.getString("lastname"));
    	    e.setEmail(rs.getString("email"));
    	    e.setDept(rs.getString("dept"));
    	   
    	   
    	   
    }
    rs.close();

    closeConn(con);
    
    System.out.println(e.getUserName());
   return e;   
}
//exporting the db in form of a csv file
public void export_csv()throws Exception {
	Connection con = getConn();
	String filepath = "C:\\Users\\arkumar\\Desktop\\test.csv";
	//PrintWriter pw = new PrintWriter(new File(""));
	FileWriter fw = new FileWriter(filepath);
	
   // StringBuilder sb = new StringBuilder();
	//String line = in.readLine();
    String username,firstname,lastname,email,dept;
	String s="select * from USERTABLE";
	//PreparedStatement ps = con.prepareStatement(s);
	Statement stmt=con.createStatement();
	ResultSet rs = stmt.executeQuery(s);
	while(rs.next()) {
        username = rs.getString("username");
        fw.append(username);
        fw.append(",");
        firstname = rs.getString("firstname");
        fw.append(firstname);
        fw.append(",");
        lastname=rs.getString("lastname");
        fw.append(lastname);
        fw.append(",");
        /*password1=rs.getString("password1");
        fw.append(password1);
        fw.append(",");*/
        email=rs.getString("email");
        fw.append(email);
        fw.append(",");
        dept=rs.getString("dept");
        fw.append(dept);
        fw.append("\n");
        
	}
	fw.close();
	closeConn(con);
	
	
}
//generating usernames according to first names and last names
public String genUserName(String fname,String lname)throws Exception{
	String username="";
	char f=fname.charAt(0);
	int i;
	username=username+f;
	for(i=0;i<lname.length();i++)
	{
	username=username+lname.charAt(i);
	if(username.length()==8) {
		break;
	
	}
	
	}
	boolean flag=false;
	//if same user is already there then append some integer 
	flag=checkForDuplicateUser(username);
	if(flag) {
		Random rand = new Random(); 
		int value = rand.nextInt(50);
		username+=value;
	}
return username;
	
}
//check if a username already exists or not
public boolean checkForDuplicateUser(String username) throws Exception {
	Connection con = getConn();
	String s="select email from USERTABLE where USERNAME=?";
	PreparedStatement ps = con.prepareStatement(s);
	ps.setString(1, username);
	ResultSet rs = ps.executeQuery();
	if(rs.next()) {
        return true;
	}
	ps.close();
	closeConn(con);
	return false;
	
}
//function to gen random password from the admin page
//
private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
private static String genpassword() {
	
	//private int RANDOM_STRING_LENGTH = 10;
	StringBuffer randStr = new StringBuffer();
    for(int i=0; i<10; i++){
        int number = getRandomNumber();
        char ch = CHAR_LIST.charAt(number);
        randStr.append(ch);
    }
    return randStr.toString();
}
private static int getRandomNumber() {
    int randomInt = 0;
    Random randomGenerator = new Random();
    randomInt = randomGenerator.nextInt(CHAR_LIST.length());
    if (randomInt - 1 == -1) {
        return randomInt;
    } else {
        return randomInt - 1;
    }
}

	
}