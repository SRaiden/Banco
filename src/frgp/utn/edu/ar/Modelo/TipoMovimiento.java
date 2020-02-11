package frgp.utn.edu.ar.Modelo;

public class TipoMovimiento {
	private int tipoMovimiento;
	private String Descripcion;
	
	public TipoMovimiento() {}

	public TipoMovimiento(int tipoMovimiento, String descripcion) {
		super();
		this.tipoMovimiento = tipoMovimiento;
		Descripcion = descripcion;
	}

	public int getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(int tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoMovimiento [tipoMovimiento=" + tipoMovimiento + ", Descripcion=" + Descripcion + "]";
	}
	
	
}
