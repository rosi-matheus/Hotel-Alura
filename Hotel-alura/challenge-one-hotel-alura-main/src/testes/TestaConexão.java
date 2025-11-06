package testes;

import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class TestaConexao {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        
        System.out.println("=== 🔌 TESTE DE CONEXÃO COM BANCO DE DADOS ===\n");
        
        try (Connection connection = connectionFactory.recuperaConexao()) {
            
            // ✅ Teste básico de conexão
            if (connection != null && !connection.isClosed()) {
                System.out.println("✅ Conexão estabelecida com sucesso!");
                
                // ✅ Obter informações do banco
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println("📊 Informações do Banco:");
                System.out.println("   • URL: " + metaData.getURL());
                System.out.println("   • Usuário: " + metaData.getUserName());
                System.out.println("   • Driver: " + metaData.getDriverName());
                System.out.println("   • Versão: " + metaData.getDriverVersion());
                System.out.println("   • Banco: " + metaData.getDatabaseProductName() + " " + metaData.getDatabaseProductVersion());
                
                // ✅ Testar transação
                connection.setAutoCommit(false);
                System.out.println("   • AutoCommit: " + connection.getAutoCommit());
                connection.setAutoCommit(true); // Voltar ao padrão
                
                System.out.println("\n🎉 Todas as verificações passaram! Conexão está funcionando perfeitamente.");
                
            } else {
                System.out.println("❌ Falha na conexão: conexão é nula ou fechada");
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erro ao conectar com o banco de dados:");
            System.err.println("   • Código: " + e.getErrorCode());
            System.err.println("   • Estado: " + e.getSQLState());
            System.err.println("   • Mensagem: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
