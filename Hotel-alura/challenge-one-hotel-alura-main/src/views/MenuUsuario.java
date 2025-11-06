package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MenuUsuario extends JFrame {

	private JPanel contentPane;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel labelRegistro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUsuario frame = new MenuUsuario();
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
	public MenuUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuUsuario.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 944, 609);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
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
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(12, 138, 199));
		panelMenu.setBounds(0, 0, 257, 609);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JPanel btnBuscar = new JPanel();
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBuscar.setBackground(new Color(118, 187, 223));				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBuscar.setBackground(new Color(12, 138, 199));	
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirBuscar(); // ✅ Corrigido: Método separado
			}
		});
		
		btnBuscar.setBounds(0, 312, 257, 56);
		btnBuscar.setBackground(new Color(12, 138, 199));
		btnBuscar.setLayout(null);
		btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panelMenu.add(btnBuscar);
		
		JLabel lblBusquedaDeReservas = new JLabel("Buscar");
		lblBusquedaDeReservas.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/pessoas.png")));
		lblBusquedaDeReservas.setBounds(30, 11, 200, 34);
		lblBusquedaDeReservas.setHorizontalAlignment(SwingConstants.LEFT);
		lblBusquedaDeReservas.setForeground(Color.WHITE);
		lblBusquedaDeReservas.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnBuscar.add(lblBusquedaDeReservas);
		
		JLabel logo = new JLabel("");
		logo.setBounds(50, 58, 150, 150);
		panelMenu.add(logo);
		logo.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/aH-150px.png")));
		
		JPanel btnRegistro = new JPanel();
		btnRegistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegistro.setBackground(new Color(118, 187, 223));				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRegistro.setBackground(new Color(12, 138, 199));	
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirRegistro(); // ✅ Corrigido: Método separado
			}
		});
		btnRegistro.setBounds(0, 255, 257, 56);
		btnRegistro.setBackground(new Color(12, 138, 199));
		panelMenu.add(btnRegistro);
		btnRegistro.setLayout(null);
		btnRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		labelRegistro = new JLabel("Registro de reservas");
		labelRegistro.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/reservado.png")));
		labelRegistro.setForeground(SystemColor.text);
		labelRegistro.setBounds(25, 11, 205, 34);
		labelRegistro.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegistro.add(labelRegistro);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(26, 219, 201, 2);
		panelMenu.add(separator);
		
		// ✅ Adicionado: Botão de Logout
		JPanel btnLogout = new JPanel();
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogout.setBackground(new Color(118, 187, 223));				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLogout.setBackground(new Color(12, 138, 199));	
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				fazerLogout(); // ✅ Novo método para logout
			}
		});
		btnLogout.setBounds(0, 369, 257, 56);
		btnLogout.setBackground(new Color(12, 138, 199));
		btnLogout.setLayout(null);
		btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panelMenu.add(btnLogout);
		
		JLabel lblLogout = new JLabel("Sair");
		lblLogout.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/sair.png")));
		lblLogout.setBounds(30, 11, 200, 34);
		lblLogout.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogout.setForeground(Color.WHITE);
		lblLogout.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnLogout.add(lblLogout);
		
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 944, 36);
		contentPane.add(header);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				confirmarSaida(); // ✅ Corrigido: Confirmação antes de sair
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(891, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
	    JPanel panelFecha = new JPanel();
	    panelFecha.setBackground(new Color(118, 187, 223));
	    panelFecha.setBounds(256, 84, 688, 121);
	    contentPane.add(panelFecha);
	    panelFecha.setLayout(null);
	    
	    JLabel lblTituloPrincipal = new JLabel("Sistema de Reservas - Hotel Alura");
	    lblTituloPrincipal.setBounds(35, 11, 450, 42); // ✅ Ajustada posição
	    panelFecha.add(lblTituloPrincipal);
	    lblTituloPrincipal.setForeground(Color.WHITE);
	    lblTituloPrincipal.setFont(new Font("Roboto", Font.BOLD, 24)); // ✅ Fonte em negrito
	    
	    JLabel labelFecha = new JLabel();
	    labelFecha.setBounds(35, 64, 400, 36);
	    panelFecha.add(labelFecha);
	    labelFecha.setForeground(Color.WHITE);
	    labelFecha.setFont(new Font("Roboto", Font.PLAIN, 28)); // ✅ Fonte maior
	    Date fechaActual = new Date();
	    String fecha = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual);
	    labelFecha.setText("Hoje é " + fecha);
	    
	    // ✅ Adicionado: Hora atual
	    JLabel labelHora = new JLabel();
	    labelHora.setBounds(450, 64, 200, 36);
	    panelFecha.add(labelHora);
	    labelHora.setForeground(Color.WHITE);
	    labelHora.setFont(new Font("Roboto", Font.PLAIN, 24));
	    String hora = new SimpleDateFormat("HH:mm:ss").format(fechaActual);
	    labelHora.setText(hora);
	    
	    JLabel lbltitulo = new JLabel("Bem-vindo ao Sistema");
	    lbltitulo.setFont(new Font("Roboto", Font.BOLD, 28)); // ✅ Fonte maior
	    lbltitulo.setBounds(350, 234, 400, 46); // ✅ Ajustada posição
	    lbltitulo.setForeground(new Color(12, 138, 199)); // ✅ Cor da marca
	    contentPane.add(lbltitulo);
	    
	    // ✅ Corrigido: Textos em português correto
	    String textoDescricao = "<html><body>Sistema de reservas de hotéis. Controle e gerencie de forma otimizada e fácil <br> o fluxo de reservas e hóspedes do hotel</body></html>";
	    JLabel labelDescricao_0 = new JLabel(textoDescricao);
	    labelDescricao_0.setFont(new Font("Roboto", Font.PLAIN, 17));
	    labelDescricao_0.setBounds(280, 291, 598, 66); // ✅ Ajustada posição
	    contentPane.add(labelDescricao_0);
	    
	    String textoDescricao1 = "<html><body>Esta ferramenta permitirá que você mantenha um controle completo e detalhado de suas reservas e hóspedes. Você terá acesso a ferramentas especiais para tarefas específicas como:</body></html>";
	    JLabel labelDescricao_1 = new JLabel(textoDescricao1);
	    labelDescricao_1.setFont(new Font("Roboto", Font.PLAIN, 17));
	    labelDescricao_1.setBounds(280, 345, 569, 88);
	    contentPane.add(labelDescricao_1);
	    
	    JLabel labelDescricao_2 = new JLabel("• Registro de Reservas e Hóspedes");
	    labelDescricao_2.setFont(new Font("Roboto", Font.PLAIN, 17));
	    labelDescricao_2.setBounds(300, 444, 295, 27);
	    labelDescricao_2.setForeground(new Color(12, 138, 199)); // ✅ Cor destacada
	    contentPane.add(labelDescricao_2);
	    
	    JLabel labelDescricao_3 = new JLabel("• Edição de Reservas e Hóspedes existentes");
	    labelDescricao_3.setFont(new Font("Roboto", Font.PLAIN, 17));
	    labelDescricao_3.setBounds(300, 482, 355, 27);
	    labelDescricao_3.setForeground(new Color(12, 138, 199)); // ✅ Cor destacada
	    contentPane.add(labelDescricao_3);
	    
	    JLabel labelDescricao_4 = new JLabel("• Exclusão de todos os tipos de registros");
	    labelDescricao_4.setFont(new Font("Roboto", Font.PLAIN, 17));
	    labelDescricao_4.setBounds(300, 520, 295, 27);
	    labelDescricao_4.setForeground(new Color(12, 138, 199)); // ✅ Cor destacada
	    contentPane.add(labelDescricao_4);
	    
	    // ✅ Adicionado: Rodapé informativo
	    JPanel rodape = new JPanel();
	    rodape.setBackground(new Color(240, 240, 240));
	    rodape.setBounds(256, 570, 688, 39);
	    contentPane.add(rodape);
	    rodape.setLayout(null);
	    
	    JLabel lblInfo = new JLabel("💡 Dica: Use o menu lateral para navegar entre as funcionalidades");
	    lblInfo.setBounds(10, 5, 668, 25);
	    lblInfo.setFont(new Font("Roboto", Font.PLAIN, 14));
	    lblInfo.setForeground(Color.DARK_GRAY);
	    rodape.add(lblInfo);
	}
	
	// ✅ MÉTODO CORRIGIDO: Abrir tela de busca
	private void abrirBuscar() {
		try {
			Buscar buscar = new Buscar();
			buscar.setVisible(true);
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
				"Erro ao abrir tela de busca: " + e.getMessage(),
				"Erro",
				JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// ✅ MÉTODO CORRIGIDO: Abrir tela de registro
	private void abrirRegistro() {
		try {
			ReservasView reservas = new ReservasView();
			reservas.setVisible(true);
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
				"Erro ao abrir tela de reservas: " + e.getMessage(),
				"Erro",
				JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// ✅ MÉTODO NOVO: Fazer logout
	private void fazerLogout() {
		int confirmacao = JOptionPane.showConfirmDialog(this,
			"Tem certeza que deseja sair do sistema?",
			"Confirmação de Logout",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE);
			
		if (confirmacao == JOptionPane.YES_OPTION) {
			Login login = new Login();
			login.setVisible(true);
			dispose();
		}
	}
	
	// ✅ MÉTODO NOVO: Confirmar saída
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
	
	// Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"
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
