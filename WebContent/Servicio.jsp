<%@ include file="MasterPage/cabeza.jsp" %>

<%
	boolean Edicion = request.getParameter("action") != null ? request.getParameter("action").equalsIgnoreCase("editar") : false;
%>

<form method="POST" action="ServicioServlet" style="float: inherit;margin-top: 0px;margin-right: 0px;padding: 0px;">
	<table>
		<% 
			Servicio s = (Servicio) request.getAttribute("servicio");
			if (Edicion) {
		%>
		<tr>
			<td class="tdServicios"><label class="lblServicios">ID Servicio:</label></td>
			<td class="tdServicios"><input type="text" name="idservicio" value="<%= s.getId_Servicio() %>" readonly="readonly" /></td>
		</tr>
		<% } %>
		<tr>
			<td class="tdServicios"><label class="lblServicios">Razón Social:</label></td>
			<td class="tdServicios"><input type="text" name="razonsocial" value="<%= Edicion ? s.getRazonSocial_Servicio() : "" %>" ></td>
		</tr>
		<tr>
			<td class="tdServicios"><label class="lblServicios">Descripción:</label></td>
			<td class="tdServicios"><input type="text" name="descripcion" value="<%= Edicion ? s.getDescripcion_Servicio() : "" %>"></td>
		</tr>
		<tr>
			<td class="tdServicios"><label class="lblServicios">Estado:</label></td>
			<td class="tdServicios"><select name="tipoestado">
			<% 
				@SuppressWarnings("unchecked")
				List<TipoEstado> lstTiposEstado =  (List<TipoEstado>) request.getAttribute("lstTiposEstado");
				for (TipoEstado te : lstTiposEstado) {
			%>
				<option value="<%= te.getTipoEstado() %>" <%= (Edicion && s.getTipoEstado() == te.getTipoEstado()) ? "selected" : "" %>><%= te.getDescripcion() %></option>
			<% } %>
			</select></td>
		</tr>
		<tr>
			<td class="tdServicios"><input type="submit" name="action" value="<%= Edicion ? "Editar" : "Agregar" %>" style="width: 20%;margin-left: 100%;height: 30px;"/></td>
		</tr>
	</table>
	<% 
		if( request.getAttribute("estadoServicio")!=null)
		{
				if(((Boolean)request.getAttribute("estadoServicio"))==true)
				{
					out.print("Se agrego o se edito el Servicio");
				}
				else
				{
					out.print("Error al ingresar o editar el Servicio.");
				}
		}
 	%>
</form>

<%@ include file="MasterPage/Pie.jsp" %>