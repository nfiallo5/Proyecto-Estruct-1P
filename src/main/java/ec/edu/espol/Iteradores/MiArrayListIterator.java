package ec.edu.espol.Iteradores;

public class MiArrayListIterator<T> {
    private T[] array;              // Array que almacena los elementos
    private int currentIndex = 0;   // Índice actual de la iteración
    private int size;               // Tamaño de elementos en el array

    public MiArrayListIterator(T[] array, int size) {
        this.array = array;
        this.size = size;
    }

    public boolean hasNext() {
        // Comprueba si hay un siguiente elemento
        return currentIndex < size;
    }

    public T next() {
        // Devuelve el elemento actual y avanza el índice
        if (!hasNext()) {
            throw new RuntimeException("No hay más elementos");
        }
        return array[currentIndex++];
    }

    public T current() {
        // Devuelve el elemento actual sin avanzar el índice
        if (currentIndex >= size) {
            throw new RuntimeException("No hay elemento actual");
        }
        return array[currentIndex];
    }

    public int size() {
        // Devuelve el tamaño actual de la lista
        return size;
    }

    public void remove() {
        // Elimina el último elemento devuelto
        if (currentIndex <= 0) {
            throw new IllegalStateException("No hay elementos para eliminar");
        }
        // Desplaza todos los elementos hacia la izquierda
        for (int i = currentIndex - 1; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = null; // Ajusta el tamaño y elimina el último elemento
        currentIndex--;
    }

    public void reset() {
        // Reinicia el índice actual al inicio del array
        currentIndex = 0;
    }
}
