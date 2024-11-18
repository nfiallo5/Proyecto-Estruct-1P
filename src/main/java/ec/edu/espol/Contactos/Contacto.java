package ec.edu.espol.Contactos;

import java.util.HashSet;
import java.util.Comparator;
import java.util.HashMap;
import ec.edu.espol.Atributos.Email;
import ec.edu.espol.Atributos.Numero;
import ec.edu.espol.List.MiArrayList;
import ec.edu.espol.List.MiLinkedList;
import java.io.Serializable;

public abstract class Contacto implements Comparable<Contacto>, Serializable{
    protected String nombre; //nombre del contacto
    protected HashSet<Numero> numeros; //Los numeros telefonicos del contacto
    protected HashSet<Email> correos; //Los correos del contacto
    protected MiArrayList<String> direcciones;
    protected HashMap<String, MiArrayList<String>> redesSociales;
    protected MiLinkedList<Contacto> relacionados; //Significa los contactos relacionados, no es obligatorio este campo 
    protected MiArrayList<String> fotos;
    protected String tipo;
    private static final long serialVersionUID = 1L;

    public Contacto(String nombre, Numero numero){
        this.nombre = nombre;
        this.numeros = new HashSet<>();
        numeros.add(numero);
        this.correos = new HashSet<>();
        this.direcciones = new MiArrayList<>();
        this.redesSociales = new HashMap<>();
        this.relacionados = new MiLinkedList<>();
    }

    public void addContacto(Contacto contacto){
    	relacionados.add(contacto);
    }

    public void removeContacto(Contacto contacto){ // Lanza Excepción
        relacionados.remove(contacto);
    }

    public void addNumero(Numero numero){
        boolean success = numeros.add(numero);
        if (!success)
            System.out.println("El número: " + numero + " ya existe.");     
    }

    public void removeNumero(Numero numero){
	    numeros.remove(numero);
    }
        
    public void addCorreo(Email correo){
        boolean success = correos.add(correo);
        if (!success)
            System.out.println("El correo: " + correo + " ya existe.");
    }

    public void removeCorreo(Email correo){ // Lanza Excepción
	    correos.remove(correo);
    }

    public void addDireccion(String direccion) {
        if (direccion != null && !direccion.isBlank()) {
            direcciones.add(direccion);
        }
    }

    public void removeDireccion(String direccion) { // Lanza Excepción
        direcciones.remove(direccion);
    }

    public MiArrayList<String> getDireccion() {
        return direcciones;
    }
    
    public void addRedSocial(String red, String usuario) {
        if (red != null && !red.isBlank() && usuario != null && !usuario.isBlank()) {
            redesSociales.putIfAbsent(red, new MiArrayList<>());
            redesSociales.get(red).add(usuario);
        }
    }

    public void removeRedSocial(String red, String usuario) {
        if (redesSociales.containsKey(red)) {
            MiArrayList<String> usuarios = redesSociales.get(red);
            usuarios.remove(usuario);
            if (usuarios.isEmpty()) {
                redesSociales.remove(red); // Elimina la red social si no hay usuarios
            }
        }
    }
    
    /*
    public void editRedSocial(String red, String nuevoUsuario) {
        if (redesSociales.containsKey(red)) {
            redesSociales.put(red, nuevoUsuario);
        } else {
            System.out.println("La red social " + red + " no existe.");
        }
    }
    */
    
    public MiArrayList<String> getUsuariosRedSocial(String red) {
        return redesSociales.get(red);
    }
    
    public void addFoto(String foto) {
        if (foto != null && !foto.isBlank()) {
            fotos.add(foto);
        }
    }

    public void removeFoto(String foto) {
        fotos.remove(foto);
    }

    public MiArrayList<String> getFotos() {
        return fotos;
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

    public static Comparator<Contacto> ordenarPorTipo = new Comparator<Contacto>() {
        @Override
        public int compare(Contacto c1, Contacto c2) {
            return c1.tipo.compareToIgnoreCase(c2.tipo);
        }
    };

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder("Contacto: " + this.nombre);
        strb.append("\nDirecciones: ");
        if (direcciones.isEmpty()) {
            strb.append("No especificadas");
        } else {
            for (int i = 0; i < direcciones.size(); i++) {
                strb.append("\n\t- " + direcciones.get(i));
            }
        }
        strb.append("\nTeléfonos: ");
        for (Numero numero : numeros) {
            strb.append("\t" + numero);
        }
        strb.append("\nCorreos:");
        for (Email correo : correos) {
            strb.append("\t" + correo);
        }
        strb.append("\nRedes Sociales:");
        if (redesSociales.isEmpty()) {
            strb.append(" No especificadas");
        } else {
            for (String red : redesSociales.keySet()) {
                strb.append("\n\t" + red + ": ");
                for (int j = 0; j < redesSociales.get(red).size(); j++) {
                    strb.append("\n\t\t- " + redesSociales.get(red).get(j));
                }
            }
        }
        return strb.toString();
    }
}
