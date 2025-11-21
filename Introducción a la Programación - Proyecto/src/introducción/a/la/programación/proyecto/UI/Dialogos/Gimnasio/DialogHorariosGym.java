/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Muestra una lista de horarios del gimnasio indicando si están
 * libres u ocupados, junto con el nombre del empleado.
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Gimnasio;

import javax.swing.*;
import java.awt.*;
import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class DialogHorariosGym extends JDialog {

    public DialogHorariosGym(Frame owner) {
        super(owner, "Horarios del Gimnasio", true);

        setSize(640, 380);
        setLocationRelativeTo(owner);
        setResizable(false);

        GradientPanel fondo = new GradientPanel();
        fondo.setLayout(new GridBagLayout());
        setContentPane(fondo);

        StringBuilder texto = new StringBuilder("Horarios:\n\n");

        for (int i = 0; i < Data.horariosGym.length; i++) {
            if (Data.reservasGym[i] == null) {
                texto.append("- ").append(Data.horariosGym[i]).append(": Libre\n");
            } else {
                texto.append("- ").append(Data.horariosGym[i]).append(": Ocupado por ")
                        .append(Util.obtenerNombreEmpleado(Data.reservasGym[i])).append("\n");
            }
        }

        JTextArea area = new JTextArea(texto.toString());
        area.setOpaque(false);
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        GlassCard card = new GlassCard(new BorderLayout());
        card.setPreferredSize(new Dimension(310, 220));
        card.add(scroll, BorderLayout.CENTER);

        fondo.add(card);
    }
}
