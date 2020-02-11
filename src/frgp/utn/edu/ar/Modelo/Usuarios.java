package frgp.utn.edu.ar.Modelo;

public class Usuarios {
	private String login;
	private String password;
	private int idCliente;
	
	public Usuarios() {
		//Empty constructor
	}
	
	public Usuarios(String login, String password, int idCliente)
	{
		this.login = login;
		this.password = password;
		this.idCliente = idCliente;
	}

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", password=" + password + ", idCliente=" + idCliente + "]";
	}
}
