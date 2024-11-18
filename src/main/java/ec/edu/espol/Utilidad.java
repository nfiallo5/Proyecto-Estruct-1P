/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol;

/**
 *
 * @author sebas
 */
public class Utilidad {
    
    public static boolean validarLetras(String letras){
        if (!letras.matches("^[a-zA-Z]+$")){
            System.out.println("Debe ingresar sólo letras.\nInténtelo de nuevo.");
            return false;
        }
        return true;
    }
    
    public static boolean validarCorreo(String correo){
        if (!correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            System.out.println("Por favor ingrese un correo válido.");
            return false;
        }
        return true;
    }
    
}
