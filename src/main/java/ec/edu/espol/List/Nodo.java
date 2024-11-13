package ec.edu.espol.List;

public class Nodo<E> {
	E dato; // Dato almacenado en el nodo
	Nodo<E> sigt; // Referencia al siguiente nodo en la lista
	Nodo<E> prev; // Referencia al nodo anterior en la lista
 
	// Constructor que inicializa el nodo con un dato
	protected Nodo(E dato) {
	    this.dato = dato;
	    this.sigt = null;
	    this.prev = null;
	}

	public Nodo<E> getSigt() {
		return sigt;
	}

	public void setSigt(Nodo<E> siguiente) {
		this.sigt = siguiente;
	}

	public Nodo<E> getPrev() {
		return prev;
	}

	public void setPrev(Nodo<E> anterior) {
		this.prev = anterior;
	}

	public E getData(){
		return dato;
	}
 }