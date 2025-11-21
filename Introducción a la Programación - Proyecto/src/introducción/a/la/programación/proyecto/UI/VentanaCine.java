/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Ventana principal del módulo de Cine. Muestra el mapa de asientos, permite
 * reservarlos y liberarlos por empleado, además de acceder a diálogos para
 * cambiar la película, ver reservaciones y reiniciar la sala.
 */

package introducción.a.la.programación.proyecto.UI;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class VentanaCine extends JFrame {

    private final String[] FILAS = {"A", "B", "C", "D", "E"};
    private JButton[][] botonesAsientos;
    private JLabel lblPelicula;
    private JLabel lblInfo;

    /**
     * Constructor de la ventana del módulo Cine.
     */
    public VentanaCine() {

        setTitle("Módulo de Cine");
        setSize(900, 580);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Fondo con gradiente
        GradientPanel fondo = new GradientPanel();
        fondo.setLayout(new BorderLayout());
        setContentPane(fondo);

        // ============ HEADER ============
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(18, 38, 10, 38));

        JLabel titulo = new JLabel("Cine - Reserva de Asientos");
        titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);

        lblPelicula = new JLabel("Película actual: " + Data.pelicula);
        lblPelicula.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblPelicula.setForeground(new Color(245, 240, 255));

        header.add(titulo, BorderLayout.NORTH);
        header.add(lblPelicula, BorderLayout.SOUTH);

        fondo.add(header, BorderLayout.NORTH);

        // ============ CENTRO (MAPA ASIENTOS + ACCIONES) ============
        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        fondo.add(center, BorderLayout.CENTER);

        // ----- Tarjeta con los asientos -----
        GlassCard cardSala = new GlassCard(new BorderLayout(10, 10));
        cardSala.setPreferredSize(new Dimension(600, 360));

        JPanel panelAsientos = new JPanel(new GridLayout(5, 6, 8, 8));
        panelAsientos.setOpaque(false);

        botonesAsientos = new JButton[5][6];

        // Crear botones de asientos
        for (int f = 0; f < 5; f++) {
            for (int c = 0; c < 6; c++) {

                String codigo = FILAS[f] + (c + 1);
                JButton btn = new JButton(codigo);
                btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
                btn.setFocusPainted(false);
                btn.setOpaque(true);
                btn.setBorderPainted(false);

                final int fila = f;
                final int col = c;

                // Listener de click en asiento
                btn.addActionListener(e -> manejarClickAsiento(fila, col));

                botonesAsientos[f][c] = btn;
                panelAsientos.add(btn);

                // Ajustar color según estado inicial en Data.asientosCine
                actualizarBotonAsiento(f, c);
            }
        }

        // ----- Leyenda -----
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

        // ----- Tarjeta de acciones a la derecha -----
        GlassCard cardAcciones = new GlassCard(new BorderLayout());
        cardAcciones.setPreferredSize(new Dimension(240, 260));

        JPanel actionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        actionsPanel.setOpaque(false);
        actionsPanel.setBorder(BorderFactory.createEmptyBorder());

        ModernButton btnCambiar = new ModernButton("Cambiar película");
        ModernButton btnLista   = new ModernButton("Reservaciones");
        ModernButton btnReset   = new ModernButton("Reiniciar sala");
        ModernButton btnCerrar  = new ModernButton("Cerrar");

        btnCambiar.addActionListener(e -> new introducción.a.la.programación.proyecto.UI.Dialogos.Cine.DialogCambiarPelicula(this).setVisible(true));
        btnLista.addActionListener(e -> new introducción.a.la.programación.proyecto.UI.Dialogos.Cine.DialogVerReservacionesCine(this).setVisible(true));
        btnReset.addActionListener(e -> new introducción.a.la.programación.proyecto.UI.Dialogos.Cine.DialogReiniciarSala(this).setVisible(true));
        btnCerrar.addActionListener(e -> dispose());

        actionsPanel.add(btnCambiar);
        actionsPanel.add(btnLista);
        actionsPanel.add(btnReset);
        actionsPanel.add(btnCerrar);

        cardAcciones.add(actionsPanel, BorderLayout.CENTER);

        gc.gridx = 1;
        center.add(cardAcciones, gc);

        // ============ PIE (MENSAJE INFORMATIVO) ============
        JPanel abajo = new JPanel(new BorderLayout());
        abajo.setOpaque(false);
        abajo.setBorder(BorderFactory.createEmptyBorder(0, 40, 18, 40));
        abajo.add(lblInfo, BorderLayout.CENTER);

        fondo.add(abajo, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Actualiza el título de la película mostrado en la parte superior.
     */
    public void actualizarTituloPelicula() {
        lblPelicula.setText("Película actual: " + Data.pelicula);
    }

    /**
     * Recorre todos los asientos y actualiza el color según el estado
     * almacenado en Data.asientosCine.
     */
    public void refrescarAsientos() {
        for (int f = 0; f < 5; f++) {
            for (int c = 0; c < 6; c++) {
                actualizarBotonAsiento(f, c);
            }
        }
    }

    /**
     * Actualiza el color de un botón de asiento concreto.
     * Verde = Libre (sin ID en Data.asientosCine)
     * Rojo  = Ocupado (con ID de empleado).
     */
    private void actualizarBotonAsiento(int fila, int col) {

        JButton btn = botonesAsientos[fila][col];
        String id = Data.asientosCine[fila][col];

        if (id == null) {
            // Asiento libre
            btn.setBackground(new Color(200, 255, 200));
            btn.setBorder(BorderFactory.createLineBorder(new Color(120, 180, 120)));
            btn.setToolTipText("Libre");
        } else {
            // Asiento ocupado
            btn.setBackground(new Color(255, 200, 200));
            btn.setBorder(BorderFactory.createLineBorder(new Color(190, 120, 120)));
            String nombre = Util.obtenerNombreEmpleado(id);
            btn.setToolTipText("Ocupado por " + nombre + " (" + id + ")");
        }
    }

    /**
     * Maneja la lógica cuando el usuario hace clic en un asiento:
     * - Si está libre: solicita ID de empleado, valida y reserva.
     * - Si está ocupado: muestra detalle y ofrece cancelar la reservación.
     */
    private void manejarClickAsiento(int fila, int col) {

        String codigo = Util.codigoAsiento(fila, col);
        String idActual = Data.asientosCine[fila][col];

        // Asiento libre -> reservar
        if (idActual == null) {

            String id = JOptionPane.showInputDialog(this,
                    "Asiento " + codigo + " está libre.\n\nIngrese ID del empleado:",
                    "Reservar asiento " + codigo, JOptionPane.QUESTION_MESSAGE);

            if (id == null || id.trim().isEmpty()) {
                return;
            }

            id = id.trim();

            if (!Util.existeEmpleado(id)) {
                Util.mostrarError("No existe un empleado con ese ID.");
                return;
            }

            if (empleadoYaTieneAsiento(id)) {
                Util.mostrarError("Ese empleado ya tiene un asiento reservado.");
                return;
            }

            // Reservar asiento
            Data.asientosCine[fila][col] = id;
            actualizarBotonAsiento(fila, col);
            lblInfo.setText("Asiento " + codigo + " reservado para " + Util.obtenerNombreEmpleado(id) + ".");

        } else {
            // Asiento ocupado -> mostrar info y preguntar si desea cancelar
            String nombre = Util.obtenerNombreEmpleado(idActual);

            int opcion = JOptionPane.showConfirmDialog(this,
                    "Asiento " + codigo + " está reservado por:\n"
                            + nombre + " (" + idActual + ")\n\n¿Cancelar reservación?",
                    "Asiento Ocupado", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                Data.asientosCine[fila][col] = null;
                actualizarBotonAsiento(fila, col);
                lblInfo.setText("Se canceló la reservación del asiento " + codigo + ".");
            }
        }
    }

    /**
     * Verifica si el empleado con el ID dado ya tiene algún asiento
     * reservado en la matriz de Data.asientosCine.
     */
    private boolean empleadoYaTieneAsiento(String id) {

        for (int f = 0; f < 5; f++) {
            for (int c = 0; c < 6; c++) {
                if (id.equals(Data.asientosCine[f][c])) {
                    return true;
                }
            }
        }
        return false;
    }

}
