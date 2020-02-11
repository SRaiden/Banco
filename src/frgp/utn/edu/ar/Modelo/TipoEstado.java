package frgp.utn.edu.ar.Modelo;

public class TipoEstado {
	private int tipoEstado;
	private String Descripcion;
	
	public TipoEstado() {
		//Empty constructor
	}
	
	public TipoEstado(int tipoEstado, String Descripcion)
	{
		this.tipoEstado = tipoEstado;
		this.Descripcion = Descripcion;
	}

	public int getTipoEstado() {
		return tipoEstado;
	}
	
	public void setTipoEstado(int tipoEstado) {
		this.tipoEstado = tipoEstado;
	}
	
	public String getDescripcion() {
		return Descripcion;
	}
	
	public void setDescripcion(String Descripcion) {
		this.Descripcion = Descripcion;
	}

	@Override
	public String toString() {
		return "TipoEstado [tipoEstado=" + tipoEstado + ", Descripcion=" + Descripcion + "]";
	}
}
