package ec.edu.espol.Atributos;

import java.io.Serializable;

public abstract class Numero implements Serializable{
    protected int numerotelefonico;
    protected String descripcion;
    private static final long serialVersionUID = 1L;


    public Numero(int numero){
        // if(contarPalabra(numero) != 10){
        //     throw new NumberFormatException();
        //	}
	    this.numerotelefonico = numero;
    }

    private int contarPalabra(String palabra){
	    if(palabra == null || palabra.isEmpty()){
            return 0;
        } 
	    String[] letras = palabra.trim().split("\\s");
	    return letras.length;
    }

    @Override
    public String toString(){
        String str = "Descripcion: "+this.descripcion+"\n Numero: "+this.numerotelefonico+"\n";
        return str;
    }
}
