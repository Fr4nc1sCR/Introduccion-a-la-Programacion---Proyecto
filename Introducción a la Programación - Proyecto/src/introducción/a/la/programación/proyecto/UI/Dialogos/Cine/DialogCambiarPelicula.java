/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Diálogo para cambiar el nombre de la película actual del módulo de Cine.
 * Actualiza la variable global Data.pelicula y refresca el título en
 * VentanaCine.
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Cine;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.UI.VentanaCine;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class DialogCambiarPelicula extends JDialog {

    private final JTextField txtPelicula;
    private final VentanaCine ventanaCine;

    /**
     * Constructor del diálogo.
     * @param owner VentanaCine que abre el diálogo.
     */
    public DialogCambiarPelicula(VentanaCine owner) {
        super(owner, "Cambiar película", true);
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

        JLabel lblTitulo = new JLabel("Cambiar Película");
        lblTitulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(40, 35, 55));

        JLabel lblActual = new JLabel("Película actual: " + Data.pelicula);
        lblActual.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblNueva = new JLabel("Nueva película:");
        txtPelicula = new JTextField(25);
        txtPelicula.setText(Data.pelicula);

        ModernButton btnCancelar = new ModernButton("Cancelar");
        ModernButton btnGuardar  = new ModernButton("Guardar");

        btnCancelar.addActionListener(e -> dispose());
        btnGuardar.addActionListener(e -> guardar());

        // Colocar componentes
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        card.add(lblTitulo, c);

        c.gridy = 1;
        card.add(lblActual, c);

        c.gridy = 2; c.gridwidth = 1;
        card.add(lblNueva, c);

        c.gridx = 1;
        card.add(txtPelicula, c);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        panelBotones.setOpaque(false);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnGuardar);

        c.gridx = 0; c.gridy = 3; c.gridwidth = 2;
        card.add(panelBotones, c);

        fondo.add(card);
    }

    /**
     * Valida el texto, actualiza Data.pelicula y refresca la ventana de Cine.
     */
    private void guardar() {
        String nueva = txtPelicula.getText().trim();

        if (nueva.isEmpty()) {
            Util.mostrarError("Debe ingresar el nombre de la película.");
            return;
        }

        Data.pelicula = nueva;
        if (ventanaCine != null) {
            ventanaCine.actualizarTituloPelicula();
        }

        JOptionPane.showMessageDialog(this,
                "Película cambiada correctamente.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        dispose();
    }
}
