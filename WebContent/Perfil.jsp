<%@ include file="MasterPage/cabeza.jsp" %>

<form>
	<table>
		<% Cliente cli = (Cliente) request.getAttribute("cliente");%>
		<tr>
			<td class="tdPerfil"><label class="lblPerfil">ID Cliente:</label></td>
			<td class="tdPerfil"><input type="text" name="idcliente" value="<%= cli.getIdCliente() %>" readonly="readonly"></td>
		</tr>
		<tr>
			<td class="tdPerfil"><label class="lblPerfil">Nombre:</label></td>
			<td class="tdPerfil"><input type="text" name="nombre" value="<%= cli.getNombre_Cliente() %>"></td>
		</tr>
		<tr>
			<td class="tdPerfil"><label class="lblPerfil">Apellido:</label></td>
			<td class="tdPerfil"><input type="text" name="apellido" value="<%= cli.getApellido_Cliente() %>"></td>
		</tr>
		<tr>
			<td class="tdPerfil"><label class="lblPerfil">DNI:</label></td>
			<td class="tdPerfil"><input type="text" name="dni" value="<%= cli.getDNI_Cliente() %>"></td>
		</tr>
		<tr>
			<td class="tdPerfil"><label class="lblPerfil">Dirección:</label></td>
			<td class="tdPerfil"><input type="text" name="direccion" value="<%= cli.getDireccion_Cliente() %>"></td>
		</tr>
		<tr>
			<td class="tdPerfil"><label class="lblPerfil">Email:</label></td>
			<td class="tdPerfil"><input type="text" name="email" value="<%= cli.getEmail_Cliente() %>" ></td>
		</tr>
		<tr>
			<td class="tdPerfil"><label class="lblPerfil">Teléfono:</label></td>
			<td class="tdPerfil"><input type="text" name="telefono" value="<%= cli.getTelefono_Cliente() %>"></td>
		</tr>
		<tr>
			<td class="tdPerfil"><label class="lblPerfil">Fecha de nacimiento:</label></td>
			<td class="tdPerfil"><input type="text" name="fechanac" value="<%= new SimpleDateFormat("dd/MM/yyyy").format(cli.getFechaNac_Cliente()) %>"></td>
		</tr>
		<tr>
			<td class="tdPerfil"><label class="lblPerfil">Cuenta 1:</label></td>
			<td class="tdPerfil"><input type="text" name="cuenta1" value="<%= cli.getCuenta1_Cliente() %>" ></td>
		</tr>
		<tr>
			<td class="tdPerfil"><label class="lblPerfil">Cuenta 2:</label></td>
			<td class="tdPerfil"><input type="text" name="cuenta2" value="<%= cli.getCuenta2_Cliente() %>"></td>
		</tr>
		<tr>
			<td class="tdPerfil"><label class="lblPerfil">Cuenta 3:</label></td>
			<td class="tdPerfil"><input type="text" name="cuenta3" value="<%= cli.getCuenta3_Cliente() %>"></td>
		</tr>
		<tr>
			<td class="tdPerfil"><label class="lblPerfil">Cuenta 4:</label></td>
			<td class="tdPerfil"><input type="text" name="cuenta4" value="<%= cli.getCuenta4_Cliente() %>"></td>
		</tr>
	</table>
	
</form>

<%@ include file="MasterPage/Pie.jsp" %>
