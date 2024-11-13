package ec.edu.espol.List;

public class MiLinkedListDoble<T> {
	private Nodo<T> head;
	private Nodo<T> tail;
	private int size;

	public MiLinkedListDoble(){
		head = null;
		tail = null;
		size = 0;
	}

	public void addElement(T elemento){
		Nodo<T> nuevoNodo = new Nodo<>(elemento);
		if (head == null) {
			head = nuevoNodo; //Si no tiene Nodos, el creado se vuelve el head y el tail
			tail = nuevoNodo; 
		} else {
			tail.setSigt(nuevoNodo); //El nuevonodo se vuelve el tail
			nuevoNodo.setPrev(tail);
			tail = nuevoNodo;  
		}
		size++; 
	}

	public T get(int indice){
		if(indice < 0 || indice > size){
			throw new IndexOutOfBoundsException();
		}

		Nodo<T> actual = head;
		for(int i =0; i<indice; i++){
			actual = actual.getSigt(); //Busqueda similar al LinkedList simple
		}

		return actual.getData();
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
			this.tail.setSigt(null); //el nuevo tail define como nulo su siguiente nodo
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
			head = head.getSigt();
			if(head != null) head.setPrev(null);
			else 
				tail = null;
		}
		else if(indice == size -1){ //Si el indice es el ultimo nodo
			data = tail.getData(); //Se obtiene la data del tail
			tail = tail.getPrev(); //Se define el nuevo tail como el penultimo nodo
			if(tail != null) tail.setSigt(null); //Se define NULO el sigt nodo del tail
			else
				head = null;
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

}
