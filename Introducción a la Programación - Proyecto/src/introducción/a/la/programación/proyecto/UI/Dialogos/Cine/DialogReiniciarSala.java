/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Diálogo para reiniciar completamente la sala de Cine. Elimina todas las
 * reservaciones de asientos (Data.asientosCine) y solicita confirmación al
 * usuario antes de aplicar los cambios.
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Cine;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.UI.VentanaCine;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class DialogReiniciarSala extends JDialog {

    private final VentanaCine ventanaCine;

    /**
     * Constructor del diálogo.
     * @param owner VentanaCine que abre el diálogo.
     */
    public DialogReiniciarSala(VentanaCine owner) {
        super(owner, "Reiniciar sala", true);
        this.ventanaCine = owner;

        setSize(520, 360);
        setLocationRelativeTo(owner);
        setResizable(false);

        GradientPanel fondo = new GradientPanel();
        fondo.setLayout(new GridBagLayout());
        setContentPane(fondo);

        GlassCard card = new GlassCard(new GridBagLayout());
        card.setPreferredSize(new Dimension(660, 180));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 10, 8, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Reiniciar Sala de Cine");
        lblTitulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(40, 35, 55));

        JLabel lblMensaje = new JLabel(
                "<html>Esta acción eliminará <b>TODAS</b> las reservaciones de asientos.<br>" +
                "¿Desea continuar?</html>"
        );
        lblMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        ModernButton btnCancelar = new ModernButton("Cancelar");
        ModernButton btnReiniciar = new ModernButton("Reiniciar sala");

        btnCancelar.addActionListener(e -> dispose());
        btnReiniciar.addActionListener(e -> reiniciar());

        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        card.add(lblTitulo, c);

        c.gridy = 1;
        card.add(lblMensaje, c);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        panelBotones.setOpaque(false);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnReiniciar);

        c.gridy = 2;
        card.add(panelBotones, c);

        fondo.add(card);
    }

    /**
     * Limpia la matriz de asientos en Data y refresca el mapa en VentanaCine.
     */
    private void reiniciar() {

        // Poner todos los asientos a null (libres)
        for (int f = 0; f < 5; f++) {
            for (int c = 0; c < 6; c++) {
                Data.asientosCine[f][c] = null;
            }
        }

        if (ventanaCine != null) {
            ventanaCine.refrescarAsientos();
        }

        JOptionPane.showMessageDialog(this,
                "Sala reiniciada correctamente.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        dispose();
    }
}
