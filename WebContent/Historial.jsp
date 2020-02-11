<%@ include file="MasterPage/cabeza.jsp" %>

<%
	@SuppressWarnings("unchecked")
	List<Movimiento> lstHistorial = (List<Movimiento>) request.getAttribute("lstHistorial");
%>

<div style="margin-bottom: 40%;">
	<table id="tabla-clientes">
		<thead>
			<tr>
				<th class="tdListarMovimiento">ID Cliente</th>
				<th class="tdListarMovimiento">CBU Origen</th>
				<th class="tdListarMovimiento">CBU Destino</th>
				<th class="tdListarMovimiento">Monto</th>
				<th class="tdListarMovimiento">Tipo Movimiento</th>
				<th class="tdListarMovimiento">ID Servicio</th>
				<th class="tdListarMovimiento">Fecha Movimiento</th>
			</tr>
		</thead>
		<tbody>
			<% for(Movimiento mov : lstHistorial) { %>
				<tr>
					<td class="tdListarMovimiento"><%= mov.getIdCliente() %></td>
					<td class="tdListarMovimiento"><%= mov.getCBU_Origen() %></td>
					<td class="tdListarMovimiento"><%= mov.getCBU_Destino() %></td>
					<td class="tdListarMovimiento"><%= mov.getMonto() %></td>
					<td class="tdListarMovimiento"><%= mov.getTipoMovimiento() %></td>
					<td class="tdListarClientes"><%= mov.getIDServicio() %></td>
					<td class="tdListarClientes"><%= mov.getFechaMovimiento() %></td>
					
				</tr>
			<% } %>
		</tbody>
	</table>
</div>

<%@ include file="MasterPage/Pie.jsp" %>