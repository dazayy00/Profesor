/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import Model.Profesor;
import Model.ProfesorInterino;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author johan
 */
public class ProfesorManager {
    
    private ArrayList<Profesor> listinProfesores;
    
    private ProfesorInterinoService interinoService;
    private ProfesorTitularService titularService;

    public ProfesorManager() {
        this.listinProfesores = new ArrayList<>();
        this.interinoService = new ProfesorInterinoService();
        this.titularService = new ProfesorTitularService();
        
        cargarDatosIniciales();
    }
    
    public void addProfesor(Profesor profesor){
        listinProfesores.add(profesor);
    }
    
    private void cargarDatosIniciales(){
        Calendar fecha1 = Calendar.getInstance();
        fecha1.set(2019, 10, 22);
        
        addProfesor(interinoService.crearProfesorInterino("Johan", "Baltazar", 23, "S21020562", fecha1));
        addProfesor(interinoService.crearProfesorInterino("Jose", "Balcazar", 24, "s21020567", fecha1));
        addProfesor(titularService.crearProfesorTitular("David", "Trinidad", 28, "s21027954"));
        addProfesor(titularService.crearProfesorTitular("deivid", "Trinity", 30, "s210211032")); 
    }
    
    public String getListinProfesoresReport() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("--- DATOS DE PROFESORES ---\n\n");
        
        for(Profesor tmp : listinProfesores){
            sb.append(tmp.toString()).append("\n");
            
            String tipo;
            if(tmp instanceof ProfesorInterino){
                tipo = "Interino ";
            } else {
                tipo = "Titular ";
            }
            
            sb.append("Tipo de profesor: ").append(tipo);
            sb.append("Nomina: ").append(tmp.importeNomina()).append(" €\n\n");
        }
        
        sb.append("============================\n");
        sb.append("TOTAL NÓMINA GENERAL: ").append(getTotalNominas()).append(" €");
        
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
