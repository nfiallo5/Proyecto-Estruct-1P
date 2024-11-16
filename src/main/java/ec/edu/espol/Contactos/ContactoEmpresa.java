package ec.edu.espol.Contactos;

import java.util.Comparator;

import ec.edu.espol.Atributos.Numero;

public class ContactoEmpresa extends Contacto {
    private String rol;
    private String empresa;

    public ContactoEmpresa(String nombre, Numero numero, String empresa, String rol){
	    super(nombre, numero);
	    this.empresa = empresa;
        this.rol = rol;
        this.tipo = "empresa";
    }

    @Override
    public int compareTo(Contacto o) {
        // Comparar por nombre o por algún otro criterio relevante
        return this.getNombre().compareToIgnoreCase(o.getNombre());
    }

    public static Comparator<Contacto> ordenarPorNombre = new Comparator<Contacto>() {
        @Override
        public int compare(Contacto c1, Contacto c2) {
            //Compara por el nombre del Contacto
            return c1.getNombre().compareToIgnoreCase(c2.getNombre());
        }
    };
	
}
