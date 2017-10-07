<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员注册</title>
</head>
<body>
	<form action="RegisterServlet" method="post">
		用户名：<input type="text" name='username'/><br/>
		密码：<input type="password" name='password'/><br/>
		确认密码：<input type="password" name="confirmPassword"/><br/>
		邮箱：<input type="text" name="email"/><br/>
		<input type="submit" value="注册"/>
	</form>
</body>
</html>