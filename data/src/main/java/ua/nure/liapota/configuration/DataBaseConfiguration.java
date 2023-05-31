package ua.nure.liapota.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;
import java.util.Map;

public class DataBaseConfiguration {
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hibernateDdl;
    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;

    protected LocalContainerEntityManagerFactoryBean entityManagerPropertiesSetting(
            LocalContainerEntityManagerFactoryBean entityManager) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", hibernateDdl);
        properties.put("hibernate.dialect", hibernateDialect);
        entityManager.setJpaPropertyMap(properties);
        return entityManager;
    }
}
