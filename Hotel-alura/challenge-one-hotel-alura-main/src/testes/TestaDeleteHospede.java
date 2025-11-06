package testes;

import dao.HospedeDAO;
import factory.ConnectionFactory;

public class TestaDeleteHospede {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            HospedeDAO hospedeDAO = new HospedeDAO(connectionFactory.recuperaConexao());

            int idHospede = 30;
            
            System.out.println("=== 🗑️ TESTANDO EXCLUSÃO DE HÓSPEDE ===");
            System.out.println("📋 ID do hóspede a ser deletado: " + idHospede);
            
            // ✅ Verificar se o hóspede existe antes de deletar
            var hospedes = hospedeDAO.buscarIdReserva(idHospede);
            if (!hospedes.isEmpty()) {
                System.out.println("✅ Hóspede encontrado. Prosseguindo com exclusão...");
                
                hospedeDAO.deletar(idHospede);
                System.out.println("🎉 Hóspede com ID " + idHospede + " deletado com sucesso!");
                
            } else {
                System.out.println("⚠️  Hóspede com ID " + idHospede + " não encontrado na base de dados.");
            }

        } catch (Exception e) {
            System.err.println("❌ Erro ao deletar hóspede: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
