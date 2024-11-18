package ec.edu.espol;

import java.util.Scanner;
import java.util.logging.Logger;

import ec.edu.espol.Atributos.Email;
import ec.edu.espol.Atributos.Numero;
import ec.edu.espol.Atributos.NumeroPersonal;
import ec.edu.espol.Atributos.NumeroEmpresa;
import ec.edu.espol.Contactos.Contacto;
import ec.edu.espol.Contactos.ContactoEmpresa;
import ec.edu.espol.Contactos.ContactoPersonal;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UserInputHandler {
	private ListaContactos admin = ListaContactos.getInstance();
	Scanner scanner = new Scanner(System.in);

	//Muestra el menu y devuelve el indice dado por el usuario
	public int mostrarMenu(){
		System.out.println(admin.toString());
		System.out.println("Escoja el indice de un contacto o para añadir un contacto (-1): ");
		System.out.println("[exit: -2]");
		return Integer.parseInt(scanner.nextLine());
	}

	//Muestra el contenido del contacto pedido por el usuario
	public int mostrarContacto(int indice){
		int input;
		Contacto contacto;
		do {
			contacto = admin.getContactos().get(indice);
			System.out.println("Contacto["+indice+"]: ");
			System.out.println(contacto);
			System.out.println("[0] anterior | [1] siguiente | [2] editar contacto | [3] eliminar contacto | [4] volver a la lista");
			//Devuelve la accion que quiere el usuario
			input = Integer.parseInt(scanner.nextLine());
			switch (input) {
				case 0:
					admin.retroceder();
					indice = admin.getCurrentIndex();
					break;
				case 1:
					admin.avanzar();
					indice = admin.getCurrentIndex();
					break;
				case 2:
					break;
				case 3:
					admin.removeContacto(contacto);
					break;
				default:
					break;
			}
		} while(input != 4);
		return input;
	}

	public void crearContacto(){
            String nom;
		do{System.out.println("Nombre: ");
		nom = scanner.nextLine();}
                while (Utilidad.validarLetras(nom));
                System.out.println("P = Personal\nE = Empresarial\nElija un tipo de contacto (P/E):");
                String tipo  = scanner.nextLine();
                while(!tipo.equals("E") && !tipo.equals("P")){
                    System.out.println("Tipo debe ser Empresa o Personal.");
                    System.out.println("Tipo de contacto (P/E):");
                    tipo  = scanner.nextLine();}
		System.out.println("Numero telefonico: ");
		String numero = scanner.nextLine();
                while(!numero.matches("\\d{10}")){
                    System.out.println("Por favor ingrese un número de 10 carácteres:");
                    numero = scanner.nextLine();
                }
		switch (tipo) {
			case "P":
				crearContactoPersonal(nom, numero);
				break;
			case "E":
				crearContactoEmpresa(nom, numero);
				break;
			default:
				break;
		}
	}

	private void menuAtributos(Contacto c1){
		int input;
		do {
			System.out.println("Añadir atributos");
			System.out.println("[0] correo \n[1] direccion \n[2] relacionar contactos \n[3] salir");
			input = Integer.parseInt(scanner.nextLine());
			switch (input) {
				case 0:
					anadirCorreo(c1);
					break;
				case 1:
					anadirDireccion(c1);
					break;
				case 2:
					break;
				default:
					break;
			}
		} while (input != 3);
		
	}

	private String anadirCorreo(Contacto c1){
		String input;
		do{ 
                    System.out.println("Ingrese correo [volver: -1]: ");
                    input =scanner.nextLine();
                    while(!Utilidad.validarCorreo(input) && !(input.equals("-1"))){
                        System.out.println("El correo no es válido. Inténtelo otra vez.");
                        System.out.println("Ingrese correo [volver: -1]: ");
                        input =scanner.nextLine();
                    }
                    if(!input.equals("-1")){
                        String etq;
                        do{System.out.println("Ingrese una etiqueta de para este correo: ");
                        etq = scanner.nextLine();
                        }
                        while(Utilidad.validarLetras(etq));
                        Email correo = new Email(input, etq);
                        c1.addCorreo(correo);
                    }
                }
                while(!input.equals("-1"));
                    
			
		return input;
	}

	private String anadirDireccion(Contacto c1){
		String input;
		do {
			System.out.println("Ingrese direccion [volver: -1]: ");
			input =scanner.nextLine();
			if(!(input.equals("-1"))){
				c1.addDireccion(input);
			}
		} while (!input.equals("-1"));
		return input;
	}

	public void crearContactoPersonal(String nombre, String numero){
                String tipo;
                do{System.out.println("Ingrese una etiqueta para este número (Personal, casa, trabajo, etc):");
                tipo = scanner.nextLine();}
                while (Utilidad.validarLetras(tipo));
                String apellido;
		do{System.out.println("Apellido: ");
                apellido = scanner.nextLine();}
                while (Utilidad.validarLetras(apellido));
		Contacto c1 = new ContactoPersonal(nombre, apellido, numero, tipo);
		menuAtributos(c1);
		admin.addContacto(c1);
		System.out.println("Contacto Creado con Exito");
	}

	public void crearContactoEmpresa(String nombre, String numero){
            String empresa;
		do{System.out.println("Empresa: ");
		empresa = scanner.nextLine();}
                while(Utilidad.validarLetras(empresa));
                String rol;
		do{System.out.println("Rol: ");
		rol = scanner.nextLine();}
                while(Utilidad.validarLetras(rol));
		Contacto c1 = new ContactoEmpresa(nombre, numero, empresa, rol);
		menuAtributos(c1);
		admin.addContacto(c1);
		System.out.println("Contacto Creado con Exito");

	}

	public ListaContactos getListaContactos(){
		return admin;
	}
        
        public boolean cargarContactos(ListaContactos usuario){
            try{usuario.load();
                System.out.println("La lista de contactos se ha cargado exitosamente.\n");
            return true;}
            catch (FileNotFoundException e){
                System.out.println("No existe una lista de contactos para cargar.\n");
            }
            catch (IOException | ClassNotFoundException e1){
                System.out.println("Error al cargar contactos.\n");
            }
            return false;
        }


	public static void main(String[] args){
		UserInputHandler ui = new UserInputHandler();
		ListaContactos admin = ui.getListaContactos();
                System.out.println("Bienvenido a la aplicación de Lista de Contactos!\n");
                ui.cargarContactos(admin);
                
		int input1;
		do {
			input1 = ui.mostrarMenu();
			if(input1 >= 0){
				ui.mostrarContacto(input1);
			} else if(input1 == -1){
				ui.crearContacto();
			}
		} while(input1 != -2);
                
                try{
                    admin.save();}
                catch(IOException e){
                    System.out.println("Hubo un error al guardar los datos.");
                }
	}
}
