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
    static String[] empleados = new String[3];
    static String[] nombres = new String[3];
    static String[] horariosGym = new String[6];
    static String[] reservasGym = new String[6];
    static String pelicula;
    static String[][] asientosCine;
    static int cupoBaile = 0;
    static int cupoYoga = 0;
    static String[][] reservasClases;
    static int totalReservasClases = 0;
    static String[][] pedidosBarista;
    static int totalPedidos;

    // ===== MAIN =====
    public static void main(String[] args) {
        cargarDatos();
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }

    // =========================================================
    // ===================== BOTÓN GLASSMORPHISM ===============
    // =========================================================
    static class ModernButton extends JButton {
        private Color base = new Color(255, 255, 255, 70);
        private Color hover = new Color(255, 255, 255, 110);
        private Color pressed = new Color(255, 255, 255, 150);

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
            setBorder(BorderFactory.createEmptyBorder(14, 24, 14, 24));
            setHorizontalAlignment(SwingConstants.LEFT);

            addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { isHover = true; repaint(); }
                @Override public void mouseExited(MouseEvent e)  { isHover = false; repaint(); }
                @Override public void mousePressed(MouseEvent e) { isPressed = true; repaint(); }
                @Override public void mouseReleased(MouseEvent e){ isPressed = false; repaint(); }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color bg = base;
            if (isPressed) bg = pressed;
            else if (isHover) bg = hover;

            // Sombra
            g2.setColor(new Color(0, 0, 0, 60));
            g2.fillRoundRect(4, 6, getWidth() - 8, getHeight() - 8, 24, 24);

            // Vidrio
            g2.setColor(bg);
            g2.fillRoundRect(0, 0, getWidth() - 8, getHeight() - 10, 24, 24);

            // Borde brillante
            g2.setColor(new Color(255, 255, 255, 170));
            g2.setStroke(new BasicStroke(1.4f));
            g2.drawRoundRect(0, 0, getWidth() - 8, getHeight() - 10, 24, 24);

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
            setTitle("Sistema de Gestión - Proyecto Final");
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

            GlassCard card = new GlassCard(new GridLayout(5, 1, 14, 14));
            card.setPreferredSize(new Dimension(430, 320));

            ModernButton btnCine      = new ModernButton("   🎬   Cine");
            ModernButton btnGimnasio  = new ModernButton("   🏋   Gimnasio");
            ModernButton btnClases    = new ModernButton("   💃   Clases de Baile / Yoga");
            ModernButton btnBarista   = new ModernButton("   ☕   Barista");
            ModernButton btnSalir     = new ModernButton("   ❌   Salir");

            btnCine.addActionListener(e -> manejarCine());
            btnGimnasio.addActionListener(e -> manejarGimnasio());
            btnClases.addActionListener(e -> new VentanaClases());
            btnBarista.addActionListener(e -> new VentanaBarista());
            btnSalir.addActionListener(e -> salir());

            card.add(btnCine);
            card.add(btnGimnasio);
            card.add(btnClases);
            card.add(btnBarista);
            card.add(btnSalir);

            center.add(card);
            fondo.add(center, BorderLayout.CENTER);

            setVisible(true);
        }
    }

    // =========================================================
    // ===================== VENTANA CLASES =====================
    // =========================================================
    static class VentanaClases extends JFrame {
        public VentanaClases() {
            setTitle("Módulo de Clases 💃");
            setSize(860, 540);
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

            JLabel subtitulo = new JLabel("Administre las clases de Baile y Yoga: reservaciones, horarios y más.");
            subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            subtitulo.setForeground(new Color(245, 240, 255));

            header.add(titulo, BorderLayout.NORTH);
            header.add(subtitulo, BorderLayout.SOUTH);
            fondo.add(header, BorderLayout.NORTH);

            JPanel center = new JPanel(new GridBagLayout());
            center.setOpaque(false);

            GlassCard card = new GlassCard(new GridLayout(5, 1, 14, 14));
            card.setPreferredSize(new Dimension(450, 320));

            ModernButton btnReservar   = new ModernButton("   📝   Reservar Clase");
            ModernButton btnHorarios   = new ModernButton("   🕒   Consultar Horarios");
            ModernButton btnModificar  = new ModernButton("   ✏️   Modificar Reservaciones");
            ModernButton btnConsultar  = new ModernButton("   📋   Consultar Reservaciones");
            ModernButton btnEliminar   = new ModernButton("   ❌   Eliminar Reservaciones");

            // Ahora cada botón abre un formulario moderno,
            // ya NO usamos JOptionPane para pedir datos:
            btnReservar.addActionListener(e -> new DialogReservarClase(this).setVisible(true));
            btnHorarios.addActionListener(e -> new DialogHorariosClases(this).setVisible(true));
            btnModificar.addActionListener(e -> new DialogModificarClase(this).setVisible(true));
            btnConsultar.addActionListener(e -> new DialogConsultarReservacionesClase(this).setVisible(true));
            btnEliminar.addActionListener(e -> new DialogEliminarClase(this).setVisible(true));

            card.add(btnReservar);
            card.add(btnHorarios);
            card.add(btnModificar);
            card.add(btnConsultar);
            card.add(btnEliminar);

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
            setTitle("Módulo Barista ☕");
            setSize(820, 500);
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

            JLabel subtitulo = new JLabel("Registre y administre las bebidas solicitadas por los empleados.");
            subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            subtitulo.setForeground(new Color(245, 240, 255));

            header.add(titulo, BorderLayout.NORTH);
            header.add(subtitulo, BorderLayout.SOUTH);
            fondo.add(header, BorderLayout.NORTH);

            JPanel center = new JPanel(new GridBagLayout());
            center.setOpaque(false);

            GlassCard card = new GlassCard(new GridLayout(4, 1, 14, 14));
            card.setPreferredSize(new Dimension(430, 260));

            ModernButton btnSolicitar = new ModernButton("   📝   Solicitar Bebida");
            ModernButton btnModificar = new ModernButton("   ✏️   Modificar Pedido");
            ModernButton btnConsultar = new ModernButton("   📋   Consultar Pedidos");
            ModernButton btnEliminar  = new ModernButton("   ❌   Eliminar Pedido");

            btnSolicitar.addActionListener(e -> new DialogSolicitarBebida(this).setVisible(true));
            btnModificar.addActionListener(e -> new DialogModificarPedido(this).setVisible(true));
            btnConsultar.addActionListener(e -> new DialogConsultarPedidos(this).setVisible(true));
            btnEliminar.addActionListener(e -> new DialogEliminarPedido(this).setVisible(true));

            card.add(btnSolicitar);
            card.add(btnModificar);
            card.add(btnConsultar);
            card.add(btnEliminar);

            center.add(card);
            fondo.add(center, BorderLayout.CENTER);

            setVisible(true);
        }
    }

    // =========================================================
    // =============== DIALOGOS PARA CLASES ====================
    // =========================================================

    // --- Reservar clase ---
    static class DialogReservarClase extends JDialog {
        private JTextField txtId;
        private JComboBox<String> comboClase;
        private JLabel lblInfo;

        public DialogReservarClase(Frame owner) {
            super(owner, "Reservar Clase", true);
            setSize(500, 280);
            setLocationRelativeTo(owner);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new GridBagLayout());
            setContentPane(fondo);

            GlassCard card = new GlassCard(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(6, 6, 6, 6);
            c.fill = GridBagConstraints.HORIZONTAL;

            JLabel titulo = new JLabel("Reservar Clase");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
            titulo.setForeground(new Color(40, 35, 55));

            txtId = new JTextField(15);
            comboClase = new JComboBox<>(new String[]{"Clase de Baile", "Clase de Yoga"});
            lblInfo = new JLabel("Ingrese su ID de empleado y elija la clase.");
            lblInfo.setForeground(new Color(60, 60, 60));

            ModernButton btnConfirmar = new ModernButton("   ✅   Confirmar reserva");
            ModernButton btnCancelar  = new ModernButton("   ❌   Cancelar");

            btnConfirmar.addActionListener(e -> confirmar());
            btnCancelar.addActionListener(e -> dispose());

            c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
            card.add(titulo, c);

            c.gridy = 1; c.gridwidth = 2;
            card.add(lblInfo, c);

            c.gridy = 2; c.gridwidth = 1;
            card.add(new JLabel("ID Empleado:"), c);

            c.gridx = 1;
            card.add(txtId, c);

            c.gridx = 0; c.gridy = 3;
            card.add(new JLabel("Clase:"), c);

            c.gridx = 1;
            card.add(comboClase, c);

            JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
            panelBotones.setOpaque(false);
            panelBotones.add(btnCancelar);
            panelBotones.add(btnConfirmar);

            c.gridx = 0; c.gridy = 4; c.gridwidth = 2;
            card.add(panelBotones, c);

            fondo.add(card);
        }

        private void confirmar() {
            String id = txtId.getText().trim();
            String clase = (String) comboClase.getSelectedItem();

            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el ID de empleado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar si existe el empleado
            boolean existe = false;
            for (String emp : empleados) {
                if (emp.equals(id)) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                JOptionPane.showMessageDialog(this, "No existe un empleado con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if ("Clase de Baile".equals(clase)) {
                if (cupoBaile >= 30) {
                    JOptionPane.showMessageDialog(this, "No hay cupo disponible para la Clase de Baile.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                reservasClases[totalReservasClases][0] = id;
                reservasClases[totalReservasClases][1] = "Clase de Baile";
                reservasClases[totalReservasClases][2] = "7:00 pm";
                cupoBaile++;
                totalReservasClases++;
                JOptionPane.showMessageDialog(this, "Reserva exitosa para la Clase de Baile.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            } else {
                if (cupoYoga >= 30) {
                    JOptionPane.showMessageDialog(this, "No hay cupo disponible para la Clase de Yoga.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                reservasClases[totalReservasClases][0] = id;
                reservasClases[totalReservasClases][1] = "Clase de Yoga";
                reservasClases[totalReservasClases][2] = "8:00 pm";
                cupoYoga++;
                totalReservasClases++;
                JOptionPane.showMessageDialog(this, "Reserva exitosa para la Clase de Yoga.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }

            dispose();
        }
    }

    // --- Consultar horarios (solo muestra información) ---
    static class DialogHorariosClases extends JDialog {
        public DialogHorariosClases(Frame owner) {
            super(owner, "Horarios de Clases", true);
            setSize(480, 250);
            setLocationRelativeTo(owner);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new GridBagLayout());
            setContentPane(fondo);

            GlassCard card = new GlassCard(new BorderLayout(10, 10));

            JLabel titulo = new JLabel("Horarios de Clases", SwingConstants.CENTER);
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
            titulo.setForeground(new Color(40, 35, 55));

            String texto = "Horarios disponibles:\n\n" +
                    "Clase de Baile: 7:00 pm\n" +
                    "Clase de Yoga: 8:00 pm\n\n" +
                    "Cupos disponibles para Baile: " + (30 - cupoBaile) + "\n" +
                    "Cupos disponibles para Yoga: " + (30 - cupoYoga);

            JTextArea area = new JTextArea(texto);
            area.setEditable(false);
            area.setOpaque(false);
            area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            area.setForeground(new Color(40, 35, 55));
            area.setLineWrap(true);
            area.setWrapStyleWord(true);

            JScrollPane scroll = new JScrollPane(area);
            scroll.setOpaque(false);
            scroll.getViewport().setOpaque(false);
            scroll.setBorder(null);

            ModernButton btnCerrar = new ModernButton("   Cerrar");
            btnCerrar.addActionListener(e -> dispose());

            JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            bottom.setOpaque(false);
            bottom.add(btnCerrar);

            card.add(titulo, BorderLayout.NORTH);
            card.add(scroll, BorderLayout.CENTER);
            card.add(bottom, BorderLayout.SOUTH);

            fondo.add(card);
        }
    }

    // --- Consultar reservaciones de clases ---
    static class DialogConsultarReservacionesClase extends JDialog {
        public DialogConsultarReservacionesClase(Frame owner) {
            super(owner, "Reservaciones de Clases", true);
            setSize(600, 380);
            setLocationRelativeTo(owner);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new GridBagLayout());
            setContentPane(fondo);

            GlassCard card = new GlassCard(new BorderLayout(10, 10));

            JLabel titulo = new JLabel("Reservaciones de Clases", SwingConstants.CENTER);
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
            titulo.setForeground(new Color(40, 35, 55));

            StringBuilder mensaje = new StringBuilder();
            if (totalReservasClases == 0) {
                mensaje.append("No hay reservaciones hechas aún.");
            } else {
                mensaje.append("Reservaciones actuales:\n\n");
                for (int i = 0; i < totalReservasClases; i++) {
                    if (reservasClases[i][0] != null) {
                        String id = reservasClases[i][0];
                        String nombre = "";
                        for (int j = 0; j < empleados.length; j++) {
                            if (empleados[j].equals(id)) {
                                nombre = nombres[j];
                                break;
                            }
                        }
                        mensaje.append((i + 1)).append(". ").append(nombre)
                                .append(" (").append(id).append(") - ")
                                .append(reservasClases[i][1]).append(" a las ")
                                .append(reservasClases[i][2]).append("\n");
                    }
                }
            }

            JTextArea area = new JTextArea(mensaje.toString());
            area.setEditable(false);
            area.setOpaque(false);
            area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            area.setForeground(new Color(40, 35, 55));
            area.setLineWrap(true);
            area.setWrapStyleWord(true);

            JScrollPane scroll = new JScrollPane(area);
            scroll.setOpaque(false);
            scroll.getViewport().setOpaque(false);
            scroll.setBorder(null);

            ModernButton btnCerrar = new ModernButton("   Cerrar");
            btnCerrar.addActionListener(e -> dispose());

            JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            bottom.setOpaque(false);
            bottom.add(btnCerrar);

            card.add(titulo, BorderLayout.NORTH);
            card.add(scroll, BorderLayout.CENTER);
            card.add(bottom, BorderLayout.SOUTH);

            fondo.add(card);
        }
    }

    // --- Modificar reservación de clase (cambiar de Baile a Yoga o viceversa) ---
    static class DialogModificarClase extends JDialog {
        private JTextField txtId;

        public DialogModificarClase(Frame owner) {
            super(owner, "Modificar Reservación de Clase", true);
            setSize(520, 260);
            setLocationRelativeTo(owner);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new GridBagLayout());
            setContentPane(fondo);

            GlassCard card = new GlassCard(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(6, 6, 6, 6);
            c.fill = GridBagConstraints.HORIZONTAL;

            JLabel titulo = new JLabel("Modificar Reservación de Clase");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
            titulo.setForeground(new Color(40, 35, 55));

            txtId = new JTextField(15);

            JLabel lblInfo = new JLabel("Ingrese su ID de empleado para cambiar de Baile a Yoga o viceversa.");
            lblInfo.setForeground(new Color(60, 60, 60));

            ModernButton btnModificar = new ModernButton("   ✏️   Modificar");
            ModernButton btnCancelar  = new ModernButton("   ❌   Cancelar");

            btnModificar.addActionListener(e -> modificar());
            btnCancelar.addActionListener(e -> dispose());

            c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
            card.add(titulo, c);

            c.gridy = 1;
            card.add(lblInfo, c);

            c.gridy = 2; c.gridwidth = 1;
            card.add(new JLabel("ID Empleado:"), c);

            c.gridx = 1;
            card.add(txtId, c);

            JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panelBotones.setOpaque(false);
            panelBotones.add(btnCancelar);
            panelBotones.add(btnModificar);

            c.gridx = 0; c.gridy = 3; c.gridwidth = 2;
            card.add(panelBotones, c);

            fondo.add(card);
        }

        private void modificar() {
            String id = txtId.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean existe = false;
            for (String emp : empleados) {
                if (emp.equals(id)) { existe = true; break; }
            }
            if (!existe) {
                JOptionPane.showMessageDialog(this, "No existe un empleado con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean encontrada = false;

            for (int i = 0; i < totalReservasClases; i++) {
                if (reservasClases[i][0] != null && reservasClases[i][0].equals(id)) {
                    String claseActual = reservasClases[i][1];
                    String nuevaClase;
                    String nuevaHora;

                    if ("Clase de Baile".equals(claseActual)) {
                        if (cupoYoga >= 30) {
                            JOptionPane.showMessageDialog(this, "No hay cupo disponible para Yoga.", "Aviso", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        nuevaClase = "Clase de Yoga";
                        nuevaHora = "8:00 pm";
                        cupoYoga++;
                        cupoBaile--;
                    } else if ("Clase de Yoga".equals(claseActual)) {
                        if (cupoBaile >= 30) {
                            JOptionPane.showMessageDialog(this, "No hay cupo disponible para Baile.", "Aviso", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        nuevaClase = "Clase de Baile";
                        nuevaHora = "7:00 pm";
                        cupoBaile++;
                        cupoYoga--;
                    } else {
                        JOptionPane.showMessageDialog(this, "La reservación no es de una clase válida.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    reservasClases[i][1] = nuevaClase;
                    reservasClases[i][2] = nuevaHora;
                    JOptionPane.showMessageDialog(this, "Clase modificada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    encontrada = true;
                    break;
                }
            }

            if (!encontrada) {
                JOptionPane.showMessageDialog(this, "No se encontró una reservación con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                dispose();
            }
        }
    }

    // --- Eliminar reservación de clase ---
    static class DialogEliminarClase extends JDialog {
        private JTextField txtId;

        public DialogEliminarClase(Frame owner) {
            super(owner, "Eliminar Reservación de Clase", true);
            setSize(480, 240);
            setLocationRelativeTo(owner);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new GridBagLayout());
            setContentPane(fondo);

            GlassCard card = new GlassCard(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(6, 6, 6, 6);
            c.fill = GridBagConstraints.HORIZONTAL;

            JLabel titulo = new JLabel("Eliminar Reservación de Clase");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
            titulo.setForeground(new Color(40, 35, 55));

            txtId = new JTextField(15);

            JLabel lblInfo = new JLabel("Ingrese el ID del empleado cuya reservación desea eliminar.");
            lblInfo.setForeground(new Color(60, 60, 60));

            ModernButton btnEliminar = new ModernButton("   ❌   Eliminar");
            ModernButton btnCancelar = new ModernButton("   Cancelar");

            btnEliminar.addActionListener(e -> eliminar());
            btnCancelar.addActionListener(e -> dispose());

            c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
            card.add(titulo, c);

            c.gridy = 1;
            card.add(lblInfo, c);

            c.gridy = 2; c.gridwidth = 1;
            card.add(new JLabel("ID Empleado:"), c);

            c.gridx = 1;
            card.add(txtId, c);

            JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panelBotones.setOpaque(false);
            panelBotones.add(btnCancelar);
            panelBotones.add(btnEliminar);

            c.gridx = 0; c.gridy = 3; c.gridwidth = 2;
            card.add(panelBotones, c);

            fondo.add(card);
        }

        private void eliminar() {
            String id = txtId.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean existe = false;
            for (String emp : empleados) {
                if (emp.equals(id)) { existe = true; break; }
            }
            if (!existe) {
                JOptionPane.showMessageDialog(this, "No existe un empleado con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean encontrada = false;

            for (int i = 0; i < totalReservasClases; i++) {
                if (reservasClases[i][0] != null && reservasClases[i][0].equals(id)) {
                    String tipoClase = reservasClases[i][1];

                    for (int j = i; j < totalReservasClases - 1; j++) {
                        reservasClases[j][0] = reservasClases[j + 1][0];
                        reservasClases[j][1] = reservasClases[j + 1][1];
                        reservasClases[j][2] = reservasClases[j + 1][2];
                    }

                    reservasClases[totalReservasClases - 1][0] = null;
                    reservasClases[totalReservasClases - 1][1] = null;
                    reservasClases[totalReservasClases - 1][2] = null;

                    totalReservasClases--;

                    if ("Clase de Baile".equals(tipoClase)) cupoBaile--;
                    else if ("Clase de Yoga".equals(tipoClase)) cupoYoga--;

                    JOptionPane.showMessageDialog(this, "Reservación eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    encontrada = true;
                    break;
                }
            }

            if (!encontrada) {
                JOptionPane.showMessageDialog(this, "No se encontró una reservación con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                dispose();
            }
        }
    }

    // =========================================================
    // =============== DIALOGOS PARA BARISTA ===================
    // =========================================================

    // --- Solicitar Bebida ---
    static class DialogSolicitarBebida extends JDialog {
        private JTextField txtId;
        private JComboBox<String> comboBebida;
        private JTextField txtHora;

        public DialogSolicitarBebida(Frame owner) {
            super(owner, "Solicitar Bebida", true);
            setSize(540, 300);
            setLocationRelativeTo(owner);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new GridBagLayout());
            setContentPane(fondo);

            GlassCard card = new GlassCard(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(6, 6, 6, 6);
            c.fill = GridBagConstraints.HORIZONTAL;

            JLabel titulo = new JLabel("Solicitar Bebida");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
            titulo.setForeground(new Color(40, 35, 55));

            JLabel lblInfo = new JLabel("Ingrese su ID, seleccione la bebida y la hora de entrega.");
            lblInfo.setForeground(new Color(60, 60, 60));

            txtId = new JTextField(15);
            comboBebida = new JComboBox<>(new String[]{
                    "Café normal",
                    "Capuchino",
                    "Capuchino con vainilla",
                    "Chocolate",
                    "Moka",
                    "Té chai",
                    "Café frío"
            });
            txtHora = new JTextField(10);
            txtHora.setText("3:30 pm");

            ModernButton btnSolicitar = new ModernButton("   📝   Registrar Pedido");
            ModernButton btnCancelar  = new ModernButton("   ❌   Cancelar");

            btnSolicitar.addActionListener(e -> registrar());
            btnCancelar.addActionListener(e -> dispose());

            c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
            card.add(titulo, c);

            c.gridy = 1;
            card.add(lblInfo, c);

            c.gridy = 2; c.gridwidth = 1;
            card.add(new JLabel("ID Empleado:"), c);
            c.gridx = 1;
            card.add(txtId, c);

            c.gridx = 0; c.gridy = 3;
            card.add(new JLabel("Bebida:"), c);
            c.gridx = 1;
            card.add(comboBebida, c);

            c.gridx = 0; c.gridy = 4;
            card.add(new JLabel("Hora entrega:"), c);
            c.gridx = 1;
            card.add(txtHora, c);

            JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panelBotones.setOpaque(false);
            panelBotones.add(btnCancelar);
            panelBotones.add(btnSolicitar);

            c.gridx = 0; c.gridy = 5; c.gridwidth = 2;
            card.add(panelBotones, c);

            fondo.add(card);
        }

        private void registrar() {
            String id = txtId.getText().trim();
            String bebida = (String) comboBebida.getSelectedItem();
            String hora = txtHora.getText().trim();

            if (id.isEmpty() || hora.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar ID y hora.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean existe = false;
            for (String emp : empleados) {
                if (emp.equals(id)) { existe = true; break; }
            }
            if (!existe) {
                JOptionPane.showMessageDialog(this, "No existe un empleado con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int i = 0; i < totalPedidos; i++) {
                if (pedidosBarista[i][0].equals(id)) {
                    JOptionPane.showMessageDialog(this, "Ya has pedido una bebida hoy.", "Aviso", JOptionPane.WARNING_MESSAGE);
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

    // --- Consultar pedidos ---
    static class DialogConsultarPedidos extends JDialog {
        public DialogConsultarPedidos(Frame owner) {
            super(owner, "Pedidos de Bebidas", true);
            setSize(600, 380);
            setLocationRelativeTo(owner);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new GridBagLayout());
            setContentPane(fondo);

            GlassCard card = new GlassCard(new BorderLayout(10, 10));

            JLabel titulo = new JLabel("Pedidos Registrados", SwingConstants.CENTER);
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
            titulo.setForeground(new Color(40, 35, 55));

            StringBuilder mensaje = new StringBuilder();
            if (totalPedidos == 0) {
                mensaje.append("No hay pedidos registrados aún.");
            } else {
                mensaje.append("Pedidos actuales:\n\n");
                for (int i = 0; i < totalPedidos; i++) {
                    if (pedidosBarista[i][0] != null) {
                        String id = pedidosBarista[i][0];
                        String nombre = "";
                        for (int j = 0; j < empleados.length; j++) {
                            if (empleados[j].equals(id)) {
                                nombre = nombres[j];
                                break;
                            }
                        }
                        mensaje.append((i + 1)).append(". ").append(nombre)
                                .append(" (").append(id).append(") - ")
                                .append(pedidosBarista[i][1]).append(" a las ")
                                .append(pedidosBarista[i][2]).append("\n");
                    }
                }
            }

            JTextArea area = new JTextArea(mensaje.toString());
            area.setEditable(false);
            area.setOpaque(false);
            area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            area.setForeground(new Color(40, 35, 55));
            area.setLineWrap(true);
            area.setWrapStyleWord(true);

            JScrollPane scroll = new JScrollPane(area);
            scroll.setOpaque(false);
            scroll.getViewport().setOpaque(false);
            scroll.setBorder(null);

            ModernButton btnCerrar = new ModernButton("   Cerrar");
            btnCerrar.addActionListener(e -> dispose());

            JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            bottom.setOpaque(false);
            bottom.add(btnCerrar);

            card.add(titulo, BorderLayout.NORTH);
            card.add(scroll, BorderLayout.CENTER);
            card.add(bottom, BorderLayout.SOUTH);

            fondo.add(card);
        }
    }

    // --- Modificar pedido ---
    static class DialogModificarPedido extends JDialog {
        private JTextField txtId;
        private JComboBox<String> comboBebida;
        private JTextField txtHora;

        public DialogModificarPedido(Frame owner) {
            super(owner, "Modificar Pedido de Bebida", true);
            setSize(540, 300);
            setLocationRelativeTo(owner);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new GridBagLayout());
            setContentPane(fondo);

            GlassCard card = new GlassCard(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(6, 6, 6, 6);
            c.fill = GridBagConstraints.HORIZONTAL;

            JLabel titulo = new JLabel("Modificar Pedido");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
            titulo.setForeground(new Color(40, 35, 55));

            JLabel lblInfo = new JLabel("Ingrese el ID del empleado y los nuevos datos del pedido.");
            lblInfo.setForeground(new Color(60, 60, 60));

            txtId = new JTextField(15);
            comboBebida = new JComboBox<>(new String[]{
                    "Café normal",
                    "Capuchino",
                    "Capuchino con vainilla",
                    "Chocolate",
                    "Moka",
                    "Té chai",
                    "Café frío"
            });
            txtHora = new JTextField(10);
            txtHora.setText("4:00 pm");

            ModernButton btnModificar = new ModernButton("   ✏️   Guardar cambios");
            ModernButton btnCancelar  = new ModernButton("   ❌   Cancelar");

            btnModificar.addActionListener(e -> modificar());
            btnCancelar.addActionListener(e -> dispose());

            c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
            card.add(titulo, c);

            c.gridy = 1;
            card.add(lblInfo, c);

            c.gridy = 2; c.gridwidth = 1;
            card.add(new JLabel("ID Empleado:"), c);
            c.gridx = 1;
            card.add(txtId, c);

            c.gridx = 0; c.gridy = 3;
            card.add(new JLabel("Nueva Bebida:"), c);
            c.gridx = 1;
            card.add(comboBebida, c);

            c.gridx = 0; c.gridy = 4;
            card.add(new JLabel("Nueva Hora:"), c);
            c.gridx = 1;
            card.add(txtHora, c);

            JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panelBotones.setOpaque(false);
            panelBotones.add(btnCancelar);
            panelBotones.add(btnModificar);

            c.gridx = 0; c.gridy = 5; c.gridwidth = 2;
            card.add(panelBotones, c);

            fondo.add(card);
        }

        private void modificar() {
            String id = txtId.getText().trim();
            String nuevaBebida = (String) comboBebida.getSelectedItem();
            String nuevaHora = txtHora.getText().trim();

            if (id.isEmpty() || nuevaHora.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar ID y hora.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean existe = false;
            for (String emp : empleados) {
                if (emp.equals(id)) { existe = true; break; }
            }
            if (!existe) {
                JOptionPane.showMessageDialog(this, "No existe un empleado con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int i = 0; i < totalPedidos; i++) {
                if (pedidosBarista[i][0] != null && pedidosBarista[i][0].equals(id)) {
                    pedidosBarista[i][1] = nuevaBebida;
                    pedidosBarista[i][2] = nuevaHora;
                    JOptionPane.showMessageDialog(this, "Pedido modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    return;
                }
            }

            JOptionPane.showMessageDialog(this, "No se encontró un pedido con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- Eliminar pedido ---
    static class DialogEliminarPedido extends JDialog {
        private JTextField txtId;

        public DialogEliminarPedido(Frame owner) {
            super(owner, "Eliminar Pedido de Bebida", true);
            setSize(480, 240);
            setLocationRelativeTo(owner);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new GridBagLayout());
            setContentPane(fondo);

            GlassCard card = new GlassCard(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(6, 6, 6, 6);
            c.fill = GridBagConstraints.HORIZONTAL;

            JLabel titulo = new JLabel("Eliminar Pedido");
            titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
            titulo.setForeground(new Color(40, 35, 55));

            JLabel lblInfo = new JLabel("Ingrese el ID del empleado cuyo pedido desea eliminar.");
            lblInfo.setForeground(new Color(60, 60, 60));

            txtId = new JTextField(15);

            ModernButton btnEliminar = new ModernButton("   ❌   Eliminar");
            ModernButton btnCancelar = new ModernButton("   Cancelar");

            btnEliminar.addActionListener(e -> eliminar());
            btnCancelar.addActionListener(e -> dispose());

            c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
            card.add(titulo, c);

            c.gridy = 1;
            card.add(lblInfo, c);

            c.gridy = 2; c.gridwidth = 1;
            card.add(new JLabel("ID Empleado:"), c);
            c.gridx = 1;
            card.add(txtId, c);

            JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panelBotones.setOpaque(false);
            panelBotones.add(btnCancelar);
            panelBotones.add(btnEliminar);

            c.gridx = 0; c.gridy = 3; c.gridwidth = 2;
            card.add(panelBotones, c);

            fondo.add(card);
        }

        private void eliminar() {
            String id = txtId.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean existe = false;
            for (String emp : empleados) {
                if (emp.equals(id)) { existe = true; break; }
            }
            if (!existe) {
                JOptionPane.showMessageDialog(this, "No existe un empleado con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean encontrado = false;

            for (int i = 0; i < totalPedidos; i++) {
                if (pedidosBarista[i][0] != null && pedidosBarista[i][0].equals(id)) {
                    for (int j = i; j < totalPedidos - 1; j++) {
                        pedidosBarista[j][0] = pedidosBarista[j + 1][0];
                        pedidosBarista[j][1] = pedidosBarista[j + 1][1];
                        pedidosBarista[j][2] = pedidosBarista[j + 1][2];
                    }
                    pedidosBarista[totalPedidos - 1][0] = null;
                    pedidosBarista[totalPedidos - 1][1] = null;
                    pedidosBarista[totalPedidos - 1][2] = null;
                    totalPedidos--;

                    JOptionPane.showMessageDialog(this, "Pedido eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(this, "No se encontró un pedido con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                dispose();
            }
        }
    }

    // =========================================================
    // ======================== LÓGICA ORIGINAL =================
    // =========================================================
    // Tus métodos originales se mantienen (aunque ya no se usan para input).
    // Los dejo igual por si el profe quiere revisarlos.

    public static void manejarCine() {
        JOptionPane.showMessageDialog(null, "Has elegido: Cine 🎬");
    }

    public static void manejarGimnasio() {
        JOptionPane.showMessageDialog(null, "Has elegido: Gimnasio 🏋️");
    }

    public static void escogerClase() {
        String input = JOptionPane.showInputDialog("Seleccione:\n1 - Baile\n2 - Yoga");
        if (input == null || input.isEmpty()) return;
        try {
            int op = Integer.parseInt(input);
            if (op == 1) reservar("Clase de Baile");
            else if (op == 2) reservar("Clase de Yoga");
        } catch (NumberFormatException e) {
            mostrarError("Entrada inválida.");
        }
    }

    public static void reservar(String actividad){
        // (Lógica original conservada por si se usa en el futuro)
        String id = JOptionPane.showInputDialog("Ingrese su ID de empleado porfavor");
        if(id == null || id.trim().isEmpty()){
            mostrarError("No ingresaste ninguna opción. El programa se cerrará.");
        }

        boolean existe = false;
        for(String emp : empleados){
            if(emp.equals(id.trim())){
                existe = true; break;
            }
        }

        if(!existe){
            mostrarError("No existe un empleado registrado con ese ID");
        }

        if(actividad.equals("Clase de Baile")){
            if(cupoBaile >= 30){
                JOptionPane.showMessageDialog(null,"No hay un cupo disponible para la Clase de Baile");
                return;
            }else{
                String hora = "7:00 pm";
                reservasClases[totalReservasClases][0] = id;
                reservasClases[totalReservasClases][1] = "Clase de Baile";
                reservasClases[totalReservasClases][2] = hora;

                cupoBaile++;
                totalReservasClases++;

                JOptionPane.showMessageDialog(null, "Reserva exitosa para la Clase de Baile");
            }

        } else if(actividad.equals("Clase de Yoga")){
            if(cupoYoga >= 30){
                JOptionPane.showMessageDialog(null,"No hay un cupo disponible para la Clase de Yoga");
            }else{
                String hora = "8:00 pm";
                reservasClases[totalReservasClases][0] = id;
                reservasClases[totalReservasClases][1] = "Clase de Yoga";
                reservasClases[totalReservasClases][2] = hora;

                cupoYoga++;
                totalReservasClases++;

                JOptionPane.showMessageDialog(null, "Reserva exitosa para la Clase de Yoga");
            }

        } else if(actividad.equals("Cine")){

        } else if(actividad.equals("Gimnasio")){

        } else if(actividad.equals("Barista")){
            for(int i = 0; i < totalPedidos; i++){
                if(pedidosBarista[i][0].equals(id.trim())){
                    JOptionPane.showMessageDialog(null, "Ya has pedido una bebida hoy");
                    return;
                }
            }

            String bebida = JOptionPane.showInputDialog("Menú de bebidas:\n\n1 - Café normal\n2 - Capuchino\n3 - Capuchino con vainilla\n4 - Chocolate\n5 - Moka\n6 - Té chai\n7 - Café frío\n\nDigite la bebida:");
            if(bebida == null || bebida.trim().isEmpty()){
                mostrarError("No ingresaste ninguna bebida");
            }

            String hora = JOptionPane.showInputDialog("Ingrese la hora de entrega (ej: 3:30 pm)");
            if(hora == null || hora.trim().isEmpty()) {
                mostrarError("No ingresaste ninguna hora.");
            }

            pedidosBarista[totalPedidos][0] = id.trim();
            pedidosBarista[totalPedidos][1] = bebida.trim();
            pedidosBarista[totalPedidos][2] = hora.trim();
            totalPedidos++;

            JOptionPane.showMessageDialog(null, "Pedido registrado exitosamente.");
        }
    }

    public static void consultarHorarios(String actividad){
        if(actividad.equals("Clases")){
            JOptionPane.showMessageDialog(null, "Horarios disponibles:\n\nClase de Baile: 7:00 pm\nClase de Yoga: 8:00 pm\n\nCupos Baile: "
                    + (30 - cupoBaile) + "\nCupos Yoga: " + (30 - cupoYoga));
        }
    }

    public static void consultarReservaciones(String actividad) {
        if (actividad.equals("Clases")) {

            String mensaje = "Reservaciones actuales:\n\n";

            if (totalReservasClases == 0) {
                mensaje = "No hay reservaciones hechas aún.";
            } else {
                for (int i = 0; i < totalReservasClases; i++) {
                    if (reservasClases[i][0] != null) {

                        String nombre = "";
                        for (int j = 0; j < empleados.length; j++) {
                            if (empleados[j].equals(reservasClases[i][0])) {
                                nombre = nombres[j];
                                break;
                            }
                        }

                        mensaje += (i + 1) + ". " + nombre + " (" + reservasClases[i][0] + ") - "
                                + reservasClases[i][1] + " a las " + reservasClases[i][2] + "\n";
                    }
                }
            }

            JOptionPane.showMessageDialog(null, mensaje);

        } else if(actividad.equals("Barista")){
            if(totalPedidos == 0){
                JOptionPane.showMessageDialog(null, "No hay pedidos registrados aún.");
                return;
            }

            String mensaje = "Pedidos registrados:\n\n";

            for (int i = 0; i < totalPedidos; i++) {

                String nombre = "";
                for (int j = 0; j < empleados.length; j++) {
                    if (empleados[j].equals(pedidosBarista[i][0])) {
                        nombre = nombres[j];
                        break;
                    }
                }

                mensaje += (i + 1) + ". " + nombre + " (" + pedidosBarista[i][0] + ") - "
                        + pedidosBarista[i][1] + " a las " + pedidosBarista[i][2] + "\n";
            }

            JOptionPane.showMessageDialog(null, mensaje);
        }
    }

    public static void modificarReservaciones(String actividad){
        // Lógica original mantenida
    }

    public static void eliminarReservaciones(String actividad){
        // Lógica original mantenida
    }

    // =========================================================
    // ======================== UTILIDADES ======================
    // =========================================================

    public static void cargarDatos() {
        empleados = new String[]{"E001", "E002", "E003"};
        nombres = new String[]{"Julián Quesada", "Valeria Alfaro", "Justin Cordero"};
        horariosGym = new String[]{"2pm", "3pm", "4pm", "5pm", "6pm", "7pm"};
        pelicula = "Matrix Reloaded";
        asientosCine = new String[5][6];
        reservasClases = new String[60][3];
        pedidosBarista = new String[100][3];
    }

    public static void salir() {
        JOptionPane.showMessageDialog(null, "¡Gracias por usar el sistema!");
        System.exit(0);
    }

    public static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}