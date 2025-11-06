package views;

import controller.HospedeController;
import controller.ReservaController;
import modelo.Hospede;
import modelo.Reserva;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
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
	public Buscar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);

		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

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
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
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
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarDados();
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editarRegistro();
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnDeletar = new JPanel();
		btnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deletarRegistro();
			}
		});
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);

		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
	}

	// ✅ MÉTODO CORRIGIDO: Buscar dados
	private void buscarDados() {
		// Limpar tabelas
		modelo.setRowCount(0);
		modeloHospedes.setRowCount(0);
		
		String textoBusca = txtBuscar.getText().trim();
		
		if (textoBusca.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, digite algo para buscar.", "Campo Vazio", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			HospedeController hospedeController = new HospedeController();
			List<Hospede> hospedeList;

			// ✅ Verificar se é número ou texto
			if (ehNumeroInteiro(textoBusca)) {
				int id = Integer.parseInt(textoBusca);
				hospedeList = hospedeController.listarPorIdReserva(id);
			} else {
				hospedeList = hospedeController.listarPorSobreNome(textoBusca);
			}

			if (hospedeList.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Nenhum registro encontrado.", "Busca", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			// ✅ Preencher tabela de hóspedes
			for (Hospede hospede : hospedeList) {
				modeloHospedes.addRow(new Object[]{
					hospede.getId(), 
					hospede.getNome(), 
					hospede.getSobreNome(),
					hospede.getDataNascimento(), 
					hospede.getNacionalidade(), 
					hospede.getTelefone(),
					hospede.getIdReserva()
				});

				// ✅ Preencher tabela de reservas
				if (hospede.getIdReserva() != null) {
					List<Reserva> reservaList = new ReservaController().listar(hospede.getIdReserva());
					for (Reserva reserva : reservaList) {
						modelo.addRow(new Object[]{
							reserva.getId(), 
							reserva.getDataEntrada(), 
							reserva.getDataSaida(),
							reserva.getValor(), 
							reserva.getFormaDePagamento()
						});
					}
				}
			}

			JOptionPane.showMessageDialog(this, 
				hospedeList.size() + " registro(s) encontrado(s).", 
				"Busca Concluída", 
				JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, 
				"Erro ao buscar dados: " + e.getMessage(), 
				"Erro", 
				JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	// ✅ MÉTODO CORRIGIDO: Editar registro
	private void editarRegistro() {
		int indexTabela = getTabbedPane().getSelectedIndex();
		
		if (indexTabela == 0) { // Reservas
			if (tbReservas.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, selecione uma reserva para editar.", "Seleção", JOptionPane.WARNING_MESSAGE);
				return;
			}
			editarReserva();
		} else if (indexTabela == 1) { // Hóspedes
			if (tbHospedes.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, selecione um hóspede para editar.", "Seleção", JOptionPane.WARNING_MESSAGE);
				return;
			}
			editarHospede();
		}
	}

	// ✅ MÉTODO NOVO: Editar reserva
	private void editarReserva() {
		try {
			int selectedRow = tbReservas.getSelectedRow();
			Reserva reserva = new Reserva();

			reserva.setId((Integer) modelo.getValueAt(selectedRow, 0));
			reserva.setDataEntrada(LocalDate.parse(modelo.getValueAt(selectedRow, 1).toString()));
			reserva.setDataSaida(LocalDate.parse(modelo.getValueAt(selectedRow, 2).toString()));
			
			// ✅ Tratamento seguro para BigDecimal
			Object valorObj = modelo.getValueAt(selectedRow, 3);
			if (valorObj instanceof BigDecimal) {
				reserva.setValor((BigDecimal) valorObj);
			} else {
				reserva.setValor(new BigDecimal(valorObj.toString()));
			}
			
			reserva.setFormaDePagamento((String) modelo.getValueAt(selectedRow, 4));

			if (reserva.isValid()) {
				new ReservaController().alterar(reserva);
				JOptionPane.showMessageDialog(this, "Reserva atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Dados da reserva são inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao editar reserva: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	// ✅ MÉTODO NOVO: Editar hóspede
	private void editarHospede() {
		try {
			int selectedRow = tbHospedes.getSelectedRow();
			Hospede hospede = new Hospede();

			hospede.setId((Integer) modeloHospedes.getValueAt(selectedRow, 0));
			hospede.setNome(modeloHospedes.getValueAt(selectedRow, 1).toString());
			hospede.setSobreNome(modeloHospedes.getValueAt(selectedRow, 2).toString());
			hospede.setDataNascimento(LocalDate.parse(modeloHospedes.getValueAt(selectedRow, 3).toString()));
			hospede.setNacionalidade(modeloHospedes.getValueAt(selectedRow, 4).toString());
			hospede.setTelefone(modeloHospedes.getValueAt(selectedRow, 5).toString());
			
			// ✅ Tratamento seguro para ID da reserva
			Object idReservaObj = modeloHospedes.getValueAt(selectedRow, 6);
			if (idReservaObj instanceof Integer) {
				hospede.setIdReserva((Integer) idReservaObj);
			} else {
				hospede.setIdReserva(Integer.parseInt(idReservaObj.toString()));
			}

			if (hospede.isValid()) {
				new HospedeController().alterar(hospede);
				JOptionPane.showMessageDialog(this, "Hóspede atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Dados do hóspede são inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao editar hóspede: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	// ✅ MÉTODO CORRIGIDO: Deletar registro
	private void deletarRegistro() {
		int indexTabela = getTabbedPane().getSelectedIndex();
		
		if (indexTabela == 0) { // Reservas
			deletarReserva();
		} else if (indexTabela == 1) { // Hóspedes
			deletarHospede();
		}
	}

	// ✅ MÉTODO NOVO: Deletar reserva
	private void deletarReserva() {
		int selectedRow = tbReservas.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Por favor, selecione uma reserva para excluir.", "Seleção", JOptionPane.WARNING_MESSAGE);
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(this, 
			"Tem certeza que deseja excluir esta reserva?\nEsta ação não pode ser desfeita.", 
			"Confirmação de Exclusão", 
			JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			try {
				Integer id = (Integer) modelo.getValueAt(selectedRow, 0);
				new ReservaController().deletar(id);
				modelo.removeRow(selectedRow);
				JOptionPane.showMessageDialog(this, "Reserva excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Erro ao excluir reserva: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// ✅ MÉTODO NOVO: Deletar hóspede
	private void deletarHospede() {
		int selectedRow = tbHospedes.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Por favor, selecione um hóspede para excluir.", "Seleção", JOptionPane.WARNING_MESSAGE);
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(this, 
			"Tem certeza que deseja excluir este hóspede?\nEsta ação não pode ser desfeita.", 
			"Confirmação de Exclusão", 
			JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			try {
				Integer id = (Integer) modeloHospedes.getValueAt(selectedRow, 0);
				new HospedeController().deletar(id);
				modeloHospedes.removeRow(selectedRow);
				JOptionPane.showMessageDialog(this, "Hóspede excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Erro ao excluir hóspede: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// ✅ MÉTODO UTILITÁRIO: Verificar se é número
	private boolean ehNumeroInteiro(String texto) {
		if (texto == null || texto.trim().isEmpty()) {
			return false;
		}
		try {
			Integer.parseInt(texto);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// ✅ MÉTODO UTILITÁRIO: Obter tabbed pane
	private JTabbedPane getTabbedPane() {
		for (java.awt.Component comp : contentPane.getComponents()) {
			if (comp instanceof JTabbedPane) {
				return (JTabbedPane) comp;
			}
		}
		return null;
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



