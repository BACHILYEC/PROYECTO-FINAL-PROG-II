package UserInterface.GUI;

import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

import java.awt.*;
import java.awt.event.MouseAdapter;

public class LoginScreen extends JFrame{

    private static JTextField txtUsuario;
    private static JPasswordField txtContrasena;
    private static JButton btnContinuar;
    private static JButton btnRegistrarse;

    public LoginScreen() {
        configurarVentana();
        crearInterfaz();
    }
    
    private void crearInterfaz() {
        setLayout(new BorderLayout(20, 20));
        
        // PANEL SUPERIOR - Título y progreso
        JPanel panelSuperior = crearPanelSuperior();
        add(panelSuperior, BorderLayout.NORTH);

        JPanel panelCentral = crearPanelCentral();
        add(panelCentral, BorderLayout.CENTER);

        JPanel panelInferior = crearPanelInferior();
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void configurarVentana() {
        setTitle("");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(102, 50, 33));
        setFocusable(true);
        requestFocusInWindow();
    }

    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 189, 89));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        
        JLabel lblTitulo = new JLabel("POLIQUIZZ");
        lblTitulo.setFont(new Font("Sans Seriff", Font.BOLD, 44));
        lblTitulo.setForeground(new Color(163, 24, 62));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(lblTitulo);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        return panel;
    }

    private JPanel crearPanelCentral() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(102, 50, 33));
        panel.setBorder(BorderFactory.createEmptyBorder(80, 30, 10, 30));

        JLabel lblLogin = new JLabel("Iniciar Sesión");
        lblLogin.setFont(new Font("Arial", Font.BOLD, 30));
        lblLogin.setForeground(new Color(255, 250, 250));
        lblLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUsuario.setForeground(new Color(255, 250, 250));
        lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtUsuario = new JTextField(18);
        txtUsuario.setMaximumSize(new Dimension(260, 35));
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsuario.setBackground(new Color(255, 250, 250));
        txtUsuario.setForeground(Color.BLACK);
        txtUsuario.setCaretColor(Color.BLACK);
        txtUsuario.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        txtUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
        lblContrasena.setForeground(new Color(255, 250, 250));
        lblContrasena.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtContrasena = new JPasswordField(18);
        txtContrasena.setMaximumSize(new Dimension(260, 35));
        txtContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
        txtContrasena.setBackground(new Color(255, 250, 250));
        txtContrasena.setForeground(Color.BLACK);
        txtContrasena.setCaretColor(Color.BLACK);
        txtContrasena.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        txtContrasena.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lblLogin);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));

        panel.add(lblUsuario);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(txtUsuario);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        panel.add(lblContrasena);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(txtContrasena);

        return panel;
    }

    private JPanel crearPanelInferior() {

        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBackground(new Color(255, 189, 89));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 150, 30, 150));

        btnContinuar = new JButton("CONTINUAR");
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 20));
        btnContinuar.setBackground(new Color(163, 24, 62));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setFocusPainted(false);
        btnContinuar.setBorderPainted(false);
        btnContinuar.setPreferredSize(new Dimension(0, 50));
        btnContinuar.setEnabled(false);
        btnContinuar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // btnContinuar.addActionListener(e -> validarLogin());
        
        btnContinuar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (btnContinuar.isEnabled()) {
                    btnContinuar.setBackground(new Color(37, 99, 235));
                }
            }
            public void mouseExited(MouseEvent e) {
                btnContinuar.setBackground(new Color(59, 130, 246));
            }
        });

        panel.add(btnContinuar, BorderLayout.SOUTH);

        return panel;

    }

    public String getUsuario() {
        return txtUsuario.getText();
    }

    public String getContrasena() {
        return new String(txtContrasena.getPassword());
    }




}
