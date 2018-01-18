package com.db.service.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.db.service.dao", 
    entityManagerFactoryRef = "monitorDbEntityManagerFactory", 
    transactionManagerRef = "monitorDbTransactionManager"
)
public class MonitorDbConfig {
	
	JpaVendorAdapter jpaVendorAdapter(){
		
		HibernateJpaVendorAdapter adapter=new HibernateJpaVendorAdapter();
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		adapter.setShowSql(false);
		return adapter;
	}
	
	@Bean
	@ConfigurationProperties(prefix="monitor.db")
	public DataSource monitorDbDataSource(){
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="monitorDbEntityManagerFactory")
	public EntityManagerFactory entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean lefb=new LocalContainerEntityManagerFactoryBean();
		lefb.setDataSource(monitorDbDataSource());
		lefb.setJpaVendorAdapter(jpaVendorAdapter());
		lefb.setPackagesToScan("com.db.service.entity");
		lefb.setPersistenceUnitName("monitorDbPersistenceUnit");
		
		Properties additionalProperties = new Properties();
		additionalProperties.put( "hspring.jpa.hibernate.use-new-id-generator-mappings", "false");
		lefb.setJpaProperties(additionalProperties);
		lefb.afterPropertiesSet();
		
		return lefb.getObject();
	}
	
	@Bean(name="monitrDbEntityManager")
	public EntityManager entityManager(){
		return entityManagerFactory().createEntityManager();
	}
	
	@Bean(name="monitorDbTransactionManager")
	public PlatformTransactionManager monitorDbTransactionManager(){
		return new JpaTransactionManager(entityManagerFactory());
		
	}
	
	@Bean(name = "monitorNamedParameterJdbcTemplate") 
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
		return new NamedParameterJdbcTemplate(monitorDbDataSource());
	}

}
