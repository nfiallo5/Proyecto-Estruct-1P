package ec.edu.espol.List;

public class NodoDoble<E> {
	E dato; // Dato almacenado en el nodo
	NodoDoble<E> sigt; // Referencia al siguiente nodo en la lista
	NodoDoble<E> prev; // Referencia al nodo anterior en la lista
 
	// Constructor que inicializa el nodo con un dato
	protected NodoDoble(E dato) {
	    this.dato = dato;
	    this.sigt = null;
	    this.prev = null;
	}

	public NodoDoble<E> getSigt() {
		return sigt;
	}

	public void setSigt(NodoDoble<E> siguiente) {
		this.sigt = siguiente;
	}

	public NodoDoble<E> getPrev() {
		return prev;
	}

	public void setPrev(NodoDoble<E> anterior) {
		this.prev = anterior;
	}

	public E getData(){
		return dato;
	}
 }