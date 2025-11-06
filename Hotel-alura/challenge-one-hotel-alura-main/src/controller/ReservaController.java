package controller;

import com.toedter.calendar.JDateChooser;
import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReservaController {

    private ReservaDAO reservaDAO;

    public ReservaController() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        this.reservaDAO = new ReservaDAO(connectionFactory.recuperaConexao());
    }

    public BigDecimal calculaValorReserva(LocalDate dataEntrada, LocalDate dataSaida) {
        // Validações
        if (dataEntrada == null || dataSaida == null) {
            throw new IllegalArgumentException("Datas não podem ser nulas");
        }
        
        if (dataSaida.isBefore(dataEntrada)) {
            throw new IllegalArgumentException("Data de saída não pode ser anterior à data de entrada");
        }

        BigDecimal valorDiaria = new BigDecimal("20.00");
        long dias = ChronoUnit.DAYS.between(dataEntrada, dataSaida);
        
        // Garante mínimo de 1 dia
        if (dias == 0) dias = 1;
        
        return valorDiaria.multiply(new BigDecimal(dias))
                         .setScale(2, RoundingMode.HALF_EVEN);
    }

    public LocalDate formataData(JDateChooser data) {
        if (data == null || data.getDate() == null) {
            return null;
        }
        
        Date date = data.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        return LocalDate.of(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH)
        );
    }

    public int salvar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("Reserva não pode ser nula");
        }
        return this.reservaDAO.salvar(reserva);
    }

    public List<Reserva> listar(int idReserva) {
        return reservaDAO.buscar(idReserva);
    }

    public void alterar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("Reserva não pode ser nula");
        }
        this.reservaDAO.alterar(reserva);
    }

    // ✅ MÉTODO ADICIONADO
    public void deletar(int id) {
        this.reservaDAO.deletar(id);
    }
}
