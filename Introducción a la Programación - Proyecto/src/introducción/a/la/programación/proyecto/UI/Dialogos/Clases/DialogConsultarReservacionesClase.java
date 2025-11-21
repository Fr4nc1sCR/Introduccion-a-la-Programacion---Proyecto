/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Lista todas las reservaciones registradas para las clases,
 * mostrando ID, nombre del empleado, tipo de clase y hora.
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Clases;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class DialogConsultarReservacionesClase extends JDialog {

    public DialogConsultarReservacionesClase(Frame owner) {
        super(owner, "Reservaciones de Clases", true);

        setSize(580, 420);
        setLocationRelativeTo(owner);
        setResizable(false);

        GradientPanel fondo = new GradientPanel();
        fondo.setLayout(new GridBagLayout());
        setContentPane(fondo);

        GlassCard card = new GlassCard(new BorderLayout(8, 8));
        card.setPreferredSize(new Dimension(390, 280));

        JLabel titulo = new JLabel("Reservaciones de Clases", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        titulo.setForeground(new Color(40, 35, 55));

        StringBuilder sb = new StringBuilder();

        if (Data.totalReservasClases == 0) {
            sb.append("No hay reservaciones registradas.");
        } else {
            sb.append("Reservaciones actuales:\n\n");
            for (int i = 0; i < Data.totalReservasClases; i++) {
                String id = Data.reservasClases[i][0];

                sb.append(i + 1).append(". ")
                        .append(Util.obtenerNombreEmpleado(id))
                        .append(" (").append(id).append(") - ")
                        .append(Data.reservasClases[i][1]).append(" a las ")
                        .append(Data.reservasClases[i][2]).append("\n");
            }
        }

        JTextArea area = new JTextArea(sb.toString());
        area.setOpaque(false);
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        ModernButton btnCerrar = new ModernButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.add(btnCerrar);

        card.add(titulo, BorderLayout.NORTH);
        card.add(scroll, BorderLayout.CENTER);
        card.add(bottom, BorderLayout.SOUTH);

        fondo.add(card);
    }
}
