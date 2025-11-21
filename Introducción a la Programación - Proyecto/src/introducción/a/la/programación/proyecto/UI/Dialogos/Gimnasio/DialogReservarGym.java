/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Diálogo para permitir al usuario reservar un horario del gimnasio.
 * Valida ID del empleado, verifica disponibilidad y guarda la reservación.
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Gimnasio;

import javax.swing.*;
import java.awt.*;
import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class DialogReservarGym extends JDialog {

    private final JTextField txtId;
    private final JComboBox<String> comboHorario;

    /**
     * Constructor del diálogo de reserva.
     */
    public DialogReservarGym(Frame owner) {
        super(owner, "Reservar Gimnasio", true);

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

        JLabel titulo = new JLabel("Reservar Gimnasio");
        titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        titulo.setForeground(new Color(40, 35, 55));

        txtId = new JTextField(15);
        comboHorario = new JComboBox<>(Data.horariosGym);

        ModernButton btnReservar = new ModernButton("Reservar");
        ModernButton btnCancelar = new ModernButton("Cancelar");

        btnReservar.addActionListener(e -> reservar());
        btnCancelar.addActionListener(e -> dispose());

        Dimension labelSize = new Dimension(140, 25);
        JLabel lblId = new JLabel("ID Empleado:");
        lblId.setPreferredSize(labelSize);

        JLabel lblHorario = new JLabel("Horario:");
        lblHorario.setPreferredSize(labelSize);

        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        card.add(titulo, c);

        c.gridy = 1; c.gridwidth = 1;
        card.add(lblId, c);
        c.gridx = 1;
        card.add(txtId, c);

        c.gridx = 0; c.gridy = 2;
        card.add(lblHorario, c);
        c.gridx = 1;
        card.add(comboHorario, c);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        botones.setOpaque(false);
        botones.add(btnCancelar);
        botones.add(btnReservar);

        c.gridx = 0; c.gridy = 3; c.gridwidth = 2;
        card.add(botones, c);

        fondo.add(card);
    }

    /**
     * Lógica para reservar un horario.
     */
    private void reservar() {
        String id = txtId.getText().trim();
        int index = comboHorario.getSelectedIndex();

        if (id.isEmpty()) {
            Util.mostrarError("Debe ingresar el ID.");
            return;
        }

        if (!Util.existeEmpleado(id)) {
            Util.mostrarError("El empleado no existe.");
            return;
        }

        for (String res : Data.reservasGym) {
            if (id.equals(res)) {
                Util.mostrarError("Ya tiene un horario reservado.");
                return;
            }
        }

        if (Data.reservasGym[index] != null) {
            Util.mostrarError("Ese horario ya está ocupado.");
            return;
        }

        Data.reservasGym[index] = id;

        JOptionPane.showMessageDialog(this,
                "Horario " + Data.horariosGym[index] + " reservado exitosamente.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        dispose();
    }
}
