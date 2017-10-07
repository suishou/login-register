package cn.jerryshell;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 错误信息 Servlet
 */
@WebServlet("/ErrorServlet")
public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		List<String> errorList = (List<String>) request.getAttribute("errorList");
		if (errorList == null) {
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
			return;
		}
		for (String error : errorList) {
			out.println(error + "<br/>");
		}
	}

}
