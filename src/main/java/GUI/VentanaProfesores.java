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

    private final ProfesorManager manager;
    private final ProfesorTitularService titularService;
    private final ProfesorInterinoService interinoService;

    private JTextArea areaLog;
    private JComboBox<String> comboTipo;
    private JTextField txtNombre, txtApellidos, txtEdad, txtId;
    
    private JLabel lblFechaInterinidad;
    private JTextField txtFechaInterinidad; 
    

    public VentanaProfesores() {
        this.titularService = new ProfesorTitularService();
        this.interinoService = new ProfesorInterinoService();
        this.manager = new ProfesorManager(); 

        setTitle("Gestión de Profesores (Arquitectura de Servicios)");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        initComponents(); 
        
        areaLog.setText(manager.getListinProfesoresReport());
    }

    private void initComponents() {
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
        comboTipo = new JComboBox<>(new String[]{"Profesor Titular", "Profesor Interino"});
        panelForm.add(comboTipo);
        
        panelForm.add(new JLabel(" Nombre: "));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);
        
        panelForm.add(new JLabel(" Apellidos: "));
        txtApellidos = new JTextField();
        panelForm.add(txtApellidos);
        
        panelForm.add(new JLabel(" Edad: "));
        txtEdad = new JTextField();
        panelForm.add(txtEdad);
        
        panelForm.add(new JLabel(" ID Profesor: "));
        txtId = new JTextField();
        panelForm.add(txtId);

        lblFechaInterinidad = new JLabel(" Fecha Ingreso (DD/MM/AAAA): ");
        txtFechaInterinidad = new JTextField();
        
        panelForm.add(lblFechaInterinidad);
        panelForm.add(txtFechaInterinidad);

        JButton btnAgregar = new JButton(" Agregar Profesor ");
        panelForm.add(new JLabel());
        panelForm.add(btnAgregar);
        
        add(panelForm, BorderLayout.NORTH);

        btnMostrarListin.addActionListener(e -> {
            areaLog.setText(manager.getListinProfesoresReport());
        });

        btnCalcularTotal.addActionListener(e -> {
            float total = manager.getTotalNominas();
            areaLog.setText("El importe total de las nóminas es " + String.format("%.2f", total) + " euros");
        });
        
        btnAgregar.addActionListener(e -> accionAgregar());
        comboTipo.addActionListener(e -> actualizarFormularioEspecifico());
        
        actualizarFormularioEspecifico(); 
    }
    
    private void actualizarFormularioEspecifico() {
        String tipo = (String) comboTipo.getSelectedItem();
        boolean esInterino = "Profesor Interino".equals(tipo);
        
        lblFechaInterinidad.setVisible(esInterino);
        txtFechaInterinidad.setVisible(esInterino);
        limpiarFormulario();
    }

    private void accionAgregar() {
        try {
            String nombre = txtNombre.getText().trim();
            String apellidos = txtApellidos.getText().trim();
            int edad = Integer.parseInt(txtEdad.getText());
            String id = txtId.getText().trim();
            String tipo = (String) comboTipo.getSelectedItem();
            
            if (nombre.isEmpty() || id.isEmpty()) {
                 throw new IllegalArgumentException("Nombre e ID no pueden estar vacíos.");
            }

            Profesor nuevoProfesor = null;

            if ("Profesor Titular".equals(tipo)) {
                nuevoProfesor = titularService.crearProfesorTitular(nombre, apellidos, edad, id);
            
            } else if ("Profesor Interino".equals(tipo)) {
                Calendar fechaIngreso = parsearFecha(txtFechaInterinidad.getText().trim());
                
                nuevoProfesor = interinoService.crearProfesorInterino(nombre, apellidos, edad, id, fechaIngreso);
            }
            
            if (nuevoProfesor != null) {
                manager.addProfesor(nuevoProfesor);
                areaLog.setText("¡Profesor " + nuevoProfesor.getNombre() + " agregado!\n\n" + manager.getListinProfesoresReport());
                limpiarFormulario();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: La Edad debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Error de Validación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar profesor. Verifique los datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Calendar parsearFecha(String fechaStr) throws IllegalArgumentException {
        if (fechaStr.isEmpty()) {
            return Calendar.getInstance();
        }

        String[] partes = fechaStr.split("/");
        if (partes.length == 3) {
            try {
                int dia = Integer.parseInt(partes[0]);
                int mes = Integer.parseInt(partes[1]) - 1;
                int anio = Integer.parseInt(partes[2]);
                Calendar c = Calendar.getInstance();
                c.set(anio, mes, dia);
                return c;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Formato de fecha inválido (debe ser DD/MM/AAAA).");
            }
        }
        return Calendar.getInstance();
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtEdad.setText("");
        txtId.setText("");
        txtFechaInterinidad.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
            new VentanaProfesores().setVisible(true);
        });
    }
}