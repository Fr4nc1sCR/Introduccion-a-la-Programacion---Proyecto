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

    // ===== COMPONENTE PERSONALIZADO: Botón moderno =====
    static class ModernButton extends JButton {
        private Color baseColor = new Color(70, 130, 180);
        private Color hoverColor = new Color(100, 149, 237);
        private Color clickColor = new Color(60, 110, 160);
        private boolean hover = false;
        private boolean click = false;

        public ModernButton(String text) {
            super(text);
            setForeground(Color.WHITE);
            setBackground(baseColor);
            setFont(new Font("Segoe UI", Font.BOLD, 15));
            setFocusPainted(false);
            setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setOpaque(false);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) { hover = true; repaint(); }
                @Override
                public void mouseExited(MouseEvent e) { hover = false; repaint(); }
                @Override
                public void mousePressed(MouseEvent e) { click = true; repaint(); }
                @Override
                public void mouseReleased(MouseEvent e) { click = false; repaint(); }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color color = baseColor;
            if (click) color = clickColor;
            else if (hover) color = hoverColor;

            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            super.paintComponent(g);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // ===== PANEL DE FONDO CON DEGRADADO =====
    static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            GradientPaint gp = new GradientPaint(0, 0, new Color(30, 50, 80),
                                                 0, getHeight(), new Color(10, 20, 40));
            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    // ===== Ventana Principal =====
    static class VentanaPrincipal extends JFrame {
        public VentanaPrincipal() {
            setTitle("Sistema de Gestión - Proyecto Final");
            setSize(550, 450);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new BorderLayout());

            JLabel lblTitulo = new JLabel("Menú Principal", SwingConstants.CENTER);
            lblTitulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 28));
            lblTitulo.setForeground(Color.WHITE);
            lblTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

            JPanel panelBotones = new JPanel(new GridLayout(5, 1, 15, 15));
            panelBotones.setOpaque(false);
            panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 100, 60, 100));

            ModernButton btnCine = new ModernButton("🎬 Cine");
            ModernButton btnGimnasio = new ModernButton("🏋 Gimnasio");
            ModernButton btnClases = new ModernButton("💃 Clases de Baile o Yoga");
            ModernButton btnBarista = new ModernButton("☕ Barista");
            ModernButton btnSalir = new ModernButton("❌ Salir");

            btnCine.addActionListener(e -> manejarCine());
            btnGimnasio.addActionListener(e -> manejarGimnasio());
            btnClases.addActionListener(e -> new VentanaClases());
            btnBarista.addActionListener(e -> new VentanaBarista());
            btnSalir.addActionListener(e -> salir());

            panelBotones.add(btnCine);
            panelBotones.add(btnGimnasio);
            panelBotones.add(btnClases);
            panelBotones.add(btnBarista);
            panelBotones.add(btnSalir);

            fondo.add(lblTitulo, BorderLayout.NORTH);
            fondo.add(panelBotones, BorderLayout.CENTER);
            add(fondo);
            setVisible(true);
        }
    }

    // ===== Ventana Clases =====
    static class VentanaClases extends JFrame {
        public VentanaClases() {
            setTitle("Módulo de Clases 💃");
            setSize(450, 400);
            setLocationRelativeTo(null);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new BorderLayout());

            JLabel lblTitulo = new JLabel("Gestión de Clases", SwingConstants.CENTER);
            lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
            lblTitulo.setForeground(Color.WHITE);
            lblTitulo.setBorder(BorderFactory.createEmptyBorder(25, 0, 15, 0));

            JPanel panel = new JPanel(new GridLayout(5, 1, 15, 15));
            panel.setOpaque(false);
            panel.setBorder(BorderFactory.createEmptyBorder(10, 80, 40, 80));

            ModernButton btnReservar = new ModernButton("📝 Reservar Clase");
            ModernButton btnHorarios = new ModernButton("🕒 Consultar Horarios");
            ModernButton btnModificar = new ModernButton("✏️ Modificar Reservaciones");
            ModernButton btnConsultar = new ModernButton("📋 Consultar Reservaciones");
            ModernButton btnEliminar = new ModernButton("❌ Eliminar Reservaciones");

            btnReservar.addActionListener(e -> escogerClase());
            btnHorarios.addActionListener(e -> consultarHorarios("Clases"));
            btnModificar.addActionListener(e -> modificarReservaciones("Clases"));
            btnConsultar.addActionListener(e -> consultarReservaciones("Clases"));
            btnEliminar.addActionListener(e -> eliminarReservaciones("Clases"));

            panel.add(btnReservar);
            panel.add(btnHorarios);
            panel.add(btnModificar);
            panel.add(btnConsultar);
            panel.add(btnEliminar);

            fondo.add(lblTitulo, BorderLayout.NORTH);
            fondo.add(panel, BorderLayout.CENTER);
            add(fondo);
            setVisible(true);
        }
    }

    // ===== Ventana Barista =====
    static class VentanaBarista extends JFrame {
        public VentanaBarista() {
            setTitle("Módulo Barista ☕");
            setSize(420, 360);
            setLocationRelativeTo(null);
            setResizable(false);

            GradientPanel fondo = new GradientPanel();
            fondo.setLayout(new BorderLayout());

            JLabel lblTitulo = new JLabel("Gestión del Barista", SwingConstants.CENTER);
            lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
            lblTitulo.setForeground(Color.WHITE);
            lblTitulo.setBorder(BorderFactory.createEmptyBorder(25, 0, 15, 0));

            JPanel panel = new JPanel(new GridLayout(4, 1, 15, 15));
            panel.setOpaque(false);
            panel.setBorder(BorderFactory.createEmptyBorder(10, 80, 40, 80));

            ModernButton btnSolicitar = new ModernButton("📝 Solicitar Bebida");
            ModernButton btnModificar = new ModernButton("✏️ Modificar Pedido");
            ModernButton btnConsultar = new ModernButton("📋 Consultar Pedidos");
            ModernButton btnEliminar = new ModernButton("❌ Eliminar Pedido");

            btnSolicitar.addActionListener(e -> reservar("Barista"));
            btnModificar.addActionListener(e -> modificarReservaciones("Barista"));
            btnConsultar.addActionListener(e -> consultarReservaciones("Barista"));
            btnEliminar.addActionListener(e -> eliminarReservaciones("Barista"));

            panel.add(btnSolicitar);
            panel.add(btnModificar);
            panel.add(btnConsultar);
            panel.add(btnEliminar);

            fondo.add(lblTitulo, BorderLayout.NORTH);
            fondo.add(panel, BorderLayout.CENTER);
            add(fondo);
            setVisible(true);
        }
    }

    
    /**
     * Este método va a realizar toda la lógica que se necesita para poder mostrar el menu del cine y sus opciones.
     */
    public static void manejarCine() {
        JOptionPane.showMessageDialog(null, "Has elegido: Cine 🎬");
        // Código para desarrollar la lógica del cine
        // Se ponen de acuerdo quien lo hace
    }
    
    
    /**
     * Este método va a realizar toda la lógica que se necesita para poder mostrar el menu del gimnasio y sus opciones.
     */
    public static void manejarGimnasio() {
        JOptionPane.showMessageDialog(null, "Has elegido: Gimnasio 🏋️");
        // Código para desarrollar la lógica del Gimnasio
        // Se ponen de acuerdo quien lo hace
    }

    public static void escogerClase() {
        String input = JOptionPane.showInputDialog("Seleccione:\n1 - Baile\n2 - Yoga");
        if (input == null || input.isEmpty()) return;
        try {
            int op = Integer.parseInt(input);
            if (op == 1) reservar("Clase de Baile");
            else if (op == 2) reservar("Clase de Yoga");
        } catch (NumberFormatException e) { mostrarError("Entrada inválida."); }
    }

    // === CRUD ===
    /**
     * Este método va a poder realizar la reservación ya sea del cine, gimnasio, clases entre otros...
     * @param actividad 
     */
    public static void reservar(String actividad){
        
        // Se le pide al usuario que ingrese su ID de empleado
        String id = JOptionPane.showInputDialog("Ingrese su ID de empleado porfavor");
        
        // Si no se ingresa ningún valor
        if(id == null || id.trim().isEmpty()){
            mostrarError("No ingresaste ninguna opción. El programa se cerrará.");
        }
        
        // Validación para ver si el ID ingresado está registrado
        boolean existe = false;
        for(String emp : empleados){
            if(emp.equals(id.trim())){
                existe = true;
                break;
            }
        }
     
        // Mostrar un mensaje de error por el ID ingresado que no existe
        if(!existe){
            mostrarError("No existe un empleado registrado con ese ID");
        }
        
        // Ahora se hace la reservación según la actividad
        if(actividad.equals("Clase de Baile")){
            
            // Se hace una validación para ver si ya hay más de 30 cupos ocupados
            if(cupoBaile >= 30){
                JOptionPane.showMessageDialog(null,"No hay un cupo disponible para la Clase de Baile");
                return; // Esto para poder devolvernos o mejor dicho salir del método
            }else{
                // Se guarda la reservación según ID y se referencia a cual clase
                String hora = actividad.equals("Clase de Baile") ? "7:00 pm" : "8:00 pm";
                reservasClases[totalReservasClases][0] = id;
                reservasClases[totalReservasClases][1] = "Clase de Baile";
                reservasClases[totalReservasClases][2] = hora;

                
                // Si hay cupos disponibles se incrementa el cupo de Baile 
                cupoBaile++;
                
                // Este es un contador general para imprimir el orden correcto de las reservaciones que se hacen
                totalReservasClases++;
                
                // Se muestra el mensaje exitoso
                JOptionPane.showMessageDialog(null, "Reserva exitosa para la Clase de Baile");
            }
        } else if(actividad.equals("Clase de Yoga")){
            // Se hace una validación para ver si ya hay más de 30 cupos ocupados
            if(cupoYoga >= 30){
                JOptionPane.showMessageDialog(null,"No hay un cupo disponible para la Clase de Yoga");
            }else{
                // Se guarda la reservación según ID y se referencia a cual clase
                String hora = actividad.equals("Clase de Baile") ? "7:00 pm" : "8:00 pm";
                reservasClases[totalReservasClases][0] = id;
                reservasClases[totalReservasClases][1] = "Clase de Yoga";
                reservasClases[totalReservasClases][2] = hora;
                
                // Si hay cupos disponibles se incrementa el cupo de Yoga 
                cupoYoga++;
                
                // Este es un contador general para imprimir el orden correcto de las reservaciones que se hacen
                totalReservasClases++;
                
                // Se muestra el mensaje exitoso
                JOptionPane.showMessageDialog(null, "Reserva exitosa para la Clase de Yoga");
            }
        } else if(actividad.equals("Cine")){
            // Código para reservar en el cine
            // Se ponen de acuerdo quien lo hace
        } else if(actividad.equals("Gimnasio")){
            // Código para reservar en el gimnasio
            // Se ponen de acuerdo quien lo hace
        } else if(actividad.equals("Barista")){
            // Se hace una validación para verificar si ya pidió antes
            for(int i = 0; i < totalPedidos; i++){
                if(pedidosBarista[i][0].equals(id.trim())){
                    JOptionPane.showMessageDialog(null, "Ya has pedido una bebida hoy");
                    return;
                }
            }
            
            // Menú de bebidas
            String bebida = JOptionPane.showInputDialog("Este sería el menu de bebidas:" + "\n\n" +
                                                        "1 - Café normal" + "\n" +
                                                        "2 - Capuchino" + "\n" +
                                                        "3 - Capuchino con vainilla" + "\n" +
                                                        "4 - Chocolate" + "\n" +
                                                        "5 - Moka" + "\n" +
                                                        "6 - Té chai" + "\n" +
                                                        "7 - Café frío" + "\n\n" +
                                                        "Digite el nombre de la bebida que desea ordenar.");
            
            // Validación por si no se escribe nada
            if(bebida == null || bebida.trim().isEmpty()){
                mostrarError("No ingresaste ninguna bebida");
            }
            
            // Se le pide al usuario la hora a la que quiere la bebida
            String hora = JOptionPane.showInputDialog("Ingrese la hora de entrega (ej: 3:30 pm)");
            
            // Velidación por si no se ingresó ninguna hora
            if (hora == null || hora.trim().isEmpty()) {
                mostrarError("No ingresaste ninguna hora.");
            }
            
            // Guardar la información del pedido
            pedidosBarista[totalPedidos][0] = id.trim();
            pedidosBarista[totalPedidos][1] = bebida.trim();
            pedidosBarista[totalPedidos][2] = hora.trim();
            totalPedidos++;
            
            // Si todo salió bien, mostrar el mensaje exitoso
            JOptionPane.showMessageDialog(null, "Pedido registrado exitosamente.");
        }
    }

    /**
     * Este método va a poder consultar los horarios ya sea del cine, gimnasio, clases entre otros...
     * @param actividad
     */
    public static void consultarHorarios(String actividad){
        if(actividad.equals("Clases")){
            JOptionPane.showMessageDialog(null, "Horarios disponibles para clases:" + "\n\n" +
                                                "Clase de Baile: 7:00 pm" + "\n" +
                                                "Clase de Yoga: 8:00 pm" + "\n\n" +
                                                "Cupos disponibles para la Clase de Baile: " + (30 - cupoBaile) + "\n" +
                                                "Cupos disponibles para la Clase de Yoga: " + (30 - cupoYoga) + "\n");
        } else if(actividad.equals("Cine")){
            // Código para consultar los horarios del cine
            // Se ponen de acuerdo quien lo hace
        } else if(actividad.equals("Gimnasio")){
            // Código para consultar los horarios del gimnasio
            // Se ponen de acuerdo quien lo hace
        }
    }

    /**
     * Este método va a poder hacer que se muestren todas las reservaciones que
     * haga el usuario solo indicando de cual actividad
     * @param actividad 
     */
    public static void consultarReservaciones(String actividad) {
        
        // Consultar reservaciones para la actividad de las clases
        if (actividad.equals("Clases")) {
            
            // Variable de mensaje para mostrar las reservaciones
            String mensaje = "Reservaciones actuales:\n\n";
            
            // Verificar si hay alguna reservación hecha
            if (totalReservasClases == 0) {
                mensaje = "No hay reservaciones hechas aún.";
            } else { 
                // Si hay una reservación hecha, se guarda la informacion en la variable mensaje
                for (int i = 0; i < totalReservasClases; i++) {
                    if (reservasClases[i][0] != null) {
                        
                        // Esto para guardar el nombre del empleado segun el id
                        String nombre = "";
                        for (int j = 0; j < empleados.length; j++) {
                            if (empleados[j].equals(reservasClases[i][0])) {
                                nombre = nombres[j];
                                break;
                            }
                        }
                        
                        // Se termina de guardar toda la información en la varible mensaje
                        mensaje += (i + 1) + ". " + nombre + " (" + reservasClases[i][0] + ") - "
                        + reservasClases[i][1] + " a las " + reservasClases[i][2] + "\n";
                    }
                }
            }

            // Mostrar el mensaje, sea que haya reservaciones o no
            JOptionPane.showMessageDialog(null, mensaje);
        } else if(actividad.equals("Cine")){
            // Código para consultar las reservaciones del cine
            // Se ponen de acuerdo quien lo hace
        } else if(actividad.equals("Gimnasio")){
            // Código para consultar las reservaciones del Gimnasio
            // Se ponen de acuerdo quien lo hace
        } else if(actividad.equals("Barista")){
            // Se muestra un mensaje por si no hay pedidos registrados
            if(totalPedidos == 0){
                JOptionPane.showMessageDialog(null, "No hay pedidos registrados aún.");
                return;
            }
            
            // Se empieza a formar el mensaje donde se muestran los pedidos
            String mensaje = "Pedidos registrados:\n\n";
            
            // Ciclo for para guardar todos los pedidos dentro del mensaje
            for (int i = 0; i < totalPedidos; i++) {
                
                // Esto para guardar el nombre del empleado segun el id
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
            
            // Se imprime el mensaje con los pedidos registrados de ese momento
            JOptionPane.showMessageDialog(null, mensaje);
        }
    }

    /**
     * Este método es para poder modificar la información de las reservaciones
     * segun el tipo de actividad que sea
     * @param actividad 
     */
    public static void modificarReservaciones(String actividad){
        
        // Se le pide al usuario que ingrese su ID de empleado
        String id = JOptionPane.showInputDialog("Ingrese su ID de empleado porfavor");
        
        // Si no se ingresa ningún valor
        if(id == null || id.trim().isEmpty()){
            mostrarError("No ingresaste ninguna opción. El programa se cerrará.");
        }
        
        // Validación para ver si el ID ingresado está registrado
        boolean existe = false;
        for(String emp : empleados){
            if(emp.equals(id.trim())){
                existe = true;
                break;
            }
        }
     
        // Mostrar un mensaje de error por el ID ingresado que no existe
        if(!existe){
            mostrarError("No existe un empleado registrado con ese ID");
        }
        
        // Ahora si se puede modificar la información de las reservaciones
        if (actividad.equals("Clases")) {
            // Se crea la variable booleana para usarla como bandera
            boolean encontrada = false;
            
            // Ciclo for para que se repita en todas las reservaciones que hay en ese momento
            for (int i = 0; i < totalReservasClases; i++) {
                // Validación por si hay una reservación que concuerda con el ID ingresado
                if (reservasClases[i][0] != null && reservasClases[i][0].equals(id.trim())) {
                    // Declaración de la variable claseActual para saber en que clase se encuentra la reservación actual
                    String claseActual = reservasClases[i][1];

                    // Mostrar clase actual
                    JOptionPane.showMessageDialog(null, "Estás inscrito en: " + claseActual);

                    // Preguntar si desea cambiar
                    String respuesta = JOptionPane.showInputDialog("¿Deseas cambiarte a la otra clase?" + "\n\n" +
                                                                   "Escribe 'Sí' para confirmar.");

                    if (respuesta == null || respuesta.trim().isEmpty()) {
                        mostrarError("No ingresaste ninguna opción. El programa se cerrará.");
                    }
                    
                    // Si la respuesta es si, se cambia la clase
                    if (respuesta.equalsIgnoreCase("Si")) {
                        
                        // Se declaran estas variables para guardar los nuevos datos
                        String nuevaClase = "";
                        String nuevaHora = "";
                        
                        // Si la clase actual es igual a "Clase de Baile" se verifica si hay cupos para esa clase y si es así
                        // Se hacen los cambios
                        if (claseActual.equals("Clase de Baile")) {
                            if (cupoYoga >= 30) {
                                JOptionPane.showMessageDialog(null, "No hay cupo disponible para la Clase de Yoga.");
                                return;
                            }
                            nuevaClase = "Clase de Yoga";
                            nuevaHora = "8:00 pm";
                            cupoYoga++;
                            cupoBaile--;
                        } else if (claseActual.equals("Clase de Yoga")) { // Lo mismo para la clase de Yoga
                            if (cupoBaile >= 30) {
                                JOptionPane.showMessageDialog(null, "No hay cupo disponible para la Clase de Baile.");
                                return;
                            }
                            nuevaClase = "Clase de Baile";
                            nuevaHora = "7:00 pm";
                            cupoBaile++;
                            cupoYoga--;
                        }

                        // Actualizar la reservación
                        reservasClases[i][1] = nuevaClase;
                        reservasClases[i][2] = nuevaHora;
                        
                        // Si todo ha funcionado como se debe, se muestra un mensaje exitoso
                        JOptionPane.showMessageDialog(null, "Clase modificada exitosamente.");
                        encontrada = true; // Bandera == a true porque si se encontró la reservación y se cambio los datos
                        break;
                    } else {
                        // Por si no se pudo realizar el cambio o algo falló
                        JOptionPane.showMessageDialog(null, "No se realizó ningún cambio.");
                        return;
                    }
                }
            }
            // Si no hubo una reservación con ese ID, muestra este mensaje de error
            if (!encontrada) {
                JOptionPane.showMessageDialog(null, "No se encontró una reservación con ese ID.");
            }
        } else if (actividad.equals("Cine")) {
            // Código para modificar las reservaciones del cine
            // Se ponen de acuerdo quien lo hace
        } else if (actividad.equals("Gimnasio")) {
            // Código para modificar las reservaciones del gimnasio
            // Se ponen de acuerdo quien lo hace
        } else if (actividad.equals("Barista")) {
            // Un for para confirmar si hay un pedido con ese ID
            for (int i = 0; i < totalPedidos; i++) {
                if (pedidosBarista[i][0].equals(id.trim())) {

                    // Se solicita la nueva bebida y la nueva hora
                    String nuevaBebida = JOptionPane.showInputDialog("Este sería el menu de bebidas:" + "\n\n"
                            + "1 - Café normal" + "\n"
                            + "2 - Capuchino" + "\n"
                            + "3 - Capuchino con vainilla" + "\n"
                            + "4 - Chocolate" + "\n"
                            + "5 - Moka" + "\n"
                            + "6 - Té chai" + "\n"
                            + "7 - Café frío" + "\n\n"
                            + "Digite el nombre de la nueva bebida que desea ordenar.");
                    String nuevaHora = JOptionPane.showInputDialog("Ingrese la nueva hora de entrega ej: 3:30 pm)");

                    // Se reescribe la información con la nueva bebida y hora
                    pedidosBarista[i][1] = nuevaBebida;
                    pedidosBarista[i][2] = nuevaHora;

                    // Si todo salió bien, se muestra mensaje exitoso
                    JOptionPane.showMessageDialog(null, "Pedido modificado exitosamente.");
                    return;
                }
            }

            // Si no hay un pedido con ese ID entonces mostrar mensaje de error
            mostrarError("No se encontró un pedido con ese ID.");
        }
    }

    /**
     * Este método lo que hace es que elimina alguna reservación que se haya
     * hecho en alguna de las actividades
     * @param actividad 
     */
    public static void eliminarReservaciones(String actividad){
        // Se le pide al usuario que brinde el ID del empleado para continuar
        String id = JOptionPane.showInputDialog("Digite el ID del empleado");
        
        // Validación por si no se escribe nada
        if(id == null || id.trim().isEmpty()){
            mostrarError("No ingresaste ninguna opción. El programa se cerrará.");
        }
        
        // Validación para ver si el ID ingresado está registrado
        boolean existe = false;
        for(String emp : empleados){
            if(emp.equals(id.trim())){
                existe = true;
                break;
            }
        }
     
        // Mostrar un mensaje de error por el ID ingresado que no existe
        if(!existe){
            mostrarError("No existe un empleado registrado con ese ID");
        }
        
        // Elimina alguna reservación de la clase de Baile o Yoga
        if(actividad.equals("Clases")){
            
            // Esta variable para ver si se puede encontrar una reservacion con ese ID
            boolean encontrada = false;
            
            // El ciclo for para encontrar en cual posición se encuentra la reservación
            for(int i = 0; i < totalReservasClases; i++){
                //Validación por si hay una reservación con ese ID se elimina
                if(reservasClases[i][0] != null && reservasClases[i][0].equals(id.trim())){
                    // Variable para guardar que tipo de clase es (Baile o Yoga)
                    String tipoClase = reservasClases[i][1];
                    
                    // Se mueve las reservaciones siguientes una posición atrás
                    for(int j = i; j < totalReservasClases - 1; j++){
                        reservasClases[j][0] = reservasClases[j + 1][0];
                        reservasClases[j][1] = reservasClases[j + 1][1];
                    }
                    
                    // Se Limpia la última posición
                    reservasClases[totalReservasClases - 1][0] = null;
                    reservasClases[totalReservasClases - 1][1] = null;
                    
                    // Se actualiza los contadores
                    totalReservasClases--;
                    if (tipoClase.equals("Clase de Baile")) {
                        cupoBaile--;
                    } else if (tipoClase.equals("Clase de Yoga")) {
                        cupoYoga--;
                    }
                    
                    // Si se elimina todo bien, se imprime mensaje exitoso
                    JOptionPane.showMessageDialog(null, "Reservación eliminada exitosamente.");
                    encontrada = true;
                    break;
                }
            }
            
            // Si no se encontró ninguna reservación con el ID ingresado
            if (!encontrada) {
                JOptionPane.showMessageDialog(null, "No se encontró una reservación con ese ID.");
            }
            
            
        } else if(actividad.equals("Gimnasio")){ 
            // Elimina alguna reservación del Gimnasio
            // Se ponen de acuerdo quien lo hace
        } else if(actividad.equals("Cine")){ 
            // Elimina alguna reservación del Cine
            // Se ponen de acuerdo quien lo hace
        } else if(actividad.equals("Barista")){
            
            // Se crea una variable booleana para poder hacer la verificación de un pedido con ese ID
            boolean encontrado = false;
            
            // Se crea un for para encontrar la posición del pedido según el ID proporcionado
            for(int i = 0; i < totalPedidos; i++){
                //Validación para confirmar si la posición actual es igual al ID proporcionado
                if (pedidosBarista[i][0] != null && pedidosBarista[i][0].equals(id.trim())) {
                    
                    // Mover los pedidos siguientes una posición atrás
                    for (int j = i; j < totalPedidos - 1; j++) {
                        pedidosBarista[j][0] = pedidosBarista[j + 1][0];
                        pedidosBarista[j][1] = pedidosBarista[j + 1][1];
                        pedidosBarista[j][2] = pedidosBarista[j + 1][2];
                    }

                    // Limpiar la última posición
                    pedidosBarista[totalPedidos - 1][0] = null;
                    pedidosBarista[totalPedidos - 1][1] = null;
                    pedidosBarista[totalPedidos - 1][2] = null;
                    
                    // Se decrementa el número de pedidos
                    totalPedidos--;
                    
                    // Si todo salió como lo esperado, se muestra un mensaje exitoso
                    JOptionPane.showMessageDialog(null, "Pedido eliminado exitosamente.");
                    encontrado = true;
                    break;
                }
            }
            
            // Si no hay ningún pedido con ese ID, mostrar mensaje
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "No se encontró un pedido con ese ID.");
            }
        }
    }

    // ===== Utilidades =====
    
    /**
     * Este métodos va a cargar los datos necesarios que necesita el sistema
     * Con el fin de que el mismo pueda reservar, consultar información, trabajar
     * con esa información entre otros...
     */
    public static void cargarDatos() {
        empleados = new String[]{"E001", "E002", "E003"};
        nombres = new String[]{"Julián Quesada", "Valeria Alfaro", "Justin Cordero"};
        horariosGym = new String[]{"2pm", "3pm", "4pm", "5pm", "6pm", "7pm"};
        pelicula = "Matrix Reloaded";
        asientosCine = new String[5][6];
        reservasClases = new String[60][3];
        pedidosBarista = new String[100][3];
    }

    
    /**
     * Este método permitirá que el usuario pueda salirse de la aplicación
     */
    public static void salir() {
        JOptionPane.showMessageDialog(null, "¡Gracias por usar el sistema!");
        System.exit(0);
    }

    /**
     * Permitirá mostrar un mensaje de error en caso de que haya uno y así hacerle saber al usuario
     * el porque el sistema se apagó
     * @param mensaje 
     */
    public static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}