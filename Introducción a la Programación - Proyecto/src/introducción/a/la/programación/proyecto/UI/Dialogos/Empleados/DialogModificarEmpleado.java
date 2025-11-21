/**
 * Dialogo para modificar un empleado existente.
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Empleados;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class DialogModificarEmpleado extends JDialog {

    private JTextField txtId, txtNombre;
    private String idOriginal;

    public DialogModificarEmpleado(Frame owner, String id) {
        super(owner, "Modificar Empleado", true);
        this.idOriginal = id;

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

        JLabel titulo = new JLabel("Modificar Empleado");
        titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
        titulo.setForeground(new Color(40, 35, 55));

        txtId = new JTextField(12);
        txtNombre = new JTextField(18);

        cargarDatos();

        ModernButton btnGuardar = new ModernButton("Guardar");
        ModernButton btnCancelar = new ModernButton("Cancelar");

        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> dispose());

        Dimension labelSize = new Dimension(140, 25);

        JLabel lblId = new JLabel("Nuevo ID:");
        lblId.setPreferredSize(labelSize);

        JLabel lblNombre = new JLabel("Nuevo Nombre:");
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

    private void cargarDatos() {
        for (int i = 0; i < Data.empleados.length; i++) {
            if (idOriginal.equals(Data.empleados[i])) {
                txtId.setText(Data.empleados[i]);
                txtNombre.setText(Data.nombres[i]);
                return;
            }
        }
    }

    private void guardar() {
        String nuevoId = txtId.getText().trim();
        String nuevoNombre = txtNombre.getText().trim();

        if (nuevoId.isEmpty() || nuevoNombre.isEmpty()) {
            Util.mostrarError("Ningún campo puede estar vacío.");
            return;
        }

        // Verificar ID duplicado
        if (!nuevoId.equals(idOriginal)) {
            for (String emp : Data.empleados) {
                if (nuevoId.equals(emp)) {
                    Util.mostrarError("Ya existe un empleado con ese ID.");
                    return;
                }
            }
        }

        // Modificar
        for (int i = 0; i < Data.empleados.length; i++) {
            if (Data.empleados[i] != null &&
                Data.empleados[i].equals(idOriginal)) {

                Data.empleados[i] = nuevoId;
                Data.nombres[i] = nuevoNombre;

                JOptionPane.showMessageDialog(this,
                        "Empleado modificado con éxito.");
                dispose();
                return;
            }
        }
    }
}
