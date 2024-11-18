package ec.edu.espol.List;

import java.io.Serializable;

public class Nodo<E> implements Serializable{
	private Nodo<E> prev;
	private E dato;
	private Nodo<E> next;
 
	protected Nodo(E dato) {
		this.next = null;
	    this.dato = dato;
	    this.prev = null;
	}

	public Nodo<E> getNext() {
		return next;
	}

	public void setNext(Nodo<E> siguiente) {
		this.next = siguiente;
	}

	public Nodo<E> getNextNext(){
		return next.next;
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