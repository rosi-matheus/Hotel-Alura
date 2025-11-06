package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Reserva {
    private Integer id;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private BigDecimal valor;
    private String formaDePagamento;

    // ✅ Construtores
    public Reserva() {
        // Construtor padrão
    }

    public Reserva(LocalDate dataEntrada, LocalDate dataSaida, 
                   BigDecimal valor, String formaDePagamento) {
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valor = valor;
        this.formaDePagamento = formaDePagamento;
    }

    // ✅ Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor != null ? valor.setScale(2, BigDecimal.ROUND_HALF_UP) : null;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento != null ? formaDePagamento.trim() : null;
    }

    // ✅ Métodos utilitários
    public long getNumeroDias() {
        if (dataEntrada == null || dataSaida == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(dataEntrada, dataSaida);
    }

    public boolean isAtiva() {
        if (dataSaida == null) {
            return false;
        }
        return LocalDate.now().isBefore(dataSaida) || LocalDate.now().isEqual(dataSaida);
    }

    public boolean isConcluida() {
        if (dataSaida == null) {
            return false;
        }
        return LocalDate.now().isAfter(dataSaida);
    }

    public BigDecimal getValorPorDia() {
        long dias = getNumeroDias();
        if (dias == 0 || valor == null) {
            return BigDecimal.ZERO;
        }
        return valor.divide(BigDecimal.valueOf(dias), 2, BigDecimal.ROUND_HALF_UP);
    }

    // ✅ equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // ✅ toString melhorado
    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", dataEntrada=" + dataEntrada +
                ", dataSaida=" + dataSaida +
                ", valor=" + (valor != null ? "R$ " + valor : "null") +
                ", formaDePagamento='" + formaDePagamento + '\'' +
                ", dias=" + getNumeroDias() +
                '}';
    }

    // ✅ Método de validação
    public boolean isValid() {
        return dataEntrada != null &&
               dataSaida != null &&
               dataSaida.isAfter(dataEntrada) &&
               valor != null && valor.compareTo(BigDecimal.ZERO) > 0 &&
               formaDePagamento != null && !formaDePagamento.trim().isEmpty();
    }

    // ✅ Validações específicas
    public boolean isDataEntradaValida() {
        return dataEntrada != null && 
               !dataEntrada.isBefore(LocalDate.now());
    }

    public boolean isDataSaidaValida() {
        return dataSaida != null && 
               dataEntrada != null && 
               dataSaida.isAfter(dataEntrada);
    }
}
