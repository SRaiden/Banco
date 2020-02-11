package frgp.utn.edu.ar.Modelo;

import java.sql.Date;

public class Cuenta {
	private String ID_Cuenta;
	private double Saldo_Cuenta;
	private int idCliente_Cuenta;
	private int tipoCuenta;
	private int tipoEstado;
	private Date FechaRegistro_Cuenta;
	
	public Cuenta() {
		//Empty constructor
	}

	public Cuenta(String iD_Cuenta, double saldo_Cuenta, int idCliente_Cuenta, int tipoCuenta, int tipoEstado,
			Date fechaRegistro_Cuenta) {
		super();
		ID_Cuenta = iD_Cuenta;
		Saldo_Cuenta = saldo_Cuenta;
		this.idCliente_Cuenta = idCliente_Cuenta;
		this.tipoCuenta = tipoCuenta;
		this.tipoEstado = tipoEstado;
		FechaRegistro_Cuenta = fechaRegistro_Cuenta;
	}

	public String getID_Cuenta() {
		return ID_Cuenta;
	}

	public void setID_Cuenta(String iD_Cuenta) {
		ID_Cuenta = iD_Cuenta;
	}

	public double getSaldo_Cuenta() {
		return Saldo_Cuenta;
	}

	public void setSaldo_Cuenta(double saldo_Cuenta) {
		Saldo_Cuenta = saldo_Cuenta;
	}

	public int getIdCliente_Cuenta() {
		return idCliente_Cuenta;
	}

	public void setIdCliente_Cuenta(int idCliente_Cuenta) {
		this.idCliente_Cuenta = idCliente_Cuenta;
	}

	public int getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(int tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public int getTipoEstado() {
		return tipoEstado;
	}

	public void setTipoEstado(int tipoEstado) {
		this.tipoEstado = tipoEstado;
	}

	public Date getFechaRegistro_Cuenta() {
		return FechaRegistro_Cuenta;
	}

	public void setFechaRegistro_Cuenta(Date fechaRegistro_Cuenta) {
		FechaRegistro_Cuenta = fechaRegistro_Cuenta;
	}

	@Override
	public String toString() {
		return "Cuenta [ID_Cuenta=" + ID_Cuenta + ", Saldo_Cuenta=" + Saldo_Cuenta + ", idCliente_Cuenta="
				+ idCliente_Cuenta + ", tipoCuenta=" + tipoCuenta + ", tipoEstado=" + tipoEstado
				+ ", FechaRegistro_Cuenta=" + FechaRegistro_Cuenta + "]";
	}
	
}
