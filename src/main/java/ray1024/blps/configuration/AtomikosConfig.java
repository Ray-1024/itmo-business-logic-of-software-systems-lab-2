package ray1024.blps.configuration;

import jakarta.transaction.SystemException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import static org.springframework.transaction.TransactionDefinition.ISOLATION_READ_COMMITTED;
import static org.springframework.transaction.TransactionDefinition.ISOLATION_REPEATABLE_READ;

@Configuration
@EnableTransactionManagement
public class AtomikosConfig {

    @Bean
    public TransactionTemplate transactionTemplate() {
        TransactionTemplate template = new TransactionTemplate(createJtaTransactionManager());
        template.setIsolationLevel(ISOLATION_REPEATABLE_READ);
        return template;
    }

    @Bean
    public TransactionTemplate readOnlyTransactionTemplate() {
        TransactionTemplate template = new TransactionTemplate(createJtaTransactionManager());
        template.setIsolationLevel(ISOLATION_READ_COMMITTED);
        return template;
    }

    @Bean
    public UserTransactionManager userTransactionManager() throws SystemException {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setTransactionTimeout(300);
        userTransactionManager.setForceShutdown(true);
        return userTransactionManager;
    }

    private JtaTransactionManager createJtaTransactionManager() {
        JtaTransactionManager transactionManager = new JtaTransactionManager();
        transactionManager.setTransactionManager(userTransactionManager());
        transactionManager.setUserTransaction(userTransactionManager());
        return transactionManager;
    }

}