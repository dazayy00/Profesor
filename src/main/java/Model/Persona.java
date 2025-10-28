/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author johan
 */
public class Persona {
    private String nombre;
    private String apellidos;
    private int edad;
    
    public Persona() {
        nombre = "";
        apellidos = "";
        edad = 0;
    }

    public Persona(String nombre, String apellidos, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getEdad() {
        return edad;
    }
    
    @Override
    public String toString(){
        Integer datoEdad = edad;
        return "Nombre: ".concat(nombre)
                .concat("Apellidos ").concat(apellidos)
                .concat("Edad: ").concat(datoEdad.toString());
    }
}
