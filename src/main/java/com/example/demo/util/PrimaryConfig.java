package com.example.demo.util;


 import org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
 import org.springframework.core.env.Environment;
 import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

//# 主数据源配置-mysql
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef="entityManagerFactoryPrimary",
		transactionManagerRef="transactionManagerPrimary",
		basePackages= { "com.example.demo.dao" }) //设置Repository所在位置
public class PrimaryConfig {
	@Autowired
	@Qualifier("primaryDataSource")
	private DataSource primaryDataSource;

	@Autowired
	private JpaProperties jpaPreperties;

	@Autowired
	private HibernateProperties hibernateProperties;

	@Value("${spring.jpa.hibernate.primary-dialect}")
	private String primaryDialect;

	@Autowired
	private Environment environment;

	@Primary
	@Bean(name = "entityManagerPrimary")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) throws Exception {
		return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
	}


	@Bean(name = "entityManagerFactoryPrimary")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(
																		EntityManagerFactoryBuilder  builder)
			throws Exception {
		/*
		 * 多数据源环境下，有可能application.properties中的hibernate.hbm2ddl.auto不生效，如果不生效我们自行在配置类中加上去
		 */


		Map<String,String> map=new HashMap<>();
		map.put("hibernate.dialect",primaryDialect);
		map.put("hibernate.hbm2ddl.auto", "none"); //十分危险 建议写成NONE
		map.put("hibernate.show_sql", "true");

		Map<String,Object> properties = hibernateProperties.determineHibernateProperties(jpaPreperties.getProperties(),new HibernateSettings());
		jpaPreperties.setProperties(map);
		return builder
				.dataSource(primaryDataSource)
				.properties(jpaPreperties.getProperties())
				.packages("com.example.demo.bean") //设置实体类所在位置
				.persistenceUnit("primaryPersistenceUnit")
				.build();
	}



	//主数据源事务配置
	@Bean(name = "transactionManagerPrimary")
	@Primary
	public PlatformTransactionManager transactionManager1(@Qualifier("entityManagerFactoryPrimary") LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager(primaryEntityManagerFactory.getObject());
		return transactionManager;
	}


}
