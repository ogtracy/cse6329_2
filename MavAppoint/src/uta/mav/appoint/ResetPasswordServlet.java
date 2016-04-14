/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.mav.appoint;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uta.mav.appoint.beans.GetSet;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.email.Email;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.login.StudentUser;

/**
 *
 * @author varma
 */
@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
	String email;
	String password;
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

		email = request.getParameter("email");
		password = request.getParameter("password");
		
		

		// need to add check for maverick email address
		// need to add check that both passwords match
		// need to redirect back to register with correct error message

		StudentUser studentUser = new StudentUser();
		

		try {

			
			studentUser.setEmail(email);

			
			studentUser.setPassword(password);

			

			DatabaseManager dbm = new DatabaseManager();
			if (dbm.updateStudent(studentUser)) {
				
				request.getRequestDispatcher("success.jsp").forward(request,response);
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
