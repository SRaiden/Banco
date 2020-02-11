<%@ include file="MasterPage/cabeza.jsp" %>

<%
	@SuppressWarnings("unchecked")
	List<Cuenta> lstCuentas = (List<Cuenta>) request.getAttribute("lstCuentas");
	@SuppressWarnings("unchecked")
	List<TipoCuenta> lstTiposCuenta = (List<TipoCuenta>) request.getAttribute("lstTiposCuenta");
	@SuppressWarnings("unchecked")
	List<TipoEstado> lstTiposEstado = (List<TipoEstado>) request.getAttribute("lstTiposEstado");
%>

<form method="POST" action='CuentaServlet'  style="padding: 0;margin-bottom: 20px;text-align: center;margin-top: 0px;margin-right: 0px;float: inherit;">
	<label>Ver cuentas:</label>
	<select name="estado" style="width: 20%;">
	<% int Estado = (session.getAttribute("estadoCuentas") != null) ? (Integer) session.getAttribute("estadoCuentas") : 0; %>
	<option value="0" <%= (Estado == 0) ? "selected" : "" %>>TODAS</option>
	<% for (TipoEstado te : lstTiposEstado) { %>
		<option value="<%= te.getTipoEstado() %>" <%= (Estado == te.getTipoEstado()) ? "selected" : "" %>><%= te.getDescripcion() %></option>
	<% } %>
	</select>
	<input type="submit" name="action" value="Filtrar" style="width: 20%;"/>
</form>
<div style="margin-bottom: 40%;">
	<table>
		<thead>
			<tr>
				<th class="tdListarCuentas">Opciones</th>
				<th class="tdListarCuentas">CBU</th>
				<th class="tdListarCuentas">Saldo $</th>
				<th class="tdListarCuentas">ID Cliente</th>
				<th class="tdListarCuentas">Tipo Cuenta</th>
				<th class="tdListarCuentas">Estado</th>
				<th class="tdListarCuentas">Fecha Registro</th>
			</tr>
		</thead>
		<tbody>
			<% for(Cuenta c : lstCuentas) { %>
				<tr>
					<td class="tdListarCuentas"><a href="<%= request.getContextPath() %>/CuentaServlet?action=editar&cbu=<%= c.getID_Cuenta() %>">Editar</a> / 
					<a href="<%= request.getContextPath() %>/CuentaServlet?action=<%= (c.getTipoEstado() == 1) ? "alta" : "baja" %>&cbu=<%= c.getID_Cuenta() %>"><%= (c.getTipoEstado() == 1) ? "Rehabilitar" : "Borrar" %></a></td>
					<td class="tdListarCuentas"><%= c.getID_Cuenta() %></td>
					<td class="tdListarCuentas"><%= c.getSaldo_Cuenta() %></td>
					<td class="tdListarCuentas"><a href="<%= request.getContextPath() %>/ClienteServlet?action=editar&idcliente=<%= c.getIdCliente_Cuenta() %>" target="_blank"><%= c.getIdCliente_Cuenta() %></a></td>
					<td class="tdListarCuentas"><%= lstTiposCuenta.get(c.getTipoCuenta() - 1).getDescripcion() %></td>
					<td class="tdListarCuentas"><%= lstTiposEstado.get(c.getTipoEstado() - 1).getDescripcion() %></td>
					<td class="tdListarCuentas"><%= c.getFechaRegistro_Cuenta() %></td>
				</tr>
			<% } %>
		</tbody>
	</table>
</div>

<%@ include file="MasterPage/Pie.jsp" %>

