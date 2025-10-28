/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author johan
 */
public class ProfesorTitular extends Profesor{

    public ProfesorTitular(String nombre, String apellidos, int edad, String id) {
        super(nombre, apellidos, edad, id);
    }
    
    @Override
    public float importeNomina(){
        return 30f * 43.20f;
    }
}
