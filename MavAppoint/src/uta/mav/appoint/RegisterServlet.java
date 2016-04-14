package uta.mav.appoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.email.Email;
import uta.mav.appoint.login.StudentUser;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String email;
	String password;
	String rpassword;
	String role;
	String studentId;
	HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// recently added two lines
		session = request.getSession();
		session.setAttribute("message", "");
		request.setAttribute("includeHeader", "templates/header.jsp");

		request.getRequestDispatcher("/register.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		email = request.getParameter("emailAddress");
		password = request.getParameter("password");
		rpassword = request.getParameter("repeatPassword");
		studentId = request.getParameter("userid");
		role = "student";

		// need to add check for maverick email address
		// need to add check that both passwords match
		// need to redirect back to register with correct error message

		StudentUser studentUser = new StudentUser();
		studentUser.setRole(role);

		try {

			if (!email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@mavs.uta.edu")) {
				System.out.println("Email Address Invalid");
				session.setAttribute("message", "Email Address Invalid");
				//request.setAttribute("error", "Unable to add user");
				response.setHeader("Refresh","2; URL=register");
				request.getRequestDispatcher("/failure.jsp").forward(request,response);
				//request.getRequestDispatcher("/register.jsp").forward(request,
					//	response);
			}
			studentUser.setEmail(email);

			if (!((password).equals(rpassword))) {
				System.out.println("Password Does Not Match");
				session.setAttribute("message", "Password Does Not Match");
				request.setAttribute("error", "Unable to add user");
				response.setHeader("Refresh","2; URL=register");
				request.getRequestDispatcher("/failure.jsp").forward(request,response);
				//request.getRequestDispatcher("/register.jsp").forward(request,
					//	response);
			}
			studentUser.setPassword(password);

			if (!studentId.matches("^100\\d{7}")
					&& !request.getParameter("student_Id").matches(
							"^6000\\d{6}")) {
				System.out.println("StudentID Invalid");
				session.setAttribute("message", "StudentID Invalid");
				//request.setAttribute("error", "Unable to add user");
				response.setHeader("Refresh","2; URL=register");
				request.getRequestDispatcher("/failure.jsp").forward(request,response);
				//request.getRequestDispatcher("/register.jsp").forward(request,
					//	response);
			}
			studentUser.setStudentId(studentId);

			DatabaseManager dbm = new DatabaseManager();
			if (dbm.createStudent(studentUser)) {
				Email userEmail = new Email("MavAppoint Account Created",
						"Your account for MavAppoint has been created! Your account information is:\n"
								+ "Role: " + role + "\n" + "Password: "
								+ password, email);
				userEmail.sendMail();
				response.setHeader("Refresh","2; URL=login");
				request.getRequestDispatcher("/success.jsp").forward(request,response);
				//session.setAttribute("message",
					//	"Account Created! Please check your E-mail.");
			} else {

				session.setAttribute("message", "Account could not be created");
			}
		} catch (Exception e) {
			System.out.println(e + " RegisterServlet");
		}

	}

}
