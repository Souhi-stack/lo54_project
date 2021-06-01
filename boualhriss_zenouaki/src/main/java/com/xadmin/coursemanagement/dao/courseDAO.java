package com.xadmin.coursemanagement.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import com.xadmin.coursemanagement.bean.Course;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class courseDAO {

	private static String jdbcURL = "jdbc:mysql://localhost:3306/coursedb?useSSL=false";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "rootpasswordgiven";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	
	
	private static final String INSERT_COURSES_SQL = "INSERT INTO courses" + "  (code,title) VALUES "
			+ " (?,?);";

	private static final String SELECT_COURSE_BY_ID = "select id,code,title from courses where id =?";
	private static final String SELECT_ALL_COURSES = "select * from courses";
	private static final String DELETE_COURSES_SQL = "delete from courses where id = ?;";
	private static final String UPDATE_COURSES_SQL = "update courses set code=?, title = ? where id = ?;";
	
	
	public courseDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("jdbcDriver");
			connection = (Connection) DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}


	public static void insertCourse(Course course) throws SQLException {
		System.out.println(INSERT_COURSES_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSES_SQL)) {
			preparedStatement.setString(1, course.getCode());
			preparedStatement.setString(2, course.getTitle());
		
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	private static void printSQLException(SQLException ex) {
		// TODO Auto-generated method stub
		
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	public static Course selectCourse(int id) {
		Course course = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSE_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String code = rs.getString("code");
				String title = rs.getString("title");
				course = new Course(id, code, title);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return course;
	}

	public static List<Course> selectAllCourses() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Course> courses = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COURSES);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String code = rs.getString("code");
				String title = rs.getString("title");
				courses.add(new Course(id, code, title));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return courses;
	}

	public boolean deleteCourse(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_COURSES_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateCourse(Course course) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_COURSES_SQL);) {
			System.out.println("updated Course:"+statement);
			statement.setString(1, course.getCode());
			statement.setString(2, course.getTitle());
			statement.setInt(4, course.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
}
