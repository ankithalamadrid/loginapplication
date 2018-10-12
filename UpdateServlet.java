

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSession;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		employee e=new employee();
		//HttpSession session=request.getSession();
		
		String username =request.getParameter("username");
		String first_name = request.getParameter("FirstName");
		String last_name = request.getParameter("LastName");
		//String password = request.getParameter("Password");
		String email = request.getParameter("Email");
		String dept = request.getParameter("dept");
		
		System.out.println(username);
		//employeeDao edao=new employeeDao();
		e.setUserName(username);
	//	e.setPassword(edao.encryptPassword(password));
		e.setEmail(email);
		e.setDept(dept);
		e.setFirstName(first_name);
		e.setLastName(last_name);
		
		response.setContentType("text/html");	
				
				employeeDao e1 = new employeeDao();
				try {
					e1.updateUser(e);
					System.out.println("User Updated Successfully");
					HttpSession session=request.getSession();
					request.setAttribute("updateMessage", "User Updated Successfully");
					session.setAttribute("user", username);
				RequestDispatcher rd = 	request.getRequestDispatcher("userPage.jsp");
				rd.forward(request, response);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
