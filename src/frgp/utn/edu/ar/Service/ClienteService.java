package frgp.utn.edu.ar.Service;

import java.sql.*;
import java.util.*;

import frgp.utn.edu.ar.Modelo.Cliente;
import frgp.utn.edu.ar.Util.Conexion;

public class ClienteService {

	// AGREGAR CLIENTE
	public static boolean AgregarCliente(Cliente cliente) {
		boolean agregado = false;
		try {
			String query = "{call Proc_CrearCliente (?, ?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			cst.setString(1,cliente.getNombre_Cliente());
			cst.setString(2,cliente.getApellido_Cliente());
			cst.setString(3,cliente.getDNI_Cliente());
			cst.setString(4,cliente.getDireccion_Cliente());
			cst.setString(5,cliente.getEmail_Cliente());
			cst.setString(6,cliente.getTelefono_Cliente());
			cst.setDate(7,cliente.getFechaNac_Cliente());
			cst.setInt(8,cliente.getTipoEstado_Cliente());
			agregado = cst.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return agregado;
	}
	
	// ACTUALIZAR CLIENTE
	public static boolean ActualizarCliente(Cliente cli) {
		boolean actualizar = false;
		
		try {
			String query = "{call Proc_ActualizarCliente (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);

			cst.setInt(1,cli.getIdCliente());
			cst.setString(2,cli.getNombre_Cliente());
			cst.setString(3,cli.getApellido_Cliente());
			cst.setString(4,cli.getDNI_Cliente());
			cst.setString(5,cli.getDireccion_Cliente());
			cst.setString(6,cli.getEmail_Cliente());
			cst.setString(7,cli.getTelefono_Cliente());
			cst.setDate(8,cli.getFechaNac_Cliente());
			cst.setString(9,cli.getCuenta1_Cliente());
			cst.setString(10,cli.getCuenta2_Cliente());
			cst.setString(11,cli.getCuenta3_Cliente());
			cst.setString(12,cli.getCuenta4_Cliente());
			cst.setInt(13,cli.getTipoEstado_Cliente());
			
			actualizar = cst.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return actualizar;
	}
	
	// ELIMINAR CLIENTE
	public static boolean EliminarCliente(int idCli) {
		boolean eliminar = false;
		try {
			String query = "{call Proc_EliminarCliente (?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			cst.setInt		(1,	idCli);
			eliminar = cst.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return eliminar;
	}
	
	// REHABILITAR CLIENTE
	public static boolean RehabilitarCliente(int idCli) {
		boolean rehabilitar = false;
		try {
			String query = "{call Proc_RehabilitarCliente (?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			cst.setInt		(1,	idCli);
			
			rehabilitar = cst.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return rehabilitar;
	}
	
	// TRAER CLIENTES
	public static List<Cliente> TraerClientes(int tipoEstado) {
		List<Cliente> lstClientes = new ArrayList<Cliente>();
		
		try {
			String query = "{call Proc_TraerClientes (?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			cst.setInt(1,tipoEstado);
			
			ResultSet rs = cst.executeQuery();
			while (rs.next()) {
				lstClientes.add(
					new Cliente(
						rs.getInt("idCliente"),
						rs.getString("Nombre"),
						rs.getString("Apellido"),
						rs.getString("DNI"),
						rs.getString("Direccion"),
						rs.getString("Email"),
						rs.getString("Telefono"),
						rs.getDate("FechaNac"),
						rs.getString("Cuenta1"),
						rs.getString("Cuenta2"),
						rs.getString("Cuenta3"),
						rs.getString("Cuenta4"),
						rs.getInt("tipoEstado"),
						rs.getDate("FechaRegistro")
					)
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return lstClientes;
	}
	
	// TRAER CLIENTE POR EL ID
	public static Cliente TraerClientePorId(int idCli) {
		Cliente cliente = null;
		
		try {
			String query = "{call Proc_TraerCliente (?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			cst.setInt(1,idCli);
			
			ResultSet rs = cst.executeQuery();
			
			if (rs.next()) {
				cliente = new Cliente(
						rs.getInt("idCliente"),
						rs.getString("Nombre"),
						rs.getString("Apellido"),
						rs.getString("DNI"),
						rs.getString("Direccion"),
						rs.getString("Email"),
						rs.getString("Telefono"),
						rs.getDate("FechaNac"),
						rs.getString("Cuenta1"),
						rs.getString("Cuenta2"),
						rs.getString("Cuenta3"),
						rs.getString("Cuenta4"),
						rs.getInt("tipoEstado"),
						rs.getDate("FechaRegistro")
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return cliente;
	}
	
	// TRAER CLIENTE POR EL CBU
	public static Cliente TraerClientePorCBU(String CBU) {
		Cliente cliente = null;
		
		try {
			String query = "{call Proc_TraerClienteCBU (?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			cst.setString(1,CBU);
			
			ResultSet rs = cst.executeQuery();
			
			if (rs.next()) {
				cliente = new Cliente(
						rs.getInt("idCliente"),
						rs.getString("Nombre"),
						rs.getString("Apellido"),
						rs.getString("DNI"),
						rs.getString("Direccion"),
						rs.getString("Email"),
						rs.getString("Telefono"),
						rs.getDate("FechaNac"),
						rs.getString("Cuenta1"),
						rs.getString("Cuenta2"),
						rs.getString("Cuenta3"),
						rs.getString("Cuenta4"),
						rs.getInt("tipoEstado"),
						rs.getDate("FechaRegistro")
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return cliente;
	}
}
