package testes;

import dao.HospedeDAO;
import factory.ConnectionFactory;
import modelo.Hospede;

import java.util.List;

public class TestaBuscaHospede {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            HospedeDAO hospedeDAO = new HospedeDAO(connectionFactory.recuperaConexao());
            
            String sobrenomeBuscado = "Almeida Pereira";
            
            // ✅ Buscar hóspedes por sobrenome
            List<Hospede> hospedeList = hospedeDAO.buscarSobrenome(sobrenomeBuscado);

            // ✅ Exibir resultados
            if (hospedeList.isEmpty()) {
                System.out.println("❌ Nenhum hóspede encontrado com o sobrenome: " + sobrenomeBuscado);
            } else {
                System.out.println("✅ " + hospedeList.size() + " hóspede(s) encontrado(s):");
                
                for (Hospede hospede : hospedeList) {
                    System.out.println("---");
                    System.out.println("ID: " + hospede.getId());
                    System.out.println("Nome: " + hospede.getNomeCompleto());
                    System.out.println("Data Nascimento: " + hospede.getDataNascimento());
                    System.out.println("Idade: " + hospede.getIdade() + " anos");
                    System.out.println("Nacionalidade: " + hospede.getNacionalidade());
                    System.out.println("Telefone: " + hospede.getTelefone());
                    System.out.println("ID Reserva: " + hospede.getIdReserva());
                }
                
                System.out.println("\n📊 Lista completa: " + hospedeList);
            }

        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar hóspedes: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
