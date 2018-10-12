

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewUser")
public class viewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public viewUser() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		employeeDao emp = new employeeDao(); 
    	ArrayList<employee> list = new ArrayList<>();
    	
    			try {
					list = emp.view_user();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
    			PrintWriter out = response.getWriter();
    			response.setContentType("text/html");
    			out.println("<html><head><title>User Records</title>");
    			out.println("<style>");
    			out.println("table.timecard {\r\n" + 
    					"	margin: auto;\r\n" + 
    					"	width: 600px;\r\n" + 
    					"	border-collapse: collapse;\r\n" + 
    					"	border: 1px solid #fff;\r\n" + 
    					"	border-style: hidden;\r\n" + 
    					"}");
    			out.println("td.even {\r\n" + 
    					"	background-color: #fde9d9;\r\n" + 
    					"}");
    			out.println("out.println(\"body {\\r\\n\" + \r\n" + 
    					"    					\"	background-color: #f6f6ff;\\r\\n\" + \r\n" + 
    					"    					\"	font-family: Calibri, Myriad;\\r\\n\" + \r\n" + 
    					"    					\"}\");");
    			
    			out.println("td.odd {\r\n" + 
    					"	background-color: #0000ff;\r\n" + 
    					"}");
    			out.println("</style>");
    			
    			
    			out.println("</head><body>");
    			out.println("<table class=timecard>");
    			out.println("<tr class=first><th>Username</th><th>first name</th><th>last name</th><th>email</th><th>dept</dept></tr>");
    			
    			 for(employee al : list) {
    				 
    				 out.println("<tr>");
    				 out.println("<td class=even>");out.println(al.getUserName());out.println("</td>");
    				 out.println("<td class=odd>");out.println(al.getFirstName());out.println("</td>");
    				 out.println("<td class=even>");out.println(al.getLastName());out.println("</td>");
    				 out.println("<td class=odd>");out.println(al.getEmail());out.println("</td>");
    				 out.println("<td class=even>");out.println(al.getDept());out.println("</td>");
    	                out.println("\n");
    	                out.println("</tr>");
    			 }
    			 out.println("</table>");
    			 out.println("</body>");
    			 out.println("</html>");
    			 
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
