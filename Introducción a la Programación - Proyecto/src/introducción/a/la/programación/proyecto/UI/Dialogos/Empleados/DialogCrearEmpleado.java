/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 22/11/2025
 *
 * Descripción:
 * Este diálogo permite registrar nuevos empleados en el sistema, validando que:
 *  - No existan ID duplicados
 *  - Los campos no estén vacíos
 *  - Haya espacio disponible en el arreglo
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Empleados;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class DialogCrearEmpleado extends JDialog {

    private JTextField txtId;
    private JTextField txtNombre;

    public DialogCrearEmpleado(Frame owner) {
        super(owner, "Registrar Nuevo Empleado", true);

        setSize(530, 380);
        setLocationRelativeTo(owner);
        setResizable(false);

        GradientPanel fondo = new GradientPanel();
        fondo.setLayout(new GridBagLayout());
        setContentPane(fondo);

        GlassCard card = new GlassCard(new GridBagLayout());
        card.setPreferredSize(new Dimension(580, 320));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 10, 8, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Registrar Empleado");
        titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
        titulo.setForeground(new Color(40, 35, 55));

        txtId = new JTextField(12);
        txtNombre = new JTextField(18);

        ModernButton btnGuardar = new ModernButton("Guardar");
        ModernButton btnCancelar = new ModernButton("Cancelar");

        btnGuardar.addActionListener(e -> guardarEmpleado());
        btnCancelar.addActionListener(e -> dispose());

        Dimension labelSize = new Dimension(140, 25);

        JLabel lblId = new JLabel("ID del empleado:");
        JLabel lblNombre = new JLabel("Nombre completo:");
        lblId.setPreferredSize(labelSize);
        lblNombre.setPreferredSize(labelSize);

        // Layout
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        card.add(titulo, c);

        c.gridy = 1; c.gridwidth = 1;
        card.add(lblId, c);
        c.gridx = 1;
        card.add(txtId, c);

        c.gridx = 0; c.gridy = 2;
        card.add(lblNombre, c);
        c.gridx = 1;
        card.add(txtNombre, c);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        botones.setOpaque(false);
        botones.add(btnCancelar);
        botones.add(btnGuardar);

        c.gridx = 0; c.gridy = 3; c.gridwidth = 2;
        card.add(botones, c);

        fondo.add(card);
    }

    /**
     * Guarda el empleado en Data.empleados y Data.nombres
     * con las validaciones respectivas.
     */
    private void guardarEmpleado() {

        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();

        if (id.isEmpty() || nombre.isEmpty()) {
            Util.mostrarError("Debe llenar todos los campos.");
            return;
        }

        // Validar ID duplicado
        for (String emp : Data.empleados) {
            if (id.equals(emp)) {
                Util.mostrarError("Ya existe un empleado con ese ID.");
                return;
            }
        }

        // Buscar espacio vacío
        int pos = -1;
        for (int i = 0; i < Data.empleados.length; i++) {
            if (Data.empleados[i] == null) {
                pos = i;
                break;
            }
        }

        if (pos == -1) {
            Util.mostrarError("No hay espacio para registrar más empleados.");
            return;
        }

        // Registrar empleado
        Data.empleados[pos] = id;
        Data.nombres[pos] = nombre;

        JOptionPane.showMessageDialog(
                this,
                "Empleado registrado exitosamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
        );

        dispose();
    }
}