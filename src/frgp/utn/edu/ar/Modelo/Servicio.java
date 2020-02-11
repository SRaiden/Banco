package frgp.utn.edu.ar.Modelo;

import java.sql.Date;

public class Servicio {
	private int id_Servicio;
	private String RazonSocial_Servicio;
	private String Descripcion_Servicio;
	private int tipoEstado_Servicio;
	private Date FechaRegistro_Servicio;
	
	public Servicio() {
		//Empty constructor
	}

	public Servicio(int id_Servicio, String razonSocial_Servicio, String descripcion_Servicio, int tipoEstado,
			Date fechaRegistro_Servicio) {
		super();
		this.id_Servicio = id_Servicio;
		RazonSocial_Servicio = razonSocial_Servicio;
		Descripcion_Servicio = descripcion_Servicio;
		this.tipoEstado_Servicio = tipoEstado;
		FechaRegistro_Servicio = fechaRegistro_Servicio;
	}

	public int getId_Servicio() {
		return id_Servicio;
	}

	public void setId_Servicio(int id_Servicio) {
		this.id_Servicio = id_Servicio;
	}

	public String getRazonSocial_Servicio() {
		return RazonSocial_Servicio;
	}

	public void setRazonSocial_Servicio(String razonSocial_Servicio) {
		RazonSocial_Servicio = razonSocial_Servicio;
	}

	public String getDescripcion_Servicio() {
		return Descripcion_Servicio;
	}

	public void setDescripcion_Servicio(String descripcion_Servicio) {
		Descripcion_Servicio = descripcion_Servicio;
	}

	public int getTipoEstado() {
		return tipoEstado_Servicio;
	}

	public void setTipoEstado(int tipoEstado) {
		this.tipoEstado_Servicio = tipoEstado;
	}

	public Date getFechaRegistro_Servicio() {
		return FechaRegistro_Servicio;
	}

	public void setFechaRegistro_Servicio(Date fechaRegistro_Servicio) {
		FechaRegistro_Servicio = fechaRegistro_Servicio;
	}

	@Override
	public String toString() {
		return "Servicio [id_Servicio=" + id_Servicio + ", RazonSocial_Servicio=" + RazonSocial_Servicio
				+ ", Descripcion_Servicio=" + Descripcion_Servicio + ", tipoEstado=" + tipoEstado_Servicio
				+ ", FechaRegistro_Servicio=" + FechaRegistro_Servicio + "]";
	}
	
}
