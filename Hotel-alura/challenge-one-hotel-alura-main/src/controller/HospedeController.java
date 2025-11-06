package controller;

import dao.HospedeDAO;
import factory.ConnectionFactory;
import modelo.Hospede;

import java.util.List;

public class HospedeController {

    private HospedeDAO hospedeDAO;

    public HospedeController() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        this.hospedeDAO = new HospedeDAO(connectionFactory.recuperaConexao());
    }

    // ✅ Considere implementar este método ou removê-lo se não for usado
    public String getUltimoNrReserva() {
        return null;
    }

    public void salvar(Hospede hospede) {
        if (hospede == null) {
            throw new IllegalArgumentException("Hóspede não pode ser nulo");
        }
        this.hospedeDAO.salvar(hospede);
    }

    public List<Hospede> listarPorSobreNome(String sobrenome) {
        if (sobrenome == null || sobrenome.trim().isEmpty()) {
            throw new IllegalArgumentException("Sobrenome não pode ser vazio");
        }
        return this.hospedeDAO.buscarSobrenome(sobrenome);
    }

    public List<Hospede> listarPorIdReserva(int idReserva) {
        if (idReserva <= 0) {
            throw new IllegalArgumentException("ID da reserva deve ser positivo");
        }
        return this.hospedeDAO.buscarIdReserva(idReserva);
    }

    public void deletar(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser positivo");
        }
        this.hospedeDAO.deletar(id);
    }

    public void alterar(Hospede hospede) {
        if (hospede == null) {
            throw new IllegalArgumentException("Hóspede não pode ser nulo");
        }
        this.hospedeDAO.alterar(hospede);
    }
}
