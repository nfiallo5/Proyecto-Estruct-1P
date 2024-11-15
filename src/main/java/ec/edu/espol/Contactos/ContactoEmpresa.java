package ec.edu.espol.Contactos;

import java.util.Comparator;

import ec.edu.espol.Atributos.Numero;

public class ContactoEmpresa extends Contacto {
	private String rol;
	private String compagnia;

	public ContactoEmpresa(String nombre, Numero numero, String compagnia){
		super(nombre, numero);
		this.compagnia = compagnia;
	}

	public static Comparator<Contacto> ordenarPorNombre = new Comparator<Contacto>() {
		@Override
		public int compare(Contacto c1, Contacto c2) {
			//Compara por el nombre del Contacto
			return c1.getNombre().compareToIgnoreCase(c2.getNombre());
		}
	};

	@Override
	public int compareTo(Contacto c2){
		return this.getNombre().compareToIgnoreCase(c2.getNombre());
	}

	
	
}
