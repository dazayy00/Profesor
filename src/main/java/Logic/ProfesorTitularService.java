/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import Model.ProfesorTitular;

/**
 *
 * @author johan
 */
public class ProfesorTitularService {
    public ProfesorTitular crearProfesorTitular(String nombre, String apellidos, int edad, String id) {
        return new ProfesorTitular(nombre, apellidos, edad, id);
    }

    public float calcularNomina(ProfesorTitular profesor) {
        return 30f * 43.20f;
    }
}
