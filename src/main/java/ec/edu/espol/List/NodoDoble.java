package ec.edu.espol.List;

public class NodoDoble<E> {
	E dato; // Dato almacenado en el nodo
	NodoDoble<E> siguiente; // Referencia al siguiente nodo en la lista
	NodoDoble<E> anterior; // Referencia al nodo anterior en la lista
 
	// Constructor que inicializa el nodo con un dato
	public NodoDoble(E dato) {
	    this.dato = dato;
	    this.siguiente = null;
	    this.anterior = null;
	}
 }