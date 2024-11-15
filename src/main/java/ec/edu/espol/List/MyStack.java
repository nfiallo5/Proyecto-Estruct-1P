package ec.edu.espol.List;

public class MyStack<T> extends MiLinkedList<T> {

	public MyStack(){}

	public T peek(){
		T data = tail.getData();
		return data;
	}

	public T pop(){
		T data = removeLast();
		return data;
	}

	public void push(T element){
		add(element);
	}
}
