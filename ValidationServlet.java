

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/ValidationServlet")
public class ValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ValidationServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		employee e =  new employee();
		employeeDao e1 = new employeeDao();
		String username = request.getParameter("UserName");
		String first_name = request.getParameter("FirstName");
		String last_name = request.getParameter("LastName");
		String password1 = request.getParameter("Password");
		String email = request.getParameter("Email");
		String dept = request.getParameter("dept");
		
		System.out.println(password1);
		String password = e1.encryptPassword(password1);
		
		e.setUserName(username);
		e.setPassword(password);
		e.setEmail(email);
		e.setDept(dept);
		e.setFirstName(first_name);
		e.setLastName(last_name);
		
		/*boolean ifUserNameExists;*/
		
		response.setContentType("text/html");	
				
				
				try {
					e1.insertEmployee(e);
				RequestDispatcher rd = 	request.getRequestDispatcher("loginForm.html");
				rd.forward(request, response);
				} catch (Exception e2) {
					e2.printStackTrace();
				}	
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
