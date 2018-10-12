

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ascessssql")
public class ascessssql extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ascessssql() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>Employee Details</title>");
		pw.println("</head>");
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
		String dept1="SA_REP";
		String s="select EMPLOYEE_ID,EMAIL,JOB_ID from employees where JOB_ID=?";
		PreparedStatement ps = conne.prepareStatement(s);
		ps.setString(1,dept1 );
		ResultSet rs = ps.executeQuery();
		 while(rs.next()) {
            eid=rs.getInt("EMPLOYEE_ID");
            email1=rs.getString("EMAIL");
            //city=rs.getString("CITY");
           jobid=rs.getString("JOB_ID");
           pw.println("<h1>"+eid+"</h1>"+" "+"<h1>"+email1+"</h1>"+" "+"<h1>"+jobid+"</h1>");
            
		}
		 pw.println("</table>");
	        pw.println("</html></body>");
	        pw.close();
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
