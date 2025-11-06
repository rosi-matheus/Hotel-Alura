package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    int xMouse, yMouse;
    private JLabel labelExit;

    // ✅ Credenciais em constantes (em produção, isso viria de um banco de dados)
    private static final String USUARIO_VALIDO = "admin";
    private static final String SENHA_VALIDA = "admin";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 788, 527);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 788, 527);
        panel.setBackground(Color.WHITE);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(12, 138, 199));
        panel_1.setBounds(484, 0, 304, 527);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel imgHotel = new JLabel("");
        imgHotel.setBounds(0, 0, 304, 538);
        panel_1.add(imgHotel);
        imgHotel.setIcon(new ImageIcon(Login.class.getResource("/imagenes/img-hotel-login-.png")));
        
        JPanel btnexit = new JPanel();
        btnexit.setBounds(251, 0, 53, 36);
        panel_1.add(btnexit);
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                confirmarSaida();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }            
            @Override
            public void mouseExited(MouseEvent e) {
                 btnexit.setBackground(new Color(12, 138, 199));
                 labelExit.setForeground(Color.white);
            }
        });
        btnexit.setBackground(new Color(12, 138, 199));
        btnexit.setLayout(null);
        btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        labelExit = new JLabel("X");
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);
        labelExit.setForeground(SystemColor.text);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);        
        
        txtUsuario = new JTextField();
        txtUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (txtUsuario.getText().equals("Digite seu nome de usuario")) {
                    txtUsuario.setText("");
                    txtUsuario.setForeground(Color.black);
                }
                if (String.valueOf(txtSenha.getPassword()).isEmpty()) {
                    txtSenha.setText("********");
                    txtSenha.setForeground(Color.gray);
                }
            }
        });
        
        // ✅ Adicionar listener para Enter no campo usuário
        txtUsuario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtSenha.requestFocus();
                }
            }
        });
        
        txtUsuario.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtUsuario.setText("Digite seu nome de usuario");
        txtUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtUsuario.setForeground(SystemColor.activeCaptionBorder);
        txtUsuario.setBounds(65, 256, 324, 32);
        panel.add(txtUsuario);
        txtUsuario.setColumns(10);
        
        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(0, 120, 215));
        separator.setBounds(65, 292, 324, 2);
        panel.add(separator);
        
        JLabel labelTitulo = new JLabel("LOGIN");
        labelTitulo.setForeground(SystemColor.textHighlight);
        labelTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 26));
        labelTitulo.setBounds(196, 150, 89, 26);
        panel.add(labelTitulo);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBackground(SystemColor.textHighlight);
        separator_1.setBounds(65, 393, 324, 2);
        panel.add(separator_1);
        
        txtSenha = new JPasswordField();
        txtSenha.setText("********");
        txtSenha.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (String.valueOf(txtSenha.getPassword()).equals("********")) {
                    txtSenha.setText("");
                    txtSenha.setForeground(Color.black);
                }
                if (txtUsuario.getText().isEmpty()) {
                    txtUsuario.setText("Digite seu nome de usuario");
                    txtUsuario.setForeground(Color.gray);
                }
            }
        });
        
        // ✅ Adicionar listener para Enter no campo senha
        txtSenha.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    realizarLogin();
                }
            }
        });
        
        txtSenha.setForeground(SystemColor.activeCaptionBorder);
        txtSenha.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtSenha.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtSenha.setBounds(65, 353, 324, 32);
        panel.add(txtSenha);
        
        JLabel LabelUsuario = new JLabel("USUÁRIO"); // ✅ Corrigido acento
        LabelUsuario.setForeground(SystemColor.textInactiveText);
        LabelUsuario.setFont(new Font("Roboto Black", Font.PLAIN, 20));
        LabelUsuario.setBounds(65, 219, 107, 26);
        panel.add(LabelUsuario);
        
        JLabel lblSenha = new JLabel("SENHA");
        lblSenha.setForeground(SystemColor.textInactiveText);
        lblSenha.setFont(new Font("Roboto Black", Font.PLAIN, 20));
        lblSenha.setBounds(65, 316, 140, 26);
        panel.add(lblSenha);
        
        JPanel btnLogin = new JPanel();
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setBackground(new Color(0, 156, 223));
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(SystemColor.textHighlight);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                realizarLogin();
            }
        });
        btnLogin.setBackground(SystemColor.textHighlight);
        btnLogin.setBounds(65, 431, 122, 44);
        panel.add(btnLogin);
        btnLogin.setLayout(null);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        JLabel lblEntrar = new JLabel("ENTRAR");
        lblEntrar.setBounds(0, 0, 122, 44);
        btnLogin.add(lblEntrar);
        lblEntrar.setForeground(SystemColor.controlLtHighlight);
        lblEntrar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEntrar.setFont(new Font("Roboto", Font.PLAIN, 18));
        
        JLabel logo = new JLabel("");
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setIcon(new ImageIcon(Login.class.getResource("/imagenes/lOGO-50PX.png")));
        logo.setBounds(65, 65, 48, 59);
        panel.add(logo);
        
        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);
            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setBackground(SystemColor.window);
        header.setBounds(0, 0, 784, 36);
        panel.add(header);
        header.setLayout(null);
    }
    
    // ✅ MÉTODO MELHORADO: Realizar login com validações
    private void realizarLogin() {
        String usuarioDigitado = txtUsuario.getText().trim();
        String senhaDigitada = new String(txtSenha.getPassword()).trim();

        // ✅ Validações de campos vazios
        if (usuarioDigitado.isEmpty() || usuarioDigitado.equals("Digite seu nome de usuario")) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, digite o usuário.", 
                "Campo Obrigatório", 
                JOptionPane.WARNING_MESSAGE);
            txtUsuario.requestFocus();
            return;
        }

        if (senhaDigitada.isEmpty() || senhaDigitada.equals("********")) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, digite a senha.", 
                "Campo Obrigatório", 
                JOptionPane.WARNING_MESSAGE);
            txtSenha.requestFocus();
            return;
        }

        // ✅ Verificar credenciais
        if (usuarioDigitado.equals(USUARIO_VALIDO) && senhaDigitada.equals(SENHA_VALIDA)) {
            // ✅ Login bem-sucedido
            JOptionPane.showMessageDialog(this, 
                "Login realizado com sucesso!", 
                "Bem-vindo", 
                JOptionPane.INFORMATION_MESSAGE);
            
            MenuUsuario menu = new MenuUsuario();
            menu.setVisible(true);
            dispose();
        } else {
            // ✅ Login falhou
            JOptionPane.showMessageDialog(this, 
                "Usuário ou senha inválidos.\nTente novamente.", 
                "Erro de Login", 
                JOptionPane.ERROR_MESSAGE);
            
            // ✅ Limpar campos e focar no usuário
            txtSenha.setText("");
            txtUsuario.requestFocus();
        }
    }
    
    // ✅ MÉTODO NOVO: Confirmar saída da aplicação
    private void confirmarSaida() {
        int confirmacao = JOptionPane.showConfirmDialog(this,
            "Tem certeza que deseja sair do sistema?",
            "Confirmação de Saída",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
            
        if (confirmacao == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    // ✅ MÉTODO MELHORADO: Limpar campos do formulário
    public void limparCampos() {
        txtUsuario.setText("Digite seu nome de usuario");
        txtUsuario.setForeground(SystemColor.activeCaptionBorder);
        txtSenha.setText("********");
        txtSenha.setForeground(SystemColor.activeCaptionBorder);
    }
    
    // ✅ MÉTODO NOVO: Focar no campo de usuário
    public void focarCampoUsuario() {
        txtUsuario.requestFocus();
    }
    
    //Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"
    private void headerMousePressed(MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}
