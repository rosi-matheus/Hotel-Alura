public List<Hospede> buscarSobrenome(String sobrenome){
    String sql = "SELECT * FROM HOSPEDE WHERE SOBRENOME = ?";
    List<Hospede> hospedeList = new ArrayList<>();

    try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
        preparedStatement.setString(1, sobrenome);
        
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            while(resultSet.next()){
                Hospede hospede = new Hospede();
                hospede.setId(resultSet.getInt("ID"));
                hospede.setNome(resultSet.getString("NOME"));
                hospede.setSobreNome(resultSet.getString("SOBRENOME"));
                
                String dataNascStr = resultSet.getString("DATA_NASCIMENTO");
                if(dataNascStr != null) {
                    hospede.setDataNascimento(LocalDate.parse(dataNascStr));
                }
                
                hospede.setNacionalidade(resultSet.getString("NACIONALIDADE"));
                hospede.setTelefone(resultSet.getString("TELEFONE"));
                hospede.setIdReserva(resultSet.getInt("ID_RESERVA"));
                
                hospedeList.add(hospede);
            }
        }
    }catch (SQLException e){
        throw new RuntimeException(e);
    }
    return hospedeList;
}


