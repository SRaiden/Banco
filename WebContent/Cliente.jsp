<%@ include file="MasterPage/cabeza.jsp" %>

<%
	boolean Edicion = request.getParameter("action") != null ? request.getParameter("action").equalsIgnoreCase("editar") : false;
	//boolean esAdmin = (session.getAttribute("login") != null) ? session.getAttribute("login").equals("admin") : false;
%>

<div id="form">
<form method="post" action="ClienteServlet"  style="margin-left: 5%;margin-right: 5%;margin-top: 0px;padding: 0px;float: inherit;">

	<table>
	
	<% 
		Cliente cli = (Cliente) request.getAttribute("cliente");
		if (Edicion) {
	%>
	
		<tr>
			<td class="tdCliente"><label class="lblCliente">ID Cliente:</label></td>
			<td class="tdCliente"><input type="text" name="idcliente" value="<%= cli.getIdCliente() %>" readonly="readonly"></td>
		</tr>
		
	<% } %>	
	
		<tr>
			<td class="tdCliente"><label class="lblCliente">Nombre:</label></td>
			<td class="tdCliente"><input type="text" name="nombre" value="<%= Edicion ? cli.getNombre_Cliente() : "" %>"></td>
		</tr>
		<tr>
			<td class="tdCliente"><label class="lblCliente">Apellido:</label></td>
			<td class="tdCliente"><input type="text" name="apellido" value="<%= Edicion ? cli.getApellido_Cliente() : "" %>"></td>
		</tr>
		<tr>
			<td class="tdCliente"><label class="lblCliente">DNI:</label></td>
			<td class="tdCliente"><input type="text" name="dni" value="<%= Edicion ? cli.getDNI_Cliente() : "" %>"></td>
		</tr>
		<tr>
			<td class="tdCliente"><label class="lblCliente">Dirección:</label></td>
			<td class="tdCliente"><input type="text" name="direccion" value="<%= Edicion ? cli.getDireccion_Cliente() : "" %>"></td>
		</tr>
		<tr>
			<td class="tdCliente"><label class="lblCliente">Email:</label></td>
			<td class="tdCliente"><input type="text" name="email" value="<%= Edicion ? cli.getEmail_Cliente() : "" %>"></td>
		</tr>
		<tr>
			<td class="tdCliente"><label class="lblCliente">Teléfono:</label></td>
			<td class="tdCliente"><input type="text" name="telefono" value="<%= Edicion ? cli.getTelefono_Cliente() : "" %>"></td>
		</tr>
		<tr>
			<td class="tdCliente"><label class="lblCliente">Fecha de nacimiento:</label></td>
			<td class="tdCliente"><input type="text" name="fechanac" value="<%= Edicion ? new SimpleDateFormat("dd/MM/yyyy").format(cli.getFechaNac_Cliente()) : "" %>"></td>
		</tr>
	<% if (Edicion) { %>
		<tr>
			<td class="tdCliente"><label class="lblCliente">Cuenta 1:</label></td>
			<td class="tdCliente"><input type="text" name="cuenta1" value="<%=cli.getCuenta2_Cliente()%>" readonly="readonly"></td>
		</tr>
		<tr>
			<td class="tdCliente"><label class="lblCliente">Cuenta 2:</label></td>
			<td class="tdCliente"><input type="text" name="cuenta2" value="<%=cli.getCuenta2_Cliente()%>" readonly="readonly"></td>
		</tr>
		<tr>
			<td class="tdCliente"><label class="lblCliente">Cuenta 3:</label></td>
			<td class="tdCliente"><input type="text" name="cuenta3" value="<%=cli.getCuenta3_Cliente()%>" readonly="readonly"></td>
		</tr>
		<tr>
			<td class="tdCliente"><label class="lblCliente">Cuenta 4:</label></td>
			<td class="tdCliente"><input type="text" name="cuenta4" value="<%=cli.getCuenta4_Cliente()%>" readonly="readonly"></td>
		</tr>
	<% } %>
	<% if (esAdmin) { %>
		<tr>
			<td class="tdCliente"><label class="lblCliente">Estado:</label></td>
			<td class="tdCliente"><select name="tipoestado">
			<% 
				@SuppressWarnings("unchecked")
				List<TipoEstado> lstTiposEstado =  (List<TipoEstado>) request.getAttribute("lstTiposEstado");
				for (TipoEstado te : lstTiposEstado) {
			%>
				<option value="<%= te.getTipoEstado() %>" <%= (Edicion && cli.getTipoEstado_Cliente() == te.getTipoEstado()) ? "selected" : "" %>><%= te.getDescripcion() %></option>
			<% } %>
			</select></td>
		</tr>
	<% } %>
		<tr>
			<td class="tdCliente"><input type="submit" name="action" value="<%= Edicion ? "Editar" : "Agregar" %>" style="margin-left: 85%; width: 40%;"></td>
		</tr>
	</table>
	<% 
		if( request.getAttribute("estadoCliente")!=null)
		{
				if(((Boolean)request.getAttribute("estadoCliente"))==true)
				{
					out.print("Se agrego o se edito el Cliente");
				}
				else
				{
					out.print("Error al ingresar o editar el Cliente.");
				}
		}
 	%>
</form>
</div>

<hr style="margin-top: 2%;">
		
<%@ include file="MasterPage/Pie.jsp" %>

