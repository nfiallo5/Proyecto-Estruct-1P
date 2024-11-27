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
    protected String nombre;
    protected HashSet<Numero> numeros;
    protected HashSet<Email> correos;
    protected MiArrayList<String> direcciones;
    protected MiLinkedList<Contacto> relacionados;
    protected MiArrayList<String> fotos;
    protected String tipo;
    private Integer cantidadAtributos;
    private static final long serialVersionUID = 1L;

    public Contacto(String nombre){
        this.nombre = nombre;
        this.numeros = new HashSet<>();
        this.correos = new HashSet<>();
        this.direcciones = new MiArrayList<>();
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
        if (!correos.add(correo))
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

    public Integer getCantidadAtributos(){
        cantidadAtributos = numeros.size() + correos.size() + direcciones.size() + relacionados.size();
        return cantidadAtributos;
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

    public String getTipo() {
        return tipo;
    }

    @Override
    public int compareTo(Contacto c2){
        return this.getClass().getName().compareTo(c2.getClass().getName());
    }

    // public static Comparator<Contacto> ordenarPorTipo = new Comparator<Contacto>() {
    //     @Override
    //     public int compare(Contacto c1, Contacto c2) {
    //         return c1.tipo.compareToIgnoreCase(c2.tipo);
    //     }
    // };

    // public static Comparator<Contacto> ordenarPorClase = new Comparator<Contacto>() {
    //     @Override
    //     public int compare(Contacto c1, Contacto c2){
    //         return c1.getClass().getName().compareToIgnoreCase(c2.getClass().getName());
    //     }
    // };

    // public static Comparator<Contacto> filtarNombre = new Comparator<Contacto>() {
    //     @Override
    //     public int compare(Contacto c1, Contacto c2){
    //         return c1.getNombre().compareToIgnoreCase(c2.getNombre());
    //     }
    // };

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

        return strb.toString();
    }

    public static void main(String[] args){

    }
}
