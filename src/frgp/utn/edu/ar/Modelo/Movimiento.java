package frgp.utn.edu.ar.Modelo;

import java.sql.Date;

public class Movimiento {
	private int IdMovimiento;
	private int idCliente;
	private String CBU_Origen;
	private String CBU_Destino;
	private double Monto;
	private int TipoMovimiento;
	private int IDServicio;
	private Date FechaMovimiento;
	
	public Movimiento() {
		//Empty constructor
	}

	public Movimiento(int idMovimiento, int idCliente, String cBU_Origen, String cBU_Destino, double monto,
			int tipoMovimiento, int iDServicio, Date fechaMovimiento) {
		super();
		IdMovimiento = idMovimiento;
		this.idCliente = idCliente;
		CBU_Origen = cBU_Origen;
		CBU_Destino = cBU_Destino;
		Monto = monto;
		TipoMovimiento = tipoMovimiento;
		IDServicio = iDServicio;
		FechaMovimiento = fechaMovimiento;
	}

	public int getIdMovimiento() {
		return IdMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		IdMovimiento = idMovimiento;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getCBU_Origen() {
		return CBU_Origen;
	}

	public void setCBU_Origen(String cBU_Origen) {
		CBU_Origen = cBU_Origen;
	}

	public String getCBU_Destino() {
		return CBU_Destino;
	}

	public void setCBU_Destino(String cBU_Destino) {
		CBU_Destino = cBU_Destino;
	}

	public double getMonto() {
		return Monto;
	}

	public void setMonto(double monto) {
		Monto = monto;
	}

	public int getTipoMovimiento() {
		return TipoMovimiento;
	}

	public void setTipoMovimiento(int tipoMovimiento) {
		TipoMovimiento = tipoMovimiento;
	}

	public int getIDServicio() {
		return IDServicio;
	}

	public void setIDServicio(int iDServicio) {
		IDServicio = iDServicio;
	}

	public Date getFechaMovimiento() {
		return FechaMovimiento;
	}

	public void setFechaMovimiento(Date fechaMovimiento) {
		FechaMovimiento = fechaMovimiento;
	}

	@Override
	public String toString() {
		return "Movimiento [IdMovimiento=" + IdMovimiento + ", idCliente=" + idCliente + ", CBU_Origen=" + CBU_Origen
				+ ", CBU_Destino=" + CBU_Destino + ", Monto=" + Monto + ", TipoMovimiento=" + TipoMovimiento
				+ ", IDServicio=" + IDServicio + ", FechaMovimiento=" + FechaMovimiento + "]";
	}
	
	
}
