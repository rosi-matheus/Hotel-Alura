package testes;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import java.util.List;

public class TestaDeleteReserva {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            ReservaDAO reservaDAO = new ReservaDAO(connectionFactory.recuperaConexao());

            int idReserva = 2;
            
            System.out.println("=== 🗑️ TESTANDO EXCLUSÃO DE RESERVA ===");
            System.out.println("📋 ID da reserva a ser deletada: " + idReserva);
            
            // ✅ Verificar se a reserva existe antes de deletar
            List reservas = reservaDAO.buscar(idReserva);
            if (!reservas.isEmpty()) {
                System.out.println("✅ Reserva encontrada. Prosseguindo com exclusão...");
                
                reservaDAO.deletar(idReserva);
                System.out.println("🎉 Reserva com ID " + idReserva + " deletada com sucesso!");
                
            } else {
                System.out.println("⚠️  Reserva com ID " + idReserva + " não encontrada na base de dados.");
            }

        } catch (Exception e) {
            System.err.println("❌ Erro ao deletar reserva: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
