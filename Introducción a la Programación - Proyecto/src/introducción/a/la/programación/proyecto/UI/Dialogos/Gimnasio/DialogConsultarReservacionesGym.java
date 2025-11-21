/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Muestra todas las reservaciones actuales del gimnasio con ID,
 * nombre del empleado y horario asignado.
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Gimnasio;

import javax.swing.*;
import java.awt.*;
import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class DialogConsultarReservacionesGym extends JDialog {

    public DialogConsultarReservacionesGym(Frame owner) {
        super(owner, "Reservaciones del Gimnasio", true);

        setSize(680, 420);
        setLocationRelativeTo(owner);
        setResizable(false);

        GradientPanel fondo = new GradientPanel();
        fondo.setLayout(new GridBagLayout());
        setContentPane(fondo);

        StringBuilder texto = new StringBuilder();
        boolean hay = false;

        for (int i = 0; i < Data.reservasGym.length; i++) {
            if (Data.reservasGym[i] != null) {
                if (!hay) {
                    texto.append("Reservaciones actuales:\n\n");
                    hay = true;
                }
                texto.append("- ")
                        .append(Util.obtenerNombreEmpleado(Data.reservasGym[i]))
                        .append(" (").append(Data.reservasGym[i]).append(") - ")
                        .append(Data.horariosGym[i]).append("\n");
            }
        }

        if (!hay) {
            texto.append("No hay reservaciones registradas.");
        }

        JTextArea area = new JTextArea(texto.toString());
        area.setOpaque(false);
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        GlassCard card = new GlassCard(new BorderLayout());
        card.setPreferredSize(new Dimension(265, 220));
        card.add(scroll, BorderLayout.CENTER);

        fondo.add(card);
    }
}
