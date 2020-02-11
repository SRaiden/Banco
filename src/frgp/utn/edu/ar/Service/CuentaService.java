package frgp.utn.edu.ar.Service;

import java.sql.*;
import java.util.*;

import frgp.utn.edu.ar.Modelo.Cuenta;
import frgp.utn.edu.ar.Modelo.TipoCuenta;
import frgp.utn.edu.ar.Util.Conexion;

public class CuentaService {

	// AGREGAR CUENTA
	public static boolean AgregarCuenta(Cuenta cue) {
		boolean agregar = false;
		try {
			String query = "{call Proc_CrearCuenta (?, ?, ?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			cst.setInt(1,cue.getIdCliente_Cuenta());
			cst.setInt(2,cue.getTipoCuenta());
			cst.setInt(3,cue.getTipoEstado());
			agregar = cst.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return agregar;
	}
	
	// TRAER TODAS LAS CUENTAS
	public static List<Cuenta> traerCuentas(int tipoEstado) {
		List<Cuenta> lstCuentas = new ArrayList<Cuenta>();
		try {
			String query = "{call Proc_TraerCuentas (?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			cst.setInt(1,tipoEstado);
			
			ResultSet rs = cst.executeQuery();
			
			while (rs.next()) {
				lstCuentas.add(
					new Cuenta(
						rs.getString("CBU"),
						rs.getDouble("Saldo"),
						rs.getInt("idCliente"),
						rs.getInt("tipoCuenta"),
						rs.getInt("tipoEstado"),
						rs.getDate("FechaRegistro")
					)
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return lstCuentas;
	}
	
	// TRAER UNA CUENTA POR SU ID
	public static Cuenta traerCuentaPorCBU(String CBU) {
		Cuenta cuenta = null;
		try {
			String query = "{call Proc_TraerCuenta (?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			cst.setString	(1,	CBU);
			ResultSet rs = cst.executeQuery();
			
			if (rs.next()) {
				cuenta = new Cuenta(
						rs.getString("CBU"),
						rs.getDouble("Saldo"),
						rs.getInt("idCliente"),
						rs.getInt("tipoCuenta"),
						rs.getInt("tipoEstado"),
						rs.getDate("FechaRegistro")
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return cuenta;
	}
	
	// ELIMINAR CUENTA
	public static boolean EliminarCuenta(String ID) {
		boolean eliminar = false;
		
		try {
			String query = "{call Proc_EliminarCuenta (?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			cst.setString	(1,	ID);
			
			eliminar = cst.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return eliminar;
	}
	
	// REHABILITAR CUENTA
	public static boolean rehabilitarCuenta(String CBU) {
		boolean rehabilitar = false;
		try {
			String query = "{call Proc_RehabilitarCuenta (?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			cst.setString	(1,	CBU);
			
			rehabilitar = cst.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return rehabilitar;
	}
	
	// ACTUALIZAR CUENTA
	public static boolean ActualizarCuenta(Cuenta cue) {
		boolean actualizar = false;
		
		try {
			String query = "{call Proc_ActualizarCuenta (?, ?, ?, ?, ?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);

			cst.setString(1,cue.getID_Cuenta());
			cst.setDouble(2,cue.getSaldo_Cuenta());
			cst.setInt(3,cue.getIdCliente_Cuenta());
			cst.setInt(4,cue.getTipoCuenta());
			cst.setInt(5,cue.getTipoEstado());
			
			actualizar = cst.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return actualizar;
	}
	
	// TRAER TIPOS DE CUENTA
	public static List<TipoCuenta> traerTiposCuenta() {
		List<TipoCuenta> lstTiposCuenta = new ArrayList<TipoCuenta>();
		try {
			String query = "{call Proc_TraerTiposCuenta}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			ResultSet rs = cst.executeQuery();
			
			while (rs.next()) {
				lstTiposCuenta.add(
					new TipoCuenta(
						rs.getInt("tipoCuenta"),
						rs.getString("Descripcion")
					)
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return lstTiposCuenta;
	}
}
