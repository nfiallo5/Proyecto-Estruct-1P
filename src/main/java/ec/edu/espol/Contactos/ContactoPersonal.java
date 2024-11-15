package ec.edu.espol.Contactos;

import java.util.Comparator;

import ec.edu.espol.Atributos.FechaImportante;
import ec.edu.espol.Atributos.Numero;
import ec.edu.espol.List.MiArrayList;

public class ContactoPersonal extends Contacto {
	private String apellido;
	private MiArrayList<FechaImportante> fechas;

	public ContactoPersonal(String nombre, String apellido, Numero numero, MiArrayList<FechaImportante> fechas){
		super(nombre, numero);
		this.apellido = apellido;
                this.fechas = new MiArrayList<>();
	}

	public void setApellido(String apellido){
		this.apellido = apellido;
	}

	public String getApellido() {
		return apellido;
	}

	//Si son iguales, resultado es 0
	//Si es negativo, c1 es menor que c2
	//Si es positivo, c1 es mayor que c2
	public static Comparator<ContactoPersonal> ordenarPorNombreOApellido = new Comparator<ContactoPersonal>() {
        
        
            @Override
            public int compare(ContactoPersonal c1, ContactoPersonal c2) {
                // Tomamos el menor de nombre y apellido en ambos contactos
                String menorC1 = c1.getNombre().compareToIgnoreCase(c1.getApellido()) <= 0 ? c1.getNombre() : c1.getApellido();
                String menorC2 = c2.getNombre().compareToIgnoreCase(c2.getApellido()) <= 0 ? c2.getNombre() : c2.getApellido();

                // Comparamos los menores de cada contacto
                return menorC1.compareToIgnoreCase(menorC2);
            }
	};

	@Override
	public int compareTo(Contacto contacto2){
            if (contacto2 instanceof ContactoPersonal) {
		// Comparar por nombre y apellido
		ContactoPersonal otroPersonal = (ContactoPersonal) contacto2;
		int nombreComparacion = this.nombre.compareTo(otroPersonal.getNombre());
		return (nombreComparacion != 0) ? nombreComparacion : this.apellido.compareTo(otroPersonal.getApellido());
            }
            // Si no es un ContactoPersonal, solo comparar por nombre
            return this.nombre.compareTo(contacto2.getNombre());
	}

}
