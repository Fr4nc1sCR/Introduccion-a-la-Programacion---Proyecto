/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Permite cambiar el horario reservado por un empleado, validando:
 *  - Que exista
 *  - Que tenga una reservación previa
 *  - Que el nuevo horario esté libre
 *  - Que no intente cambiarse al MISMO horario que ya tiene
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Gimnasio;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class DialogModificarGym extends JDialog {

    private final JTextField txtId;
    private final JComboBox<String> comboNuevo;

    public DialogModificarGym(Frame owner) {
        super(owner, "Modificar Reserva Gimnasio", true);

        setSize(540, 400);
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

        JLabel titulo = new JLabel("Modificar Reservación");
        titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        titulo.setForeground(new Color(40, 35, 55));

        txtId = new JTextField(15);
        comboNuevo = new JComboBox<>(Data.horariosGym);

        ModernButton btnModificar = new ModernButton("Guardar");
        ModernButton btnCancelar = new ModernButton("Cancelar");

        btnModificar.addActionListener(e -> modificar());
        btnCancelar.addActionListener(e -> dispose());

        Dimension labelSize = new Dimension(140, 25);

        JLabel lblId = new JLabel("ID Empleado:");
        lblId.setPreferredSize(labelSize);

        JLabel lblNuevo = new JLabel("Nuevo horario:");
        lblNuevo.setPreferredSize(labelSize);

        // -------- Diseño de la tarjeta --------
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        card.add(titulo, c);

        c.gridy = 1; c.gridwidth = 1; c.gridx = 0;
        card.add(lblId, c);
        c.gridx = 1;
        card.add(txtId, c);

        c.gridx = 0; c.gridy = 2;
        card.add(lblNuevo, c);
        c.gridx = 1;
        card.add(comboNuevo, c);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        botones.setOpaque(false);
        botones.add(btnCancelar);
        botones.add(btnModificar);

        c.gridx = 0; c.gridy = 3; c.gridwidth = 2;
        card.add(botones, c);

        fondo.add(card);
    }

    /**
     * Valida y ejecuta la modificación del horario reservado por el empleado.
     * Incluye validación nueva: impedir modificar a la misma hora.
     */
    private void modificar() {
        String id = txtId.getText().trim();

        if (id.isEmpty()) {
            Util.mostrarError("Debe ingresar el ID.");
            return;
        }

        // -------- BUSCAR RESERVA ACTUAL --------
        int actual = -1;
        for (int i = 0; i < Data.reservasGym.length; i++) {
            if (id.equals(Data.reservasGym[i])) {
                actual = i;
                break;
            }
        }

        if (actual == -1) {
            Util.mostrarError("No tiene reservación.");
            return;
        }

        // -------- NUEVO ÍNDICE --------
        int nuevo = comboNuevo.getSelectedIndex();

        // -------- ❗ VALIDACIÓN NUEVA --------
        if (nuevo == actual) {
            Util.mostrarError("No puede modificar la reservación a la MISMA hora.");
            return;
        }

        // -------- VALIDAR SI ESTÁ OCUPADO --------
        if (Data.reservasGym[nuevo] != null && !Data.reservasGym[nuevo].equals(id)) {
            Util.mostrarError("Ese horario está ocupado.");
            return;
        }

        // -------- APLICAR CAMBIO --------
        Data.reservasGym[actual] = null; // liberar horario viejo
        Data.reservasGym[nuevo] = id;    // asignar nuevo

        JOptionPane.showMessageDialog(
                this,
                "Reservación modificada con éxito.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
        );

        dispose();
    }
}