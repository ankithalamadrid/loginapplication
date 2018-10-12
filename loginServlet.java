
//import java.sql.*;
import java.io.*;
//import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log=Logger.getLogger("LogFile");
   
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//employee e =  new employee();
		response.setContentType("text/html");
		String pass2="";
		employeeDao edao=new employeeDao();
		//PrintWriter pw = response.getWriter();
		String username = request.getParameter("UserName");
		System.out.println(username);
		String pass = request.getParameter("Password");
		pass2 = edao.encryptPassword(pass);
		System.out.println("pass2="+pass2);
		String pass1="";
		String dept="";
		
		System.out.println("before dao");
	
		try {
			pass1 = edao.getPassword1(username);
			System.out.println("pass1="+pass1);
			
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println(pass1);
		try {
			dept = edao.getDept(username);
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		HttpSession session = request.getSession();
		if(pass2.equals(pass1)) {
			session.setAttribute("user", username);

			log.info("Logging in,username :"+username+" ");
			System.out.println("password verified");
			if(dept.equals("IT")) {
				System.out.println("admin");
				session.setAttribute("user", username);
			//	HttpSession session = request.getSession();
		        RequestDispatcher rd=request.getRequestDispatcher("/adminPage.jsp");
		        rd.forward(request, response);
			}
			else
			{
				session.setAttribute("user", username);
				RequestDispatcher rd=request.getRequestDispatcher("/userPage.jsp");
				rd.forward(request, response);
			}
			
			
		}
		else
		{
			
			session.invalidate();
            request.setAttribute("errorMessage", "Invalid user or password");
			RequestDispatcher rd=request.getRequestDispatcher("loginForm.html");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
