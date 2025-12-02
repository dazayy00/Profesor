/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GUI;

import Logic.*;
import Model.*;
import java.awt.*;
import java.util.Calendar;
import javax.swing.*;

/**
 *
 * @author johan
 */
public class VentanaProfesores extends JFrame {

    private final GestionProfesores logica;
    private JTextArea areaLog;
    private JComboBox<String> comboTipo;
    private JTextField txtNombre, txtApellidos, txtEdad, txtId;

    public VentanaProfesores() {
        this.logica = new GestionProfesores();

        setTitle("Gestión de Profesores (Abstract)");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        areaLog = new JTextArea();
        areaLog.setEditable(false);
        areaLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(areaLog), BorderLayout.CENTER);

        JPanel panelAcciones = new JPanel(new FlowLayout());
        JButton btnMostrarListin = new JButton("Mostrar Listín y Nóminas");
        JButton btnCalcularTotal = new JButton("Calcular Nómina Total");
        panelAcciones.add(btnMostrarListin);
        panelAcciones.add(btnCalcularTotal);
        add(panelAcciones, BorderLayout.SOUTH);

        JPanel panelForm = new JPanel(new GridLayout(0, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder(" Agregar Profesor "));
        
        panelForm.add(new JLabel(" Tipo: "));
        comboTipo = new JComboBox<>(new String[]{" Profesor Titular ", " Profesor Interino "});
        panelForm.add(comboTipo);
        
        panelForm.add(new JLabel("   Nombre:   "));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);
        
        panelForm.add(new JLabel("   Apellidos:   "));
        txtApellidos = new JTextField();
        panelForm.add(txtApellidos);
        
        panelForm.add(new JLabel("   Edad:   "));
        txtEdad = new JTextField();
        panelForm.add(txtEdad);
        
        panelForm.add(new JLabel("   ID Profesor:   "));
        txtId = new JTextField();
        panelForm.add(txtId);
        
        JButton btnAgregar = new JButton("   Agregar   ");
        panelForm.add(new JLabel());
        panelForm.add(btnAgregar);
        
        add(panelForm, BorderLayout.NORTH);

        btnMostrarListin.addActionListener(e -> {
            areaLog.setText(logica.getListinProfesores());
        });

        btnCalcularTotal.addActionListener(e -> {
            float total = logica.getTotalNominas();
            areaLog.setText("El importe de las nóminas del profesorado que consta en el listín es " 
                            + total + " euros");
        });
        
        btnAgregar.addActionListener(e -> accionAgregar());

        areaLog.setText(logica.getListinProfesores());
    }

    private void accionAgregar() {
        try {
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            String id = txtId.getText();
            String tipo = (String) comboTipo.getSelectedItem();

            if ("Profesor Titular".equals(tipo)) {
                logica.addProfesor(new ProfesorTitular(nombre, apellidos, edad, id));
            } else {
                Calendar fechaHoy = Calendar.getInstance();
                logica.addProfesor(new ProfesorInterino(nombre, apellidos, edad, id, fechaHoy));
            }
            
            areaLog.setText("¡Profesor agregado!\n\n" + logica.getListinProfesores());
            limpiarFormulario();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: La edad debe ser un número.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarFormulario() {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtEdad.setText("");
        txtId.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaProfesores().setVisible(true);
        });
    }
}
