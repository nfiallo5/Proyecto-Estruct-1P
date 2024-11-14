package ec.edu.espol.List;

import java.util.Queue;
import java.util.Stack;

public class MiLinkedList<T> {
	protected Nodo<T> head;
	protected Nodo<T> tail;
	protected int size;

	public MiLinkedList(){
		head = null;
		tail = null;
		size = 0;
	}

	public void add(T elemento){
		Nodo<T> nuevoNodo = new Nodo<>(elemento);
		if (head == null) {
			head = nuevoNodo; //Si no tiene Nodos, el creado se vuelve el head y el tail
			tail = nuevoNodo; 
		} else {
			tail.setSigt(nuevoNodo); //El nuevonodo se vuelve el tail
			nuevoNodo.setPrev(tail);
			this.tail = nuevoNodo;
			formarCircular();
		}
		size++; 
	}

	public void add(T elemento, int indice){
		Nodo<T> nuevoNodo = new Nodo<>(elemento);
		if(head == null){
			head = nuevoNodo;
			tail = nuevoNodo;
		} else {
			Nodo<T> actual = head;
			for(int i = 0; i < indice; i++){
				actual = actual.getSigt();
			}
		}
	}

	private Nodo<T> getNodo(int indice){
		if(indice < 0 || indice > size){
			throw new IndexOutOfBoundsException();
		}

		Nodo<T> actual = head;
		// Va recorriendo los nodos hasta llegar al indice dado
		for(int i =0; i<indice; i++){
			actual = actual.getSigt(); 
		}

		return actual;
	}

	public T get(int indice){
		Nodo<T> nodo = getNodo(indice);
		return nodo.getData();
	}

	public T getLast(){
		if(tail == null)
			throw new IndexOutOfBoundsException();
		return tail.getData(); //Se devuelve la data del tail (ultimo)
	}

	public T removeLast(){
		if(tail == null)
			throw new IndexOutOfBoundsException();
		
		T data = tail.getData(); //Se guarda la data del tail
		if(size == 1){
			head = null; tail = null; //Si hay solo un nodo se vuelven nulos head y tail
		}
		else{
			this.tail = tail.getPrev(); //el penultimo se vuelve el tail
			formarCircular();
		}
		size--;
		return data;
	}

	public T remove(int indice){
		if(indice<0 || indice>size)
			throw new IndexOutOfBoundsException();
		
		Nodo<T> actual = head; 
		T data;

		if(indice == 0){ //Si el indice es el primer nodo
			data = actual.getData();
			this.head = head.getSigt();
			if(head != null) 
				head.setPrev(tail);
			else 
				this.tail = null;
		}
		else if(indice == size - 1){ //Si el indice es el ultimo nodo
			return removeLast();
		}
		else{
			for(int i =0; i<indice; i++){
				actual = actual.getSigt(); 
			}
			data = actual.getData();
			Nodo<T> anterior = actual.getPrev(); //Se obtiene el nodo anterior al buscado
			anterior.setSigt(actual.getSigt()); //Se define el nuevo 'NEXT' como el next nodo del que se va a remover 
			Nodo<T> posterior = actual.getSigt(); //Se obtiene el nodo posterior al buscado
			posterior.setPrev(actual.getPrev()); //Se define el nuevo 'PREVIO' como el previo nodo del que se va a remover
		}
		size--;
		return data;
	}

	// Arma una lista circular, formando el nodo siguiente del tail como el head
	// y el anterior al head como tail
	public void formarCircular(){
		this.tail.setSigt(head);
		this.head.setPrev(tail);
	}

	public int getSize(){
		return size;
	}

	public boolean isEmpty(){
		return size == 0;
	}
}
