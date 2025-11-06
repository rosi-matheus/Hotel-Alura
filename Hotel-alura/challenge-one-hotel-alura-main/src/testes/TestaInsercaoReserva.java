package testes;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestaInsercaoReserva {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            ReservaDAO reservaDAO = new ReservaDAO(connectionFactory.recuperaConexao());

            Reserva reserva = new Reserva();

            reserva.setDataEntrada(LocalDate.of(2023, 4, 25)); // ✅ Corrigido: 4 em vez de 04
            reserva.setDataSaida(LocalDate.of(2023, 7, 7)); // ✅ Corrigido: 7 em vez de 07
            reserva.setValor(BigDecimal.valueOf(200.00)); // ✅ Corrigido: valor com casas decimais
            reserva.setFormaDePagamento("Cartão de Crédito"); // ✅ Corrigido: formato mais descritivo

            System.out.println("=== 🏨 TESTANDO INSERÇÃO DE RESERVA ===");
            System.out.println("📋 Dados da reserva:");
            System.out.println("   • Data Entrada: " + reserva.getDataEntrada());
            System.out.println("   • Data Saída: " + reserva.getDataSaida());
            System.out.println("   • Número de Dias: " + reserva.getNumeroDias());
            System.out.println("   • Valor: R$ " + reserva.getValor());
            System.out.println("   • Forma de Pagamento: " + reserva.getFormaDePagamento());
            System.out.println("   • Status: " + (reserva.isAtiva() ? "Ativa" : "Concluída"));

            // ✅ Validar dados antes de salvar
            if (reserva.isValid()) {
                int id = reservaDAO.salvar(reserva);
                System.out.println("🎉 Reserva inserida com sucesso!");
                System.out.println("✅ ID gerado: " + id);
                System.out.println("✅ Valor por dia: R$ " + reserva.getValorPorDia());
            } else {
                System.out.println("❌ Dados da reserva são inválidos. Inserção cancelada.");
                System.out.println("   • Datas válidas? " + (reserva.isDataEntradaValida() && reserva.isDataSaidaValida()));
                System.out.println("   • Valor válido? " + (reserva.getValor() != null && reserva.getValor().compareTo(BigDecimal.ZERO) > 0));
                System.out.println("   • Forma pagamento válida? " + (reserva.getFormaDePagamento() != null && !reserva.getFormaDePagamento().trim().isEmpty()));
            }

        } catch (Exception e) {
            System.err.println("❌ Erro ao inserir reserva: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
