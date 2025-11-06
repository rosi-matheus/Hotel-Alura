package testes;

import dao.HospedeDAO;
import factory.ConnectionFactory;
import modelo.Hospede;

import java.util.List;

public class TestaBuscaPorIdReserva {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            HospedeDAO hospedeDAO = new HospedeDAO(connectionFactory.recuperaConexao());
            
            int idReserva = 1; // ✅ ID da reserva para buscar
            
            // ✅ Buscar hóspedes por ID da reserva
            List<Hospede> hospedeList = hospedeDAO.buscarIdReserva(idReserva);

            // ✅ Exibir resultados
            if (hospedeList.isEmpty()) {
                System.out.println("❌ Nenhum hóspede encontrado para a reserva ID: " + idReserva);
            } else {
                System.out.println("✅ " + hospedeList.size() + " hóspede(s) encontrado(s) para a reserva " + idReserva + ":");
                
                for (Hospede hospede : hospedeList) {
                    System.out.println("---");
                    System.out.println("ID: " + hospede.getId());
                    System.out.println("Nome: " + hospede.getNomeCompleto());
                    System.out.println("Data Nascimento: " + hospede.getDataNascimento());
                    System.out.println("Nacionalidade: " + hospede.getNacionalidade());
                    System.out.println("Telefone: " + hospede.getTelefone());
                }
            }

        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar hóspedes por ID da reserva: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
