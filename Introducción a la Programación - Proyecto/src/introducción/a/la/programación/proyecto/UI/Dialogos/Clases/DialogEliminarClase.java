/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Permite eliminar una reservación de clase según el ID del empleado.
 * Ajusta los cupos disponibles y valida que exista la reservación.
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Clases;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class DialogEliminarClase extends JDialog {

    private final JTextField txtId;

    public DialogEliminarClase(Frame owner) {
        super(owner, "Eliminar Reservación de Clase", true);

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

        JLabel titulo = new JLabel("Eliminar Reservación");
        titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        titulo.setForeground(new Color(40, 35, 55));

        txtId = new JTextField(15);

        ModernButton btnEliminar = new ModernButton("Eliminar");
        ModernButton btnCancelar = new ModernButton("Cancelar");

        btnEliminar.addActionListener(e -> eliminar());
        btnCancelar.addActionListener(e -> dispose());

        Dimension labelSize = new Dimension(120, 25);
        JLabel lblId = new JLabel("ID:");
        lblId.setPreferredSize(labelSize);

        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        card.add(titulo, c);

        c.gridy = 1; c.gridwidth = 1;
        card.add(lblId, c);
        c.gridx = 1;
        card.add(txtId, c);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        botones.setOpaque(false);
        botones.add(btnCancelar);
        botones.add(btnEliminar);

        c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
        card.add(botones, c);

        fondo.add(card);
    }

    private void eliminar() {
        String id = txtId.getText().trim();

        if (id.isEmpty()) {
            Util.mostrarError("Debe ingresar un ID.");
            return;
        }

        boolean encontrado = false;

        for (int i = 0; i < Data.totalReservasClases; i++) {
            if (Data.reservasClases[i][0].equals(id)) {

                String tipo = Data.reservasClases[i][1];

                for (int j = i; j < Data.totalReservasClases - 1; j++) {
                    Data.reservasClases[j] = Data.reservasClases[j + 1];
                }

                Data.reservasClases[Data.totalReservasClases - 1] = new String[3];
                Data.totalReservasClases--;

                if (tipo.equals("Clase de Baile")) {
                    Data.cupoBaile--;
                } else {
                    Data.cupoYoga--;
                }

                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            Util.mostrarError("No existe reservación asociada a ese ID.");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Reservación eliminada exitosamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }
}
