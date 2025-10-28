/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author johan
 */
public abstract class Profesor extends Persona{
    private String IdProfesor;
    
    public Profesor() {
        super();
        IdProfesor = "Unknown";
    }
    
    public Profesor(String nombre, String apellidos, int edad, String id ) {
        super(nombre, apellidos, edad);
        IdProfesor = id;
    }

    public String getIdProfesor() {
        return IdProfesor;
    }
    
    @Override
    public String toString() {
        return super.toString().concat("Id Profesor: ").concat(IdProfesor);
    }
    
    abstract public float importeNomina();
}
