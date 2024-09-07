package ray1024.blps.configuration;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "firstDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.first")
    public AtomikosDataSourceBean firstDataSource() {
        return new AtomikosDataSourceBean();
    }

    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public AtomikosDataSourceBean secondDataSource() {
        return new AtomikosDataSourceBean();
    }
}

