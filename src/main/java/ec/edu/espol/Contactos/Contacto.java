package ec.edu.espol.Contactos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.HashMap;
import ec.edu.espol.Atributos.Email;
import ec.edu.espol.Atributos.Numero;
import ec.edu.espol.List.MiArrayList;
import ec.edu.espol.List.MiLinkedList;
import java.util.ArrayList;

public abstract class Contacto implements Comparable<Contacto> {
    protected String nombre; //nombre del contacto
    protected ArrayList<String> direcciones;
    protected MiLinkedList<Contacto> relacionados; //Significa los contactos relacionados, no es obligatorio este campo 
    protected HashSet<Numero> numeros; //Los numeros telefonicos del contacto
    protected HashSet<Email> correos; //Los correos del contacto
    protected HashMap<String, ArrayList<String>> redesSociales;
    protected ArrayList<String> fotos;
    //private String atributos;

    public Contacto(String nombre, Numero numero){
        this.nombre = nombre;
        this.numeros = new HashSet<>();
        this.correos = new HashSet<>();
        numeros.add(numero);
        this.relacionados = new MiLinkedList<>();
        this.redesSociales = new HashMap<>();
        this.direcciones = new ArrayList<>();
    }

        
    public void addContacto(Contacto contacto){
    	relacionados.add(contacto);
    }

    public void removeContacto(Contacto contacto){
    	relacionados.remove(contacto);
    }

    public void addNumero(Numero numero){
        if (!numeros.contains(numero)){
            numeros.add(numero);
        }else {
            System.out.println("El número: "+numero+" ya existe.");
        }
    }

    public void removeNumero(Numero numero){
	numeros.remove(numero);
    }
        
    public void addCorreo(Email correo){
        if (!correos.contains(correo)){
            correos.add(correo);
        }else {
            System.out.println("El correo: "+correo+" ya existe.");
        }
    }

    public void removeCorreo(Email correo){
	correos.remove(correo);
    }

    public void addDireccion(String direccion) {
        if (direccion != null && !direccion.isEmpty()) {
            direcciones.add(direccion);
        }
    }

    public void removeDireccion(String direccion) {
        direcciones.remove(direccion);
    }

    public ArrayList<String> getDireccion() {
        return direcciones;
    }
    
    public void addRedSocial(String red, String usuario) {
        if (red != null && !red.isEmpty() && usuario != null && !usuario.isEmpty()) {
            redesSociales.putIfAbsent(red, new ArrayList<>());
            redesSociales.get(red).add(usuario);
        }
    }

    public void removeRedSocial(String red, String usuario) {
        if (redesSociales.containsKey(red)) {
            ArrayList<String> usuarios = redesSociales.get(red);
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
    
    public ArrayList<String> getUsuariosRedSocial(String red) {
        return redesSociales.getOrDefault(red, new ArrayList<>());
    }
    
    public void addFoto(String foto) {
        if (foto != null && !foto.isEmpty()) {
            fotos.add(foto);
        }
    }

    public void removeFoto(String foto) {
        fotos.remove(foto);
    }

    public ArrayList<String> getFotos() {
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

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder("Contacto: " + this.nombre);
        strb.append("\nDirecciones: ");
        if (direcciones.isEmpty()) {
            strb.append("No especificadas");
        } else {
            for (String direccion : direcciones) {
                strb.append("\n\t- " + direccion);
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
                for (String usuario : redesSociales.get(red)) {
                    strb.append("\n\t\t- " + usuario);
                }
            }
        }
        
        return strb.toString();
    }
}
