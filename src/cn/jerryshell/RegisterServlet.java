package cn.jerryshell;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 注册 Servlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final UserDB userDB = UserDB.getInstence();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		List<String> errorList = new ArrayList<>();
		request.setAttribute("errorList", errorList);

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String email = request.getParameter("email");

		User user = new User(username, password, email);
		if (!isValidUser(user, confirmPassword, errorList)) {
			request.getRequestDispatcher("/ErrorServlet").forward(request,
					response);
			return;
		}

		userDB.addUser(user);
		out.println("用户名：" + username + "<br/>");
		out.println("密码：" + password + "<br/>");
		out.println("确认密码：" + confirmPassword + "<br/>");
		out.println("邮箱：" + email + "<br/>");
		out.println("注册成功！<br/>");
		out.println("<a href='" + request.getContextPath()
				+ "/index.jsp'>返回首页</a>");
	}

	private boolean isValidUser(User user, String confirmPassword,
			List<String> errorList) {
		if (!isValidUsername(user.getUsername())) {
			errorList.add("注册失败：用户名为空或已被注册");
			return false;
		}
		if (!isValidPassword(user.getPassword(), confirmPassword)) {
			errorList.add("注册失败：密码为空或与确认密码不相符");
			return false;
		}
		if (!isValidEmail(user.getEmail())) {
			errorList.add("注册失败：无效的邮箱");
			return false;
		}
		return true;
	}

	private boolean isValidUsername(String username) {
		if (username.isEmpty()) {
			return false;
		}
		List<User> userList = userDB.getAll();
		for (User user : userList) {
			if (user.getUsername().equals(username)) {
				return false;
			}
		}
		return true;
	}

	private boolean isValidPassword(String password, String confirmPassword) {
		if (password.isEmpty()) {
			return false;
		}
		if (!password.equals(confirmPassword)) {
			return false;
		}
		return true;
	}

	private boolean isValidEmail(String email) {
		if (email.isEmpty()) {
			return false;
		}
		if (!email.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$")) {
			return false;
		}
		return true;
	}

}
