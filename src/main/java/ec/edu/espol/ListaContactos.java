package ec.edu.espol;

import java.util.Collections;

import ec.edu.espol.Contactos.Contacto;
import ec.edu.espol.List.MiLinkedList;
import java.util.Comparator;

public class ListaContactos {
    private static ListaContactos instacia;
    private MiLinkedList<Contacto> contactos;
    private int currentIndex = 0;

    private ListaContactos(){
        this.contactos = new MiLinkedList<>();
    }

    public Contacto getContactoActual() {
        return contactos.get(currentIndex);
    }

    // Navegar hacia adelante de manera circular
    public Contacto avanzar() {
        currentIndex = (currentIndex + 1) % contactos.size();
        return getContactoActual();
    }

    // Navegar hacia atrás de manera circular
    public Contacto retroceder() {
        currentIndex = (currentIndex - 1 + contactos.size()) % contactos.size();
        return getContactoActual();
    }
    
    public static ListaContactos getInstance(){
        if(instacia == null){
            instacia = new ListaContactos();
        } 
        return instacia;
    }

    public void addContacto(Contacto contacto){
        contactos.add(contacto);
    }

    public void removeContacto(Contacto contacto){
        contactos.remove(contacto);
    }

    public String toString(){
        StringBuilder strb = new StringBuilder("Lista de Contactos:\n");
        contactos.sort(Comparator.comparing(Contacto::getNombre));
        // Collections.sort(contactos, );
        for(int i = 0; i < contactos.size(); i++){
            strb.append("[").append(i).append("] ").append(contactos.get(i)).append("\n");
        }
        return strb.toString();
    }
}