<%@ include file="MasterPage/cabeza.jsp" %>

<%
	@SuppressWarnings("unchecked")
	List<Cliente> lstClientes = (List<Cliente>) request.getAttribute("lstClientes");
	@SuppressWarnings("unchecked")
	List<TipoEstado> lstTiposEstado = (List<TipoEstado>) request.getAttribute("lstTiposEstado");
%>

<form class="filtro" method="post" action='ClienteServlet' name="frmFiltrarClientes" style="padding: 0;margin-bottom: 20px;text-align: center;margin-top: 0px;margin-right: 0px;float: inherit;">
		<label>Ver clientes:</label>
		<select name="estado" style="width: 20%;">
			<% int Estado = (session.getAttribute("estadoClientes") != null) ? (Integer) session.getAttribute("estadoClientes") : 0; %>
		<option value="0" <%= (Estado == 0) ? "selected" : "" %>>TODOS</option>
			<% for (TipoEstado te : lstTiposEstado) {%>
			<option value="<%= te.getTipoEstado() %>" <%= (Estado == te.getTipoEstado()) ? "selected" : "" %>><%= te.getDescripcion() %></option>
			<% } %>
		</select>
		<input type="submit" name="action" value="Filtrar" style="width: 20%;"/>
</form>
<div style="margin-bottom: 40%;">
	<table id="tabla-clientes">
		<thead>
			<tr>
				<th class="tdListarClientes">Opciones</th>
				<th class="tdListarClientes">ID Cliente</th>
				<th class="tdListarClientes">Nombre</th>
				<th class="tdListarClientes">Apellido</th>
				<th class="tdListarClientes">DNI</th>
				<th class="tdListarClientes">Dirección</th>
				<!--<th class="tdListarClientes">Email</th>-->
				<th class="tdListarClientes">Teléfono</th>
				<th class="tdListarClientes">Fecha Nacimiento</th>
				<th class="tdListarClientes">Cuenta 1</th>
				<th class="tdListarClientes">Cuenta 2</th>
				<th class="tdListarClientes">Cuenta 3</th>
				<th class="tdListarClientes">Cuenta 4</th>
				<th class="tdListarClientes">Estado</th>
				<th class="tdListarClientes">Fecha Registro</th>
			</tr>
		</thead>
		<tbody>
			<% for(Cliente c : lstClientes) { %>
				<tr>
					<td class="tdListarClientes"><a href="<%= request.getContextPath() %>/ClienteServlet?action=editar&idcliente=<%= c.getIdCliente() %>">Editar </a>/
					<a href="<%= request.getContextPath() %>/ClienteServlet?action=<%= (c.getTipoEstado_Cliente() == 1) ? "alta" : "baja" %>&idcliente=<%= c.getIdCliente() %>">
					<%= (c.getTipoEstado_Cliente() == 1) ? "Rehabilitar" : "Borrar" %></a></td>
					<td class="tdListarClientes"><%= c.getIdCliente() %></td>
					<td class="tdListarClientes"><%= c.getNombre_Cliente() %></td>
					<td class="tdListarClientes"><%= c.getApellido_Cliente() %></td>
					<td class="tdListarClientes"><%= c.getDNI_Cliente() %></td>
					<td class="tdListarClientes"><a href="https://www.google.com.ar/maps/place/<%= c.getDireccion_Cliente() %>" target="_blank"><%= c.getDireccion_Cliente() %></a></td>
					<!--<td><a href="mailto:<%= c.getEmail_Cliente() %>"><%= c.getEmail_Cliente() %></a></td> -->
					<td class="tdListarClientes"><a href="tel:<%= c.getTelefono_Cliente() %>"><%= c.getTelefono_Cliente() %></a></td>
					<td class="tdListarClientes"><%= c.getFechaNac_Cliente() %></td>
					
					<td class="tdListarClientes"><a href="<%= (c.getCuenta1_Cliente() != null) ? 
					request.getContextPath() + "/CuentaServlet?action=editar&cbu=" + c.getCuenta1_Cliente() : 
					request.getContextPath() + "/CuentaServlet?action=crear&idcliente=" + c.getIdCliente() %>" target="_blank">
					<%= (c.getCuenta1_Cliente() != null) ? c.getCuenta1_Cliente() : "Crear" %></a></td>
					<td class="tdListarClientes"><a href="<%= (c.getCuenta2_Cliente() != null) ? 
					request.getContextPath() + "/CuentaServlet?action=editar&cbu=" + c.getCuenta2_Cliente() : 
					request.getContextPath() + "/CuentaServlet?action=crear&idcliente=" + c.getIdCliente() %>" target="_blank">
					<%= (c.getCuenta2_Cliente() != null) ? c.getCuenta2_Cliente() : "Crear" %></a></td>
					<td class="tdListarClientes"><a href="<%= (c.getCuenta3_Cliente() != null) ? 
					request.getContextPath() + "/CuentaServlet?action=editar&cbu=" + c.getCuenta3_Cliente() : 
					request.getContextPath() + "/CuentaServlet?action=crear&idcliente=" + c.getIdCliente() %>" target="_blank">
					<%= (c.getCuenta3_Cliente() != null) ? c.getCuenta3_Cliente() : "Crear" %></a></td>
					<td class="tdListarClientes"><a href="<%= (c.getCuenta4_Cliente() != null) ? 
					request.getContextPath() + "/CuentaServlet?action=editar&cbu=" + c.getCuenta4_Cliente() : 
					request.getContextPath() + "/CuentaServlet?action=crear&idcliente=" + c.getIdCliente() %>" target="_blank">
					<%= (c.getCuenta4_Cliente() != null) ? c.getCuenta4_Cliente() : "Crear" %></a></td>
					
					<td class="tdListarClientes"><%= c.getTipoEstado_Cliente() %></td>
					<td class="tdListarClientes"><%= c.getFechaReg_Cliente() %></td>
				</tr>
			<% } %>
		</tbody>
	</table>
</div>

<%@ include file="MasterPage/Pie.jsp" %>