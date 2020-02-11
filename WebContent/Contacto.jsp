<%@ include file="MasterPage/cabeza.jsp" %>

	<!-- CONTACTO -->

	<div id="form">
			<center><h1>Contacto</h1></center>
			<fieldset><legend>Datos Personales</legend>
			<form method="get" action="AdminContacto.html" style="float: inherit;margin-top: 0px;margin-right: 0px;padding: 0px;">
				<table>
					<tr>
						<td style="width: 45%;"><label for="Nombre"> Nombre: </label> <input type="text" name="nombre" size="20" placeholder="Escribi tu nombre"></td>
						<td style="width: 45%;"><label for="Apellido"> Apellido: </label> <input type="text" id="apellido" placeholder="Escribi tu apellido"> </td>
					</tr>
					<tr>
						<td style="width: 45%;"><label for="Direccion"> Direccion: </label> <input type="text" name="direccion" size="20" placeholder="Escribi su direccion"> </td>
						<td style="width: 45%;"><label for="CP"> Codigo Postal: </label> <input type="text" name="cp" size="20" placeholder="Escribi su Codigo Postal"> </td>
					</tr>
					<tr>
						<td style="width: 45%;"><label for="Provincia"> Provincia: </label>
							<select name="prov">
								<option value="1">Buenos Aires</option>
								<option value="2">Catamarca</option>
								<option value="3">Chaco</option>
								<option value="4">Chubut</option>
								<option value="5">C&#243;rdoba</option>
								<option value="6">Corrientes</option>
								<option value="7">Entre R&#237;os</option>
								<option value="8">Formosa</option>
								<option value="9">Jujuy</option>
								<option value="10">La Pampa</option>
								<option value="11">La Rioja</option>
								<option value="12">Mendoza</option>
								<option value="13">Misiones</option>
								<option value="14">N&#233;uquen</option>
								<option value="15">R&#237;o Negro</option>
								<option value="16">Salta</option>
								<option value="17">San Juan</option>
								<option value="18">San Luis</option>
								<option value="19">Santa Cruz</option>
								<option value="20">Santa Fe</option>
								<option value="21">Santiago del Estero</option>
								<option value="22">Tierra del Fuego</option>
								<option value="23">Tucum&#225;n</option>
							</select>
						</td>
						<td style="width: 45%;"><label for="Localidad"> Localidad: </label> <input type="text" name="localidad" size="20" placeholder="Escribi su localidad"> </td>
					</tr>
					<tr>
						<td style="width: 45%;"><div class="cat"><label for="Categoria"> Categoria de edad: </label>
							<select name="cat">
								<option value="1">Joven</option>
								<option value="2">Adulto</option>
								<option value="3">Mayor</option>
								<option value="3">Anciano</option>
							</select></div>
						</td>
						<td style="width: 45%;"><label for="Telefono"> Telefono: </label> <input type="tel" name="telefono" placeholder="Escriba su numero de Tel"></td>
					</tr>
					<tr>
						<td style="width: 45%;"><label for="Cel"> Cel: </label> <input type="tel" name="celular" placeholder="Escriba su numero de Cel"></td>
						<td style="width: 45%;"><label for="Mail"> E-Mail: </label> <input type="email" name="Mail" placeholder="Escriba su Correo Electronico"></td>
					</tr>
					<tr>
						<td align="center" colspan="2"><label for="mensaje"> <b style="float: left;">Mensaje:</b> </label> <br>
						<textarea name="mensaje" rows="5" cols="60" > </textarea></td>
					</tr>
					<tr>
						<td style="width: 45%;"><div class="env"><input type="submit" value="Enviar"></div></td>
						<td style="width: 45%;"><div class="res"><input type="reset" value="Resetear"></div></td>
					</tr>
				</table>
			</form>
			</fieldset>
		</div>
		
		<hr>
		
<%@ include file="MasterPage/Pie.jsp" %>
		