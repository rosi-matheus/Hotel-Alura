package testes;

import dao.HospedeDAO;
import factory.ConnectionFactory;
import modelo.Hospede;

import java.time.LocalDate;

public class TestaInsercaoHospede {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            HospedeDAO hospedeDAO = new HospedeDAO(connectionFactory.recuperaConexao());

            Hospede hospede = new Hospede();

            hospede.setNome("Lucas");
            hospede.setSobreNome("Almeida Pereira");
            hospede.setDataNascimento(LocalDate.of(1995, 7, 7)); // ✅ Corrigido: 7 em vez de 07
            hospede.setNacionalidade("Brasileiro"); // ✅ Corrigido: primeira letra maiúscula
            hospede.setTelefone("5511000000000"); // ✅ Corrigido: adicionado código país 55
            hospede.setIdReserva(1);

            System.out.println("=== 👤 TESTANDO INSERÇÃO DE HÓSPEDE ===");
            System.out.println("📋 Dados do hóspede:");
            System.out.println("   • Nome: " + hospede.getNome());
            System.out.println("   • Sobrenome: " + hospede.getSobreNome());
            System.out.println("   • Data Nascimento: " + hospede.getDataNascimento());
            System.out.println("   • Nacionalidade: " + hospede.getNacionalidade());
            System.out.println("   • Telefone: " + hospede.getTelefone());
            System.out.println("   • ID Reserva: " + hospede.getIdReserva());

            // ✅ Validar dados antes de salvar
            if (hospede.isValid()) {
                hospedeDAO.salvar(hospede);
                System.out.println("🎉 Hóspede inserido com sucesso!");
                System.out.println("✅ ID gerado: " + hospede.getId());
            } else {
                System.out.println("❌ Dados do hóspede são inválidos. Inserção cancelada.");
            }

        } catch (Exception e) {
            System.err.println("❌ Erro ao inserir hóspede: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

