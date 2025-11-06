package modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Hospede {
    private Integer id;
    private String nome;
    private String sobreNome;
    private LocalDate dataNascimento;
    private String nacionalidade;
    private String telefone;
    private Integer idReserva;

    // ✅ Construtores
    public Hospede() {
        // Construtor padrão
    }

    public Hospede(String nome, String sobreNome, LocalDate dataNascimento, 
                   String nacionalidade, String telefone, Integer idReserva) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.idReserva = idReserva;
    }

    // ✅ Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome != null ? nome.trim() : null;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome != null ? sobreNome.trim() : null;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade != null ? nacionalidade.trim() : null;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone != null ? telefone.trim() : null;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    // ✅ Métodos utilitários
    public String getNomeCompleto() {
        return (nome != null ? nome : "") + " " + (sobreNome != null ? sobreNome : "");
    }

    public int getIdade() {
        if (dataNascimento == null) {
            return 0;
        }
        return LocalDate.now().getYear() - dataNascimento.getYear();
    }

    // ✅ equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospede hospede = (Hospede) o;
        return Objects.equals(id, hospede.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // ✅ toString melhorado
    @Override
    public String toString() {
        return "Hospede{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobreNome='" + sobreNome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", telefone='" + telefone + '\'' +
                ", idReserva=" + idReserva +
                '}';
    }

    // ✅ Método de validação
    public boolean isValid() {
        return nome != null && !nome.trim().isEmpty() &&
               sobreNome != null && !sobreNome.trim().isEmpty() &&
               dataNascimento != null &&
               nacionalidade != null && !nacionalidade.trim().isEmpty() &&
               telefone != null && !telefone.trim().isEmpty() &&
               idReserva != null && idReserva > 0;
    }
}
