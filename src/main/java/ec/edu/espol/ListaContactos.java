package ec.edu.espol;

import java.util.Collections;
import java.util.Comparator;

import ec.edu.espol.Contactos.Contacto;
import ec.edu.espol.List.MiCircularLinkedList;
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
    private MiCircularLinkedList<Contacto> contactos;
    private int currentIndex = 0;
    private static final long serialVersionUID = 1L;

    private ListaContactos(){
        this.contactos = new MiCircularLinkedList<>();
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
        contactos.add(contacto);
    }

    public void removeContacto(Contacto contacto){
        contactos.remove(contacto);
    }

    public MiCircularLinkedList<Contacto> getContactos(){
        return contactos;
    }

    public int getCurrentIndex(){
        return currentIndex;
    }

    @Override
    public String toString(){
        StringBuilder strb = new StringBuilder("Lista de Contactos:\n");
        //Por default se ordena los contactos por su nombre
        // Collections.sort(contactos, );
        for(int i = 0; i < contactos.size(); i++){
            strb.append("[").append(i).append("] ").append(contactos.get(i).getNombre()).append("\n");
        }
        return strb.toString();
    }
    
    public void save() throws IOException{
        FileOutputStream fileOut = new FileOutputStream("Contactos.ser");
        ObjectOutputStream objectOut;
        objectOut = new ObjectOutputStream(fileOut);

        objectOut.writeObject(contactos);
        System.out.println("Se han guardado los cambios.");
    }
    
    public void load() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fileIn = new FileInputStream("Contactos.ser");
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        this.contactos = (MiCircularLinkedList<Contacto>) objectIn.readObject();
        System.out.println("Se ha cargado la lista de contactos exitosamente.");
        
    }
}