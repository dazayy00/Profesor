/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import Model.ProfesorInterino;
import java.util.Calendar;

/**
 *
 * @author johan
 */
public class ProfesorInterinoService {
    public ProfesorInterino crearProfesorInterino(String nombre, String apellidos, int edad, String id, Calendar fechaComienzo) {
        return new ProfesorInterino(nombre, apellidos, edad, id, fechaComienzo);
    }
    
    public float calcularNomina(ProfesorInterino profesor) {
        return 30f * 43.20f;
    }
}
