/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Este diálogo permite registrar una reservación para las clases de
 * Baile o Yoga. Incluye validaciones importantes:
 *
 *  - El empleado debe existir
 *  - No puede tener más de una reservación activa
 *  - Si ya reservó, solo puede MODIFICAR su clase (desde otro diálogo)
 *
 * El diseño visual utiliza componentes personalizados como:
 *  - GradientPanel
 *  - GlassCard
 *  - ModernButton
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Clases;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class DialogReservarClase extends JDialog {

    private final JTextField txtId;
    private final JComboBox<String> comboClase;

    public DialogReservarClase(Frame owner) {
        super(owner, "Reservar Clase", true);

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

        JLabel titulo = new JLabel("Reservar Clase");
        titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
        titulo.setForeground(new Color(40, 35, 55));

        txtId = new JTextField(15);
        comboClase = new JComboBox<>(new String[]{
                "Clase de Baile",
                "Clase de Yoga"
        });

        ModernButton btnConfirmar = new ModernButton("Confirmar");
        ModernButton btnCancelar = new ModernButton("Cancelar");

        btnConfirmar.addActionListener(e -> confirmar());
        btnCancelar.addActionListener(e -> dispose());

        Dimension labelSize = new Dimension(140, 25);

        JLabel lblId = new JLabel("ID Empleado:");
        lblId.setPreferredSize(labelSize);

        JLabel lblClase = new JLabel("Clase:");
        lblClase.setPreferredSize(labelSize);

        // ----------- Diseño de la tarjeta -----------
        c.gridx = 0; 
        c.gridy = 0; 
        c.gridwidth = 2;
        card.add(titulo, c);

        c.gridy = 1; 
        c.gridwidth = 1;
        card.add(lblId, c);
        c.gridx = 1;
        card.add(txtId, c);

        c.gridx = 0; 
        c.gridy = 2;
        card.add(lblClase, c);
        c.gridx = 1;
        card.add(comboClase, c);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        botones.setOpaque(false);
        botones.add(btnCancelar);
        botones.add(btnConfirmar);

        c.gridx = 0; 
        c.gridy = 3; 
        c.gridwidth = 2;
        card.add(botones, c);

        fondo.add(card);
    }

    /**
     * Valida y registra la nueva reservación para el empleado.
     * Incluye regla especial:
     *
     *  ❗ SOLO UNA RESERVACIÓN POR EMPLEADO
     */
    private void confirmar() {

        String id = txtId.getText().trim();
        String tipoClase = (String) comboClase.getSelectedItem();

        if (id.isEmpty()) {
            Util.mostrarError("Debe ingresar el ID del empleado.");
            return;
        }

        // -------- VALIDAR QUE EL EMPLEADO EXISTA --------
        if (!Util.existeEmpleado(id)) {
            Util.mostrarError("El empleado no existe.");
            return;
        }

        // -------- VALIDACIÓN NUEVA: SOLO UNA RESERVACIÓN --------
        for (int i = 0; i < Data.totalReservasClases; i++) {
            if (Data.reservasClases[i][0].equals(id)) {
                Util.mostrarError(
                        "Este empleado YA tiene una reservación.\n" +
                        "Solo puede CAMBIARSE de clase (baile ↔ yoga)."
                );
                return;
            }
        }

        // -------- Registrar nueva reservación --------
        int index = Data.totalReservasClases;

        Data.reservasClases[index][0] = id;
        Data.reservasClases[index][1] = tipoClase;
        Data.reservasClases[index][2] = tipoClase.equals("Clase de Baile")
                ? "7:00 pm"
                : "8:00 pm";

        // Actualizar cupos
        if (tipoClase.equals("Clase de Baile")) {
            Data.cupoBaile++;
        } else {
            Data.cupoYoga++;
        }

        Data.totalReservasClases++;

        JOptionPane.showMessageDialog(
                this,
                "Clase reservada exitosamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
        );

        dispose();
    }
}
