package ec.edu.espol;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import ec.edu.espol.Contactos.Contacto;
import ec.edu.espol.Contactos.ContactoEmpresa;
import ec.edu.espol.Contactos.ContactoPersonal;
import ec.edu.espol.List.MiArrayList;
import ec.edu.espol.List.MiLinkedList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;


public class ListaContactos implements Serializable{
    private static ListaContactos instancia;
    MiLinkedList<Contacto> contactos;
    MiArrayList<Contacto> contactosArray;
    private int currentIndex = 0;
    private static final long serialVersionUID = 1L;

    private ListaContactos(){
        this.contactos = new MiLinkedList<>();
        this.contactosArray = new MiArrayList<>();
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
        if(instancia == null){
            instancia = new ListaContactos();
        } 
        return instancia;
    } 

    public void addContacto(Contacto contacto){
        contactosArray.add(contacto);
    }

    public void removeContacto(Contacto contacto){
        contactos.remove(contacto);
    }

    public MiLinkedList<Contacto> getContactos(){
        return contactos;
    }

    public int getCurrentIndex(){
        return currentIndex;
    }

    //Ordenar por: nombre (nombre y apellido), tipo,  atributos
    public void ordenamientoTipo(){
        Queue<Contacto> contactosSorted = new PriorityQueue<>((t2, t1) -> t1.getTipo().compareTo(t2.getTipo()));
        contactosSorted.addAll(this.contactosArray);

        afterOrdenar(contactosSorted);
    }

    public void ordenamientoAtributos(){
        Queue<Contacto> contactosSorted = new PriorityQueue<>((t1, t2) -> t1.getCantidadAtributos().compareTo(t2.getCantidadAtributos()));
        contactosSorted.addAll(this.contactosArray);
        
        afterOrdenar(contactosSorted);
    }

    public void ordenamientoTipoDescendiente(){        
            Queue<Contacto> contactosSorted = new PriorityQueue<>((t1, t2) -> t1.getTipo().compareTo(t2.getTipo()));
            contactosSorted.addAll(this.contactosArray);
            
            afterOrdenar(contactosSorted);
    }

    public void ordenarNombre(){
        Queue<Contacto> contactosSorted = new PriorityQueue<>((t1, t2) -> t1.getNombre().compareTo(t2.getNombre()));
        contactosSorted.addAll(this.contactosArray);

        afterOrdenar(contactosSorted);
    }

    private void afterOrdenar(Queue<Contacto> contactosSorted){
        MiArrayList<Contacto> contactosArray = new MiArrayList<>();

        while(!contactosSorted.isEmpty()){
            contactosArray.add(contactosSorted.poll());
        }
        this.contactosArray = contactosArray;
    }

    @Override
    public String toString(){
        StringBuilder strb = new StringBuilder("Lista de Contactos:\n");
        //Por default se ordena los contactos por su nombre
        for(int i = 0; i < contactosArray.size(); i++){
            strb.append("["+i+"] "+contactosArray.get(i).getNombre()+"\n");
        }
        return strb.toString();
    }
    
    @SuppressWarnings("resource")
    public void save() throws IOException{
        FileOutputStream fileOut = new FileOutputStream("Contactos.ser");
        ObjectOutputStream objectOut;
        objectOut = new ObjectOutputStream(fileOut);

        objectOut.writeObject(contactos);
        System.out.println("Se han guardado los cambios.");
    }
    
    @SuppressWarnings("unchecked")
    public void load() throws FileNotFoundException, IOException, ClassNotFoundException{
        try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("Contactos.ser"))) {
            this.contactos = (MiLinkedList<Contacto>) objectIn.readObject();
        }
        System.out.println("Se ha cargado la lista de contactos exitosamente.");
        
    }

    public static void main(String[] args){
        ListaContactos instance = ListaContactos.getInstance();
        instance.addContacto(new ContactoPersonal("Pedro", "Garcia", "1234567890", "P" ));
        instance.addContacto(new ContactoPersonal("Xavier",  "Garcia", "1234567890", "P"));
        instance.addContacto(new ContactoEmpresa("Ali", "1234567890", "TELCONET", "corrupto"));

        System.out.println(instance.toString());

        instance.ordenamientoTipo();

        System.out.println("\n" + instance);

    
    }

}