package vista;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;


public class FormularioIntegrante extends JFrame {

    //colores
    static final Color NAVY = new Color(0x13306B), BLUE = new Color(0x1F62C9),
            SOFT = new Color(0xE8F0FC), BG = new Color(0xF4F7FC),
            LINE = new Color(0xD6DFEE), TEXT = new Color(0x16233F),
            MUTED = new Color(0x6B7A99);
    static final Font BASE = new Font("Segoe UI", Font.PLAIN, 14);

    // Campos del formulario
    private final HintField nombres = new HintField("Esteban");
    private final HintField apellidos = new HintField("Guerrero Arevalo");
    private final HintField repositorio = new HintField("https://github.com/usuario/repositorio");
    private final JRadioButton rbNuevo = new JRadioButton("Nuevo");
    private final JRadioButton rbAntiguo = new JRadioButton("Antiguo");
    private final ButtonGroup grupoTipo = new ButtonGroup();
    private final JComboBox<String> proyecto = combo("Ingeniería de Sistemas",
            "Ingeniería Electrónica", "Ingeniería Industrial", "Ingeniería Catastral");

    //botones
    private final JButton btnRegistrar = boton("Registrar", BLUE, Color.WHITE);
    private final JButton btnLimpiar = boton("Limpiar", Color.WHITE, BLUE);

    //base de datos
    private final DefaultTableModel modeloTabla = new DefaultTableModel(new String[]{"#", "Nombres", "Apellidos", "Repositorio", "Tipo", "Proyecto"}, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable tabla = new JTable(modeloTabla);

    private final JLabel tituloTabla = new JLabel("Integrantes registrados (0)");

    //nombre de la ventana
    public FormularioIntegrante() {
        super("Programación Avanzada - Registro de integrantes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        grupoTipo.add(rbNuevo);
        grupoTipo.add(rbAntiguo);

        JPanel root = new JPanel(new BorderLayout());
        root.add(crearHeader(), BorderLayout.NORTH);
        root.add(crearMenu(), BorderLayout.WEST);
        root.add(crearContenido(), BorderLayout.CENTER);
        setContentPane(root);

    }

    public String getNombres() { return nombres.getText().trim(); }
    public String getApellidos() { return apellidos.getText().trim(); }
    public String getRepositorio() { return repositorio.getText().trim(); }
    public String getTipoEstudiante() {
        return rbNuevo.isSelected() ? "Nuevo" : rbAntiguo.isSelected() ? "Antiguo" : "";
    }
    public String getProyecto() { return (String) proyecto.getSelectedItem(); }

    public void addRegistrarListener(ActionListener l) { btnRegistrar.addActionListener(l); }
    public void addLimpiarListener(ActionListener l) { btnLimpiar.addActionListener(l); }

    public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Datos inválidos", JOptionPane.WARNING_MESSAGE);
    }

    //limpiar todos los campos
    public void limpiarFormulario() {
        nombres.setText("");
        apellidos.setText("");
        repositorio.setText("");
        grupoTipo.clearSelection();
        proyecto.setSelectedIndex(0);
    }

    private JComponent crearHeader() {
        JPanel h = new JPanel(new BorderLayout()) {
            protected void paintComponent(Graphics g) {
                ((Graphics2D) g).setPaint(new GradientPaint(getWidth() * .35f, 0,
                        new Color(0x0F2A5C), getWidth(), 0, new Color(0xB9C7E0)));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        h.setPreferredSize(new Dimension(0, 72));
        h.setBorder(new EmptyBorder(0, 28, 0, 28));

        JLabel marca = new JLabel("<html><span style='font-size:17px'><b>Programación Avanzada</b></span><br>"
                + "<span style='font-size:11px'>Proyectos educativos</span></html>");
        marca.setForeground(Color.WHITE);
        JLabel usuario = new JLabel("<html><b>Docente</b><br><span style='font-size:11px'>Administrador</span></html>");
        usuario.setForeground(NAVY);
        h.add(marca, BorderLayout.WEST);
        h.add(usuario, BorderLayout.EAST);
        return h;
    }

    private JComponent crearMenu() {
        JPanel m = new JPanel();
        m.setLayout(new BoxLayout(m, BoxLayout.Y_AXIS));
        m.setBackground(NAVY);
        m.setPreferredSize(new Dimension(210, 0));
        m.setBorder(new EmptyBorder(22, 0, 0, 0));
        String[] items = {"Inicio", "Integrantes", "Proyectos", "Reportes", "Configuración"};
        for (String it : items) {
            JButton l = new JButton(it);
            l.setFont(BASE.deriveFont(15f));
            l.setForeground(Color.WHITE);
            l.setOpaque(true);
            l.setContentAreaFilled(true);
            l.setBackground(it.equals("Integrantes") ? BLUE : NAVY);
            l.setBorder(new EmptyBorder(14, 24, 14, 10));
            l.setFocusPainted(false);
            l.setHorizontalAlignment(SwingConstants.LEFT);
            l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            l.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
            l.setAlignmentX(0f);
            m.add(l);
        }
        return m;
    }

    private JComponent crearContenido() {
        JPanel c = new JPanel(new BorderLayout(0, 18));
        c.setBackground(BG);
        c.setBorder(new EmptyBorder(24, 24, 24, 24));

        JPanel banner = new JPanel(new BorderLayout());
        banner.setBackground(SOFT);
        banner.setBorder(new CompoundBorder(new LineBorder(LINE), new EmptyBorder(18, 24, 18, 24)));
        banner.add(new JLabel("<html><span style='font-size:20px;color:#13306B'><b>Registrar integrante</b></span><br>"
                + "<span style='color:#3D5486'>Complete los datos del integrante para registrarlo en el proyecto educativo.</span></html>"));
        c.add(banner, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(18, 0));
        centro.setOpaque(false);
        centro.add(crearFormulario(), BorderLayout.CENTER);

        ScrollPanel contenido = new ScrollPanel(new BorderLayout(0, 18));
        contenido.setOpaque(false);
        contenido.add(centro, BorderLayout.NORTH);
        contenido.add(crearTabla(), BorderLayout.CENTER);

        JScrollPane sp = new JScrollPane(contenido);
        sp.setBorder(null);
        sp.getViewport().setBackground(BG);
        sp.getVerticalScrollBar().setUnitIncrement(16);
        c.add(sp, BorderLayout.CENTER);
        return c;
    }

    private JComponent crearFormulario() {
        JPanel f = tarjeta();
        f.setLayout(new BoxLayout(f, BoxLayout.Y_AXIS));

        f.add(titulo("Datos del integrante"));
        f.add(fila(campo("Nombres", true, nombres), campo("Apellidos", true, apellidos)));
        f.add(filaCompleta(campo("Repositorio del trabajo (GitHub)", true, repositorio)));

        JPanel tipo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tipo.setOpaque(false);
        tipo.setPreferredSize(new Dimension(0, 36));
        for (JRadioButton rb : new JRadioButton[]{rbNuevo, rbAntiguo}) {
            rb.setFont(BASE);
            rb.setForeground(TEXT);
            rb.setOpaque(false);
            rb.setFocusPainted(false);
            rb.setBorder(new EmptyBorder(0, 0, 0, 24));
            tipo.add(rb);
        }
        f.add(fila(campo("Tipo de estudiante", true, tipo), campo("Proyecto educativo", true, proyecto)));

        JPanel b = new JPanel(new BorderLayout());
        b.setOpaque(false);
        b.setBorder(new EmptyBorder(8, 0, 0, 0));
        b.add(btnLimpiar, BorderLayout.WEST);
        b.add(btnRegistrar, BorderLayout.EAST);
        f.add(b);
        return f;
    }

    private JComponent crearTabla() {
        JPanel card = tarjeta();
        card.setLayout(new BorderLayout(0, 10));
        card.setPreferredSize(new Dimension(0, 280));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        tituloTabla.setFont(BASE.deriveFont(Font.BOLD, 17f));
        tituloTabla.setForeground(NAVY);
        top.add(tituloTabla, BorderLayout.WEST);
        card.add(top, BorderLayout.NORTH);

        tabla.setFont(BASE);
        tabla.setRowHeight(28);
        tabla.setGridColor(LINE);
        tabla.setSelectionBackground(SOFT);
        tabla.setSelectionForeground(TEXT);
        tabla.getTableHeader().setFont(BASE.deriveFont(Font.BOLD));
        tabla.getTableHeader().setBackground(SOFT);
        tabla.getTableHeader().setForeground(NAVY);
        tabla.getColumnModel().getColumn(0).setMaxWidth(50);

        JScrollPane sp = new JScrollPane(tabla);
        sp.setBorder(new LineBorder(LINE));
        sp.getViewport().setBackground(Color.WHITE);
        card.add(sp, BorderLayout.CENTER);
        return card;
    }

    // ---------- Utilidades de estilo ----------

    private static JComboBox<String> combo(String... items) {
        JComboBox<String> c = new JComboBox<>(items);
        c.setFont(BASE);
        c.setBackground(Color.WHITE);
        return c;
    }

    private static JPanel tarjeta() {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setBorder(new CompoundBorder(new LineBorder(LINE), new EmptyBorder(20, 22, 20, 22)));
        return p;
    }

    private static JComponent titulo(String s) {
        JLabel l = new JLabel(s);
        l.setFont(BASE.deriveFont(Font.BOLD, 17f));
        l.setForeground(NAVY);
        l.setBorder(new EmptyBorder(0, 0, 12, 0));
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.add(l, BorderLayout.WEST);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        return p;
    }

    private static JPanel fila(JComponent a, JComponent b) {
        JPanel p = new JPanel(new GridLayout(1, 2, 22, 0));
        p.setOpaque(false);
        p.add(a);
        p.add(b);
        p.setBorder(new EmptyBorder(0, 0, 14, 0));
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        return p;
    }

    private static JPanel filaCompleta(JComponent a) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.add(a);
        p.setBorder(new EmptyBorder(0, 0, 14, 0));
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        return p;
    }

    private static JPanel campo(String texto, boolean requerido, JComponent c) {
        JLabel l = new JLabel(requerido
                ? "<html>" + texto + " <font color='#D93636'>*</font></html>" : texto);
        l.setFont(BASE);
        l.setForeground(TEXT);
        l.setBorder(new EmptyBorder(0, 0, 5, 0));
        if (c instanceof JTextField || c instanceof JComboBox) {
            c.setPreferredSize(new Dimension(0, 36));
            c.setFont(BASE);
            if (c instanceof JTextField)
                c.setBorder(new CompoundBorder(new LineBorder(LINE), new EmptyBorder(0, 10, 0, 10)));
        }
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.add(l, BorderLayout.NORTH);
        p.add(c, BorderLayout.CENTER);
        return p;
    }

    private static JButton boton(String t, Color fondo, Color texto) {
        JButton b = new JButton(t);
        b.setFont(BASE.deriveFont(Font.BOLD, 14f));
        b.setBackground(fondo);
        b.setForeground(texto);
        b.setOpaque(true);
        b.setFocusPainted(false);
        b.setBorder(new CompoundBorder(new LineBorder(BLUE), new EmptyBorder(9, 26, 9, 26)));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    /** Panel que ocupa todo el ancho del scroll (para que el contenido se estire). */
    private static class ScrollPanel extends JPanel implements Scrollable {
        ScrollPanel(LayoutManager lm) { super(lm); }
        public Dimension getPreferredScrollableViewportSize() { return getPreferredSize(); }
        public int getScrollableUnitIncrement(Rectangle r, int o, int d) { return 16; }
        public int getScrollableBlockIncrement(Rectangle r, int o, int d) { return 100; }
        public boolean getScrollableTracksViewportWidth() { return true; }
        public boolean getScrollableTracksViewportHeight() { return false; }
    }

    /** Campo de texto con texto de ayuda (placeholder). */
    private static class HintField extends JTextField {
        final String hint;
        HintField(String hint) { this.hint = hint; }
        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (getText().isEmpty()) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2.setColor(new Color(0x9AA7C0));
                g2.setFont(getFont());
                Insets in = getInsets();
                g2.drawString(hint, in.left, (getHeight() + g2.getFontMetrics().getAscent()
                        - g2.getFontMetrics().getDescent()) / 2);
                g2.dispose();
            }
        }
    }
}
