<%@ include file="MasterPage/cabeza.jsp" %>

<%
	@SuppressWarnings("unchecked")
	List<Usuarios> lstUsuarios = (List<Usuarios>) request.getAttribute("lstUsuarios");
%>

<div>
	<table style="width: 500px; margin-left: 30%; margin-bottom: 55%;">
		<thead>
			<tr>
				<th class="tdListarUsuarios">Opciones</th>
				<th class="tdListarUsuarios">Login</th>
				<th class="tdListarUsuarios">Password</th>
				<th class="tdListarUsuarios">ID Cliente</th>
			</tr>
		</thead>
		<tbody>
			<% for(Usuarios u : lstUsuarios) { %>
				<tr>
					<td class="tdListarUsuarios">
					<a href="<%= request.getContextPath() %>/UsuarioServlet?action=editar&login=<%= u.getLogin() %>">Editar</a>
					</td>
					<td class="tdListarUsuarios"><%= u.getLogin() %></td>
					<td class="tdListarUsuarios">****</td>
					<td class="tdListarUsuarios"><a href="<%= request.getContextPath() %>/ClienteServlet?action=editar&idcliente=<%= u.getIdCliente() %>" target="_blank"><%= u.getIdCliente() %></a></td>
				</tr>
			<% } %>
		</tbody>
			
	</table>
</div>
<%@ include file="MasterPage/Pie.jsp" %>
