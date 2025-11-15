package introducción.a.la.programación.proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Proyecto Final - Introducción a la Programación
 * Integrantes:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 */
public class IntroducciónALaProgramaciónProyecto {

    // ===== Variables globales =====
    static String[] empleados;
    static String[] nombres;
    static String[] horariosGym;
    static String[] reservasGym;
    static String pelicula;
    static String[][] asientosCine;
    static String[][] reservasClases;
    static int cupoBaile = 0;
    static int cupoYoga = 0;
    static int totalReservasClases = 0;
    static String[][] pedidosBarista;
    static int totalPedidos = 0;

    // ===== MAIN =====
    public static void main(String[] args) {
        cargarDatos();
        SwingUtilities.invokeLater(VentanaPrincipal::new);
    }

    // =========================================================
    // ===================== BOTÓN GLASSMORPHISM ===============
    // =========================================================
    static class ModernButton extends JButton {
        private final Color base = new Color(255, 255, 255, 70);
        private final Color hover = new Color(255, 255, 255, 110);
        private final Color pressed = new Color(255, 255, 255, 150);

        private boolean isHover = false;
        private boolean isPressed = false;

        public ModernButton(String text) {
            super(text);
            setFont(new Font("Segoe UI", Font.BOLD, 17));
            setForeground(new Color(40, 35, 55));
            setContentAreaFilled(false);
            setFocusPainted(false);
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Espacio interno generoso
            setBorder(BorderFactory.createEmptyBorder(14, 30, 14, 30));
            setHorizontalAlignment(SwingConstants.CENTER);

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

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color bg = base;
            if (isPressed)      bg = pressed;
            else if (isHover)   bg = hover;

            int w = getWidth();
            int h = getHeight();

            // Sombra
            g2.setColor(new Color(0, 0, 0, 60));
            g2.fillRoundRect(4, 6, w - 8, h - 8, 24, 24);

            // Vidrio
            g2.setColor(bg);
            g2.fillRoundRect(0, 0, w - 8, h - 10, 24, 24);

            // Borde brillante
            g2.setColor(new Color(255, 255, 255, 170));
            g2.setStroke(new BasicStroke(1.4f));
            g2.drawRoundRect(0, 0, w - 8, h - 10, 24, 24);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    // =========================================================
    // ===================== GRADIENTE FONDO ===================
    // =========================================================
    static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            GradientPaint gp = new GradientPaint(
                    0, 0, new Color(255, 175, 189),
                    getWidth(), getHeight(), new Color(131, 96, 195)
            );

            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
        }
    }

    // =========================================================
    // ===================== CARD GLASSMORPHISM ===============
    // =========================================================
    static class GlassCard extends JPanel {
        public GlassCard(LayoutManager layout) {
            super(layout);
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(18, 22, 18, 22));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            g2.setColor(new Color(0, 0, 0, 90));
            g2.fillRoundRect(8, 10, w - 16, h - 16, 32, 32);

            g2.setColor(new Color(255, 255, 255, 60));
            g2.fillRoundRect(0, 0, w - 10, h - 12, 32, 32);

            g2.setColor(new Color(255, 255, 255, 180));
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawRoundRect(0, 0, w - 10, h - 12, 32, 32);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    // =========================================================
    // ===================== VENTANA PRINCIPAL =================
    // =========================================================
    static class VentanaPrincipal extends JFrame {
        public VentanaPrincipal() {
            setTitle("Sistema de Gestión - Proyecto Introducción a la Programación");
            setSize(900, 600);
            setLocationRelativeTo(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new BorderLayout());
            setContentPane(fondo);

            // ----- Header -----
            JPanel header = new JPanel(new BorderLayout());
            header.setOpaque(false);
            header.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

            JLabel titulo = new JLabel("Menú Principal");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 32));
            titulo.setForeground(Color.WHITE);

            JLabel subtitulo = new JLabel("Seleccione un módulo para gestionar las actividades del sistema");
            subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            subtitulo.setForeground(new Color(245, 240, 255));

            header.add(titulo, BorderLayout.NORTH);
            header.add(subtitulo, BorderLayout.SOUTH);
            fondo.add(header, BorderLayout.NORTH);

            // ----- Centro -----
            JPanel center = new JPanel(new GridBagLayout());
            center.setOpaque(false);

            GlassCard card = new GlassCard(new BorderLayout());
            card.setPreferredSize(new Dimension(330, 320));

            JPanel actionsPanel = new JPanel(new GridLayout(5, 1, 12, 12));
            actionsPanel.setOpaque(false);
            actionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));

            ModernButton btnCine      = new ModernButton("Cine");
            ModernButton btnGimnasio  = new ModernButton("Gimnasio");
            ModernButton btnClases    = new ModernButton("Clases de Baile / Yoga");
            ModernButton btnBarista   = new ModernButton("Barista");
            ModernButton btnSalir     = new ModernButton("Salir");

            btnCine.addActionListener(e -> new VentanaCine());
            btnGimnasio.addActionListener(e -> new VentanaGimnasio());
            btnClases.addActionListener(e -> new VentanaClases());
            btnBarista.addActionListener(e -> new VentanaBarista());
            btnSalir.addActionListener(e -> salir());

            actionsPanel.add(btnCine);
            actionsPanel.add(btnGimnasio);
            actionsPanel.add(btnClases);
            actionsPanel.add(btnBarista);
            actionsPanel.add(btnSalir);

            card.add(actionsPanel, BorderLayout.CENTER);

            center.add(card);
            fondo.add(center, BorderLayout.CENTER);

            setVisible(true);
        }
    }

    // =====================================================
    // ===================== VENTANA CINE ==================
    // =====================================================
    static class VentanaCine extends JFrame {

        String[] filas = {"A", "B", "C", "D", "E"};
        JButton[][] botonesAsientos;
        JLabel lblPelicula;
        JLabel lblInfo;

        public VentanaCine() {
            setTitle("Módulo de Cine");
            setSize(900, 580);
            setLocationRelativeTo(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new BorderLayout());
            setContentPane(fondo);

            // ===== HEADER =====
            JPanel header = new JPanel(new BorderLayout());
            header.setOpaque(false);
            header.setBorder(BorderFactory.createEmptyBorder(18, 38, 10, 38));

            JLabel titulo = new JLabel("Cine - Reserva de Asientos");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
            titulo.setForeground(Color.WHITE);

            lblPelicula = new JLabel("Película actual: " + pelicula);
            lblPelicula.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            lblPelicula.setForeground(new Color(245, 240, 255));

            header.add(titulo, BorderLayout.NORTH);
            header.add(lblPelicula, BorderLayout.SOUTH);

            fondo.add(header, BorderLayout.NORTH);

            // ===== CENTRO =====
            JPanel center = new JPanel(new GridBagLayout());
            center.setOpaque(false);
            fondo.add(center, BorderLayout.CENTER);

            GlassCard cardSala = new GlassCard(new BorderLayout(10, 10));
            cardSala.setPreferredSize(new Dimension(600, 360));

            JPanel panelAsientos = new JPanel(new GridLayout(5, 6, 8, 8));
            panelAsientos.setOpaque(false);

            botonesAsientos = new JButton[5][6];

            for (int f = 0; f < 5; f++) {
                for (int c = 0; c < 6; c++) {
                    String codigo = filas[f] + (c + 1);
                    JButton btn = new JButton(codigo);
                    btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
                    btn.setFocusPainted(false);
                    btn.setOpaque(true);
                    btn.setBorderPainted(false);

                    if (asientosCine[f][c] == null) {
                        marcarLibre(btn);
                    } else {
                        marcarOcupado(btn);
                    }

                    final int fila = f;
                    final int col = c;

                    btn.addActionListener(e -> manejarClickAsiento(fila, col));

                    botonesAsientos[f][c] = btn;
                    panelAsientos.add(btn);
                }
            }

            // === Leyenda ===
            JPanel leyenda = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 4));
            leyenda.setOpaque(false);

            JLabel cuadritoLibre = new JLabel("   ");
            cuadritoLibre.setOpaque(true);
            cuadritoLibre.setBackground(new Color(200, 255, 200));
            cuadritoLibre.setBorder(BorderFactory.createLineBorder(new Color(120, 180, 120)));

            JLabel lblLibre = new JLabel("Libre");

            JLabel cuadritoOcupado = new JLabel("   ");
            cuadritoOcupado.setOpaque(true);
            cuadritoOcupado.setBackground(new Color(255, 200, 200));
            cuadritoOcupado.setBorder(BorderFactory.createLineBorder(new Color(190, 120, 120)));

            JLabel lblOcupado = new JLabel("Ocupado");

            leyenda.add(cuadritoLibre);
            leyenda.add(lblLibre);
            leyenda.add(Box.createHorizontalStrut(20));
            leyenda.add(cuadritoOcupado);
            leyenda.add(lblOcupado);

            lblInfo = new JLabel("Seleccione un asiento para reservar o cancelar.");
            lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            lblInfo.setForeground(new Color(40, 35, 55));
            lblInfo.setHorizontalAlignment(SwingConstants.CENTER);

            cardSala.add(panelAsientos, BorderLayout.CENTER);
            cardSala.add(leyenda, BorderLayout.SOUTH);

            GridBagConstraints gc = new GridBagConstraints();
            gc.gridx = 0;
            gc.gridy = 0;
            gc.insets = new Insets(10, 10, 10, 10);
            center.add(cardSala, gc);

            // ===== ACCIONES DERECHA =====
            GlassCard cardAcciones = new GlassCard(new BorderLayout());
            cardAcciones.setPreferredSize(new Dimension(240, 260)); // más ancho para que NO se corte el texto

            JPanel actionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
            actionsPanel.setOpaque(false);
            actionsPanel.setBorder(BorderFactory.createEmptyBorder());

            ModernButton btnCambiar = new ModernButton("Cambiar película");
            ModernButton btnLista   = new ModernButton("Ver reservaciones");
            ModernButton btnReset   = new ModernButton("Reiniciar sala");
            ModernButton btnCerrar  = new ModernButton("Cerrar");

            btnCambiar.addActionListener(e -> cambiarPelicula());
            btnLista.addActionListener(e -> verReservaciones());
            btnReset.addActionListener(e -> reiniciarSala());
            btnCerrar.addActionListener(e -> dispose());

            actionsPanel.add(btnCambiar);
            actionsPanel.add(btnLista);
            actionsPanel.add(btnReset);
            actionsPanel.add(btnCerrar);

            cardAcciones.add(actionsPanel, BorderLayout.CENTER);

            gc.gridx = 1;
            center.add(cardAcciones, gc);

            JPanel abajo = new JPanel(new BorderLayout());
            abajo.setOpaque(false);
            abajo.setBorder(BorderFactory.createEmptyBorder(0, 40, 18, 40));
            abajo.add(lblInfo, BorderLayout.CENTER);

            fondo.add(abajo, BorderLayout.SOUTH);

            setVisible(true);
        }

        private void marcarLibre(JButton btn) {
            btn.setBackground(new Color(200, 255, 200));
            btn.setBorder(BorderFactory.createLineBorder(new Color(120, 180, 120)));
        }

        private void marcarOcupado(JButton btn) {
            btn.setBackground(new Color(255, 200, 200));
            btn.setBorder(BorderFactory.createLineBorder(new Color(190, 120, 120)));
        }

        private void manejarClickAsiento(int fila, int col) {
            String codigo = codigoAsiento(fila, col);

            if (asientosCine[fila][col] == null) {

                String id = JOptionPane.showInputDialog(this,
                        "Asiento " + codigo + " está libre.\n\nIngrese ID del empleado:",
                        "Reservar asiento " + codigo, JOptionPane.QUESTION_MESSAGE);

                if (id == null || id.trim().isEmpty()) return;
                id = id.trim();

                if (!existeEmpleado(id)) {
                    JOptionPane.showMessageDialog(this,
                            "No existe un empleado con ese ID.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (empleadoYaTieneAsiento(id)) {
                    JOptionPane.showMessageDialog(this,
                            "Ese empleado ya tiene un asiento reservado.",
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                asientosCine[fila][col] = id;
                marcarOcupado(botonesAsientos[fila][col]);

                lblInfo.setText("Asiento " + codigo + " reservado para " + obtenerNombreEmpleado(id));

            } else {

                String id = asientosCine[fila][col];
                String nombre = obtenerNombreEmpleado(id);

                int opcion = JOptionPane.showConfirmDialog(this,
                        "Asiento " + codigo + " está reservado por:\n" + nombre
                                + " (" + id + ")\n\n¿Cancelar reservación?",
                        "Asiento Ocupado", JOptionPane.YES_NO_OPTION);

                if (opcion == JOptionPane.YES_OPTION) {
                    asientosCine[fila][col] = null;
                    marcarLibre(botonesAsientos[fila][col]);
                    lblInfo.setText("Se canceló la reservación del asiento " + codigo + ".");
                }
            }
        }

        private void cambiarPelicula() {
            String nueva = JOptionPane.showInputDialog(this,
                    "Película actual: " + pelicula + "\n\nIngrese nueva película:",
                    "Cambiar Película", JOptionPane.QUESTION_MESSAGE);

            if (nueva == null || nueva.trim().isEmpty()) return;

            pelicula = nueva.trim();
            lblPelicula.setText("Película actual: " + pelicula);
            lblInfo.setText("Película cambiada correctamente.");
        }

        private void verReservaciones() {
            StringBuilder sb = new StringBuilder();
            boolean hay = false;

            for (int f = 0; f < 5; f++) {
                for (int c = 0; c < 6; c++) {
                    if (asientosCine[f][c] != null) {
                        if (!hay) {
                            sb.append("Reservaciones actuales para ").append(pelicula).append(":\n\n");
                            hay = true;
                        }
                        String id = asientosCine[f][c];
                        sb.append("- Asiento ").append(codigoAsiento(f, c))
                                .append(": ").append(obtenerNombreEmpleado(id))
                                .append(" (").append(id).append(")\n");
                    }
                }
            }

            if (!hay) sb.append("No hay reservaciones en este momento.");

            JTextArea area = new JTextArea(sb.toString());
            area.setOpaque(false);
            area.setEditable(false);
            area.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(420, 260));
            scroll.setOpaque(false);
            scroll.getViewport().setOpaque(false);

            JOptionPane.showMessageDialog(this, scroll,
                    "Reservaciones del Cine", JOptionPane.INFORMATION_MESSAGE);
        }

        private void reiniciarSala() {
            int op = JOptionPane.showConfirmDialog(this,
                    "Esto eliminará TODAS las reservaciones.\n\n¿Continuar?",
                    "Reiniciar sala", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (op != JOptionPane.YES_OPTION) return;

            for (int f = 0; f < 5; f++) {
                for (int c = 0; c < 6; c++) {
                    asientosCine[f][c] = null;
                    marcarLibre(botonesAsientos[f][c]);
                }
            }

            lblInfo.setText("Sala reiniciada correctamente.");
        }

        private boolean empleadoYaTieneAsiento(String id) {
            for (int f = 0; f < 5; f++) {
                for (int c = 0; c < 6; c++) {
                    if (id.equals(asientosCine[f][c])) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    // =========================================================
    // ===================== VENTANA CLASES ====================
    // =========================================================
    static class VentanaClases extends JFrame {

        public VentanaClases() {
            setTitle("Módulo de Clases 💃");
            setSize(660, 540);
            setLocationRelativeTo(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new BorderLayout());
            setContentPane(fondo);

            JPanel header = new JPanel(new BorderLayout());
            header.setOpaque(false);
            header.setBorder(BorderFactory.createEmptyBorder(18, 38, 10, 38));

            JLabel titulo = new JLabel("Gestión de Clases");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 28));
            titulo.setForeground(Color.WHITE);

            JLabel subtitulo = new JLabel("Administre clases de Baile y Yoga.");
            subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            subtitulo.setForeground(new Color(245, 240, 255));

            header.add(titulo, BorderLayout.NORTH);
            header.add(subtitulo, BorderLayout.SOUTH);

            fondo.add(header, BorderLayout.NORTH);

            JPanel center = new JPanel(new GridBagLayout());
            center.setOpaque(false);

            GlassCard card = new GlassCard(new BorderLayout());
            card.setPreferredSize(new Dimension(350, 320));

            JPanel actionsPanel = new JPanel(new GridLayout(5, 1, 12, 12));
            actionsPanel.setOpaque(false);
            actionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));

            ModernButton btnReservar   = new ModernButton("Reservar Clase");
            ModernButton btnHorarios   = new ModernButton("Consultar Horarios");
            ModernButton btnModificar  = new ModernButton("Modificar Reservación");
            ModernButton btnConsultar  = new ModernButton("Consultar Reservaciones");
            ModernButton btnEliminar   = new ModernButton("Eliminar Reservación");

            btnReservar.addActionListener(e -> new DialogReservarClase(this).setVisible(true));
            btnHorarios.addActionListener(e -> new DialogHorariosClases(this).setVisible(true));
            btnModificar.addActionListener(e -> new DialogModificarClase(this).setVisible(true));
            btnConsultar.addActionListener(e -> new DialogConsultarReservacionesClase(this).setVisible(true));
            btnEliminar.addActionListener(e -> new DialogEliminarClase(this).setVisible(true));

            actionsPanel.add(btnReservar);
            actionsPanel.add(btnHorarios);
            actionsPanel.add(btnModificar);
            actionsPanel.add(btnConsultar);
            actionsPanel.add(btnEliminar);

            card.add(actionsPanel, BorderLayout.CENTER);

            center.add(card);
            fondo.add(center, BorderLayout.CENTER);

            setVisible(true);
        }
    }

    // =========================================================
    // ===================== VENTANA BARISTA ===================
    // =========================================================
    static class VentanaBarista extends JFrame {

        public VentanaBarista() {
            setTitle("Módulo Barista");
            setSize(660, 540);
            setLocationRelativeTo(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new BorderLayout());
            setContentPane(fondo);

            JPanel header = new JPanel(new BorderLayout());
            header.setOpaque(false);
            header.setBorder(BorderFactory.createEmptyBorder(18, 38, 10, 38));

            JLabel titulo = new JLabel("Gestión del Barista");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 28));
            titulo.setForeground(Color.WHITE);

            JLabel subtitulo = new JLabel("Registrar, modificar y consultar pedidos de bebidas.");
            subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            subtitulo.setForeground(new Color(245, 240, 255));

            header.add(titulo, BorderLayout.NORTH);
            header.add(subtitulo, BorderLayout.SOUTH);

            fondo.add(header, BorderLayout.NORTH);

            JPanel center = new JPanel(new GridBagLayout());
            center.setOpaque(false);

            GlassCard card = new GlassCard(new BorderLayout());
            card.setPreferredSize(new Dimension(350, 320));

            JPanel actionsPanel = new JPanel(new GridLayout(4, 1, 12, 12));
            actionsPanel.setOpaque(false);
            actionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));

            ModernButton btnSolicitar = new ModernButton("Solicitar Bebida");
            ModernButton btnModificar = new ModernButton("Modificar Pedido");
            ModernButton btnConsultar = new ModernButton("Consultar Pedidos");
            ModernButton btnEliminar  = new ModernButton("Eliminar Pedido");

            btnSolicitar.addActionListener(e -> new DialogSolicitarBebida(this).setVisible(true));
            btnModificar.addActionListener(e -> new DialogModificarPedido(this).setVisible(true));
            btnConsultar.addActionListener(e -> new DialogConsultarPedidos(this).setVisible(true));
            btnEliminar.addActionListener(e -> new DialogEliminarPedido(this).setVisible(true));

            actionsPanel.add(btnSolicitar);
            actionsPanel.add(btnModificar);
            actionsPanel.add(btnConsultar);
            actionsPanel.add(btnEliminar);

            card.add(actionsPanel, BorderLayout.CENTER);

            center.add(card);
            fondo.add(center, BorderLayout.CENTER);

            setVisible(true);
        }
    }

    // =========================================================
    // ===================== VENTANA GIMNASIO ==================
    // =========================================================
    static class VentanaGimnasio extends JFrame {

        public VentanaGimnasio() {
            setTitle("Módulo Gimnasio️");
            setSize(660, 540);
            setLocationRelativeTo(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new BorderLayout());
            setContentPane(fondo);

            JPanel header = new JPanel(new BorderLayout());
            header.setOpaque(false);
            header.setBorder(BorderFactory.createEmptyBorder(18, 38, 10, 38));

            JLabel titulo = new JLabel("Gestión del Gimnasio");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 28));
            titulo.setForeground(Color.WHITE);

            JLabel subtitulo = new JLabel("Administre las reservaciones de horarios del gimnasio.");
            subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            subtitulo.setForeground(new Color(245, 240, 255));

            header.add(titulo, BorderLayout.NORTH);
            header.add(subtitulo, BorderLayout.SOUTH);

            fondo.add(header, BorderLayout.NORTH);

            JPanel center = new JPanel(new GridBagLayout());
            center.setOpaque(false);

            GlassCard card = new GlassCard(new BorderLayout());
            card.setPreferredSize(new Dimension(350, 320));

            JPanel actionsPanel = new JPanel(new GridLayout(5, 1, 12, 12));
            actionsPanel.setOpaque(false);
            actionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));

            ModernButton btnReservar   = new ModernButton("Reservar horario");
            ModernButton btnHorarios   = new ModernButton("Consultar horarios");
            ModernButton btnModificar  = new ModernButton("Modificar reservación");
            ModernButton btnConsultar  = new ModernButton("Consultar reservaciones");
            ModernButton btnEliminar   = new ModernButton("Eliminar reservación");

            btnReservar.addActionListener(e -> new DialogReservarGym(this).setVisible(true));
            btnHorarios.addActionListener(e -> new DialogHorariosGym(this).setVisible(true));
            btnModificar.addActionListener(e -> new DialogModificarGym(this).setVisible(true));
            btnConsultar.addActionListener(e -> new DialogConsultarReservacionesGym(this).setVisible(true));
            btnEliminar.addActionListener(e -> new DialogEliminarGym(this).setVisible(true));

            actionsPanel.add(btnReservar);
            actionsPanel.add(btnHorarios);
            actionsPanel.add(btnModificar);
            actionsPanel.add(btnConsultar);
            actionsPanel.add(btnEliminar);

            card.add(actionsPanel, BorderLayout.CENTER);

            center.add(card);
            fondo.add(center, BorderLayout.CENTER);

            setVisible(true);
        }
    }

    // =========================================================
    // =================== DIALOGOS PARA CLASES ================
    // =========================================================
    // --- Reservar clase ---
    static class DialogReservarClase extends JDialog {

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
            card.setPreferredSize(new Dimension(580, 320));   // tamaño lujoso

            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(8, 10, 8, 10);
            c.fill = GridBagConstraints.HORIZONTAL;

            JLabel titulo = new JLabel("Reservar Clase");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
            titulo.setForeground(new Color(40, 35, 55));

            txtId = new JTextField(15);
            comboClase = new JComboBox<>(new String[]{"Clase de Baile", "Clase de Yoga"});

            ModernButton btnConfirmar = new ModernButton("Confirmar");
            ModernButton btnCancelar = new ModernButton("Cancelar");

            btnConfirmar.addActionListener(e -> confirmar());
            btnCancelar.addActionListener(e -> dispose());

            Dimension labelSize = new Dimension(140, 25);

            JLabel lblId = new JLabel("ID Empleado:");
            lblId.setPreferredSize(labelSize);

            JLabel lblClase = new JLabel("Clase:");
            lblClase.setPreferredSize(labelSize);

            c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
            card.add(titulo, c);

            c.gridy = 1; c.gridwidth = 1;
            card.add(lblId, c);
            c.gridx = 1;
            card.add(txtId, c);

            c.gridx = 0; c.gridy = 2;
            card.add(lblClase, c);
            c.gridx = 1;
            card.add(comboClase, c);

            JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
            panelBotones.setOpaque(false);
            panelBotones.add(btnCancelar);
            panelBotones.add(btnConfirmar);

            c.gridx = 0; c.gridy = 3; c.gridwidth = 2;
            card.add(panelBotones, c);

            fondo.add(card);
        }

        private void confirmar() {
            String id = txtId.getText().trim();
            String clase = (String) comboClase.getSelectedItem();

            if (id.isEmpty()) {
                mostrarError("Debe ingresar el ID de empleado.");
                return;
            }

            if (!existeEmpleado(id)) {
                mostrarError("El empleado no existe.");
                return;
            }

            if ("Clase de Baile".equals(clase)) {
                if (cupoBaile >= 30) {
                    mostrarError("No hay cupo para Baile.");
                    return;
                }

                reservasClases[totalReservasClases][0] = id;
                reservasClases[totalReservasClases][1] = "Clase de Baile";
                reservasClases[totalReservasClases][2] = "7:00 pm";
                cupoBaile++;
            } else {
                if (cupoYoga >= 30) {
                    mostrarError("No hay cupo para Yoga.");
                    return;
                }

                reservasClases[totalReservasClases][0] = id;
                reservasClases[totalReservasClases][1] = "Clase de Yoga";
                reservasClases[totalReservasClases][2] = "8:00 pm";
                cupoYoga++;
            }

            totalReservasClases++;
            JOptionPane.showMessageDialog(this, "Reserva exitosa.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    // --- Consultar horarios de clases ---
    static class DialogHorariosClases extends JDialog {

        public DialogHorariosClases(Frame owner) {
            super(owner, "Horarios de Clases", true);
            setSize(640, 380);
            setLocationRelativeTo(owner);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new GridBagLayout());
            setContentPane(fondo);

            GlassCard card = new GlassCard(new BorderLayout(8, 8));
            card.setPreferredSize(new Dimension(310, 190));

            JLabel titulo = new JLabel("Horarios de Clases", SwingConstants.CENTER);
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
            titulo.setForeground(new Color(40, 35, 55));

            String texto = "Clase de Baile: 7:00 pm (Disponibles: " + (30 - cupoBaile) + ")\n"
                    + "Clase de Yoga: 8:00 pm (Disponibles: " + (30 - cupoYoga) + ")";

            JTextArea area = new JTextArea(texto);
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

    // --- Consultar Reservaciones de Clases ---
    static class DialogConsultarReservacionesClase extends JDialog {

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

            StringBuilder msg = new StringBuilder();

            if (totalReservasClases == 0) {
                msg.append("No hay reservaciones registradas.");
            } else {
                msg.append("Reservaciones actuales:\n\n");
                for (int i = 0; i < totalReservasClases; i++) {
                    String id = reservasClases[i][0];
                    msg.append(i + 1).append(". ")
                            .append(obtenerNombreEmpleado(id))
                            .append(" (").append(id).append(") - ")
                            .append(reservasClases[i][1]).append(" a las ")
                            .append(reservasClases[i][2]).append("\n");
                }
            }

            JTextArea area = new JTextArea(msg.toString());
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

    // --- Modificar clase ---
    static class DialogModificarClase extends JDialog {

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
            card.setPreferredSize(new Dimension(580, 320));   // tamaño lujoso

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
                mostrarError("Debe ingresar un ID.");
                return;
            }

            if (!existeEmpleado(id)) {
                mostrarError("Empleado no existe.");
                return;
            }

            boolean encontrado = false;

            for (int i = 0; i < totalReservasClases; i++) {
                if (reservasClases[i][0].equals(id)) {
                    String claseActual = reservasClases[i][1];

                    if (claseActual.equals("Clase de Baile")) {
                        if (cupoYoga >= 30) {
                            mostrarError("No hay cupo para Yoga.");
                            return;
                        }
                        reservasClases[i][1] = "Clase de Yoga";
                        reservasClases[i][2] = "8:00 pm";
                        cupoBaile--;
                        cupoYoga++;
                    } else {
                        if (cupoBaile >= 30) {
                            mostrarError("No hay cupo para Baile.");
                            return;
                        }
                        reservasClases[i][1] = "Clase de Baile";
                        reservasClases[i][2] = "7:00 pm";
                        cupoYoga--;
                        cupoBaile++;
                    }

                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                mostrarError("El empleado no tiene reservación.");
            } else {
                JOptionPane.showMessageDialog(this, "Clase modificada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }
    }

    // --- Eliminar reservación de clase ---
    static class DialogEliminarClase extends JDialog {

        private final JTextField txtId;

        public DialogEliminarClase(Frame owner) {
            super(owner, "Eliminar Reservación de Clase", true);
            setSize(540, 400);
            setLocationRelativeTo(owner);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new GridBagLayout());
            setContentPane(fondo);

            GlassCard card = new GlassCard(new GridBagLayout());
            card.setPreferredSize(new Dimension(580, 320));   // tamaño lujoso

            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(8, 10, 8, 10);
            c.fill = GridBagConstraints.HORIZONTAL;

            JLabel titulo = new JLabel("Eliminar Reservación");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
            titulo.setForeground(new Color(40, 35, 55));

            txtId = new JTextField(15);

            ModernButton btnEliminar = new ModernButton("Eliminar");
            ModernButton btnCancelar = new ModernButton("Cancelar");

            btnEliminar.addActionListener(e -> eliminar());
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
            botones.add(btnEliminar);

            c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
            card.add(botones, c);

            fondo.add(card);
        }

        private void eliminar() {
            String id = txtId.getText().trim();

            if (id.isEmpty()) {
                mostrarError("Debe ingresar un ID.");
                return;
            }

            boolean encontrado = false;

            for (int i = 0; i < totalReservasClases; i++) {
                if (reservasClases[i][0].equals(id)) {

                    String tipo = reservasClases[i][1];

                    for (int j = i; j < totalReservasClases - 1; j++) {
                        reservasClases[j] = reservasClases[j + 1];
                    }

                    reservasClases[totalReservasClases - 1] = new String[3];
                    totalReservasClases--;

                    if (tipo.equals("Clase de Baile")) {
                        cupoBaile--;
                    } else {
                        cupoYoga--;
                    }

                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                mostrarError("No existe reservación asociada a ese ID.");
            } else {
                JOptionPane.showMessageDialog(this, "Reservación eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }
    }

    // =========================================================
    // ================== DIALOGOS BARISTA =====================
    // =========================================================
    static class DialogSolicitarBebida extends JDialog {

        private final JTextField txtId;
        private final JComboBox<String> comboBebida;
        private final JTextField txtHora;

        public DialogSolicitarBebida(Frame owner) {
            super(owner, "Solicitar Bebida", true);
            setSize(540, 400);
            setLocationRelativeTo(owner);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new GridBagLayout());
            setContentPane(fondo);

            GlassCard card = new GlassCard(new GridBagLayout());
            card.setPreferredSize(new Dimension(580, 320));   // tamaño lujoso

            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(8, 10, 8, 10);
            c.fill = GridBagConstraints.HORIZONTAL;
            
            JLabel titulo = new JLabel("Solicitar Bebida");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
            titulo.setForeground(new Color(40, 35, 55));

            JLabel lblInfo = new JLabel("Ingrese los datos del pedido:");
            lblInfo.setForeground(new Color(60, 60, 60));
            lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            txtId = new JTextField(15);
            comboBebida = new JComboBox<>(new String[]{
                    "Café normal", "Capuchino", "Capuchino con vainilla",
                    "Chocolate", "Moka", "Té chai", "Café frío"
            });
            txtHora = new JTextField(10);
            txtHora.setText("3:30 pm");

            // ---- Etiquetas alineadas ----
            Dimension labelSize = new Dimension(140, 25);

            JLabel lblId = new JLabel("ID Empleado:");
            lblId.setPreferredSize(labelSize);

            JLabel lblBebida = new JLabel("Bebida:");
            lblBebida.setPreferredSize(labelSize);

            JLabel lblHora = new JLabel("Hora:");
            lblHora.setPreferredSize(labelSize);

            // ---- Botones ----
            ModernButton btnAceptar = new ModernButton("Registrar");
            ModernButton btnCancelar = new ModernButton("Cancelar");

            btnAceptar.addActionListener(e -> registrar());
            btnCancelar.addActionListener(e -> dispose());

            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 2;
            card.add(titulo, c);

            c.gridy = 1;
            card.add(lblInfo, c);

            c.gridy = 2;
            c.gridwidth = 1;
            card.add(lblId, c);
            c.gridx = 1;
            card.add(txtId, c);

            c.gridx = 0;
            c.gridy = 3;
            card.add(lblBebida, c);
            c.gridx = 1;
            card.add(comboBebida, c);

            c.gridx = 0;
            c.gridy = 4;
            card.add(lblHora, c);
            c.gridx = 1;
            card.add(txtHora, c);

            JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
            botones.setOpaque(false);
            botones.add(btnCancelar);
            botones.add(btnAceptar);

            c.gridx = 0;
            c.gridy = 5;
            c.gridwidth = 2;
            card.add(botones, c);

            fondo.add(card);
        }

        private void registrar() {
            String id = txtId.getText().trim();
            String hora = txtHora.getText().trim();
            String bebida = (String) comboBebida.getSelectedItem();

            if (id.isEmpty() || hora.isEmpty()) {
                mostrarError("Debe ingresar ID y hora.");
                return;
            }

            if (!existeEmpleado(id)) {
                mostrarError("El empleado no existe.");
                return;
            }

            for (int i = 0; i < totalPedidos; i++) {
                if (pedidosBarista[i][0] != null
                        && pedidosBarista[i][0].equals(id)) {
                    mostrarError("Ya ha realizado un pedido hoy.");
                    return;
                }
            }

            pedidosBarista[totalPedidos][0] = id;
            pedidosBarista[totalPedidos][1] = bebida;
            pedidosBarista[totalPedidos][2] = hora;
            totalPedidos++;

            JOptionPane.showMessageDialog(this, "Pedido registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    static class DialogConsultarPedidos extends JDialog {

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

            if (totalPedidos == 0) {
                sb.append("No hay pedidos.");
            } else {
                sb.append("Pedidos actuales:\n\n");
                for (int i = 0; i < totalPedidos; i++) {
                    String id = pedidosBarista[i][0];
                    sb.append(i + 1).append(". ")
                            .append(obtenerNombreEmpleado(id)).append(" (").append(id).append(") - ")
                            .append(pedidosBarista[i][1]).append(" a las ")
                            .append(pedidosBarista[i][2]).append("\n");
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

    static class DialogModificarPedido extends JDialog {

        private final JTextField txtId;
        private final JComboBox<String> comboBebida;
        private final JTextField txtHora;

        public DialogModificarPedido(Frame owner) {
            super(owner, "Modificar Pedido", true);
            setSize(540, 400);
            setLocationRelativeTo(owner);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new GridBagLayout());
            setContentPane(fondo);

            GlassCard card = new GlassCard(new GridBagLayout());
            card.setPreferredSize(new Dimension(580, 320));   // tamaño lujoso

            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(8, 10, 8, 10);
            c.fill = GridBagConstraints.HORIZONTAL;

            JLabel titulo = new JLabel("Modificar Pedido");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
            titulo.setForeground(new Color(40, 35, 55));

            txtId = new JTextField(15);

            comboBebida = new JComboBox<>(new String[]{
                    "Café normal", "Capuchino", "Capuchino con vainilla",
                    "Chocolate", "Moka", "Té chai", "Café frío"
            });

            txtHora = new JTextField(10);
            txtHora.setText("4:00 pm");

            ModernButton btnModificar = new ModernButton("Guardar");
            ModernButton btnCancelar = new ModernButton("Cancelar");

            btnModificar.addActionListener(e -> modificar());
            btnCancelar.addActionListener(e -> dispose());

            Dimension labelSize = new Dimension(120, 25);

            JLabel lblId = new JLabel("ID:");
            lblId.setPreferredSize(labelSize);

            JLabel lblBebida = new JLabel("Bebida:");
            lblBebida.setPreferredSize(labelSize);

            JLabel lblHora = new JLabel("Hora:");
            lblHora.setPreferredSize(labelSize);

            c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
            card.add(titulo, c);

            c.gridy = 1; c.gridwidth = 1;
            card.add(lblId, c);
            c.gridx = 1;
            card.add(txtId, c);

            c.gridx = 0; c.gridy = 2;
            card.add(lblBebida, c);
            c.gridx = 1;
            card.add(comboBebida, c);

            c.gridx = 0; c.gridy = 3;
            card.add(lblHora, c);
            c.gridx = 1;
            card.add(txtHora, c);

            JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
            botones.setOpaque(false);
            botones.add(btnCancelar);
            botones.add(btnModificar);

            c.gridx = 0; c.gridy = 4; c.gridwidth = 2;
            card.add(botones, c);

            fondo.add(card);
        }

        private void modificar() {
            String id = txtId.getText().trim();
            String bebida = (String) comboBebida.getSelectedItem();
            String hora = txtHora.getText().trim();

            if (id.isEmpty() || hora.isEmpty()) {
                mostrarError("Complete todos los campos.");
                return;
            }

            if (!existeEmpleado(id)) {
                mostrarError("El empleado no existe.");
                return;
            }

            for (int i = 0; i < totalPedidos; i++) {
                if (pedidosBarista[i][0].equals(id)) {
                    pedidosBarista[i][1] = bebida;
                    pedidosBarista[i][2] = hora;

                    JOptionPane.showMessageDialog(this, "Pedido modificado con éxito.",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    return;
                }
            }

            mostrarError("El empleado no tiene pedidos.");
        }
    }

    static class DialogEliminarPedido extends JDialog {

        private final JTextField txtId;

        public DialogEliminarPedido(Frame owner) {
            super(owner, "Eliminar Pedido", true);
            setSize(540, 400);
            setLocationRelativeTo(owner);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new GridBagLayout());
            setContentPane(fondo);

            GlassCard card = new GlassCard(new GridBagLayout());
            card.setPreferredSize(new Dimension(580, 320));   // tamaño lujoso

            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(8, 10, 8, 10);
            c.fill = GridBagConstraints.HORIZONTAL;

            JLabel titulo = new JLabel("Eliminar Pedido");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
            titulo.setForeground(new Color(40, 35, 55));

            txtId = new JTextField(15);

            ModernButton btnEliminar = new ModernButton("Eliminar");
            ModernButton btnCancelar = new ModernButton("Cancelar");

            btnEliminar.addActionListener(e -> eliminar());
            btnCancelar.addActionListener(e -> dispose());

            Dimension labelSize = new Dimension(120, 25);
            JLabel lblId = new JLabel("ID:");
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
            botones.add(btnEliminar);

            c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
            card.add(botones, c);

            fondo.add(card);
        }

        private void eliminar() {
            String id = txtId.getText().trim();

            if (id.isEmpty()) {
                mostrarError("Debe ingresar un ID.");
                return;
            }

            boolean encontrado = false;

            for (int i = 0; i < totalPedidos; i++) {
                if (pedidosBarista[i][0].equals(id)) {

                    for (int j = i; j < totalPedidos - 1; j++) {
                        pedidosBarista[j] = pedidosBarista[j + 1];
                    }

                    pedidosBarista[totalPedidos - 1] = new String[3];
                    totalPedidos--;

                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                mostrarError("No existe pedido asociado a ese ID.");
            } else {
                JOptionPane.showMessageDialog(this, "Pedido eliminado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }
    }

    // =========================================================
    // ====================== GIMNASIO DIALOGOS ================
    // =========================================================
    static class DialogReservarGym extends JDialog {

        private final JTextField txtId;
        private final JComboBox<String> comboHorario;

        public DialogReservarGym(Frame owner) {
            super(owner, "Reservar Gimnasio", true);
            setSize(540, 400);
            setLocationRelativeTo(owner);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new GridBagLayout());
            setContentPane(fondo);

            GlassCard card = new GlassCard(new GridBagLayout());
            card.setPreferredSize(new Dimension(580, 320));   // tamaño lujoso

            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(8, 10, 8, 10);
            c.fill = GridBagConstraints.HORIZONTAL;

            JLabel titulo = new JLabel("Reservar Gimnasio");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
            titulo.setForeground(new Color(40, 35, 55));

            txtId = new JTextField(15);
            comboHorario = new JComboBox<>(horariosGym);

            ModernButton btnReservar = new ModernButton("Reservar");
            ModernButton btnCancelar = new ModernButton("Cancelar");

            btnReservar.addActionListener(e -> reservar());
            btnCancelar.addActionListener(e -> dispose());

            Dimension labelSize = new Dimension(140, 25);
            JLabel lblId = new JLabel("ID Empleado:");
            lblId.setPreferredSize(labelSize);

            JLabel lblHorario = new JLabel("Horario:");
            lblHorario.setPreferredSize(labelSize);

            c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
            card.add(titulo, c);

            c.gridy = 1; c.gridwidth = 1;
            card.add(lblId, c);
            c.gridx = 1;
            card.add(txtId, c);

            c.gridx = 0; c.gridy = 2;
            card.add(lblHorario, c);
            c.gridx = 1;
            card.add(comboHorario, c);

            JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
            botones.setOpaque(false);
            botones.add(btnCancelar);
            botones.add(btnReservar);

            c.gridx = 0; c.gridy = 3; c.gridwidth = 2;
            card.add(botones, c);

            fondo.add(card);
        }

        private void reservar() {
            String id = txtId.getText().trim();
            int index = comboHorario.getSelectedIndex();

            if (id.isEmpty()) {
                mostrarError("Debe ingresar el ID.");
                return;
            }

            if (!existeEmpleado(id)) {
                mostrarError("El empleado no existe.");
                return;
            }

            for (String res : reservasGym) {
                if (id.equals(res)) {
                    mostrarError("Ya tiene un horario reservado.");
                    return;
                }
            }

            if (reservasGym[index] != null) {
                mostrarError("Ese horario ya está ocupado.");
                return;
            }

            reservasGym[index] = id;

            JOptionPane.showMessageDialog(this,
                    "Horario " + horariosGym[index] + " reservado exitosamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    static class DialogHorariosGym extends JDialog {

        public DialogHorariosGym(Frame owner) {
            super(owner, "Horarios del Gimnasio", true);
            setSize(640, 380);
            setLocationRelativeTo(owner);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new GridBagLayout());
            setContentPane(fondo);

            StringBuilder texto = new StringBuilder("Horarios:\n\n");

            for (int i = 0; i < horariosGym.length; i++) {
                if (reservasGym[i] == null) {
                    texto.append("- ").append(horariosGym[i]).append(": Libre\n");
                } else {
                    texto.append("- ").append(horariosGym[i]).append(": Ocupado por ")
                            .append(obtenerNombreEmpleado(reservasGym[i])).append("\n");
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

    static class DialogConsultarReservacionesGym extends JDialog {

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

            for (int i = 0; i < reservasGym.length; i++) {
                if (reservasGym[i] != null) {
                    if (!hay) {
                        texto.append("Reservaciones actuales:\n\n");
                        hay = true;
                    }
                    texto.append("- ")
                            .append(obtenerNombreEmpleado(reservasGym[i]))
                            .append(" (").append(reservasGym[i]).append(") - ")
                            .append(horariosGym[i]).append("\n");
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

    static class DialogModificarGym extends JDialog {

        private final JTextField txtId;
        private final JComboBox<String> comboNuevo;

        public DialogModificarGym(Frame owner) {
            super(owner, "Modificar Reserva Gimnasio", true);
            setSize(540, 400);
            setLocationRelativeTo(owner);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new GridBagLayout());
            setContentPane(fondo);

            GlassCard card = new GlassCard(new GridBagLayout());
            card.setPreferredSize(new Dimension(580, 320));   // tamaño lujoso

            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(8, 10, 8, 10);
            c.fill = GridBagConstraints.HORIZONTAL;

            JLabel titulo = new JLabel("Modificar Reservación");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
            titulo.setForeground(new Color(40, 35, 55));

            txtId = new JTextField(15);
            comboNuevo = new JComboBox<>(horariosGym);

            ModernButton btnModificar = new ModernButton("Guardar");
            ModernButton btnCancelar = new ModernButton("Cancelar");

            btnModificar.addActionListener(e -> modificar());
            btnCancelar.addActionListener(e -> dispose());

            Dimension labelSize = new Dimension(140, 25);
            JLabel lblId = new JLabel("ID Empleado:");
            lblId.setPreferredSize(labelSize);

            JLabel lblNuevo = new JLabel("Nuevo horario:");
            lblNuevo.setPreferredSize(labelSize);

            c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
            card.add(titulo, c);

            c.gridy = 1; c.gridwidth = 1;
            card.add(lblId, c);
            c.gridx = 1;
            card.add(txtId, c);

            c.gridx = 0; c.gridy = 2;
            card.add(lblNuevo, c);
            c.gridx = 1;
            card.add(comboNuevo, c);

            JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
            botones.setOpaque(false);
            botones.add(btnCancelar);
            botones.add(btnModificar);

            c.gridx = 0; c.gridy = 3; c.gridwidth = 2;
            card.add(botones, c);

            fondo.add(card);
        }

        private void modificar() {
            String id = txtId.getText().trim();

            if (id.isEmpty()) {
                mostrarError("Debe ingresar el ID.");
                return;
            }

            int actual = -1;

            for (int i = 0; i < reservasGym.length; i++) {
                if (id.equals(reservasGym[i])) {
                    actual = i;
                    break;
                }
            }

            if (actual == -1) {
                mostrarError("No tiene reservación.");
                return;
            }

            int nuevo = comboNuevo.getSelectedIndex();

            if (reservasGym[nuevo] != null
                    && !reservasGym[nuevo].equals(id)) {
                mostrarError("Ese horario está ocupado.");
                return;
            }

            reservasGym[actual] = null;
            reservasGym[nuevo] = id;

            JOptionPane.showMessageDialog(this, "Modificado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    static class DialogEliminarGym extends JDialog {

        private final JTextField txtId;

        public DialogEliminarGym(Frame owner) {
            super(owner, "Eliminar Reserva de Gimnasio", true);
            setSize(540, 400);
            setLocationRelativeTo(owner);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new GridBagLayout());
            setContentPane(fondo);

            GlassCard card = new GlassCard(new GridBagLayout());
            card.setPreferredSize(new Dimension(580, 320));   // tamaño lujoso

            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(8, 10, 8, 10);
            c.fill = GridBagConstraints.HORIZONTAL;

            JLabel titulo = new JLabel("Eliminar Reserva");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
            titulo.setForeground(new Color(40, 35, 55));

            txtId = new JTextField(15);

            ModernButton btnEliminar = new ModernButton("Eliminar");
            ModernButton btnCancelar = new ModernButton("Cancelar");

            btnEliminar.addActionListener(e -> eliminar());
            btnCancelar.addActionListener(e -> dispose());

            Dimension labelSize = new Dimension(120, 25);
            JLabel lblId = new JLabel("ID:");
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
            botones.add(btnEliminar);

            c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
            card.add(botones, c);

            fondo.add(card);
        }

        private void eliminar() {
            String id = txtId.getText().trim();

            if (id.isEmpty()) {
                mostrarError("Debe ingresar un ID.");
                return;
            }

            boolean eliminado = false;

            for (int i = 0; i < reservasGym.length; i++) {
                if (id.equals(reservasGym[i])) {
                    reservasGym[i] = null;
                    eliminado = true;
                    break;
                }
            }

            if (!eliminado) {
                mostrarError("No tiene reservación.");
            } else {
                JOptionPane.showMessageDialog(this, "Reservación eliminada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }
    }

    // =========================================================
    // ======================== UTILIDADES =====================
    // =========================================================
    public static void cargarDatos() {
        empleados = new String[]{"E001", "E002", "E003"};
        nombres = new String[]{"Julián Quesada", "Valeria Alfaro", "Justin Cordero"};

        horariosGym = new String[]{"2:00 pm", "3:00 pm", "4:00 pm", "5:00 pm", "6:00 pm", "7:00 pm"};
        reservasGym = new String[6];

        pelicula = "Matrix Reloaded";
        asientosCine = new String[5][6]; // 5 filas x 6 columnas

        reservasClases = new String[60][3];
        pedidosBarista = new String[100][3];
    }

    public static boolean existeEmpleado(String id) {
        for (String e : empleados) {
            if (id.equals(e)) {
                return true;
            }
        }
        return false;
    }

    public static String obtenerNombreEmpleado(String id) {
        for (int i = 0; i < empleados.length; i++) {
            if (empleados[i].equals(id)) {
                return nombres[i];
            }
        }
        return "Empleado";
    }

    public static String codigoAsiento(int fila, int col) {
        String[] filas = {"A", "B", "C", "D", "E"};
        return filas[fila] + (col + 1);
    }

    public static void salir() {
        JOptionPane.showMessageDialog(null,
                "¡Gracias por usar el sistema!",
                "Salir", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public static void mostrarError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}