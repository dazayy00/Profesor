/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Calendar;

/**
 *
 * @author johan
 */
public class ProfesorInterino extends Profesor{
    private Calendar fechaComienzoInterinidad;

    public ProfesorInterino(String nombre, String apellidos, int edad, String id, Calendar fechaComienzoInterinidad) {
        super(nombre, apellidos, edad, id);
        this.fechaComienzoInterinidad = fechaComienzoInterinidad;
    }

    public Calendar getFechaComienzoInterinidad() {
        return fechaComienzoInterinidad;
    }
    
    @Override
    public String toString(){
        return super.toString().concat(" Fecha comienzo interinidad ")
                .concat(fechaComienzoInterinidad.getTime().toString());
    }
    
    @Override
    public float importeNomina(){
        return 30f * 43.20f;
    }
}
