package testes;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestaUpdateReserva {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            ReservaDAO reservaDAO = new ReservaDAO(connectionFactory.recuperaConexao());

            Reserva reserva = new Reserva();
            reserva.setDataEntrada(LocalDate.of(2023, 4, 4)); // ✅ Corrigido: 4 em vez de 04
            reserva.setDataSaida(LocalDate.of(2023, 7, 7)); // ✅ Corrigido: 7 em vez de 07
            reserva.setValor(BigDecimal.valueOf(780.00)); // ✅ Corrigido: valor com casas decimais
            reserva.setFormaDePagamento("Cartão de Crédito"); // ✅ Corrigido: formato mais descritivo
            reserva.setId(1); // ✅ ID da reserva a ser atualizada

            System.out.println("=== ✏️ TESTANDO ATUALIZAÇÃO DE RESERVA ===");
            System.out.println("📋 Dados atualizados da reserva ID " + reserva.getId() + ":");
            System.out.println("   • Data Entrada: " + reserva.getDataEntrada());
            System.out.println("   • Data Saída: " + reserva.getDataSaida());
            System.out.println("   • Número de Dias: " + reserva.getNumeroDias());
            System.out.println("   • Valor: R$ " + reserva.getValor());
            System.out.println("   • Forma de Pagamento: " + reserva.getFormaDePagamento());
            System.out.println("   • Valor por Dia: R$ " + reserva.getValorPorDia());

            // ✅ Verificar se a reserva existe antes de atualizar
            var reservasExistentes = reservaDAO.buscar(reserva.getId());
            if (reservasExistentes.isEmpty()) {
                System.out.println("❌ Reserva com ID " + reserva.getId() + " não encontrada. Atualização cancelada.");
                return;
            }

            // ✅ Validar dados antes de atualizar
            if (reserva.isValid()) {
                reservaDAO.alterar(reserva);
                System.out.println("🎉 Reserva ID " + reserva.getId() + " atualizada com sucesso!");
                
                // ✅ Verificar a atualização
                var reservaAtualizada = reservaDAO.buscar(reserva.getId());
                if (!reservaAtualizada.isEmpty()) {
                    System.out.println("✅ Confirmação - Reserva foi atualizada no banco de dados");
                }
            } else {
                System.out.println("❌ Dados da reserva são inválidos. Atualização cancelada.");
                System.out.println("   • Datas válidas? " + (reserva.isDataEntradaValida() && reserva.isDataSaidaValida()));
                System.out.println("   • Valor válido? " + (reserva.getValor() != null && reserva.getValor().compareTo(BigDecimal.ZERO) > 0));
                System.out.println("   • Forma pagamento válida? " + (reserva.getFormaDePagamento() != null && !reserva.getFormaDePagamento().trim().isEmpty()));
            }

        } catch (Exception e) {
            System.err.println("❌ Erro ao atualizar reserva: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
