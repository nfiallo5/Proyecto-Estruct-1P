package ec.edu.espol.List;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;

import ec.edu.espol.Contactos.Contacto;

public class MiArrayList<E> implements Iterable<E>, Serializable, List<E>{
	private int size = 0;
	private E[] array;
	private int capacity = 2;
 
	@SuppressWarnings("unchecked")
	public MiArrayList() {
	    array = (E[]) new Object[capacity];
	}
 
	public boolean add(E element) {
	    if (size == capacity) expandirCapacidad();
	    array[size++] = element;
        return true;
	}

    public void addAll(List<E> lista){
        for(int i = 0; i < lista.size(); i++){
            E dato = lista.get(i);
            this.add(dato);
        }
    }

    public void addAll(Queue<E> contactosQueue){
        for(int i = 0; i < contactosQueue.size(); i++){
            E dato = contactosQueue.poll();
            this.add(dato);
        }
    }

    public void replace(List<E> lista){
        this.removeAll();
        this.addAll(lista);
    }

    public void add(E element, int index){
        if(size == capacity) expandirCapacidad();
        for(int i = size ; i > index ; i--){
            array[i] = array[i-1];
        }
        array[index] = element;
        size++;
    }

    public void sort(PriorityQueue<E> queue){
        for(int i = 0; i < queue.size(); i++){
            queue.add(array[i]);
        }
        for(int i = 0; i < queue.size(); i++){
            array[i] = queue.poll();
        }
    }

	public E remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Índice fuera de rango");
        E removedElement = array[index];
        for (int i = index; i < size - 1; i++) array[i] = array[i + 1];
        array[index - 1] = null; 
        size--;
        return removedElement;
    }

    @Override
    public boolean remove(Object element){
        for (int i = 0; i < size; i++) 
        if (array[i].equals(element)){
            remove(i);
            return true;
        } 
        return false;
    }

    public void removeAll(){
        for(int i = 0; i < size; i++){
            array[i] = null;
        }
        size = 0;
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
        
    @Override
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

    @Override
    public void add(int arg0, E arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAll'");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAll'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    @Override
    public boolean contains(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'containsAll'");
    }

    @Override
    public int indexOf(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'indexOf'");
    }

    @Override
    public int lastIndexOf(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lastIndexOf'");
    }

    @Override
    public ListIterator<E> listIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeAll'");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
    }

    @Override
    public E set(int arg0, E arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subList'");
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    @Override
    public <T> T[] toArray(T[] arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }
 }