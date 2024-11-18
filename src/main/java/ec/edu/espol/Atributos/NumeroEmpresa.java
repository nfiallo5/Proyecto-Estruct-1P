package ec.edu.espol.Atributos;

public class NumeroEmpresa extends Numero{
    
    public NumeroEmpresa(String numero){
	super(numero);
	
    }
        
    
    @Override
    public String toString(){
        String str = "Numero: "+this.numerotelefonico+"\n";
        return str;
    }
}
