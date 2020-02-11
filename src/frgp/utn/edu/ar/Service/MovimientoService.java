package frgp.utn.edu.ar.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import frgp.utn.edu.ar.Modelo.Movimiento;
import frgp.utn.edu.ar.Modelo.TipoMovimiento;
import frgp.utn.edu.ar.Modelo.Cuenta;
import frgp.utn.edu.ar.Service.CuentaService;
import frgp.utn.edu.ar.Util.Conexion;

public class MovimientoService {

	// CREAR MOVIMIENTO POR CUENTA
	public static boolean CrearMovimientoCuenta(Movimiento mov) {
		boolean agregar1 = false;
		boolean agregar2 = false;
		boolean agregar3 = false;
		
		Cuenta cueDestino;
		Cuenta cueOrigen;
		
		try {
			cueDestino = new Cuenta();
			cueDestino = CuentaService.traerCuentaPorCBU(mov.getCBU_Destino());	 // BUSCAMOS EL CBU DESTINO EN LA TABLA CUENTA PARA HACER EL UPDATE POSITIVO
			
			cueOrigen = new Cuenta();
			cueOrigen = CuentaService.traerCuentaPorCBU(mov.getCBU_Origen());	 // BUSCAMOS EL CBU ORIGEN EN LA TABLA CUENTA PARA HACER EL UPDATE NEGATIVO
			
			// EXISTEN LOS CBUS?
			if(cueDestino != null && cueOrigen != null) {
				
				String query = "{call Proc_ActualizarSaldoCBU (?, ?)}";
				CallableStatement cst = Conexion.getConnection().prepareCall(query);
				
				double monto = mov.getMonto();
				double saldoAnterior = cueOrigen.getSaldo_Cuenta();
				double total = saldoAnterior - monto; // Restamos el monto del CBU ORIGEN
				
				cst.setString(1, cueOrigen.getID_Cuenta());	// CBU
				cst.setDouble(2, total);	// Saldo
				agregar1 = cst.execute();	// se agrego?
				
				/* ---------------------------------------------- */
				
				String query2 = "{call Proc_ActualizarSaldoCBU (?, ?)}";
				CallableStatement cst2 = Conexion.getConnection().prepareCall(query2);
					
				double saldoAnterior2 = cueDestino.getSaldo_Cuenta();
				double total2 = monto + saldoAnterior2; // Le sumamos el monto al CBU DESTINO
				cst2.setString(1, cueDestino.getID_Cuenta());	// CBU
				cst2.setDouble(2, total2);	// Saldo
				agregar2 = cst2.execute();	// se agrego?
				
				/* ---------------------------------------------- */
				
				String query3 = "{call Proc_CrearMovimientoporCuenta (?, ?, ?, ?, ?)}";
				CallableStatement cst3 = Conexion.getConnection().prepareCall(query3);
				
				cst3.setInt(1, mov.getIdCliente());
				cst3.setString(2, mov.getCBU_Origen());
				cst3.setString(3, mov.getCBU_Destino());
				cst3.setDouble(4, mov.getMonto());
				cst3.setInt(5, mov.getTipoMovimiento());
				agregar3 = cst3.execute();
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		if(agregar3 && agregar2 && agregar1) {
			return true;
		}else {
			return false;
		}
	}
	
	// CREAR MOVIMIENTO POR SERVICIO
	public static boolean CrearMovimientoServicio(Movimiento mov) {
		boolean agregar1 = false;
		boolean agregar2 = false;
		Cuenta cueOrigen;
		try {
			cueOrigen = new Cuenta();
			cueOrigen = CuentaService.traerCuentaPorCBU(mov.getCBU_Origen());	 // BUSCAMOS EL CBU ORIGEN EN LA TABLA CUENTA PARA HACER EL UPDATE NEGATIVO
			
			if(cueOrigen != null) {
				String query = "{call Proc_ActualizarSaldoCBU (?, ?)}";
				CallableStatement cst = Conexion.getConnection().prepareCall(query);
				
				double monto = mov.getMonto();
				double saldoAnterior = cueOrigen.getSaldo_Cuenta();
				double total = saldoAnterior - monto; // Restamos el monto del CBU ORIGEN
				
				cst.setString(1, cueOrigen.getID_Cuenta());	// CBU
				cst.setDouble(2, total);	// Saldo
				agregar1 = cst.execute();	// se agrego?
				
				/* ---------------------------------------------- */
				
				String query3 = "{call Proc_CrearMovimientoporServicio (?, ?, ?, ?, ?)}";
				CallableStatement cst3 = Conexion.getConnection().prepareCall(query3);
				
				cst3.setInt(1, mov.getIdCliente());
				cst3.setString(2, mov.getCBU_Origen());
				cst3.setDouble(3, mov.getMonto());
				cst3.setInt(4, mov.getTipoMovimiento());
				cst3.setInt(5, mov.getIDServicio());
				agregar2 = cst3.execute();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		if(agregar2 && agregar1) {
			return true;
		}else {
			return false;
		}
	}
	
	// HISTORIAL
	public static List<Movimiento> Historial(int idCli) {
		List<Movimiento> lstHistorial = new ArrayList<Movimiento>();
		
		try {
			String query = "{call Proc_Historial (?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			cst.setInt	(1,	idCli);
			
			ResultSet rs = cst.executeQuery();
			
			if (rs.next()) {
				lstHistorial.add(
					new Movimiento(
					rs.getInt("numMovimiento"),
					rs.getInt("idCLiente"),
					rs.getString("CBU_origen"),
					rs.getString("CBU_destino"),
					rs.getDouble("Monto"),
					rs.getInt("tipoMovimiento"),
					rs.getInt("idServicio"),
					rs.getDate("FechaMovimiento")
					)
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return lstHistorial;
	}

	/* TRAER TODOS LOS TIPOS DE MOVIMIENTO */
	public static Object traerTipoMovimiento() {
		List<TipoMovimiento> lstTiposMovimiento = new ArrayList<TipoMovimiento>();
		try {
			String query = "{call Proc_TraerTiposMovimiento}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);
			
			ResultSet rs = cst.executeQuery();
			
			while (rs.next()) {
				lstTiposMovimiento.add(
					new TipoMovimiento(
						rs.getInt("tipoMovimiento"),
						rs.getString("Descripcion")
					)
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return lstTiposMovimiento;
	}

	/* COMPROBAR SI EL CBU INGRESADO ES VALIDO */
	public static boolean CBUValido(String cbuValido) {
		boolean valor = false;
		try {
			String query = "{call Proc_TraerClienteCBU (?)}";
			CallableStatement cst = Conexion.getConnection().prepareCall(query);	
			cst.setString(1,cbuValido);
			ResultSet rs = cst.executeQuery();
			if (rs.next()) {
				valor = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return valor;
	}
		
}
