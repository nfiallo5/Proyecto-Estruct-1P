package ec.edu.espol;

import java.util.HashSet;

import ec.edu.espol.Atributos.Email;
import ec.edu.espol.Atributos.Numero;
import ec.edu.espol.List.MiArrayList;
import ec.edu.espol.List.MiLinkedList;

public class Contacto {
	private String[] nombre;
	private Plantilla tipoContacto; //Personal o Empresarial
	private MiLinkedList<Contacto> listaContactos;
	private HashSet<Numero> telefonos;
	private HashSet<Email> correos;
	//private String atributos;


	public Contacto(String nombre, String apellido, Plantilla tipoContacto){
		
		this.tipoContacto = tipoContacto;
		listaContactos = new MiLinkedList<>();
	}





}
