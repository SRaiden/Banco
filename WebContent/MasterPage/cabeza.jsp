<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, java.text.*, frgp.utn.edu.ar.Util.*, frgp.utn.edu.ar.Service.*, frgp.utn.edu.ar.Modelo.*, frgp.utn.edu.ar.Servlet.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Pago Facil</title>
	<link rel="stylesheet" type="text/css" href="CSS/estilos.css"/>
	<link rel="stylesheet" type="text/css" href="CSS/acercade.css"/>
	<link rel="stylesheet" type="text/css" href="CSS/contacto.css"/>
	<link rel="stylesheet" type="text/css" href="CSS/tablas.css"/>
	<link rel="stylesheet" type="text/css" href="CSS/IniciarSesion.css"/>
</head>
<body>
	<div class="contenedor">
	<div class="search">
			<img src="img/pagofacil.png" alt="Pagofacil">
			
			<% 
				String login = (session.getAttribute("login") != null) ? session.getAttribute("login").toString() : null; 
				boolean esAdmin = (session.getAttribute("login") != null) ? session.getAttribute("login").equals("admin") : false;
			%>
			
			<% if (login == null) { %>
			<ul class="arriba">
				<li><a href="IniciarSesion.jsp">Iniciar Sesion</a></li>
			</ul>
			<% } %>
			
			<ul class="nav">
				<li><a href="Inicio.jsp">Inicio</a></li>
				
				<% if (esAdmin) { %>
				<li><a href="#">Usuario</a>
					<ul>
						<li><a href="<%=request.getContextPath()%>/UsuarioServlet?action=listarUsuarios">Listar Usuarios</a></li>
						<li><a href="<%=request.getContextPath()%>/UsuarioServlet?action=insertar">Nuevo Usuario</a></li>
					</ul>
				</li>
				<li><a href="#">Cliente</a>
					<ul>
						<li><a href="<%=request.getContextPath()%>/ClienteServlet?action=listarClientes">Listar Clientes</a></li>
						<li><a href="<%=request.getContextPath()%>/ClienteServlet?action=insertar">Nuevo Cliente</a></li>
					</ul>
				</li>
				<li><a href="#">Cuentas</a>
					<ul>
						<li><a href="<%=request.getContextPath()%>/CuentaServlet?action=listarCuentas">Listar Cuentas</a></li>
						<li><a href="<%=request.getContextPath()%>/CuentaServlet?action=insertar">Agregar Cuenta</a></li>
					</ul>
				</li>
				<li><a href="#">Servicios</a>
					<ul>
						<li><a href="<%=request.getContextPath()%>/ServicioServlet?action=listarServicios">Listar Servicio</a></li>
						<li><a href="<%=request.getContextPath()%>/ServicioServlet?action=insertar">Agregar Servicio</a></li>
					</ul>
				</li>
				<% } else if (esAdmin == false && login != null) { %>
				<li><a href="#">Pagar Servicio / Movimiento</a>
					<ul>
						<li><a href="<%=request.getContextPath()%>/MovimientoServlet?action=transferir">Movimiento de Cuentas</a></li>
						<li><a href="<%=request.getContextPath()%>/MovimientoServlet?action=pagarServicio">Realizar Pago de Servicio</a></li>
						<li><a href="<%=request.getContextPath()%>/MovimientoServlet?action=historial">Historial</a></li>
					</ul>
				</li>
				<li><a href="<%=request.getContextPath()%>/ClienteServlet?action=perfil">Editar Perfil</a></li>
				<% } %>
				
				<li><a href="AcercaDe.jsp">Acerca de</a></li>
				<li><a href="Contacto.jsp">Contacto</a></li>
				
				<% if (login != null) { %>
				<li><a href="<%=request.getContextPath()%>/UsuarioServlet?action=logout">Cerrar sesión</a></li>
				<% } %>
			</ul>
		</div>