package ua.nure.liapota.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "ua.nure.liapota.repositories.security",
        entityManagerFactoryRef = "securityEntityManager",
        transactionManagerRef = "securityTransactionManager")
public class SecurityConfiguration extends DataBaseConfiguration{
    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource-security")
    public DataSource securityDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean securityEntityManager() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(securityDataSource());
        entityManager.setPackagesToScan("ua.nure.liapota.models.security");
        return entityManagerPropertiesSetting(entityManager);
    }

    @Bean
    public PlatformTransactionManager securityTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(securityEntityManager().getObject());
        return transactionManager;
    }
}