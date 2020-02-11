<%@ include file="MasterPage/cabeza.jsp" %>

<div id="form">
<form method="post" action="MovimientoServlet" style="margin-left: 5%;margin-right: 5%;margin-top: 0px;float: inherit;padding: 0px;">
	<table>
	<% 
		int idCliente = request.getParameter("idcliente") != null ? Integer.parseInt(request.getParameter("idcliente")) : 0;
	%>
		<tr>
			<td class="tdMovimiento"><label class="lblMovimiento">CBU Origen:</label></td>
			<td class="tdMovimiento"><input type="text" name="CBUOrigen" value=""></td>
		</tr>
		<tr>
			<td class="tdMovimiento"><label class="lblMovimiento">Servicio a Pagar:</label></td>
			<td class="tdMovimiento"><select name="tipoServicio">
			<% 
			@SuppressWarnings("unchecked")
			List<Servicio> lstTiposServicio =  (List<Servicio>) request.getAttribute("lstTiposServicio");
			for (Servicio tipoSer : lstTiposServicio) {
			%>
					<option value="<%= tipoSer.getId_Servicio() %>"> <%= tipoSer.getRazonSocial_Servicio() %></option>
			<% } %>
			</select></td>
		</tr>
		<tr>
			<td class="tdMovimiento"><label class="lblMovimiento">Monto a Transferir:</label></td>
			<td class="tdMovimiento"><input type="number" step="any" min="0" name="Monto" value=""></td>
		</tr>
		<tr>
			<td class="tdMovimiento"><label class="lblMovimiento">Tipo Movimiento:</label></td>
			<td class="tdMovimiento"><select name="tipoMovimiento">
			
		<% 
			@SuppressWarnings("unchecked")
			List<TipoMovimiento> lstTiposMovimiento =  (List<TipoMovimiento>) request.getAttribute("lstTiposMovimiento");
			for (TipoMovimiento tipomov : lstTiposMovimiento) {
				if(tipomov.getTipoMovimiento() != 4){
		%>
				<option value="<%= tipomov.getTipoMovimiento() %>"> <%= tipomov.getDescripcion() %></option>
				<% } %>
		<% } %>
			</select></td>
		</tr>
		
		<tr>
			<td class="tdMovimiento"><input type="submit" name="action" value="TransferirAServicio" style="margin-left: 85%;width: 40%;"></td>
		</tr>
	</table>
	<% 
		if( request.getAttribute("estadoMovimientoCuenta")!=null)
		{
				if(((Boolean)request.getAttribute("estadoMovimientoCuenta"))==true)
				{
					out.print("Se agrego el Movimiento");
				}
				else
				{
					out.print("Error al ingresar el Movimiento. CBU Origen o Destino Incorrectos");
				}
		}
 	%>
</form>
</div>