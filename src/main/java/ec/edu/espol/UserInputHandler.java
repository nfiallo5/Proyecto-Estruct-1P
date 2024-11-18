package ec.edu.espol;

import java.util.Scanner;
import java.util.logging.Logger;

import ec.edu.espol.Atributos.Email;
import ec.edu.espol.Atributos.Numero;
import ec.edu.espol.Atributos.NumeroPersonal;
import ec.edu.espol.Atributos.NumeroTrabajo;
import ec.edu.espol.Contactos.Contacto;
import ec.edu.espol.Contactos.ContactoEmpresa;
import ec.edu.espol.Contactos.ContactoPersonal;

public class UserInputHandler {
	private ListaContactos admin = ListaContactos.getInstance();
	private Scanner scanner = new Scanner(System.in);

	//Muestra el menu y devuelve el indice dado por el usuario
	public int mostrarMenu(){
		System.out.println(admin.toString());
		System.out.println("Escoja el indice de un contacto o para añadir un contacto (-1): ");
		System.out.println("[exit: -2]");
		Integer input = Integer.parseInt(scanner.nextLine());
		return input;
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
		System.out.println("Nombre: ");
		String nom = scanner.nextLine();
		System.out.println("Numero telefonico: ");
		int numero = Integer.parseInt(scanner.nextLine());
		System.out.println("tipo de contacto (Personal/Empresa):");
		String tipo  = scanner.nextLine();
		scanner.nextLine();
		switch (tipo) {
			case "Personal":
				crearContactoPersonal(nom, numero);
				break;
			case "Empresa":
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
		do {
			System.out.println("Ingrese correo [volver: -1]: ");
			input =scanner.nextLine();
			if(!(input.equals("-1"))){
				Email correo = new Email(input);
				if(c1 instanceof ContactoPersonal){
					c1.addCorreo(correo);
				}
				correo.setTipo("Trabajo");
				c1.addCorreo(correo);
			}
		} while (!input.equals("-1"));
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

	public void crearContactoPersonal(String nombre, int numero){
		String apellido = "";
		System.out.println("Apellido (opcional): ");
		apellido = scanner.nextLine();
		Contacto c1 = new ContactoPersonal(nombre, apellido, new NumeroPersonal(numero));
		menuAtributos(c1);
		admin.addContacto(c1);
		System.out.println("Contacto Creado con Exito");
	}

	public void crearContactoEmpresa(String nombre, int numero){
		System.out.println("Empresa: ");
		String empresa = scanner.nextLine();
		System.out.println("Rol: ");
		String rol = scanner.nextLine();
		Contacto c1 = new ContactoEmpresa(nombre, new NumeroTrabajo(numero), empresa, rol);
		menuAtributos(c1);
		admin.addContacto(c1);
		System.out.println("Contacto Creado con Exito");

	}

	public ListaContactos getListaContactos(){
		return admin;
	}


	public static void main(String[] args){
		UserInputHandler ui = new UserInputHandler();
		ListaContactos admin = ui.getListaContactos();
		int input1;
		do {
			input1 = ui.mostrarMenu();
			if(input1 >= 0){
				ui.mostrarContacto(input1);
			} else if(input1 == -1){
				ui.crearContacto();
			}
		} while(input1 != -2);
	}
}
