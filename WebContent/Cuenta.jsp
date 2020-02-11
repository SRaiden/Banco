<%@ include file="MasterPage/cabeza.jsp" %>


<%
	boolean Edicion = request.getParameter("action") != null ? request.getParameter("action").equalsIgnoreCase("editar") : false;
%>
<div id="form">
<form method="post" action="CuentaServlet" style="margin-left: 5%;margin-right: 5%;margin-top: 0px;float: inherit;padding: 0px;">
	<table>
	<% 
		int idCliente = request.getParameter("idcliente") != null ? Integer.parseInt(request.getParameter("idcliente")) : 0;
		Cuenta cue = (Cuenta) request.getAttribute("Cuenta");
		if (Edicion) {
	%>
		<tr>
			<td class="tdCuenta"><label class="lblCuenta">CBU:</label></td>
			<td class="tdCuenta"><input type="text" name="idCuenta" value="<%= Edicion ? cue.getID_Cuenta() : "" %>" readonly="readonly"></td>
		</tr>
		<tr>
			<td class="tdCuenta"><label class="lblCuenta">Saldo:</label></td>
			<td class="tdCuenta"><input type="number" step="any" min="0" name="saldoCuenta" value="<%= Edicion ? cue.getSaldo_Cuenta() : "" %>"></td>
		</tr>
	<% } %>	
		<tr>
			<td class="tdCuenta"><label class="lblCuenta">ID Cliente:</label></td>
			<td class="tdCuenta"><input type="number" name="idcliente" min="1" value="<%= Edicion ? cue.getIdCliente_Cuenta() : idCliente != 0 ? idCliente : "" %>"></td>
		</tr>
		<tr>
			<td class="tdCuenta"><label class="lblCuenta">Tipo Cuenta:</label></td>
			<td class="tdCuenta"><select name="tipocuenta">
		<% 
			@SuppressWarnings("unchecked")
			List<TipoCuenta> lstTiposCuenta =  (List<TipoCuenta>) request.getAttribute("lstTiposCuenta");
			for (TipoCuenta tipoc : lstTiposCuenta) {
		%>
				<option value="<%= tipoc.getTipoCuenta() %>" <%= (Edicion && cue.getTipoCuenta() == tipoc.getTipoCuenta()) ? "selected" : "" %>><%= tipoc.getDescripcion() %></option>
		<% } %>
			</select></td>
		</tr>
		<tr>
			<td class="tdCuenta"><label class="lblCuenta">Estado:</label></td>
			<td class="tdCuenta"><select name="tipoestado">
		<% 
			@SuppressWarnings("unchecked")
			List<TipoEstado> lstTiposEstado =  (List<TipoEstado>) request.getAttribute("lstTiposEstado");
			for (TipoEstado tipoE : lstTiposEstado) {
		%>
				<option value="<%= tipoE.getTipoEstado() %>" <%= (Edicion && cue.getTipoEstado() == tipoE.getTipoEstado()) ? "selected" : "" %>><%= tipoE.getDescripcion() %></option>
		<% } %>		
			</select></td>
		</tr>
		<tr>
			<td class="tdCuenta"><input type="submit" name="action" value="<%=Edicion ? "Editar" : "Agregar" %>" style="margin-left: 85%;width: 40%;"></td>
		</tr>
	</table>
	<% 
		if( request.getAttribute("estadoCuenta")!=null)
		{
				if(((Boolean)request.getAttribute("estadoCuenta"))==true)
				{
					out.print("Se agrego o se edito la Cuenta");
				}
				else
				{
					out.print("Error al ingresar o editar la Cuenta.");
				}
		}
 	%>
</form>
</div>

<%@ include file="MasterPage/Pie.jsp" %>
