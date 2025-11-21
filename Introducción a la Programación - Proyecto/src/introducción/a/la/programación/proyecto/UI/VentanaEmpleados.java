/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 22/11/2025
 *
 * Descripción:
 * Ventana principal del módulo de empleados. Permite ver la lista de empleados,
 * crear nuevos empleados, modificarlos y eliminarlos.
 */

package introducción.a.la.programación.proyecto.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.UI.Dialogos.Empleados.*;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class VentanaEmpleados extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    public VentanaEmpleados() {

        setTitle("Gestión de Empleados");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        GradientPanel fondo = new GradientPanel();
        fondo.setLayout(new BorderLayout());
        setContentPane(fondo);

        // --------- Título ---------
        JLabel titulo = new JLabel("Registro de Empleados", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(Color.WHITE);
        fondo.add(titulo, BorderLayout.NORTH);

        // --------- Tabla ---------
        modelo = new DefaultTableModel(new String[]{"ID", "Nombre"}, 0);
        tabla = new JTable(modelo);
        tabla.setRowHeight(24);
        cargarEmpleados();

        JScrollPane scroll = new JScrollPane(tabla);
        fondo.add(scroll, BorderLayout.CENTER);

        // --------- Botones ---------
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setOpaque(false);

        ModernButton btnCrear = new ModernButton("Crear");
        ModernButton btnModificar = new ModernButton("Modificar");
        ModernButton btnEliminar = new ModernButton("Eliminar");

        btnCrear.addActionListener(e -> {
            new DialogCrearEmpleado(this).setVisible(true);
            recargarTabla();
        });

        btnModificar.addActionListener(e -> modificar());
        btnEliminar.addActionListener(e -> eliminar());

        panelBotones.add(btnCrear);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);

        fondo.add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void cargarEmpleados() {
        for (int i = 0; i < Data.empleados.length; i++) {
            if (Data.empleados[i] != null) {
                modelo.addRow(new Object[]{
                        Data.empleados[i],
                        Data.nombres[i]
                });
            }
        }
    }

    private void recargarTabla() {
        modelo.setRowCount(0);
        cargarEmpleados();
    }

    private void modificar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            Util.mostrarError("Debe seleccionar un empleado.");
            return;
        }

        String id = modelo.getValueAt(fila, 0).toString();

        new DialogModificarEmpleado(this, id).setVisible(true);
        recargarTabla();
    }

    private void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            Util.mostrarError("Seleccione un empleado a eliminar.");
            return;
        }

        String id = modelo.getValueAt(fila, 0).toString();

        new DialogEliminarEmpleado(this, id).setVisible(true);
        recargarTabla();
    }
}
