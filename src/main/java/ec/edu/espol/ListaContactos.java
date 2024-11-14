package ec.edu.espol;

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

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}