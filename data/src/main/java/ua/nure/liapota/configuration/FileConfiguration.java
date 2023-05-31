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
        basePackages = "ua.nure.liapota.repositories.file",
        entityManagerFactoryRef = "fileEntityManager",
        transactionManagerRef = "fileTransactionManager")
public class FileConfiguration extends DataBaseConfiguration{
    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource-file")
    public DataSource fileDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean fileEntityManager() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(fileDataSource());
        entityManager.setPackagesToScan("ua.nure.liapota.models.file");
        return entityManagerPropertiesSetting(entityManager);
    }

    @Bean
    public PlatformTransactionManager fileTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(fileEntityManager().getObject());
        return transactionManager;
    }
}