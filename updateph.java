

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateph")
public class updateph extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public updateph() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		String url = "jdbc:oracle:thin:@192.168.18.40:1521:ascend";
		String user ="arkumar";
		String password ="arkumar";
		try {
			Connection conne = DriverManager.getConnection(url,user,password);
		
		int eid;
		String email1,city,jobid;
		String dept1="IT_PROG";
		String ph="654.325.258";
		String s="update employees set phone_number=? where job_id=?";
		PreparedStatement ps = conne.prepareStatement(s);
		ps.setString(1,ph );
		ps.setString(2,dept1);
		ps.executeQuery();
		
		
	    
		ps.close();
		conne.close();
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
