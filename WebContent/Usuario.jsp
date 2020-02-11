<%@ include file="MasterPage/cabeza.jsp" %>

<%
	boolean Edicion = request.getParameter("action") != null ? request.getParameter("action").equalsIgnoreCase("editar") : false;
%>

<form method="post" action="UsuarioServlet" style="float: inherit;margin-top: 0px;padding: 30px;background-color: #f7ffd0;margin-right: 0px;">
	<table>
		<% 
			Usuarios usu = (Usuarios) request.getAttribute("usuario");
		%>
		<tr>
			<td class="tdUsuarios"><label class="lblUsuarios">Usuario:</label></td>
			<td class="tdUsuarios"><input type="text" name="login" value="<%= Edicion ? usu.getLogin() : "" %>" /></td>
		</tr>
		<tr>
			<td class="tdUsuarios"><label class="lblUsuarios">Contraseña:</label></td>
			<td class="tdUsuarios"><input type="password" name="password" value="<%= Edicion ? usu.getPassword() : "" %>" class="inpPass"/></td>
		</tr>
		<tr>
			<td class="tdUsuarios"><label class="lblUsuarios">ID Cliente:</label></td>
			<td class="tdUsuarios"><input type="number" name="idcliente" min="1" value="<%= Edicion ? usu.getIdCliente() : "" %>" /></td>
		</tr>
		<tr>
			<td class="tdUsuarios"><input type="submit" name="action" value="<%= Edicion ? "Editar" : "Agregar" %>" style="
    width: 40%;
    margin-left: 85%;
"/></td>
		</tr>
	</table>
	<% 
		if( request.getAttribute("estadoUsuario")!=null)
		{
				if(((Boolean)request.getAttribute("estadoUsuario"))==true)
				{
					out.print("Se agrego o se edito el Usuario");
				}
				else
				{
					out.print("Error al ingresar o editar el Usuario.");
				}
		}
 	%>
</form>

<%@ include file="MasterPage/Pie.jsp" %>