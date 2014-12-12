package ie.gmit;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;

public class Servlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 777L;

	
	public Servlet() {
		super();
		Scheduler.getInstance();
		// TODO Auto-generated constructor stub
	}
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
											throws ServletException, IOException {
		
		Gson gson = new Gson();
		
		//String max = request.getParameter("max");
		int max = Integer.valueOf(request.getParameter("max"));
		int jobNum = Integer.valueOf(request.getParameter("jobNumber"));
		
		String [] responseArray = new String[] {"",""};

		response.setContentType("application/json");  // Set content type of the response so that jQuery knows what it can expect.
		
		if(jobNum == 0) {
			System.out.println("in 0");
			int jNum=FibonacciService.add(max);
			responseArray[0] = "0";
			responseArray[1] = String.valueOf(jNum);
			String json  = gson.toJson(responseArray);
			response.getWriter().write(json);
		}
		
		else {
			
			String result = null;
			FibonacciService client = new FibonacciService();
			result = client.getRequest(jobNum);
			System.out.println(result);
			if (result !=null)
			{
				
				System.out.println("in result");
				responseArray[0] = result;
				responseArray[1] = String.valueOf(jobNum);
				String json = gson.toJson(responseArray);
				response.getWriter().write(json);	
			}
			else
			{
				System.out.println("in no result");
				responseArray[0] = "0";
				responseArray[1] = String.valueOf(jobNum);
				String json = gson.toJson(responseArray);
				response.getWriter().write(json);	
			}
			
		}		
	}


	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML>");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

}
