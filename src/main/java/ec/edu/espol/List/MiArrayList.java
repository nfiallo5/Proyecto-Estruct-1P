package ec.edu.espol.List;

import java.util.Iterator;

public class MiArrayList<E>{
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
	    if (indice == capacidad) {
		   expandirCapacidad();
	    }
	    array[indice++] = elemento;
	}

	// Método para eliminar un elemento en una posición específica
	public E remove(int index) {
        if (index < 0 || index >= indice) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        E elementoEliminado = array[index];
        for (int i = index; i < indice - 1; i++) {
            array[i] = array[i + 1];
        }
        array[indice - 1] = null; 
        capacidad--;
        return elementoEliminado;
    }

	public E remove(E element) {
		for (int i = 0; i < indice; i++) {
			if (array[i].equals(element)) {
				return remove(i);
			}
		}
		throw new RuntimeException("Elemento no encontrado en la lista");
	}

	public E get(int index) {
        if (index < 0 || index >= indice) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        return array[index];
    }

	// Método para verificar si un elemento está en la lista
    public boolean contains(E elemento) {
        for (int i = 0; i < indice; i++) {
            if (array[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }

	public E removeLast(){
		E ultimo = array[indice];
		return ultimo;
	}
 
	// Método privado para expandir la capacidad del array en un 50%
	@SuppressWarnings("unchecked")
	private void expandirCapacidad() {
	    capacidad = capacidad + (capacidad / 2);
	    E[] nuevoArray = (E[]) new Object[capacidad];
	    System.arraycopy(array, 0, nuevoArray, 0, indice);
	    array = nuevoArray;
	}
 
	// Método para obtener el tamaño actual del ArrayList
	public int size() {
	    return indice;
	}

	public boolean isEmpty(){
    	return indice == 0;
    }

 }