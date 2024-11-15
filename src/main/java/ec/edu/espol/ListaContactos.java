package ec.edu.espol;

import java.util.Collections;

import ec.edu.espol.Contactos.Contacto;
import ec.edu.espol.List.MiLinkedList;

public class ListaContactos {
    private static ListaContactos instacia;
    private MiLinkedList<Contacto> contactos;

    private ListaContactos(){
        this.contactos = new MiLinkedList<>();
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
        // Collections.sort(contactos, );
        for(int i = 0; i < contactos.size(); i++){
            strb.append("["+i+"]" + contactos.get(i));
        }
        return strb.toString();
    }
}