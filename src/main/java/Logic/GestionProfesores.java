/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import Model.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author johan
 */
public class GestionProfesores {
    private ArrayList<Profesor> listinProfesores;

    public GestionProfesores() {
        listinProfesores = new ArrayList<>();
        cargarDatosIniciales();
    }
    
    public void addProfesor(Profesor profesor){
        listinProfesores.add(profesor);
    }
    
    private void cargarDatosIniciales(){
        Calendar fecha1 = Calendar.getInstance();
        fecha1.set(2019, 10, 22);
        
        addProfesor(new ProfesorInterino("Johan", "Baltazar", 23, "S21020562", fecha1));
        addProfesor(new ProfesorInterino("Jose", "Balcazar", 24, "s21020567", fecha1));
        addProfesor(new ProfesorTitular("David", "Trinidad", 28, "s21027954"));
        addProfesor(new ProfesorTitular("deivid", "Trinity", 30, "s210211032"));  
    }
    
    public String getListinProfesores(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("Se procede a mostrar datos de profesores existentes \n\n");
        
        for(Profesor tmp : listinProfesores){
            sb.append(tmp.toString()).append("\n");
            
            String tmpStr1;
            
            if(tmp instanceof ProfesorInterino){
                tmpStr1 = "Interino ";
            } else {
                tmpStr1 = "Titular ";
            }
            
            sb.append("Tipo de profesor ").append(tmpStr1);
            sb.append("Nomina de este profesor ").append(tmp.importeNomina()).append("\n");
        }
        return sb.toString();
    }
    
    public float getTotalNominas(){
        float importeTotal = 0f;
        for(Profesor tmp : listinProfesores){
            importeTotal += tmp.importeNomina();
        }
        return importeTotal;
    }
}
