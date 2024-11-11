package ec.edu.espol.List;

public class Nodo<E> {
	E dato; // Dato almacenado en el nodo
	Nodo<E> siguiente; // Referencia al siguiente nodo en la lista
 
	// Constructor que inicializa el nodo con un dato
	public Nodo(E dato) {
	    this.dato = dato;
	    this.siguiente = null;
	}
 }
