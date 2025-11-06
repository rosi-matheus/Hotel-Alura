package factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private DataSource dataSource;

    public ConnectionFactory() {
        try {
            ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
            
            // Configuração do driver
            comboPooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            
            // URL de conexão melhorada
            comboPooledDataSource.setJdbcUrl(
                "jdbc:mysql://localhost/hotel_alura?" +
                "useTimezone=true&serverTimezone=UTC&" +
                "useSSL=false&" +
                "allowPublicKeyRetrieval=true&" +
                "characterEncoding=utf8"
            );
            
            comboPooledDataSource.setUser("root");
            comboPooledDataSource.setPassword("bd@Lucas95");

            // Configurações do pool de conexões
            comboPooledDataSource.setMinPoolSize(3);
            comboPooledDataSource.setMaxPoolSize(15);
            comboPooledDataSource.setAcquireIncrement(3);
            comboPooledDataSource.setMaxIdleTime(300);
            comboPooledDataSource.setCheckoutTimeout(5000);
            comboPooledDataSource.setMaxConnectionAge(7200);
            comboPooledDataSource.setIdleConnectionTestPeriod(300);
            comboPooledDataSource.setTestConnectionOnCheckout(true);
            
            // Configurações de retry
            comboPooledDataSource.setAcquireRetryAttempts(3);
            comboPooledDataSource.setAcquireRetryDelay(1000);

            this.dataSource = comboPooledDataSource;
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao configurar pool de conexões", e);
        }
    }

    public Connection recuperaConexao() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter conexão do pool de conexões", e);
        }
    }
    
    public void fecharPool() {
        if (this.dataSource instanceof ComboPooledDataSource) {
            ((ComboPooledDataSource) this.dataSource).close();
        }
    }
}
