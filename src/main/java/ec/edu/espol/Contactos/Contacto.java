package ec.edu.espol.Contactos;

import java.util.Comparator;
import java.util.HashSet;

import ec.edu.espol.Atributos.Email;
import ec.edu.espol.Atributos.Numero;
import ec.edu.espol.List.MiArrayList;
import ec.edu.espol.List.MiLinkedList;

public abstract class Contacto implements Comparable<Contacto> {
	protected String nombre; //nombre del contacto
	protected MiLinkedList<Contacto> relacionados; //Significa los contactos relacionados, no es obligatorio este campo 
	protected HashSet<Numero> numeros; //Los numeros telefonicos del contacto
	protected HashSet<Email> correos; //Los correos del contacto
	//private String atributos;

	public Contacto(String nombre, Numero numero){
		this.nombre = nombre;
		this.numeros = new HashSet<>();
		numeros.add(numero);
		this.correos = new HashSet<>();
		this.relacionados = new MiLinkedList<>();
	}

	public void addContacto(Contacto contacto){
		relacionados.add(contacto);
	}

	public void removeContacto(Contacto contacto){
		relacionados.remove(contacto);
	}

	public void addNumero(Numero numero){
		numeros.add(numero);
	}

	public void removeNumero(Numero numero){
		numeros.remove(numero);
	}
        
        public void addCorreo(Email correo){
		correos.add(correo);
	}

	public void removeCorreo(Email correo){
		correos.remove(correo);
	}

	public String getNombre() {
		return nombre;
	}

	// @Override
	// public int compareTo(Contacto otroContacto) {
	//     // Comparar primero por nombre, y si son iguales, comparar por apellido
	//     int nombreComparacion = this.nombre.compareToIgnoreCase(otroContacto.nombre);
	//     if (nombreComparacion != 0) {
	// 	   return nombreComparacion;
	//     }
	//     return this.apellido.compareToIgnoreCase(otroContacto.apellido);
	// }

	@Override
	public String toString(){
		StringBuilder strb = new StringBuilder("Contacto:" + this.nombre);
		strb.append("Telefono: ");
		for(Numero numero : numeros){
			strb.append("\t"+numero);
		}
		strb.append("\nCorreo:");
		for(Email correo : correos){
			strb.append("\t"+correo);
		}
		String ultimo = strb.toString();
		return ultimo;
	}

}
