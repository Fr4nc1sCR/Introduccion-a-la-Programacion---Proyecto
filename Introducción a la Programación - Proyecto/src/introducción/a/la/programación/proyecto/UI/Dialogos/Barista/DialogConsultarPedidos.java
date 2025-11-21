/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 22/11/2025
 *
 * Descripción:
 * Muestra todos los pedidos registrados en el módulo de Barista.
 * Incluye ID del empleado, nombre, bebida seleccionada y hora.
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Barista;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class DialogConsultarPedidos extends JDialog {

    public DialogConsultarPedidos(Frame owner) {
        super(owner, "Pedidos Registrados", true);

        setSize(700, 420);
        setLocationRelativeTo(owner);
        setResizable(false);

        GradientPanel fondo = new GradientPanel();
        fondo.setLayout(new GridBagLayout());
        setContentPane(fondo);

        GlassCard card = new GlassCard(new BorderLayout());
        card.setPreferredSize(new Dimension(440, 320));

        JLabel titulo = new JLabel("Pedidos Registrados", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        titulo.setForeground(new Color(40, 35, 55));

        StringBuilder sb = new StringBuilder();

        if (Data.totalPedidos == 0) {
            sb.append("No hay pedidos.");
        } else {
            sb.append("Pedidos actuales:\n\n");

            for (int i = 0; i < Data.totalPedidos; i++) {
                String id = Data.pedidosBarista[i][0];
                sb.append(i + 1).append(". ")
                        .append(Util.obtenerNombreEmpleado(id)).append(" (").append(id).append(") - ")
                        .append(Data.pedidosBarista[i][1]).append(" a las ")
                        .append(Data.pedidosBarista[i][2]).append("\n");
            }
        }

        JTextArea area = new JTextArea(sb.toString());
        area.setOpaque(false);
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

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
