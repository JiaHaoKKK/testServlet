package com.rengu.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author YJH
 * @data 2019年5月17日上午11:46:39
 **/
public class loginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		// 与MySQL数据库连接
		try {
			// 此处需要将mysql的jar包放入tomcat的lib中
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1/testServlet";
			connection = DriverManager.getConnection(url, "rengu", "rengu");
			statement = connection.createStatement();
			resultSet = statement.executeQuery(
					"select * from user where username='" + username + "' and password='" + password + "'");
			if (resultSet.next() == true) {
				res.setContentType("text/html;charset=UTF-8");
				// 返回一个页面
				res.getWriter().print("登录成功");
			} else {
				res.setContentType("text/html;charset=UTF-8");
				// 返回一个页面
				res.getWriter().print("登录失败");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 设置返回数据的编码
		System.out.println(username + " " + password);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			doGet(req, res);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
