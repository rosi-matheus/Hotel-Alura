public List<Reserva> buscar(int id){
    String sql = "SELECT * FROM RESERVAS WHERE ID = ?";
    List<Reserva> reservaList = new ArrayList<>();

    try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
        preparedStatement.setInt(1, id);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()){
                Reserva reserva = new Reserva(); // ← Novo objeto a cada loop
                reserva.setId(resultSet.getInt("ID"));
                reserva.setDataEntrada(LocalDate.parse(resultSet.getString("DATA_ENTRADA")));
                reserva.setDataSaida(LocalDate.parse(resultSet.getString("DATA_SAIDA")));
                reserva.setValor(resultSet.getBigDecimal("VALOR"));
                reserva.setFormaDePagamento(resultSet.getString("FORMA_PAGAMENTO"));

                reservaList.add(reserva);
            }
        }
    }catch (SQLException e){
        throw new RuntimeException(e);
    }
    return reservaList;
}
