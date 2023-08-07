package com.naturep.rewards.demo;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaAuditing
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
	return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
	LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
	emf.setDataSource(dataSource);
	emf.setPackagesToScan("com.naturep.rewards.demo");
	emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	emf.setJpaProperties(getHibernateProperties());
	return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
	return new JpaTransactionManager(entityManagerFactory.getObject());
    }

    private Properties getHibernateProperties() {
	Properties properties = new Properties();
	properties.setProperty("hibernate.hbm2ddl.auto", "create");
	properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	return properties;
    }
}
