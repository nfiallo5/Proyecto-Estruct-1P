package ec.edu.espol.List;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.w3c.dom.Node;


public class MiLinkedList<T> {  //esta es una lista doblemente enlazada
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
            tail = head; 
	} else {
            tail.setSigt(nuevoNodo); //El nuevonodo se vuelve el tail
            nuevoNodo.setPrev(tail);
            this.tail = nuevoNodo;
	}
	size++; 
    }

	/*
        public void add(T elemento, int indice){
            Nodo<T> nuevoNodo = new Nodo<>(elemento);
            if(head == null){
		head = nuevoNodo;
		tail = nuevoNodo;
            } else {
                Nodo<T> actual = getNodo(indice);

            }
	}

        */
        
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
        }
        size--;
        return data;
    }

    public T remove(T elemento){
        Nodo<T> actual = head;
        T data;
        for(int i = 0; i<size; i++){
            if(actual.getData().equals(elemento)){
                data = remove(i);
                return data;
            }
            actual = actual.getSigt();
        }
            throw new RuntimeException("No existe el elemento en la lista");
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

    private Nodo<T> mergeSort(Nodo<T> head, Comparator<T> comparator) {
        if (head == null || head.getSigt() == null) {
            return head;
        }

        // Split the linked list into two halves
        Nodo<T> middle = getMiddle(head);
        Nodo<T> nextOfMiddle = middle.getSigt();
        middle.setSigt(null);

        // Recursively sort both halves
        Nodo<T> left = mergeSort(head, comparator);
        Nodo<T> right = mergeSort(nextOfMiddle, comparator);

        // Merge the sorted halves
        return sortedMerge(left, right, comparator);
    }

    //Metodo sort para ordenar los nodos en base al Comparator hecho en cada clase
    public void sort(Comparator<T> comparator){
		head = mergeSort(head, comparator);
    }

    private Nodo<T> sortedMerge(Nodo<T> a, Nodo<T> b, Comparator<T> comparator) {
        if (a == null) return b;
        if (b == null) return a;

        Nodo<T> result;
        if (comparator.compare(a.getData(), b.getData()) <= 0) {
            result = a;
            result.setSigt(sortedMerge(a.getSigt(), b, comparator));
        } else {
            result = b;
            result.setSigt(sortedMerge(a, b.getSigt(), comparator));
        }
        return result;
    }

    private Nodo<T> getMiddle(Nodo<T> head) {
        if (head == null) return head;

        Nodo<T> slow = head, fast = head;
        while (fast.getSigt() != null && fast.getSigtSigt() != null) {
            slow = slow.getSigt();
            fast = fast.getSigt();
        }
        return slow;
    }	


    public int size(){
    	return size;
    }

    public boolean isEmpty(){
    	return size == 0;
    }
    
    public Iterator iterator() {
        return new MiLinkedListIterator();
    }
    
    public class MiLinkedListIterator implements Iterator<T> {
        protected Nodo<T> current = head;

        // Reinicia el iterador al inicio
        public void reset() {
            current = head;
        }

        // Devuelve el siguiente elemento sin avanzar
        public T peek() {
            if (!hasNext()) throw new IllegalStateException("No more elements to peek");
            return current.getData();
        }

        // Retrocede al elemento anterior
        public T previous() {
            if (current == null || current.getPrev() == null) throw new IllegalStateException("No previous element");
            current = current.getPrev();
            return current.getData();
        }

        // Cuenta los elementos restantes
        public int countRemaining() {
            Nodo<T> temp = current;
            int count = 0;
            while (temp != null) {
                count++;
                temp = temp.getSigt();
            }
            return count;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new IllegalStateException("No more elements");
            T data = current.getData();
            current = current.getSigt();
            return data;
        }

        @Override
        public void remove() {
            if (current == null) {
        throw new IllegalStateException("Cannot remove element: current node is null");
        }

        Nodo<T> previous = current.getPrev();
        Nodo<T> next = current.getSigt();

        if (previous != null) {
            previous.setSigt(next); // Actualiza el siguiente del nodo anterior
        }

        if (next != null) {
            next.setPrev(previous); // Actualiza el anterior del nodo siguiente
        }

        if (current == head) {
            // Si el nodo eliminado es la cabeza, actualizamos el head
            head = next;
        }

        // Avanzar el iterador después de eliminar el nodo actual
        current = next;
    }
    }
 
}
