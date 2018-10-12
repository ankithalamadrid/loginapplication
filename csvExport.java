

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/csvExport")
public class csvExport extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public csvExport() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	employeeDao em1=new employeeDao();
	try {
		em1.export_csv();
	} catch (Exception e) {
		e.printStackTrace();
	}
	File file = new File("C:\\Users\\arkumar\\Desktop\\test.csv");
	Desktop desktop = Desktop.getDesktop();
	if(file.exists()) desktop.open(file);
	/*RequestDispatcher rd = 	request.getRequestDispatcher("userPage.jsp");
	rd.forward(request, response);*/
	
	PrintWriter out = response.getWriter();
	response.setContentType("text/html");
	out.println("<h1>CSV Exported successfully</h1>");
	out.println("<h2>press back to go to your previous page</h2>");
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
