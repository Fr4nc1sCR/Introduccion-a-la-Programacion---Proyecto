/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Esta clase define un botón personalizado con apariencia moderna utilizando
 * efectos de glassmorphism, hover visual, esquinas redondeadas y sombreados.
 * Se utiliza en toda la interfaz para mantener un estilo uniforme.
 */

package introducción.a.la.programación.proyecto.UI.Componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModernButton extends JButton {

    // Colores base usando transparencia (glassmorphism)
    private final Color base = new Color(255, 255, 255, 70);
    private final Color hover = new Color(255, 255, 255, 110);
    private final Color pressed = new Color(255, 255, 255, 150);

    // Estados visuales
    private boolean isHover = false;
    private boolean isPressed = false;

    /**
     * Constructor principal: recibe el texto del botón
     * @param text Texto del botón
     */
    public ModernButton(String text) {
        super(text);

        // Configuración visual y comportamiento
        setFont(new Font("Segoe UI", Font.BOLD, 17));
        setForeground(new Color(40, 35, 55));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Espaciado interno
        setBorder(BorderFactory.createEmptyBorder(14, 30, 14, 30));

        // Listeners para animación hover/pressed
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                isHover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHover = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                isPressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
                repaint();
            }
        });
    }

    /**
     * Método que redibuja el botón con bordes, sombreados
     * y efectos de transparencia según el estado actual.
     */
    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        // Determinar color según el estado
        Color bg = base;
        if (isPressed) bg = pressed;
        else if (isHover) bg = hover;

        // Sombra
        g2.setColor(new Color(0, 0, 0, 60));
        g2.fillRoundRect(4, 6, w - 8, h - 8, 24, 24);

        // Fondo translúcido
        g2.setColor(bg);
        g2.fillRoundRect(0, 0, w - 8, h - 10, 24, 24);

        // Borde claro
        g2.setColor(new Color(255, 255, 255, 170));
        g2.setStroke(new BasicStroke(1.4f));
        g2.drawRoundRect(0, 0, w - 8, h - 10, 24, 24);

        g2.dispose();
        super.paintComponent(g);
    }
}
