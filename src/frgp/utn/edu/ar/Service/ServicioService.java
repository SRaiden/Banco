package frgp.utn.edu.ar.Service;

import java.sql.*;
import java.util.*;

import frgp.utn.edu.ar.Modelo.Servicio;
import frgp.utn.edu.ar.Util.Conexion;

public class ServicioService {

	// CREAR SERVICIO
	public static boolean CrearServicio(Servicio ser) {
		boolean agregar = false;
		try {
			String query = "{call Proc_CrearServicio (?, ?, ?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			cst.setString(1,ser.getRazonSocial_Servicio());
			cst.setString(2,ser.getDescripcion_Servicio());
			cst.setInt(3,ser.getTipoEstado());
			
			agregar = cst.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return agregar;
	}
	
	// ACTUALIZAR SERVICIO
	public static boolean ActualizarServicio(Servicio ser) {
		boolean actualizar = false;
		try {
			String query = "{call Proc_ActualizarServicio (?, ?, ?, ?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);

			cst.setInt(1,ser.getId_Servicio());
			cst.setString(2,ser.getRazonSocial_Servicio());
			cst.setString(3,ser.getDescripcion_Servicio());
			cst.setInt(4,ser.getTipoEstado());
			
			actualizar = cst.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return actualizar;
	}
	
	// ELIMINAR SERVICIO
	public static boolean EliminarServicio(int idSer) {
		boolean borrar = false;
		try {
			String query = "{call Proc_EliminarServicio (?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			cst.setInt	(1,	idSer);
			
			borrar = cst.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return borrar;
	}
	
	// REHABILITAR SERVICIO
	public static boolean rehabilitarServicio(int idSer) {
		boolean rehabilitar = false;
		try {
			String query = "{call Proc_RehabilitarServicio (?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			cst.setInt	(1,	idSer);
			
			rehabilitar = cst.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return rehabilitar;
	}
	
	// TRAER TODOS LOS SERVICIOS
	public static List<Servicio> TraerServicios(int tipoEstado) {
		List<Servicio> lstServicios = new ArrayList<Servicio>();
		
		try {
			String query = "{call Proc_TraerServicios (?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			cst.setInt(1,tipoEstado);
			
			ResultSet rs = cst.executeQuery();
			
			while (rs.next()) {
				lstServicios.add(
					new Servicio(
						rs.getInt("id_Servicio"),
						rs.getString("RazonSocial_Servicio"),
						rs.getString("Descripcion_Servicio"),
						rs.getInt("tipoEstado_Servicio"),
						rs.getDate("FechaRegistro_Servicio")
					)
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return lstServicios;
	}
	
	// TRAER UN SERVICIO POR EL ID
	public static Servicio TraerServicioPorId(int idSer) {
		Servicio ser = null;
		
		try {
			String query = "{call Proc_TraerServicio (?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			cst.setInt	(1,	idSer);
			
			ResultSet rs = cst.executeQuery();
			
			if (rs.next()) {
				ser = new Servicio(
					rs.getInt("id_Servicio"),
					rs.getString("RazonSocial_Servicio"),
					rs.getString("Descripcion_Servicio"),
					rs.getInt("tipoEstado_Servicio"),
					rs.getDate("FechaRegistro_Servicio")
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ser;
	}

	
	public static Servicio TraerServicioPorNombre(String nombre) {
		Servicio ser = null;
		
		try {
			String query = "{call Proc_TraerServicioNombre (?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			cst.setString(1,nombre);
			
			ResultSet rs = cst.executeQuery();
			
			if (rs.next()) {
				ser = new Servicio(
					rs.getInt("id_Servicio"),
					rs.getString("RazonSocial_Servicio"),
					rs.getString("Descripcion_Servicio"),
					rs.getInt("tipoEstado_Servicio"),
					rs.getDate("FechaRegistro_Servicio")
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ser;
	}
}
