package ec.edu.espol.List;

import java.util.Iterator;

public class MiArrayList<E> implements Iterable<E>{
	private int size = 0;
	private E[] array;
	private int capacity = 2;
 
	@SuppressWarnings("unchecked")
	public MiArrayList() {
	    array = (E[]) new Object[capacity];
	}
 
	public void add(E element) {
	    if (size == capacity) expandirCapacidad();
	    array[size++] = element;
	}

	public E remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Índice fuera de rango");
        E removedElement = array[index];
        for (int i = index; i < size - 1; i++) array[i] = array[i + 1];
        array[index - 1] = null; 
        size--;
        return removedElement;
    }

	public E remove(E element) {
		for (int i = 0; i < size; i++) 
			if (array[i].equals(element)) return remove(i);
		throw new RuntimeException("Elemento no encontrado en la lista");
	}

	public E get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        return array[index];
    }

	@SuppressWarnings("unchecked")
	private void expandirCapacidad() {
	    capacity = capacity + (capacity / 2);
	    E[] newArray = (E[]) new Object[capacity];
	    System.arraycopy(array, 0, newArray, 0, size);
	    array = newArray;
	}
 
	public int size() {
	    return size;
	}

	public boolean isEmpty(){
    	return size == 0;
    }
        
    public Iterator<E> iterator(){
        return new MiArrayListIterator();
    }

	public class MiArrayListIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new IllegalStateException("No hay más elementos");
            return array[currentIndex++];
        }

        @Override
        public void remove() {
            if (currentIndex <= 0) throw new IllegalStateException("No hay elementos para eliminar");
            for (int i = currentIndex - 1; i < size - 1; i++) array[i] = array[i + 1];
            array[--size] = null;
            currentIndex--;
        }

        public void reset() {
            currentIndex = 0;
        }

        public E current() {
            if (!hasNext()) throw new IllegalStateException("No hay elemento actual");
            return array[currentIndex];
        }

        public int countRemaining() {
            return size - currentIndex;
        }

        public boolean isFirst() {
            return currentIndex == 0;
        }

        public boolean isLast() {
            return currentIndex == size - 1;
        }
    }
 }