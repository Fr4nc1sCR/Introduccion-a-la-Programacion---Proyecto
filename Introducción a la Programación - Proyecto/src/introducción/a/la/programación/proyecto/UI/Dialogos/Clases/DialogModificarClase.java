/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Permite cambiar la clase reservada por un empleado entre
 * Baile <-> Yoga, respetando cupos y validando existencia.
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Clases;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class DialogModificarClase extends JDialog {

    private final JTextField txtId;

    public DialogModificarClase(Frame owner) {
        super(owner, "Modificar Clase", true);

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

        JLabel titulo = new JLabel("Modificar Clase");
        titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        titulo.setForeground(new Color(40, 35, 55));

        txtId = new JTextField(15);

        ModernButton btnModificar = new ModernButton("Cambiar Clase");
        ModernButton btnCancelar = new ModernButton("Cancelar");

        btnModificar.addActionListener(e -> modificar());
        btnCancelar.addActionListener(e -> dispose());

        Dimension labelSize = new Dimension(140, 25);
        JLabel lblId = new JLabel("ID Empleado:");
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
        botones.add(btnModificar);

        c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
        card.add(botones, c);

        fondo.add(card);
    }

    private void modificar() {

        String id = txtId.getText().trim();

        if (id.isEmpty()) {
            Util.mostrarError("Debe ingresar un ID.");
            return;
        }

        if (!Util.existeEmpleado(id)) {
            Util.mostrarError("Empleado no existe.");
            return;
        }

        boolean encontrado = false;

        for (int i = 0; i < Data.totalReservasClases; i++) {
            if (Data.reservasClases[i][0].equals(id)) {
                String claseActual = Data.reservasClases[i][1];

                if (claseActual.equals("Clase de Baile")) {
                    if (Data.cupoYoga >= 30) {
                        Util.mostrarError("No hay cupo para Yoga.");
                        return;
                    }
                    Data.reservasClases[i][1] = "Clase de Yoga";
                    Data.reservasClases[i][2] = "8:00 pm";
                    Data.cupoBaile--;
                    Data.cupoYoga++;
                } else {
                    if (Data.cupoBaile >= 30) {
                        Util.mostrarError("No hay cupo para Baile.");
                        return;
                    }
                    Data.reservasClases[i][1] = "Clase de Baile";
                    Data.reservasClases[i][2] = "7:00 pm";
                    Data.cupoYoga--;
                    Data.cupoBaile++;
                }

                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            Util.mostrarError("El empleado no tiene reservación.");
        } else {
            JOptionPane.showMessageDialog(this, "Clase modificada con éxito.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }
}
