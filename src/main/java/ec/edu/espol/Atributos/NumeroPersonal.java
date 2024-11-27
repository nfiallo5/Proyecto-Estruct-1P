package ec.edu.espol.Atributos;


public class NumeroPersonal extends Numero{
    protected String tipo;
    
    public NumeroPersonal(String numero, String tipo){
	    super(numero);
	    this.tipo = tipo;
    }
        
    @Override
    public String toString(){
        String str = "Descripción: "+this.tipo+"\nNumero: "+this.numerotelefonico+"\n";
        return str;
    }
}
