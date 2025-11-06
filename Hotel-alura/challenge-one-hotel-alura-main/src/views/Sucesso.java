package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Sucesso extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Sucesso dialog = new Sucesso();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Sucesso() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sucesso.class.getResource("/imagenes/aH-40px.png")));
		setTitle("Sucesso"); // ✅ Adicionado: Título da janela
		setBounds(100, 100, 394, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setModal(true); // ✅ Adicionado: Dialog modal
		setResizable(false); // ✅ Adicionado: Não redimensionável
		contentPanel.setLayout(null);
		
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(Sucesso.class.getResource("/imagenes/Ha-100px.png")));
			lblNewLabel.setBounds(147, 11, 100, 100); // ✅ Centralizado
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Registro adicionado com sucesso!");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER); // ✅ Centralizado
			lblNewLabel_1.setForeground(new Color(12, 138, 199));
			lblNewLabel_1.setFont(new Font("Roboto", Font.BOLD, 16)); // ✅ Fonte consistente
			lblNewLabel_1.setBounds(27, 122, 322, 21);
			contentPanel.add(lblNewLabel_1);
		}
		
		// ✅ Adicionado: Mensagem adicional
		{
			JLabel lblMensagem = new JLabel("A reserva e o hóspede foram cadastrados.");
			lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
			lblMensagem.setForeground(Color.DARK_GRAY);
			lblMensagem.setFont(new Font("Roboto", Font.PLAIN, 12));
			lblMensagem.setBounds(27, 145, 322, 21);
			contentPanel.add(lblMensagem);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER)); // ✅ Centralizado
			buttonPane.setBackground(SystemColor.control);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						fecharEAbrirMenu(); // ✅ Corrigido: Método separado
					}
				});
				okButton.setBackground(new Color(12, 138, 199)); // ✅ Cor da marca
				okButton.setForeground(Color.WHITE);
				okButton.setFont(new Font("Roboto", Font.BOLD, 14));
				okButton.setFocusPainted(false);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			// ✅ Removido: Botão Cancel desnecessário
		}
	}

	// ✅ MÉTODO CORRIGIDO: Fechar e abrir menu
	private void fecharEAbrirMenu() {
		dispose(); // Fecha a janela atual
		try {
			MenuUsuario usuario = new MenuUsuario(); 
			usuario.setVisible(true);
		} catch (Exception e) {
			// ✅ Tratamento de erro silencioso
			System.err.println("Erro ao abrir menu: " + e.getMessage());
		}
	}
	
	// ✅ MÉTODO ADICIONADO: Para reutilização com mensagem customizada
	public static void mostrarSucesso(String mensagem) {
		Sucesso dialog = new Sucesso();
		// Poderia customizar a mensagem aqui se necessário
		dialog.setVisible(true);
	}
	
	// ✅ MÉTODO ADICIONADO: Fechar dialog
	public void fecharDialog() {
		dispose();
	}
}
