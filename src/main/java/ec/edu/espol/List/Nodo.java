package ec.edu.espol.List;

public class Nodo<E> {
	private E dato; // Dato almacenado en el nodo
	private Nodo<E> sigt; // Referencia al siguiente nodo en la lista
	private Nodo<E> prev; // Referencia al nodo anterior en la lista
 
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

	public Nodo<E> getSigtSigt(){
		return sigt.sigt;
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