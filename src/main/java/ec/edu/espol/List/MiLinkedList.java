package ec.edu.espol.List;

public class MiLinkedList<E> {
	private Nodo<E> cabeza; // Referencia al primer nodo de la lista
	private int size = 0; // Contador de elementos en la lista
 
	// Método para obtener el tamaño de la lista
	public int size() {
	    return size; // Devuelve el número de nodos en la lista
	}
 
	public void add(E dato) {
	    Nodo<E> nuevoNodo = new Nodo<>(dato);
	    if (cabeza == null) {
		   cabeza = nuevoNodo; // Si la lista está vacía, el nuevo nodo es la cabeza
	    } else {
		   Nodo<E> actual = cabeza;
		   // Recorre hasta el último nodo
		   while (actual.siguiente != null) {
			  actual = actual.siguiente;
		   }
		   actual.siguiente = nuevoNodo; // Añade el nuevo nodo al final
	    }
	    size++; // Incrementa el tamaño de la lista
	}
 }
