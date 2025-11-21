/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Esta clase representa una "tarjeta" con efecto glassmorphism. Se usa para
 * agrupar botones y contenidos dentro de la interfaz, generando un estilo
 * moderno con transparencia y borde brillante.
 */

package introducción.a.la.programación.proyecto.UI.Componentes;

import javax.swing.*;
import java.awt.*;

public class GlassCard extends JPanel {

    /**
     * Constructor que recibe un LayoutManager para organizar el contenido.
     */
    public GlassCard(LayoutManager layout) {
        super(layout);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(18, 22, 18, 22));
    }

    /**
     * Dibuja la tarjeta con efecto de transparencia, borde y sombra.
     */
    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        // Sombra detrás
        g2.setColor(new Color(0, 0, 0, 90));
        g2.fillRoundRect(8, 10, w - 16, h - 16, 32, 32);

        // Capa translúcida principal
        g2.setColor(new Color(255, 255, 255, 60));
        g2.fillRoundRect(0, 0, w - 10, h - 12, 32, 32);

        // Borde brillante
        g2.setColor(new Color(255, 255, 255, 180));
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRoundRect(0, 0, w - 10, h - 12, 32, 32);

        g2.dispose();
        super.paintComponent(g);
    }
}