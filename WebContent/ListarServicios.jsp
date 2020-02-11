<%@ include file="MasterPage/cabeza.jsp" %>

<%
	@SuppressWarnings("unchecked")
	List<Servicio> lstServicios = (List<Servicio>) request.getAttribute("lstServicios");
	@SuppressWarnings("unchecked")
	List<TipoEstado> lstTiposEstado = (List<TipoEstado>) request.getAttribute("lstTiposEstado");
%>

<form class="filtro" method="POST" action='ServicioServlet'  style="padding: 0;margin-bottom: 20px;text-align: center;margin-top: 0px;margin-right: 0px;float: inherit;">
	<label>Ver servicios:</label>
	<select name="estado" style="width: 20%;">
	<% int Estado = (session.getAttribute("estadoServicios") != null) ? (Integer) session.getAttribute("estadoServicios") : 0; %>
	<option value="0" <%= (Estado == 0) ? "selected" : "" %>>TODOS</option>
	<% for (TipoEstado te : lstTiposEstado) {%>
		<option value="<%= te.getTipoEstado() %>" <%= (Estado == te.getTipoEstado()) ? "selected" : "" %>><%= te.getDescripcion() %></option>
	<% } %>
	</select>
	<input type="submit" name="action" value="Filtrar" style="width: 20%;"/>
</form>
<div style="margin-bottom: 40%;">
	<table>
		<thead>
			<tr>
				<th class="tdListarServicios">Opciones</th>
				<th class="tdListarServicios">ID Servicio</th>
				<th class="tdListarServicios">Razón Social</th>
				<th class="tdListarServicios">Descripción</th>
				<th class="tdListarServicios">Estado</th>
				<th class="tdListarServicios">Fecha Registro</th>
			</tr>
		</thead>
		<tbody>
			<% for(Servicio s : lstServicios) { %>
				<tr>
					<td class="tdListarServicios">
					<a href="<%= request.getContextPath() %>/ServicioServlet?action=editar&idservicio=<%= s.getId_Servicio() %>">Editar</a> / 
					<a href="<%= request.getContextPath() %>/ServicioServlet?action=<%= (s.getTipoEstado() == 1) ? "alta" : "baja" %>&idservicio=<%= s.getId_Servicio() %>"><%= (s.getTipoEstado() == 1) ? "Rehabilitar" : "Borrar" %></a></td>
					<td class="tdListarServicios"><%= s.getId_Servicio() %></td>
					<td class="tdListarServicios"><%= s.getRazonSocial_Servicio() %></td>
					<td class="tdListarServicios"><%= s.getDescripcion_Servicio() %></td>
					<td class="tdListarServicios"><%= lstTiposEstado.get(s.getTipoEstado() - 1).getDescripcion() %></td>
					<td class="tdListarServicios"><%= s.getFechaRegistro_Servicio() %></td>
				</tr>
			<% } %>
		</tbody>
	</table>
</div>

<%@ include file="MasterPage/Pie.jsp" %>