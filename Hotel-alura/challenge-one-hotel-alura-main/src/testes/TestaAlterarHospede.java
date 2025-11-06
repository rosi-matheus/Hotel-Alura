package testes;

import dao.HospedeDAO;
import factory.ConnectionFactory;
import modelo.Hospede;

import java.time.LocalDate;

public class TestaAlterarHospede {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            HospedeDAO hospedeDAO = new HospedeDAO(connectionFactory.recuperaConexao());

            // ✅ Criar objeto Hospede com dados válidos
            Hospede hospede = new Hospede();
            hospede.setId(1); // ✅ ID deve ser setado PRIMEIRO para o UPDATE
            hospede.setNome("Fabíola");
            hospede.setSobreNome("Souza Ferreira");
            hospede.setDataNascimento(LocalDate.of(1995, 10, 12));
            hospede.setNacionalidade("Brasileira"); // ✅ Corrigido gênero
            hospede.setTelefone("5511999999999"); // ✅ Formato internacional
            hospede.setIdReserva(1);

            // ✅ Verificar se o hóspede é válido antes de alterar
            if (hospede.isValid()) {
                hospedeDAO.alterar(hospede);
                System.out.println("✅ Hóspede alterado com sucesso: " + hospede);
            } else {
                System.out.println("❌ Dados do hóspede são inválidos");
            }

        } catch (Exception e) {
            System.err.println("❌ Erro ao alterar hóspede: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
