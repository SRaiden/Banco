package frgp.utn.edu.ar.Service;

import java.sql.*;
import java.util.*;

import frgp.utn.edu.ar.Modelo.Usuarios;
import frgp.utn.edu.ar.Util.Conexion;

public class UsuariosService {
	
	// Crear Usuario
	public static boolean CrearUsuario(Usuarios usuario) {
		boolean creado = false;
		try {
			String query = "{call Proc_CrearUsuario(?,?,?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			cst.setString(1,usuario.getLogin());
			cst.setString(2,usuario.getPassword());
			cst.setInt(3,usuario.getIdCliente());
			
			creado = cst.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return creado;
	}
	
	// ACTUALIZAR USUARIO
	public static boolean ActualizarUsuario(Usuarios usuario) {
		boolean actualizar = false;
		try {
			String query = "{call Proc_ActualizarUsuario (?, ?, ?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);

			cst.setString(1,usuario.getLogin());
			cst.setString(2,usuario.getPassword());
			cst.setInt(3,usuario.getIdCliente());
			
			actualizar = cst.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return actualizar;
	}
	
	// TRAER TODOS LOS USUARIOS
	public static List<Usuarios> TraerUsuarios() {
		List<Usuarios> lstUsuarios = new ArrayList<Usuarios>();
		try {
			String query = "{call Proc_TraerUsuarios}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			ResultSet rs = cst.executeQuery();
			
			while (rs.next()) {
				lstUsuarios.add(
					new Usuarios(
						rs.getString("login"),
						"",
						rs.getInt("idCliente")
					)
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return lstUsuarios;
	}
	
	// TRAER USUARIO POR EL LOGIN
	public static Usuarios TraerUsuarioPorLogin(String login) {
		Usuarios usuario = null;
		try {
			String query = "{call Proc_TraerUsuario (?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			cst.setString(1,login);
			
			ResultSet rs = cst.executeQuery();
			
			if (rs.next()) {
				usuario = new Usuarios(
					rs.getString("login"),
					rs.getString("password"),
					rs.getInt("idCliente")
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	// TRAER USUARIO POR ID
	public static Usuarios TraerUsuarioPorIdCliente(int idCli) {
		Usuarios usuario = null;
		try {
			String query = "{call Proc_TraerUsuarioPorId (?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			cst.setInt	(1,	idCli);
			
			ResultSet rs = cst.executeQuery();
			
			if (rs.next()) {
				usuario = new Usuarios(
					rs.getString("login"),
					rs.getString("password"),
					rs.getInt("idCliente")
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
}
