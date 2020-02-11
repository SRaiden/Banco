package frgp.utn.edu.ar.Service;

import java.sql.*;
import java.util.*;

import frgp.utn.edu.ar.Modelo.TipoEstado;
import frgp.utn.edu.ar.Util.Conexion;

public class EstadoService {

	// TRAER TODOS LOS TIPOS DE ESTADO
	public static List<TipoEstado> TraerTiposEstado() {
		List<TipoEstado> lstTiposEstado = new ArrayList<TipoEstado>();
		
		try {
			String query = "{call Proc_TraerTiposEstado}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			ResultSet rs = cst.executeQuery();
			
			while (rs.next()) {
				lstTiposEstado.add(
					new TipoEstado(
						rs.getInt("tipoEstado"),
						rs.getString("Descripcion")
					)
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return lstTiposEstado;
	}
	
}
