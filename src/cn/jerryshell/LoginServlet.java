package cn.jerryshell;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录 Servlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final UserDB userDB = UserDB.getInstence();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (!login(username, password)) {
			out.println("登录失败！<br/>");
			out.println("<a href='" + request.getContextPath()
					+ "/index.jsp'>返回首页</a>");
			return;
		}
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		out.println("登录成功！<br/>");
		out.println("<a href='" + request.getContextPath()
				+ "/index.jsp'>返回首页</a>");
	}

	private boolean login(String username, String password) {
		List<User> userList = userDB.getAll();
		for (User user : userList) {
			if (user.getUsername().equals(username)
					&& user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
}
