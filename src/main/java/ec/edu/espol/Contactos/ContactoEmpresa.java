package ec.edu.espol.Contactos;

import java.util.Comparator;


import ec.edu.espol.Atributos.NumeroEmpresa;

public class ContactoEmpresa extends Contacto {
    private String rol;
    private String empresa;

    public ContactoEmpresa(String nombre, String numero, String empresa, String rol){
	super(nombre);
	this.empresa = empresa;
        numeros.add(new NumeroEmpresa(numero));
        this.rol = rol;
        super.tipo = "E";
    }

    @Override
    public int compareTo(Contacto o) {
        return this.getNombre().compareToIgnoreCase(o.getNombre());
    }

    public static Comparator<Contacto> ordenarPorNombre = new Comparator<Contacto>() {
        @Override
        public int compare(Contacto c1, Contacto c2) {
            return c1.getNombre().compareToIgnoreCase(c2.getNombre());
        }
    };
	
}
