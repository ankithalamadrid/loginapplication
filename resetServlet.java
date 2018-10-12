

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/resetServlet")
public class resetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public resetServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("UserName");
		String password=request.getParameter("Password");
		employee e=new employee();
		employeeDao edao=new employeeDao();
		e.setUserName(username);
		e.setPassword(edao.encryptPassword(password));
		boolean ifReset=true;
		try {
			ifReset=edao.resetPassword(e);
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		if(ifReset) {
			RequestDispatcher rd = 	request.getRequestDispatcher("success.html");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = 	request.getRequestDispatcher("userNotExist.jsp");
			rd.forward(request, response);
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
