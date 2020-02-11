<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Iniciar Sesion</title>
	<link rel="stylesheet" type="text/css" href="CSS/IniciarSesion.css"/>
</head>
<body style="background-color: #5c5c6d;">
	<div style="width: 30%;height: 300px;background-color: #fff;margin: auto;margin-top: 10%; border: 3px solid white;border-radius: 20px 0px 20px 20px;box-shadow: 5px 6px #a23f3f;">
		<div class="buscarSesion">
			<img src="img/pagofacil.png" alt="Pagofacil" style="margin-top: 10%;margin-left: 3%;">
			
			<% boolean error = (request.getParameter("msg") != null) ? request.getParameter("msg").equalsIgnoreCase("error") : false; %>
			
			<form method="post" action="UsuarioServlet" style="float: right;margin-top: 5%;margin-right: 10%;padding: 15px;">
				<center><h1>Iniciar Sesion</h1></center>
				<table>
					<tr>
						<td style="width: 100%;"><label for="Usuario"> Usuario: </label> <input type="text" name="login" style="margin-bottom: 10px;padding: 5px;border: 2px solid gray;margin-left: 8%;"></td>
					</tr>
					<tr>
						<td style="width: 100%;"><label for="Password"> Contraseña: </label> <input type="password" name="password" style="margin-bottom: 10px;padding: 5px;border: 2px solid gray;"> </td>
					</tr>
					<tr>
						<td style="width: 100%;"><div class="env"><input type="submit" name="action" value="Iniciar sesión" style="background-color: #1668c4;color: #fff;border: none;width: 50%;height: 20px;margin-left: 25%;"></div></td>
					</tr>
					<% if (error) { %>
					<tr>
						<td style="width: 100%;"><font color="red">El usuario o la contraseña son incorrectos</font></td>
					</tr>
					<% } %>
				</table>
			</form>			
		</div>
	</div>
</body>
</html>