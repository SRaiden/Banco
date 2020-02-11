package frgp.utn.edu.ar.Modelo;

import java.sql.Date;

public class Cliente {
	private int idCliente;
	private String Nombre_Cliente;
	private String Apellido_Cliente;
	private String DNI_Cliente;
	private String Direccion_Cliente;
	private String Email_Cliente;
	private String Telefono_Cliente;
	private Date FechaNac_Cliente;
	private String Cuenta1_Cliente;
	private String Cuenta2_Cliente;
	private String Cuenta3_Cliente;
	private String Cuenta4_Cliente;
	private int tipoEstado_Cliente;
	private Date FechaReg_Cliente;
	
	public Cliente() {
		//Empty constructor
	}

	public Cliente(int idCliente, String nombre_Cliente, String apellido_Cliente, String dNI_Cliente,
			String direccion_Cliente, String email_Cliente, String telefono_Cliente, Date fechaNac_Cliente,
			String cuenta1_Cliente, String cuenta2_Cliente, String cuenta3_Cliente, String cuenta4_Cliente,
			int tipoEstado_Cliente, Date fechaReg_Cliente) {
		super();
		this.idCliente = idCliente;
		Nombre_Cliente = nombre_Cliente;
		Apellido_Cliente = apellido_Cliente;
		DNI_Cliente = dNI_Cliente;
		Direccion_Cliente = direccion_Cliente;
		Email_Cliente = email_Cliente;
		Telefono_Cliente = telefono_Cliente;
		FechaNac_Cliente = fechaNac_Cliente;
		Cuenta1_Cliente = cuenta1_Cliente;
		Cuenta2_Cliente = cuenta2_Cliente;
		Cuenta3_Cliente = cuenta3_Cliente;
		Cuenta4_Cliente = cuenta4_Cliente;
		this.tipoEstado_Cliente = tipoEstado_Cliente;
		FechaReg_Cliente = fechaReg_Cliente;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre_Cliente() {
		return Nombre_Cliente;
	}

	public void setNombre_Cliente(String nombre_Cliente) {
		Nombre_Cliente = nombre_Cliente;
	}

	public String getApellido_Cliente() {
		return Apellido_Cliente;
	}

	public void setApellido_Cliente(String apellido_Cliente) {
		Apellido_Cliente = apellido_Cliente;
	}

	public String getDNI_Cliente() {
		return DNI_Cliente;
	}

	public void setDNI_Cliente(String dNI_Cliente) {
		DNI_Cliente = dNI_Cliente;
	}

	public String getDireccion_Cliente() {
		return Direccion_Cliente;
	}

	public void setDireccion_Cliente(String direccion_Cliente) {
		Direccion_Cliente = direccion_Cliente;
	}

	public String getEmail_Cliente() {
		return Email_Cliente;
	}

	public void setEmail_Cliente(String email_Cliente) {
		Email_Cliente = email_Cliente;
	}

	public String getTelefono_Cliente() {
		return Telefono_Cliente;
	}

	public void setTelefono_Cliente(String telefono_Cliente) {
		Telefono_Cliente = telefono_Cliente;
	}

	public Date getFechaNac_Cliente() {
		return FechaNac_Cliente;
	}

	public void setFechaNac_Cliente(Date fechaNac_Cliente) {
		FechaNac_Cliente = fechaNac_Cliente;
	}

	public String getCuenta1_Cliente() {
		return Cuenta1_Cliente;
	}

	public void setCuenta1_Cliente(String cuenta1_Cliente) {
		Cuenta1_Cliente = cuenta1_Cliente;
	}

	public String getCuenta2_Cliente() {
		return Cuenta2_Cliente;
	}

	public void setCuenta2_Cliente(String cuenta2_Cliente) {
		Cuenta2_Cliente = cuenta2_Cliente;
	}

	public String getCuenta3_Cliente() {
		return Cuenta3_Cliente;
	}

	public void setCuenta3_Cliente(String cuenta3_Cliente) {
		Cuenta3_Cliente = cuenta3_Cliente;
	}

	public String getCuenta4_Cliente() {
		return Cuenta4_Cliente;
	}

	public void setCuenta4_Cliente(String cuenta4_Cliente) {
		Cuenta4_Cliente = cuenta4_Cliente;
	}

	public int getTipoEstado_Cliente() {
		return tipoEstado_Cliente;
	}

	public void setTipoEstado_Cliente(int tipoEstado_Cliente) {
		this.tipoEstado_Cliente = tipoEstado_Cliente;
	}

	public Date getFechaReg_Cliente() {
		return FechaReg_Cliente;
	}

	public void setFechaReg_Cliente(Date fechaReg_Cliente) {
		FechaReg_Cliente = fechaReg_Cliente;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", Nombre_Cliente=" + Nombre_Cliente + ", Apellido_Cliente="
				+ Apellido_Cliente + ", DNI_Cliente=" + DNI_Cliente + ", Direccion_Cliente=" + Direccion_Cliente
				+ ", Email_Cliente=" + Email_Cliente + ", Telefono_Cliente=" + Telefono_Cliente + ", FechaNac_Cliente="
				+ FechaNac_Cliente + ", Cuenta1_Cliente=" + Cuenta1_Cliente + ", Cuenta2_Cliente=" + Cuenta2_Cliente
				+ ", Cuenta3_Cliente=" + Cuenta3_Cliente + ", Cuenta4_Cliente=" + Cuenta4_Cliente
				+ ", tipoEstado_Cliente=" + tipoEstado_Cliente + ", FechaReg_Cliente=" + FechaReg_Cliente + "]";
	}
	
	
}
