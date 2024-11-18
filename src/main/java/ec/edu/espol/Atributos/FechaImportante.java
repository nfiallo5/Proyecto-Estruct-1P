package ec.edu.espol.Atributos;

import java.io.Serializable;
import java.time.LocalDate;

public class FechaImportante implements Serializable{
	private String descripcion;
	private static final long serialVersionUID = 1L;
	private LocalDate fecha;

	public FechaImportante(String descripcion, LocalDate fecha) {
		this.descripcion = descripcion;
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

}
