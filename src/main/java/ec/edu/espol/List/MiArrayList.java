package ec.edu.espol.List;

public class MiArrayList<E> {
	private int indice = 0; // Número de elementos en el ArrayList
	private E[] array; // Array para almacenar elementos
	private int capacidad = 10; // Capacidad inicial
 
	// Constructor: inicializa el ArrayList con la capacidad inicial
	@SuppressWarnings("unchecked")
	public MiArrayList() {
	    array = (E[]) new Object[capacidad];
	}
 
	// Método add para añadir un elemento al final del ArrayList
	public void add(E elemento) {
	    // Si el array está lleno, expandir la capacidad en un 50%
	    if (indice == capacidad) {
		   expandirCapacidad();
	    }
	    array[indice++] = elemento; // Asignar el nuevo elemento al final del array
	}

	public E removeLast(){
		E ultimo = array[indice];
		

		return ultimo;
	}
 
	// Método privado para expandir la capacidad del array en un 50%
	@SuppressWarnings("unchecked")
	private void expandirCapacidad() {
	    capacidad = capacidad + (capacidad / 2); // Incremento del 50%
	    E[] nuevoArray = (E[]) new Object[capacidad];
	    // Copiar elementos del array antiguo al nuevo
	    System.arraycopy(array, 0, nuevoArray, 0, indice);
	    array = nuevoArray; // Reemplazar el array antiguo por el nuevo
	}
 
	// Método para obtener el tamaño actual del ArrayList
	public int size() {
	    return indice;
	}
 }
