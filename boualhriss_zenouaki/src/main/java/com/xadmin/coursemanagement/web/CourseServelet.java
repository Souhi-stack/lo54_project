package com.xadmin.coursemanagement.web;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.xadmin.coursemanagement.bean.Course;
import com.xadmin.coursemanagement.dao.courseDAO;



/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the courses.
 */

@WebServlet("/")
public class CourseServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private courseDAO courseDAO;
	
	public void init()  {
		//courseDAO = new courseDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			//case "/insert":
				//insertCourse(request, response);
			//	break;
			case "/delete":
				deleteCourse(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateCourse(request, response);
				break;
			default:
				listCourse(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listCourse(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Course> listCourse = courseDAO.selectAllCourses();
		request.setAttribute("listCourse", listCourse);
		RequestDispatcher dispatcher = request.getRequestDispatcher("course-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("course-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Course existingCourse = courseDAO.selectCourse(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("course-form.jsp");
		request.setAttribute("course", existingCourse);
		dispatcher.forward(request, response);

	}

	//private void insertCourse(HttpServletRequest request, HttpServletResponse response) 
	//throws SQLException, IOException {
		//String code = request.getParameter("code");
	//String title = request.getParameter("title");
	//Course newCourse = new Course(code, title);
		//courseDAO.insertCourse(newCourse);
		//response.sendRedirect("list");//
	//}//

	private void updateCourse(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String code = request.getParameter("code");
		String title = request.getParameter("title");

		Course cour = new Course(id, code, title);
		courseDAO.updateCourse(cour);
		response.sendRedirect("list");
	}

	private void deleteCourse(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		courseDAO.deleteCourse(id);
		response.sendRedirect("list");

	}

}