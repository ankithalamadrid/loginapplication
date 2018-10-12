/*import java.io.*;*/
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/uploadUser")
public class uploadUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public uploadUser() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("file");
		System.out.println(name);
		employeeDao e1=new employeeDao();
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		try {
			e1.csv_upload(name);
			pw.println("<h1>Successfully Inserted</h1>");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
