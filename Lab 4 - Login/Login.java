package lab4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/lab4/Login")
public class Login extends HttpServlet{
	private static final long serialVersionUID= 1L;

	private String getUsersName( HttpServletRequest request ){
		Cookie[] cookies = request.getCookies();
		if ( cookies != null )
			for (Cookie cookie : cookies)
				if ( cookie.getName().equals("name") )
					return cookie.getValue();
		return null;
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		List<Lab4User> registeredUsers = new ArrayList<Lab4User>();
		registeredUsers.add(new Lab4User(1, "John Doe", "john","abcd"));
		registeredUsers.add(new Lab4User(2, "Mary Jane", "mary","abcd"));
		registeredUsers.add(new Lab4User(3, "Joe Boxer", "joe","abcd"));
		getServletContext().setAttribute("registeredUsers",registeredUsers);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");			
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Login</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div align = \"center\" margin = \"auto\">");
		out.println("<h1>User Login</h1>");
		out.println("<form action=\"Login\" method=\"post\">");
		out.println("Enter username: " + "<input name =\"name\" id=\"userName\" type=\"text\"> <br>");
		out.println("Enter password: " + "<input name =\"passwords\" id=\"inputedPassword\" type=\"password\"> <br>");
		out.println("<input type = \"checkbox\" name = \"checked\"> Remember Me <br>");
		out.println("<button type=\"submit\">Login </button> <br>");
		out.println("<a href = \" \" class = \"forgot\">" + "Forgot Password" + "</a>");
		out.println("</form>");
		out.println("</div>");
		out.println("</body></html>");

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String userName = request.getParameter("name");
		String password = request.getParameter("passwords");
		ArrayList<Lab4User> user = (ArrayList<Lab4User>) getServletContext().getAttribute("registeredUsers");
		for(Lab4User userInput: user){
			if(userName.equals(userInput.getUsername()) && password.equals(userInput.getPassword())){
				
				String userNames = getUsersName(request);

				if (userNames == null){
					userNames = request.getParameter("name");
					if(request.getParameterValues("checked") != null){
					Cookie usernames = new Cookie("name", userNames);
					response.addCookie( usernames );
				}
					HttpSession session = request.getSession();
					request.getSession().setAttribute("user", userInput);
				}
				
				response.sendRedirect("welcomePage");
				return;

			}
		/*	else{
				request.setAttribute("Error", "Invalid username and/or password");
				doGet(request, response);
			}*/
		}
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");			
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Login</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div align = \"center\" margin = \"auto\">");
		out.println("<h1>User Login</h1>");
		out.println("<h3 class = \"text-danger\"> Invalid userName and/or password <br> TRY AGAIN </h3> ");
		out.println("<form action=\"Login\" method=\"post\">");
		out.println("Enter username: " + "<input name =\"name\" id=\"userName\" type=\"text\"> <br>");
		out.println("Enter password: " + "<input name =\"passwords\" id=\"inputedPassword\" type=\"password\"> <br>");
		out.println("<input type = \"checkbox\"> Remember Me <br>");
		out.println("<button type=\"submit\">Login </button> <br>");
		out.println("<a href = \" \" class = \"forgot\">" + "Forgot Password" + "</a>");
		out.println("</form>");
		out.println("</div>");
		out.println("</body></html>");
	}
}	






