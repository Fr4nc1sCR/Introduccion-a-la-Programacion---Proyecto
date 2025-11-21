/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Este panel dibuja un fondo con un degradado de colores suave. Se utiliza
 * como panel principal en las ventanas del sistema para dar un estilo moderno.
 */

package introducción.a.la.programación.proyecto.UI.Componentes;

import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {

    /**
     * Sobrescribe el pintado del panel para dibujar un fondo degradado.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                            RenderingHints.VALUE_RENDER_QUALITY);

        // Degradado suave rosado-morado
        GradientPaint gp = new GradientPaint(
                0, 0, new Color(255, 175, 189),     // Arriba
                getWidth(), getHeight(), new Color(131, 96, 195) // Abajo
        );

        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.dispose();
    }
}
