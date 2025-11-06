package views;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import controller.HospedeController;
import controller.ReservaController;
import modelo.Hospede;
import modelo.Reserva;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class RegistroHospede extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JTextField txtTelefone;
	private JDateChooser txtDataN;
	private JComboBox<String> txtNacionalidade; // ✅ Corrigido: JComboBox<String>
	private JLabel labelExit;
	private JLabel labelAtras;
	int xMouse, yMouse;
	private Reserva reserva;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroHospede frame = new RegistroHospede();
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
	public RegistroHospede() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHospede.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36); // ✅ Corrigido: bounds
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
		header.setLayout(null);
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				voltarReservas(); // ✅ Corrigido: Método separado
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(new Color(12, 138, 199));
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setForeground(Color.WHITE);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
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
		btnexit.setBackground(Color.white);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(SystemColor.black);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNome.setBounds(560, 135, 285, 33);
		txtNome.setBackground(Color.WHITE);
		txtNome.setColumns(10);
		txtNome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNome);
		
		txtSobrenome = new JTextField();
		txtSobrenome.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtSobrenome.setBounds(560, 204, 285, 33);
		txtSobrenome.setColumns(10);
		txtSobrenome.setBackground(Color.WHITE);
		txtSobrenome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtSobrenome);
		
		txtDataN = new JDateChooser();
		txtDataN.setBounds(560, 278, 285, 36);
		txtDataN.getCalendarButton().setIcon(new ImageIcon(RegistroHospede.class.getResource("/imagenes/icon-reservas.png")));
		txtDataN.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtDataN.setDateFormatString("yyyy-MM-dd");
		// ✅ Adicionado: Limitar datas futuras
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -18); // Mínimo 18 anos
		txtDataN.setMaxSelectableDate(cal.getTime());
		contentPane.add(txtDataN);
		
		// ✅ Corrigido: JComboBox<String> com tipo explícito
		txtNacionalidade = new JComboBox<String>();
		txtNacionalidade.setBounds(560, 350, 289, 36);
		txtNacionalidade.setBackground(SystemColor.text);
		txtNacionalidade.setFont(new Font("Roboto", Font.PLAIN, 16));
		// ✅ Simplificado: Nacionalidades mais comuns primeiro
		txtNacionalidade.setModel(new DefaultComboBoxModel<String>(new String[] {
			"brasileiro", "português", "espanhol", "estadunidense", "francês", 
			"alemão", "italiano", "japonês", "chinês", "argentino", "uruguaio"
		}));
		contentPane.add(txtNacionalidade);
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setBounds(562, 119, 253, 14);
		lblNome.setForeground(SystemColor.textInactiveText);
		lblNome.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNome);
		
		JLabel lblSobrenome = new JLabel("SOBRENOME");
		lblSobrenome.setBounds(560, 189, 255, 14);
		lblSobrenome.setForeground(SystemColor.textInactiveText);
		lblSobrenome.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblSobrenome);
		
		JLabel lblDataN = new JLabel("DATA DE NASCIMENTO");
		lblDataN.setBounds(560, 256, 255, 14);
		lblDataN.setForeground(SystemColor.textInactiveText);
		lblDataN.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblDataN);
		
		JLabel lblNacionalidade = new JLabel("NACIONALIDADE");
		lblNacionalidade.setBounds(560, 326, 255, 14);
		lblNacionalidade.setForeground(SystemColor.textInactiveText);
		lblNacionalidade.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNacionalidade);
		
		JLabel lblTelefone = new JLabel("TELEFONE");
		lblTelefone.setBounds(562, 406, 253, 14);
		lblTelefone.setForeground(SystemColor.textInactiveText);
		lblTelefone.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtTelefone.setBounds(560, 424, 285, 33);
		txtTelefone.setColumns(10);
		txtTelefone.setBackground(Color.WHITE);
		txtTelefone.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		// ✅ Adicionado: Placeholder
		txtTelefone.setText("+55 (11) 99999-9999");
		txtTelefone.setForeground(Color.GRAY);
		txtTelefone.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (txtTelefone.getText().equals("+55 (11) 99999-9999")) {
					txtTelefone.setText("");
					txtTelefone.setForeground(Color.BLACK);
				}
			}
		});
		contentPane.add(txtTelefone);
		
		JLabel lblTitulo = new JLabel("REGISTRO HÓSPEDE");
		lblTitulo.setBounds(606, 55, 234, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 23));
		contentPane.add(lblTitulo);

		// Separadores
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(560, 170, 289, 2);
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2);
		
		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(560, 240, 289, 2);
		separator_1_2_1.setForeground(new Color(12, 138, 199));
		separator_1_2_1.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_1);
		
		JSeparator separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(560, 314, 289, 2);
		separator_1_2_2.setForeground(new Color(12, 138, 199));
		separator_1_2_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_2);
		
		JSeparator separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(560, 386, 289, 2);
		separator_1_2_3.setForeground(new Color(12, 138, 199));
		separator_1_2_3.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_3);
		
		JSeparator separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(560, 457, 289, 2);
		separator_1_2_4.setForeground(new Color(12, 138, 199));
		separator_1_2_4.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_4);

		JPanel btnsalvar = new JPanel();
		btnsalvar.setBounds(723, 560, 122, 35);
		btnsalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				salvarHospede(); // ✅ Corrigido: Método separado
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnsalvar.setBackground(new Color(0, 156, 223));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnsalvar.setBackground(new Color(12, 138, 199));
			}
		});
		btnsalvar.setLayout(null);
		btnsalvar.setBackground(new Color(12, 138, 199));
		contentPane.add(btnsalvar);
		btnsalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		JLabel labelSalvar = new JLabel("SALVAR");
		labelSalvar.setHorizontalAlignment(SwingConstants.CENTER);
		labelSalvar.setForeground(Color.WHITE);
		labelSalvar.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelSalvar.setBounds(0, 0, 122, 35);
		btnsalvar.add(labelSalvar);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(12, 138, 199));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel imageFundo = new JLabel("");
		imageFundo.setBounds(0, 121, 479, 502);
		panel.add(imageFundo);
		imageFundo.setIcon(new ImageIcon(RegistroHospede.class.getResource("/imagenes/registro.png")));
		
		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		panel.add(logo);
		logo.setIcon(new ImageIcon(RegistroHospede.class.getResource("/imagenes/Ha-100px.png")));
	}
	
	// ✅ MÉTODO CORRIGIDO: Salvar hóspede com validações
	private void salvarHospede() {
		try {
			// ✅ Validações de campos obrigatórios
			if (!validarCampos()) {
				return;
			}
			
			Hospede hospede = new Hospede();
			HospedeController hospedeController = new HospedeController();
			ReservaController reservaController = new ReservaController();

			// ✅ Setando os valores dos atributos com tratamento seguro
			hospede.setNome(txtNome.getText().trim());
			hospede.setSobreNome(txtSobrenome.getText().trim());
			hospede.setTelefone(txtTelefone.getText().trim());
			
			// ✅ Tratamento seguro da data
			if (txtDataN.getDate() != null) {
				Date dataNasc = txtDataN.getDate();
				Calendar cal = Calendar.getInstance();
				cal.setTime(dataNasc);
				LocalDate dataNascimento = LocalDate.of(
					cal.get(Calendar.YEAR),
					cal.get(Calendar.MONTH) + 1,
					cal.get(Calendar.DAY_OF_MONTH)
				);
				hospede.setDataNascimento(dataNascimento);
			}
			
			hospede.setNacionalidade(txtNacionalidade.getSelectedItem().toString());

			// ✅ Verificar se a reserva existe antes de salvar
			if (reserva == null) {
				JOptionPane.showMessageDialog(this, 
					"Erro: Reserva não encontrada.", 
					"Erro", 
					JOptionPane.ERROR_MESSAGE);
				return;
			}

			// ✅ Salvando reserva e retorna id gerado
			int idReserva = reservaController.salvar(reserva);
			
			// ✅ Atribuindo o id da reserva ao hóspede
			hospede.setIdReserva(idReserva);
			
			// ✅ Validar objeto hóspede antes de salvar
			if (hospede.isValid()) {
				hospedeController.salvar(hospede);
				
				JOptionPane.showMessageDialog(this, 
					"Reserva criada com ID: " + idReserva + "\nHóspede registrado com sucesso!", 
					"Sucesso", 
					JOptionPane.INFORMATION_MESSAGE);
				
				Sucesso sucesso = new Sucesso();
				sucesso.setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, 
					"Dados do hóspede são inválidos. Verifique os campos.", 
					"Erro de Validação", 
					JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, 
				"Erro ao salvar hóspede: " + e.getMessage(), 
				"Erro", 
				JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	// ✅ MÉTODO NOVO: Validar campos obrigatórios
	private boolean validarCampos() {
		if (txtNome.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha o nome.", "Campo Obrigatório", JOptionPane.WARNING_MESSAGE);
			txtNome.requestFocus();
			return false;
		}
		
		if (txtSobrenome.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha o sobrenome.", "Campo Obrigatório", JOptionPane.WARNING_MESSAGE);
			txtSobrenome.requestFocus();
			return false;
		}
		
		if (txtDataN.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Por favor, selecione a data de nascimento.", "Campo Obrigatório", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if (txtTelefone.getText().trim().isEmpty() || txtTelefone.getText().equals("+55 (11) 99999-9999")) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha o telefone.", "Campo Obrigatório", JOptionPane.WARNING_MESSAGE);
			txtTelefone.requestFocus();
			return false;
		}
		
		return true;
	}
	
	// ✅ MÉTODO NOVO: Voltar para tela de reservas
	private void voltarReservas() {
		int confirmacao = JOptionPane.showConfirmDialog(this,
			"Tem certeza que deseja voltar? Os dados não salvos serão perdidos.",
			"Confirmação",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE);
			
		if (confirmacao == JOptionPane.YES_OPTION) {
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
	}
	
	// ✅ MÉTODO NOVO: Confirmar saída
	private void confirmarSaida() {
		int confirmacao = JOptionPane.showConfirmDialog(this,
			"Tem certeza que deseja sair? Os dados não salvos serão perdidos.",
			"Confirmação de Saída",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE);
			
		if (confirmacao == JOptionPane.YES_OPTION) {
			MenuPrincipal principal = new MenuPrincipal();
			principal.setVisible(true);
			dispose();
		}
	}
	
	// ✅ MÉTODO NOVO: Limpar campos
	public void limparCampos() {
		txtNome.setText("");
		txtSobrenome.setText("");
		txtTelefone.setText("+55 (11) 99999-9999");
		txtTelefone.setForeground(Color.GRAY);
		txtDataN.setDate(null);
		txtNacionalidade.setSelectedIndex(0);
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

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Reserva getReserva() {
		return this.reserva;
	}
}
